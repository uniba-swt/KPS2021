package evaluation.korat.doublylinkedlist;

import java.util.Set;

public class DoublyLinkedList {

    public Entry header;

    public int size = 0;

    public boolean repOK() {
        return repOkNS();
    }

    @SuppressWarnings("unchecked")
    public boolean repOkCommon() {

        if (header == null){
            return false;
        }
        // if (header.element == null)
        //     return size == 0 && header.next == header
        //             && header.previous == header;

        Set visited = new java.util.HashSet();
        visited.add(header);
        Entry current = header;

        while (true) {
            Entry next = current.next;
            if (next == null){
                return false;
            }
            if (next.previous != current){
                return false;
            }
            current = next;
            if (!visited.add(next)){
                break;
            }
        }
        if (current != header){
            return false; // // maybe not needed (also in SortedList.java)
        }
        if (visited.size() != size){
            return false;
        }
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
}
