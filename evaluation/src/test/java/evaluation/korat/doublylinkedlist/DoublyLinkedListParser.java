package evaluation.korat.doublylinkedlist;

import evaluation.korat.CellGraph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DoublyLinkedListParser {
    public List<DoublyLinkedList> fromCellGraphs(List<CellGraph> cellGraphs, int size) {
        return cellGraphs.stream().map(dll -> fromCellGraph(dll, size)).collect(Collectors.toList());
    }

    public DoublyLinkedList fromCellGraph(CellGraph cellGraph, int size) {

        Map<String, Entry> map = new HashMap<>();
        map.put("null", null);

        // construct objects
        cellGraph.matrix.forEach((node, fieldValues) -> {
            Entry n = new Entry();
            n.element = new ListObject();
            map.put(node, n);
        });

        // set pointers
        cellGraph.matrix.forEach((node, fieldValues) -> {
            Entry n = map.get(node);
            fieldValues.forEach((field, value) -> {
                if ("next".equals(field)) {
                    n.next = map.get(value);
                }
                if ("previous".equals(field)) {
                    n.previous = map.get(value);
                }
            });
        });

        // set entry
        DoublyLinkedList dll = new DoublyLinkedList();
        dll.header = map.get(cellGraph.entries.get(0));
        dll.size = size;

        return dll;
    }
}
