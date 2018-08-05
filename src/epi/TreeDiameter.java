package epi;

import utils.UnitTester;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * EPI: Recursion, page 300
 * Compute the Diameter of a Tree
 */
public class TreeDiameter {

    static class Node {
        List<Integer> edges = new ArrayList<>();
        List<Node> nodes = new ArrayList<>();

        Node() {
        }

        Node(List<Integer> edges, List<Node> nodes) {
            this.edges = edges;
            this.nodes = nodes;
        }
    }

    static class NodePaths {
        NodePaths(int maxPathFromNode, int maxSubteeDiameter) {
            this.maxPathFromNode = maxPathFromNode;
            this.maxSubteeDiameter = maxSubteeDiameter;
        }

        int maxPathFromNode;
        int maxSubteeDiameter;

    }

    private NodePaths getDiameter(Node root) {
        int maxPathFromNode = 0;
        int maxChildSubtreeDiameter = 0;
        List<Integer> fromNodeMaxPaths = new ArrayList<>();
        for (int i = 0; i < root.nodes.size(); i++) {
            NodePaths childPaths = getDiameter(root.nodes.get(i));
            fromNodeMaxPaths.add(childPaths.maxPathFromNode + root.edges.get(i));
            maxPathFromNode = Math.max(maxPathFromNode, childPaths.maxPathFromNode + root.edges.get(i));
            maxChildSubtreeDiameter = Math.max(maxChildSubtreeDiameter, childPaths.maxSubteeDiameter);
        }
        // Get top 2 paths from node
        fromNodeMaxPaths.sort((o1, o2) -> Integer.compare(o2, o1));
        int maxSubtreeDiameter = (fromNodeMaxPaths.size() > 0 ? fromNodeMaxPaths.get(0) : 0)
                + (fromNodeMaxPaths.size() > 1 ? fromNodeMaxPaths.get(1) : 0);
        return new NodePaths(maxPathFromNode, Math.max(maxSubtreeDiameter, maxChildSubtreeDiameter));
    }

    public static void main(String[] args) {
        TreeDiameter td = new TreeDiameter();
        Node root = new Node(Arrays.asList(7, 14, 3),
                Arrays.asList(
                        new Node(Arrays.asList(4, 3),
                                Arrays.asList(new Node(Collections.singletonList(6),
                                        Collections.singletonList(new Node())), new Node())),
                        new Node(),
                        new Node(Arrays.asList(2, 1), Arrays.asList(new Node(),
                                new Node(Arrays.asList(6, 4), Arrays.asList(new Node(), new Node())))
                        ))

        );
        NodePaths paths = td.getDiameter(root);
        System.out.println(paths.maxSubteeDiameter);
        UnitTester.assertEquals(31, paths.maxSubteeDiameter);

        root = new Node(Arrays.asList(1, 2),
                Arrays.asList(
                        new Node(Arrays.asList(10, 13, 12), Arrays.asList(new Node(), new Node(), new Node())),
                        new Node()));
        paths = td.getDiameter(root);
        System.out.println(paths.maxSubteeDiameter);
        UnitTester.assertEquals(25, paths.maxSubteeDiameter);
    }
}
