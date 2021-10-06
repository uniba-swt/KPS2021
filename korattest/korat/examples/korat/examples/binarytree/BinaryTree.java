package korat.examples.binarytree;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import korat.finitization.IFinitization;
import korat.finitization.IObjSet;
import korat.finitization.impl.FinitizationFactory;

public class BinaryTree {

    public static class Node {
        Node left;
        Node right;
    }

    private Node root;
    private int size;

    public boolean negatedRepOK() {
        boolean result = !repOK();
        if(result){
            Set<Node> flattenedTree = flattenTreeToSet();
            System.out.print(printTreeToCellgraph(flattenedTree));
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    private Set<Node> flattenTreeToSet() {
        Set<Node> nodes = new HashSet();
        if (root == null){
            return nodes;
        }
        nodes.add(root);
        LinkedList<Node> workList = new LinkedList();
        workList.add(root);
        while (!workList.isEmpty()) {
            Node current = workList.removeFirst();
            if (current.left != null && nodes.add(current.left)) {
                workList.add(current.left);
            }
            if (current.right != null && nodes.add(current.right)) {
                workList.add(current.right);
            }
        }
        return nodes;
    }

    private String printTreeToCellgraph(Set<Node> nodes){
        String rootId = (root == null) ? "null" : Integer.toString(root.hashCode());
        String matrix = "";

        for(Node n : nodes){
            String nodeId = Integer.toString(n.hashCode());
            String leftId = (n.left == null) ? "null" : Integer.toString(n.left.hashCode());
            String rightId = (n.right == null) ? "null" : Integer.toString(n.right.hashCode());
            matrix += "\"" + nodeId + "\": {\"left\": \"" + leftId + "\", \"right\": \"" + rightId + "\"},\r\n";
        }

        String result = 
            "{\r\n" + 
            "\"entries\": [\"" + rootId + "\"],\r\n" +
            "\"consumed\": [],\r\n" +
            "\"matrix\": {\r\n" + 
            matrix + "}\r\n" +
            "},\r\n";

        return result;
    }


    @SuppressWarnings("unchecked")
    public boolean repOK() {
        if (root == null)
            return size == 0;
        // checks that tree has no cycle
        Set visited = new HashSet();
        visited.add(root);
        LinkedList workList = new LinkedList();
        workList.add(root);
        while (!workList.isEmpty()) {
            Node current = (Node) workList.removeFirst();
            if (current.left != null) {
                if (!visited.add(current.left))
                    return false;
                workList.add(current.left);
            }
            if (current.right != null) {
                if (!visited.add(current.right))
                    return false;
                workList.add(current.right);
            }
        }
        // checks that size is consistent
        return (visited.size() == size);
    }

    public static IFinitization finBinaryTree(int size) {
        return finBinaryTree(size, size, size);
    }

    public static IFinitization finBinaryTree(int nodesNum, int minSize,
            int maxSize) {
        IFinitization f = FinitizationFactory.create(BinaryTree.class);
        IObjSet nodes = f.createObjSet(Node.class, nodesNum, true);
        f.set("root", nodes);
        f.set("size", f.createIntSet(minSize, maxSize));
        f.set("Node.left", nodes);
        f.set("Node.right", nodes);
        return f;
    }
}
