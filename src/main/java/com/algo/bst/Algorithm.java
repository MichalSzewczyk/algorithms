package com.algo.bst;

import java.util.LinkedList;
import java.util.List;

public class Algorithm {
    public static void main(String[] args) {
        BSTTree<String> tree = new BSTTree<>();

        tree.addElement("a");
        tree.addElement("c");
        tree.addElement("d");
        tree.addElement("b");

        System.out.println(tree.getValues());
        System.out.println(tree.contains("a"));
    }

    private static class BSTTree<T extends Comparable<T>> {
        private Node root;

        void addElement(T value) {
            if (root == null) {
                root = new Node(value);
            } else {
                Node currentNode = root;
                Node previousNode = root;
                while (currentNode != null) {
                    previousNode = currentNode;
                    if (currentNode.getValue().compareTo(value) < 0) {
                        currentNode = currentNode.getRightChild();
                    } else {
                        currentNode = currentNode.getLeftChild();
                    }
                }
                if (previousNode.getValue().compareTo(value) < 0) {
                    previousNode.setRightChild(new Node(value));
                } else {
                    previousNode.setLeftChild(new Node(value));
                }
            }
        }

        List<T> getValues() {
            Node currentNode = root;
            List<T> values = new LinkedList<>();
            getValuesRecursive(currentNode, values);
            return values;
        }

        boolean contains(T value) {
            Node currentNode = root;
            while (currentNode != null) {
                if (currentNode.getValue().equals(value)) {
                    return true;
                } else if (currentNode.getValue().compareTo(value) < 0) {
                    currentNode = currentNode.getRightChild();
                } else {
                    currentNode = currentNode.getLeftChild();
                }
            }
            return false;
        }

        private void getValuesRecursive(Node currentNode, List<T> values) {
            if (currentNode != null) {
                getValuesRecursive(currentNode.getLeftChild(), values);
                values.add(currentNode.getValue());
                getValuesRecursive(currentNode.getRightChild(), values);
            }
        }

        private class Node implements Comparable<Node> {
            private final T value;
            private Node leftChild;
            private Node rightChild;

            public Node(T value) {
                this.value = value;
            }

            public Node getLeftChild() {
                return leftChild;
            }

            public Node getRightChild() {
                return rightChild;
            }

            public void setLeftChild(Node leftChild) {
                this.leftChild = leftChild;
            }

            public void setRightChild(Node rightChild) {
                this.rightChild = rightChild;
            }

            public T getValue() {
                return value;
            }

            @Override
            public int compareTo(Node node) {
                return value.compareTo(node.getValue());
            }
        }
    }
}
