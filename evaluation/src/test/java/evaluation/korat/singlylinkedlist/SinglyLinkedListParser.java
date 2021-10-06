package evaluation.korat.singlylinkedlist;

import evaluation.korat.CellGraph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SinglyLinkedListParser {
    public List<SinglyLinkedList> fromCellGraphs(List<CellGraph> cellGraphs, int size) {
        return cellGraphs.stream().map(sll -> fromCellGraph(sll, size)).collect(Collectors.toList());
    }

    public SinglyLinkedList fromCellGraph(CellGraph cellGraph, int size) {

        Map<String, Entry> map = new HashMap<>();
        map.put("null", null);

        // construct objects
        cellGraph.matrix.forEach((node, fieldValues) -> {
            Entry n = new Entry();
            n.element = new SerializableObject();
            map.put(node, n);
        });

        // set pointers
        cellGraph.matrix.forEach((node, fieldValues) -> {
            Entry n = map.get(node);
            fieldValues.forEach((field, value) -> {
                if ("next".equals(field)) {
                    n.next = map.get(value);
                }
            });
        });

        // set entry
        SinglyLinkedList sll = new SinglyLinkedList();
        sll.header = map.get(cellGraph.entries.get(0));
        sll.size = size - 1;

        return sll;
    }
}
