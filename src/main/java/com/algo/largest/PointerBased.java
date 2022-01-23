package com.algo.largest;

/**
 * Algorithm to k-th largest element in the array (pointer based approach with time complexity of O(n * m))
 */

public class PointerBased {
    public static void main(String[] args) {
        System.out.println(getKthElement(new int[]{1, 2, 3, 4, 5, 6, 7}, 3));
    }

    private static int getKthElement(int[] ints, int k) {
        Queue<Integer> queue = new Queue<>(k);
        for (int current : ints) {
            queue.add(current);
        }
        return queue.getKth();
    }
}

class Queue<T extends Comparable<T>> {
    private Node first;
    private final int k;
    private int size;

    Queue(int k) {
        this.k = k;
    }

    class Node {
        private final T value;
        private Node next;

        public Node(T value, Node next) {
            this.value = value;
            this.next = next;
        }

        public T getValue() {
            return value;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    void add(T value) {
        if (first == null) {
            size++;
            first = new Node(value, null);
        } else {
            add(value, first);
        }
    }

    T getKth() {
        return first.value;
    }

    private void add(T value, Node first) {
        if (first.value.compareTo(value) > 0) {
            if (size < k) {
                this.first = new Node(value, first);
                size++;
            }
        } else {
            Node previous = first;
            Node current = first.next;
            while (current != null && current.value.compareTo(value) <= 0) {
                previous = current;
                current = current.next;
            }
            Node newNode = new Node(value, current);
            previous.setNext(newNode);
            this.first = first.next;
        }
    }
}