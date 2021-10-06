import { writeFileSync } from "fs";
import pl from "tau-prolog";
import config from "./config";
import { CellGraph } from "./types";

const tau_consult = (session: any, program: string) => {
  return new Promise((resolve, reject) => {
    session.consult(program, {
      success: () => {
        resolve(session);
      },
      error: (err: any) => {
        reject(err);
      },
    });
  });
};

const tau_query = (session: any, query: string) => {
  return new Promise((resolve, reject) => {
    session.query(query, {
      success: () => {
        resolve(session);
      },
      error: (err: any) => {
        reject(err);
      },
    });
  });
};

const parseData = (data: any): CellGraph => {
  let graph: CellGraph = {
    entries: [data.root],
    consumed: [],
    matrix: {},
  };

  const regex = /\[[a-zA-Z0-9]+,[a-zA-Z0-9]+,[a-zA-Z0-9]+\]/gm;
  let m;
  while ((m = regex.exec(data.edges)) !== null) {
    // This is necessary to avoid infinite loops with zero-width matches
    if (m.index === regex.lastIndex) {
      regex.lastIndex++;
    }
    // The result can be accessed through the `m`-variable.
    m.forEach((match) => {
      let str = match.slice(1, -1).split(",");
      let str_field = str[0];
      let str_src = str[1];
      let str_dst = str[2];
      if (!(str_src in graph.matrix)) {
        graph.matrix[str_src] = {};
      }
      graph.matrix[str_src][str_field] = str_dst;
    });
  }
  return graph;
};

const tau_answers = (session: any): Promise<CellGraph[] | undefined> => {
  let data: CellGraph[] = [];
  return new Promise((resolve, reject) => {
    let success = (answer: any) => {
      data.push(
        // will be parsed by the above method parseData
        parseData({
          root: answer.links["Root"].toString(),
          edges: answer.links["Edges"].toString(),
        })
      );

      session.answer({
        success: success,
        error: error,
        fail: fail,
        limit: limit,
      });
    };

    let error = (err: any) => {
      resolve(data);
    };

    let fail = () => {
      resolve(data);
    };

    let limit = () => {
      resolve(data);
    };

    session.answer({
      success: success,
      error: error,
      fail: fail,
      limit: limit,
    });
  });
};

const execute = async (
  program: string,
  query: string
): Promise<CellGraph[] | undefined> => {
  if (config.writeProgramToDisk) {
    writeFileSync("debug.pl", `${program}\n\n\n% ${query}`);
  }

  var session = pl.create(10000000);
  await tau_consult(session, program);
  await tau_query(session, query);
  return await tau_answers(session);
};

export { execute };
