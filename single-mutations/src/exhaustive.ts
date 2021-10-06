import assert from "assert";
import _ from "lodash";
import config from "./config";
import { matchesPredicate } from "./predicates";
import { CellGraph } from "./types";

/**
 * Generates all possible cell graphs with a single entry for a given size and fields.
 *
 * @param size
 * @param fields
 * @returns
 */
function allGraphs(size: number, fields: string[]): CellGraph[] {
  let graphs: CellGraph[] = [];

  let codomain = ["null"].concat(_.range(1, size + 1).map((i) => `n${i}`));
  assert.strict(codomain.length === 1 + size);

  // initial graph
  let init: CellGraph = {
    entries: ["null"],
    matrix: {},
  };
  _.range(1, size + 1).forEach((i) =>
    fields.forEach((f) => {
      init.matrix[`n${i}`] = {};
      init.matrix[`n${i}`][f] = "null";
    })
  );
  graphs.push(init);
  assert.strict(graphs.length === 1);

  // expand wrt. entries
  graphs = _.flatten(
    graphs.map((graph) => {
      return codomain.map((v) => {
        let clone = _.cloneDeep(graph);
        clone.entries = [v];
        return clone;
      });
    })
  );
  assert.strict(graphs.length === size + 1);

  // expand wrt. node.field
  _.range(1, size + 1).forEach((srcID) => {
    fields.forEach((field) => {
      // expand each graph at the current srcID.field location wrt. all possible codomain values
      graphs = _.flatten(
        graphs.map((graph) => {
          return codomain.map((v) => {
            let clone = _.cloneDeep(graph);
            clone.matrix[`n${srcID}`][field] = v;
            return clone;
          });
        })
      );
    });
  });
  assert.strict(graphs.length === Math.pow(1 + size, 1 + size * fields.length));

  return graphs;
}

/**
 * Exhaustively generates all graphs of given size resembling a negative test input with respect to the given predicate
 * @param predicate
 * @param size
 * @returns
 */
async function generateExhaustiveGraphs(
  predicate: string,
  size: number
): Promise<CellGraph[]> {
  let fields = config.predicates[predicate];
  let graphs = allGraphs(size, fields);

  const shouldRemove = await Promise.all(
    graphs.map(async (g) => matchesPredicate(g, predicate))
  );
  graphs = graphs.filter((_value, index) => !shouldRemove[index]);

  return graphs;
}

export { generateExhaustiveGraphs };
