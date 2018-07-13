package epi;

import utils.UnitTester;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * EPI: Recursion, page 295
 * Write a program which returns all distinct binary trees with a specified number of
 * nodes.
 */
public class AllPossibleTrees {

    static class Node {
        Node left;
        Node right;
        String value;

        Node(Node left, Node right, String value) {
            this.left = left;
            this.right = right;
            this.value = value;
        }
    }

    private List<Node> createSubTree(int nodesNeeded) {
        List<Node> allTrees = new ArrayList<>();

        if (nodesNeeded == 0) {
            allTrees.add(null);
        }

        for (int leftTreeNodes = 0; leftTreeNodes < nodesNeeded; leftTreeNodes++) {
            // 0 left, N right
            // 1 left, N-1 right
            // etc
            int rightTreeNodes = nodesNeeded - leftTreeNodes - 1;
            List<Node> leftSubtrees = createSubTree(leftTreeNodes);
            List<Node> rightSubtrees = createSubTree(rightTreeNodes);

            for (Node leftSubtree : leftSubtrees) {
                for (Node rightSubtree : rightSubtrees) {
                    allTrees.add(new Node(leftSubtree, rightSubtree, " v "));
                }
            }
        }
        return allTrees;
    }

    private static void printLevels(Node root) {
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        Queue<Node> nextLevel = new LinkedList<>();
        while (!q.isEmpty()) {
            while (!q.isEmpty()) {
                Node currentLevelNode = q.poll();
                if (currentLevelNode == null) {
                    continue;
                }
                System.out.print(currentLevelNode.value + " ");

                if (currentLevelNode.left != null) {
                    nextLevel.offer(currentLevelNode.left);
                }
                if (currentLevelNode.right != null) {
                    nextLevel.offer(currentLevelNode.right);
                }
            }
            System.out.println();
            while (!nextLevel.isEmpty()) {
                q.offer(nextLevel.poll());
            }
        }
        System.out.println();
        System.out.println();
    }

    public static void main(String[] args) {
        AllPossibleTrees trees = new AllPossibleTrees();

        UnitTester.assertEquals(trees.createSubTree(1).size(), 1);
        UnitTester.assertEquals(trees.createSubTree(2).size(), 2);
        UnitTester.assertEquals(trees.createSubTree(3).size(), 5);

        List<Node> treeList = trees.createSubTree(3);
        for (Node root : treeList) {
            printLevels(root);
        }

    }
}
