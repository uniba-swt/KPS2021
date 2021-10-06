package evaluation.korat.singlylinkedlist;

import java.util.Set;


public class SinglyLinkedList {

    public Entry header;

    public int size = 0;

    public boolean repOK() {
        return repOkNS();
    }

    public boolean repOkCommon() {
        if (header == null){
            return false;
        }

        // if (header.element != null)
        //     return false;

        Set<Entry> visited = new java.util.HashSet<Entry>();
        visited.add(header);
        Entry current = header;

        while (true) {
            Entry next = current.next;
            if (next == null) {
                break;
            }
            if (next.element == null){
                return false;
            }
            if (!visited.add(next)){
                return false;
            }
            current = next;
        }

        // if (current != header)
        // return false; // // maybe not needed (also in SortedList.java)

        if (visited.size() - 1 != size){
            return false;
        }

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
}
