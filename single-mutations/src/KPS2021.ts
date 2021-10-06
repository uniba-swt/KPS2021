import { exists, existsSync, mkdir, writeFileSync } from "fs";
import _ from "lodash";
import { generateExhaustiveGraphs } from "./exhaustive";
import { singleMutations } from "./mutations";
import {
  dropIsomorphicGraphs,
  generateGraphs,
  matchesPredicate,
} from "./predicates";
import { CellGraph } from "./types";

let testInputs = [
  { predicate: "bt", size: 1 },
  { predicate: "bt", size: 2 },
  { predicate: "bt", size: 3 },
  { predicate: "sll", size: 1 },
  { predicate: "sll", size: 2 },
  { predicate: "sll", size: 3 },
  { predicate: "cdll", size: 1 },
  { predicate: "cdll", size: 2 },
  { predicate: "cdll", size: 3 },
];

const folder = `evaluation-KPS`;

mkdir(`./${folder}`, { recursive: true }, (err) => {
  if (err) throw err;
});

// single mutation JSON data
testInputs.forEach(async (testInput) => {
  let predicate = testInput.predicate;
  let size = testInput.size;

  let resultFile = `./${folder}/mutation-${predicate}-${size}.json`;

  if (existsSync(resultFile)) {
    console.log(
      `[${resultFile}]: skipping, because result file already exists`
    );
    return;
  }

  let graphs = await generateGraphs(predicate, size);
  console.log(`[${resultFile}]: generateGraphs yields ${graphs.length} graphs`);

  graphs = dropIsomorphicGraphs(graphs);
  console.log(
    `[${resultFile}]: dropIsomorphicGraphs reduced the number of graphs to ${graphs.length}`
  );

  let mutatedGraphs: CellGraph[] = _.flatten(
    graphs.map((graph) => singleMutations(graph))
  );
  console.log(
    `[${resultFile}]: Derived ${mutatedGraphs.length} mutated graph(s)`
  );

  mutatedGraphs = dropIsomorphicGraphs(mutatedGraphs);
  console.log(
    `[${resultFile}]: dropIsomorphicGraphs reduced the number of graphs to ${mutatedGraphs.length}`
  );

  const shouldRemove = await Promise.all(
    mutatedGraphs.map(async (g) => matchesPredicate(g, predicate))
  );
  mutatedGraphs = mutatedGraphs.filter((value, index) => !shouldRemove[index]);

  console.log(
    `[${resultFile}]: removing funcEquiv mutants reduced the number of graphs to ${mutatedGraphs.length}`
  );

  writeFileSync(`${resultFile}`, JSON.stringify(mutatedGraphs, null, 4));
});

// exhaustive JSON data
testInputs.forEach(async (testInput) => {
  let predicate = testInput.predicate;
  let size = testInput.size;

  let resultFile = `./${folder}/exhaustive-${predicate}-${size}.json`;

  if (existsSync(resultFile)) {
    console.log(
      `[${resultFile}]: skipping, because result file already exists`
    );
    return;
  }

  console.log(`[${resultFile}]: triggering generation ... `);

  let exhaustiveGraphs = await generateExhaustiveGraphs(predicate, size);

  console.log(
    `[${resultFile}]: number of exhaustive graphs is ${exhaustiveGraphs.length}`
  );

  writeFileSync(`${resultFile}`, JSON.stringify(exhaustiveGraphs, null, 4));
});
