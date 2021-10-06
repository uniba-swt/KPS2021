import { readFileSync } from "fs";
import _ from "lodash";
import config from "./config";
import { areEquivalent } from "./mutations";
import { execute } from "./prologExecutor";
import { CellGraph } from "./types";

async function generateGraphs(
  predicate: string,
  size: number
): Promise<CellGraph[]> {
  let fields = config.predicates[predicate];

  let functions: string = readFileSync("src/functions.pl").toString();
  let predicates: string = readFileSync("src/predicates.pl").toString();

  let program = `
% Debug.pl for function call generateGraphs(predicate: ${predicate}, size: ${size})

${functions}
${predicates}
  `;

  let nodes = _.range(1, size + 1).map((i) => `n${i}`);

  let edges = _.flatten(
    fields.map((f) => {
      return nodes.map(
        (n) => `[${f}, ${n}, ${n.toUpperCase()}${f.toUpperCase()}]`
      );
    })
  );

  let edgeMembers = _.flatten(
    fields.map((f) => {
      return nodes.map(
        (n) => `member(${n.toUpperCase()}${f.toUpperCase()}, CNodes)`
      );
    })
  );

  let querySegments: string[] = [
    `Nodes = [${nodes.join(", ")}]`,
    `member(Root, Nodes)`,
    `append(Nodes, [${config.null}], CNodes)`,
    `Edges = [ ${edges.join(", ")} ]`,
    ...edgeMembers,
    `${predicate}(Root, Nodes, [], Edges)`,
  ];

  let query = `${querySegments.join(", ")}.`;
  return await execute(program, query);
}

function dropIsomorphicGraphs(isoGraphs: CellGraph[]): CellGraph[] {
  let graphs: CellGraph[] = [];

  for (let i = 0; i < isoGraphs.length; i++) {
    let noOtherEquivGraphs = true;
    for (let j = i + 1; j < isoGraphs.length; j++) {
      if (areEquivalent(isoGraphs[i], isoGraphs[j])) {
        noOtherEquivGraphs = false;
        break;
      }
    }
    if (noOtherEquivGraphs) {
      graphs.push(isoGraphs[i]);
    }
  }

  return graphs;
}

/**
 * Checks whether a cell graph matches a shape predicate.
 */
async function matchesPredicate(
  graph: CellGraph,
  predicate: string
): Promise<boolean> {
  let functions: string = readFileSync("src/functions.pl").toString();
  let predicates: string = readFileSync("src/predicates.pl").toString();

  let program = `
  % Debug.pl for function call matchesPredicate(graph: ${JSON.stringify(
    graph
  )}, predicate: ${predicate})
  
  ${functions}
  ${predicates}
    `;

  let nodes = Object.keys(graph.matrix);

  let edges = _.flatten(
    Object.keys(graph.matrix).map((node) => {
      return Object.keys(graph.matrix[node]).map((field) => {
        return `[${field}, ${node}, ${graph.matrix[node][field]}]`;
      });
    })
  );

  let querySegments: string[] = [
    `Root = ${graph.entries[0]}`,
    `Nodes = [${nodes.join(", ")}]`,
    `Edges = [ ${edges.join(", ")} ]`,
    `${predicate}(Root, Nodes, [], Edges)`,
  ];

  let query = `${querySegments.join(", ")}.`;
  let res = await execute(program, query);
  return res.length !== 0;
}

export { generateGraphs, dropIsomorphicGraphs, matchesPredicate };
