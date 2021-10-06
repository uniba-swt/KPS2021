package korat.examples.doublylinkedlist;

import java.util.Set;
import java.util.HashSet;

import korat.finitization.IFinitization;
import korat.finitization.IIntSet;
import korat.finitization.IObjSet;
import korat.finitization.impl.FinitizationFactory;

public class DoublyLinkedList {

    public Entry header;

    private int size = 0;

    public boolean negatedRepOK() {
        boolean isCorrupted = !repOK();
        if(isCorrupted){
            Set<Entry> flattenedList = flattenListToSet();
            System.out.print(printListToCellgraph(flattenedList));
        }
        return isCorrupted;
    }

    private Set<Entry> flattenListToSet() {
        Set<Entry> nextNodes = new HashSet();
        if (header == null)
            return nextNodes;

        nextNodes.add(header);
        Entry current = header;

        while (true) {
            Entry next = current.next;
            if (next == null || !nextNodes.add(next))
                break;
            
            current = next;
        }

        Set<Entry> prevNodes = new HashSet();
        while (true) {
            Entry previous = current.previous;
            if (previous == null || !prevNodes.add(previous))
                break;
            
            current = previous;
        }

        Set<Entry> nodes = new HashSet();
        nodes.addAll(nextNodes);
        nodes.addAll(prevNodes);
        return nodes;
    }

    private String printListToCellgraph(Set<Entry> nodes){
        String headerId = (header == null) ? "null" : Integer.toString(header.hashCode());
        String matrix = "";

        for(Entry n : nodes){
            String nodeId = Integer.toString(n.hashCode());
            String nextId = (n.next == null) ? "null" : Integer.toString(n.next.hashCode());
            String prevId = (n.previous == null) ? "null" : Integer.toString(n.previous.hashCode());
            matrix += "\"" + nodeId + "\": {\"next\": \"" + nextId + "\", \"previous\": \"" + prevId + "\"},\r\n";
        }

        String result = 
            "{\r\n" + 
            "\"entries\": [\"" + headerId + "\"],\r\n" +
            "\"consumed\": [],\r\n" +
            "\"matrix\": {\r\n" + 
            matrix + "}\r\n" +
            "},\r\n";

        return result;
    }

    public boolean repOK() {
        return repOkNS();
    }

    @SuppressWarnings("unchecked")
    public boolean repOkCommon() {

        if (header == null)
            return false;
        // if (header.element == null)
        //     return size == 0 && header.next == header
        //             && header.previous == header;

        Set visited = new java.util.HashSet();
        visited.add(header);
        Entry current = header;

        while (true) {
            Entry next = current.next;
            if (next == null)
                return false;
            if (next.previous != current)
                return false;
            current = next;
            if (!visited.add(next))
                break;
        }
        if (current != header)
            return false; // // maybe not needed (also in SortedList.java)
        if (visited.size() != size)
            return false;
        return true;
    }

    public boolean repOkNS() {
        return repOkCommon();
    }

    @SuppressWarnings("unchecked")
    public boolean repOkSorted() {
        if (!repOkCommon())
            return false;
        // check for sorted
        if ((header.next != header)
                && (!(header.next.element instanceof Comparable)))
            return false;
        for (Entry current = header.next; current.next != header; current = current.next) {
            if ((!(current.next.element instanceof Comparable))
                    || (((Comparable) current.element).compareTo((Comparable) current.next.element) > 0))
                return false;
        }
        return true;
    }

    public String toString() {
        String res = "";
        Entry cur = this.header;
        while (cur != null) {
            res += cur.toString();
            cur = cur.next;
            if (cur == header)
                break;
        }
        return res;
    }

    public static IFinitization finDoublyLinkedList(int size) {
        return finDoublyLinkedList(size, size, size+1, size+1);
    }
        
    public static IFinitization finDoublyLinkedList(int minSize, int maxSize,
            int numEntries, int numElems) {
        
        IFinitization f = FinitizationFactory.create(DoublyLinkedList.class);

        IObjSet entries = f.createObjSet(Entry.class, true);
        entries.addClassDomain(f.createClassDomain(Entry.class, numEntries));

        IObjSet elems = f.createObjSet(ListObject.class, true);
        elems.addClassDomain(f.createClassDomain(ListObject.class, numElems));

        IIntSet sizes = f.createIntSet(minSize, maxSize);

        f.set("header", entries);
        f.set("size", sizes);
        f.set(Entry.class, "element", elems);
        f.set(Entry.class, "next", entries);
        f.set(Entry.class, "previous", entries);
        
        return f;
        
    }

}
