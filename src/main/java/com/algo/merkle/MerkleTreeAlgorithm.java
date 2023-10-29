package com.algo.merkle;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MerkleTreeAlgorithm {
    public static void main(String[] args) {
        MerkleTree<Integer> firstTree = new MerkleTree<>(3, 2, 12, 11);
        MerkleTree<Integer> secondTree = new MerkleTree<>(3, 2, 12, 13);
        System.out.println(firstTree.getDiff(secondTree));
    }
}

class MerkleTree<T> {
    private final Map<Node, T> nodesToValues;
    private final Node root;

    public MerkleTree(T... values) {
        this.nodesToValues = new HashMap<>();
        this.root = buildTree(values);
    }

    public boolean isTheSame(MerkleTree<T> tree) {
        return this.root.hash == tree.root.hash;
    }

    public Optional<T> getDiff(MerkleTree<T> tree) {
        Node thisNode = root;
        Node otherNode = tree.root;
        if (thisNode.hash == otherNode.hash) {
            return Optional.empty();
        }
        while (!nodesToValues.containsKey(thisNode)) {
            if (thisNode.left.hash != otherNode.left.hash) {
                thisNode = thisNode.left;
                otherNode = otherNode.left;
            } else {
                thisNode = thisNode.right;
                otherNode = otherNode.right;

            }
        }
        return Optional.of(nodesToValues.get(thisNode));
    }

    private Node buildTree(T[] values) {
        int start = 0;
        int mid = values.length / 2;
        int end = values.length - 1;
        Node left = buildRecursively(values, start, mid - 1);
        Node right = buildRecursively(values, mid, end);
        return new Node(left, right, left.getHash() + right.getHash());
    }

    private Node buildRecursively(T[] values, int start, int end) {
        if (start == end) {
            T value = values[start];
            Node node = new Node(null, null, value.hashCode());
            nodesToValues.put(node, value);
            return node;
        } else {
            int mid = (start + end) / 2;
            Node left = buildRecursively(values, start, mid);
            Node right = buildRecursively(values, mid + 1, end);
            return new Node(left, right, left.getHash() + right.getHash());
        }
    }

    private static class Node {
        private final Node left;
        private final Node right;
        private final int hash;

        public Node(Node left, Node right, int hash) {
            this.left = left;
            this.right = right;
            this.hash = hash;
        }

        public int getHash() {
            return hash;
        }
    }

}