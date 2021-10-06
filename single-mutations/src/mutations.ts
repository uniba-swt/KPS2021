import _ from "lodash";
import config from "./config";
import { CellGraph } from "./types";

/**
 * Checks if two graphs g1 and g2 are isomorphic with respect to the nodes reachable from the entries.
 */
function areEquivalent(g1: CellGraph, g2: CellGraph) {
  if (Object.keys(g1.matrix).length !== Object.keys(g2.matrix).length) {
    return false;
  }

  if (Object.keys(g1.entries).length !== Object.keys(g2.entries).length) {
    return false;
  }

  let mapping: { [key: string]: string } = {};
  mapping[config.null] = config.null;

  let stackG1 = [];

  for (let i = 0; i < Object.keys(g1.entries).length; i++) {
    // if one but not both entry values are null, then the graphs cannot be isomorphic
    if (
      (g1.entries[i] === config.null && g2.entries[i] !== config.null) ||
      (g1.entries[i] !== config.null && g2.entries[i] === config.null)
    ) {
      return false;
    }

    mapping[g1.entries[i]] = g2.entries[i];
    stackG1.push(g1.entries[i]);
  }

  while (stackG1.length !== 0) {
    let g1n = stackG1.pop();

    if (g1n === config.null) {
      continue;
    }

    let fields = Object.keys(g1.matrix[g1n]);

    for (let field of fields) {
      let g2n = mapping[g1n];
      // we have a mapping
      if (g1.matrix[g1n][field] in mapping) {
        // g1n has a mapping
        if (g2.matrix[g2n][field] !== mapping[g1.matrix[g1n][field]]) {
          // g2n does not have the expected value
          return false;
        } else {
          // g2n has the expected value
          continue;
        }
      }
      // we dont have a mapping so far
      else {
        // if both are null
        if (
          g1.matrix[g1n][field] !== config.null &&
          g2.matrix[g2n][field] === config.null
        ) {
          return false;
        }

        mapping[g1.matrix[g1n][field]] = g2.matrix[g2n][field];

        // we already have another mapping for the target node
        if (
          g2.matrix[g2n][field] in mapping &&
          mapping[g2.matrix[g2n][field]] !== g1.matrix[g1n][field]
        ) {
          return false;
        }

        stackG1.push(g1.matrix[g1n][field]);
      }
    }
  }
  return true;
}

/**
 * Returns a list of mutated cell graphs with respect to a provided cell graph.
 * A mutated cell graph is produced by changing a single pointer value.
 */
function singleMutations(graph: CellGraph): CellGraph[] {
  let mutations: CellGraph[] = [];

  let candidateValues = [config.null, ...Object.keys(graph.matrix)];

  // mutate the entry pointers
  for (let i = 0; i < graph.entries.length; i++) {
    for (let mutatedValue of candidateValues) {
      if (mutatedValue === graph.entries[i]) {
        // mutations must differ from the current value
        continue;
      }
      let newCandidates = _.cloneDeep(graph.entries);
      newCandidates[i] = mutatedValue;
      mutations.push({
        ...graph,
        entries: newCandidates,
      });
    }
  }

  // mutate individual pointers
  Object.keys(graph.matrix).forEach((node) => {
    Object.keys(graph.matrix[node]).forEach((field) => {
      for (let mutatedValue of candidateValues) {
        if (mutatedValue === graph.matrix[node][field]) {
          // mutations must differ from the current value
          continue;
        }
        let matrixMutation = _.cloneDeep(graph.matrix);
        matrixMutation[node][field] = mutatedValue;

        mutations.push({
          ...graph,
          matrix: matrixMutation,
        });
      }
    });
  });

  return mutations;
}

export { areEquivalent, singleMutations };
