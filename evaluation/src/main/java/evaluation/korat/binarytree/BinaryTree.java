package evaluation.korat.binarytree;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class BinaryTree {

    public static class Node {
        public Node left;
        public Node right;
    }

    public Node root;
    public int size;

    @SuppressWarnings("unchecked")
    public boolean repOK() {
        if (root == null){
            return size == 0;
        }
        // checks that tree has no cycle
        Set visited = new HashSet();
        visited.add(root);
        LinkedList workList = new LinkedList();
        workList.add(root);
        while (!workList.isEmpty()) {
            Node current = (Node) workList.removeFirst();
            if (current.left != null) {
                if (!visited.add(current.left)){
                    return false;
                }
                workList.add(current.left);
            }
            if (current.right != null) {
                if (!visited.add(current.right)) {
                    return false;
                }
                workList.add(current.right);
            }
        }
        // checks that size is consistent
        return (visited.size() == size);
    }
}
