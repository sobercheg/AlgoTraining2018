package epi;

import utils.UnitTester;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * EPI: Recursion, page 300
 * Compute the Diameter of a Tree
 */
public class TreeDiameter {
    static class Edge {
        int length;
        Node node;

        Edge(int length, Node node) {
            this.length = length;
            this.node = node;
        }
    }

    static class Node {
        List<Edge> edges = new ArrayList<>();

        Node() {
        }

        Node(List<Edge> edges) {
            this.edges = edges;
        }
    }

    static class HeightAndDiameter {
        HeightAndDiameter(int height, int maxSubteeDiameter) {
            this.height = height;
            this.maxSubteeDiameter = maxSubteeDiameter;
        }

        int height;
        int maxSubteeDiameter;

    }

    private HeightAndDiameter getDiameter(Node root) {
        int maxHeightFromNode = 0;
        int maxChildSubtreeDiameter = 0;
        // Get top 2 paths from node
        int[] maxHeights = new int[]{0, 0};
        for (Edge edge : root.edges) {
            HeightAndDiameter childPaths = getDiameter(edge.node);
            int heightFromNode = childPaths.height + edge.length;
            if (heightFromNode > maxHeights[0]) {
                maxHeights[1] = maxHeights[0];
                maxHeights[0] = heightFromNode;
            } else if (heightFromNode > maxHeights[1]) {
                maxHeights[1] = heightFromNode;
            }
            maxHeightFromNode = Math.max(maxHeightFromNode, heightFromNode);
            maxChildSubtreeDiameter = Math.max(maxChildSubtreeDiameter, childPaths.maxSubteeDiameter);
        }
        int maxSubtreeDiameter = maxHeights[0] + maxHeights[1];
        return new HeightAndDiameter(maxHeightFromNode, Math.max(maxSubtreeDiameter, maxChildSubtreeDiameter));
    }

    public static void main(String[] args) {
        TreeDiameter td = new TreeDiameter();
        Node root = new Node(Arrays.asList(
                new Edge(7, new Node(Arrays.asList(
                        new Edge(4, new Node(Arrays.asList(new Edge(6, new Node())))),
                        new Edge(3, new Node())))),
                new Edge(14, new Node()),
                new Edge(3, new Node(Arrays.asList(
                        new Edge(2, new Node()),
                        new Edge(1, new Node(Arrays.asList(
                                new Edge(6, new Node()),
                                new Edge(4, new Node(Arrays.asList(
                                        new Edge(1, new Node()),
                                        new Edge(2, new Node()),
                                        new Edge(3, new Node())))))))))))
        );
        HeightAndDiameter paths = td.getDiameter(root);
        System.out.println(paths.maxSubteeDiameter);
        UnitTester.assertEquals(31, paths.maxSubteeDiameter);

        root = new Node(Arrays.asList(
                new Edge(1, new Node(Arrays.asList(
                        new Edge(10, new Node()),
                        new Edge(13, new Node()),
                        new Edge(12, new Node())))),
                new Edge(2, new Node())));
        paths = td.getDiameter(root);
        System.out.println(paths.maxSubteeDiameter);
        UnitTester.assertEquals(25, paths.maxSubteeDiameter);
    }
}
