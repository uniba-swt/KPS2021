package evaluation.korat.binarytree;

import evaluation.korat.CellGraph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BinaryTreeParser {
    public List<BinaryTree> fromCellGraphs(List<CellGraph> cellGraphs, int size) {
        return cellGraphs.stream().map(bt -> fromCellGraph(bt, size)).collect(Collectors.toList());
    }

    public BinaryTree fromCellGraph(CellGraph cellGraph, int size) {

        Map<String, BinaryTree.Node> map = new HashMap<>();
        map.put("null", null);

        // construct objects
        cellGraph.matrix.forEach((node, fieldValues) -> {
            BinaryTree.Node n = new BinaryTree.Node();
            map.put(node, n);
        });

        // set pointers
        cellGraph.matrix.forEach((node, fieldValues) -> {
            BinaryTree.Node n = map.get(node);
            fieldValues.forEach((field, value) -> {
                if ("left".equals(field)) {
                    n.left = map.get(value);
                }
                if ("right".equals(field)) {
                    n.right = map.get(value);
                }
            });
        });

        // set entry
        BinaryTree bt = new BinaryTree();
        bt.root = map.get(cellGraph.entries.get(0));
        bt.size = size;

        return bt;
    }
}
