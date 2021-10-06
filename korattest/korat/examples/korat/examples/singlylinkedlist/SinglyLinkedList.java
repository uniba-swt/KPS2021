package korat.examples.singlylinkedlist;

import java.util.HashSet;
import java.util.Set;

import korat.finitization.IFinitization;
import korat.finitization.IIntSet;
import korat.finitization.IObjSet;
import korat.finitization.impl.FinitizationFactory;

public class SinglyLinkedList {

    public Entry header;

    private int size = 0;
    
    public boolean negatedRepOK() {
        boolean result = !repOK();
        if(result){
            Set<Entry> flattenedList = flattenListToSet();
            System.out.println(printListToCellgraph(flattenedList));
        }
        return result;
    }

    private Set<Entry> flattenListToSet() {
        Set<Entry> nodes = new HashSet();
        if (header == null)
            return nodes;

        nodes.add(header);
        Entry current = header;

        while (true) {
            Entry next = current.next;
            if (next == null || !nodes.add(next))
                break;
            
            current = next;
        }
        return nodes;
    }

    private String printListToCellgraph(Set<Entry> nodes){
        String headerId = (header == null) ? "null" : Integer.toString(header.hashCode());
        String matrix = "";

        for(Entry n : nodes){
            String nodeId = Integer.toString(n.hashCode());
            String nextId = (n.next == null) ? "null" : Integer.toString(n.next.hashCode());
            matrix += "\"" + nodeId + "\": {\"next\": \"" + nextId + "\"},\r\n";
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
        return repOkCommon();
    }

    public boolean repOkCommon() {
        if (header == null)
            return false;

       // if (header.element != null)
       //     return false;

        Set<Entry> visited = new java.util.HashSet<Entry>();
        visited.add(header);
        Entry current = header;

        while (true) {
            Entry next = current.next;
            if (next == null)
                break;

            // if (next.element == null)
            //     return false;

            if (!visited.add(next))
                return false;

            current = next;
        }

        // if (current != header)
        // return false; // // maybe not needed (also in SortedList.java)

        if (visited.size() - 1 != size)
            return false;

        return true;
    }

    public boolean repOkNS() {
        if (!repOkCommon())
            return false;
        return true;
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
        String res = "(";
        if (header != null) {
            Entry cur = header.next;
            while (cur != null && cur != header) {
                res += cur.toString();
                cur = cur.next;
            }
        }
        return res + ")";
    }

    public static IFinitization finSinglyLinkedList(int size) {
        return finSinglyLinkedList(size, size, size + 1, size + 1);
    }
            
    public static IFinitization finSinglyLinkedList(int minSize, int maxSize,
            int numEntries, int numElems) {

        IFinitization f = FinitizationFactory.create(SinglyLinkedList.class);

        IObjSet entries = f.createObjSet(Entry.class);
        entries.setNullAllowed(true);
        entries.addClassDomain(f.createClassDomain(Entry.class, numEntries));

        IObjSet elems = f.createObjSet(SerializableObject.class);
        elems.setNullAllowed(true);
        elems.addClassDomain(f.createClassDomain(SerializableObject.class,
                numElems));

        IIntSet sizes = f.createIntSet(minSize, maxSize);

        f.set("header", entries);
        f.set("size", sizes);
        f.set("Entry.element", elems);
        f.set("Entry.next", entries);

        return f;

    }

}
