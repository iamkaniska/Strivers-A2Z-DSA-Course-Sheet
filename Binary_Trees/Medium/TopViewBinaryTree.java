import java.util.*;

// Node class for a binary tree node
class Node {
    int data;
    Node left, right;

    public Node(int data) {
        this.data = data;
        left = right = null;
    }
}

public class TopViewBinaryTree {
    // Pair class to store a node and its horizontal distance
    static class Pair {
        Node node;
        int hd;

        Pair(Node node, int hd) {
            this.node = node;
            this.hd = hd;
        }
    }

    // Function to print the top view of a binary tree
    public static void printTopView(Node root) {
        if (root == null)
            return;

        // TreeMap to store the top view nodes based on their horizontal distance (HD)
        TreeMap<Integer, Integer> topViewMap = new TreeMap<>();

        // Queue for level-order traversal (BFS)
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root, 0)); // Start with the root at HD 0

        while (!queue.isEmpty()) {
            Pair currentPair = queue.poll();
            Node currentNode = currentPair.node;
            int hd = currentPair.hd;

            // If a node at this horizontal distance hasn't been added yet, add it
            if (!topViewMap.containsKey(hd)) {
                topViewMap.put(hd, currentNode.data);
            }

            // Add the left and right children with their corresponding HDs
            if (currentNode.left != null) {
                queue.add(new Pair(currentNode.left, hd - 1));
            }
            if (currentNode.right != null) {
                queue.add(new Pair(currentNode.right, hd + 1));
            }
        }

        // Print the top view
        for (Map.Entry<Integer, Integer> entry : topViewMap.entrySet()) {
            System.out.print(entry.getValue() + " ");
        }
    }

    public static void main(String[] args) {
        // Example binary tree
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        System.out.println("Top view of the binary tree:");
        printTopView(root);  // Output will be: 4 2 1 3 7
    }
}
