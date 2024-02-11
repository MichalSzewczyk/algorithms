package com.algo.interview.google.removekey;

public class Main {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.add("first");
        list.add("second");
        list.display();
        list.deleteNode("second");
        list.display();
    }
}

class LinkedList {
    private final LinkedListNode head;

    public LinkedList() {
        this.head = new LinkedListNode(null);
    }

    public void add(String key) {
        LinkedListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.setNext(new LinkedListNode(key));
    }

    public void remove(String key) {
        LinkedListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.setNext(new LinkedListNode(key));
    }

    void display() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        LinkedListNode current = head;
        while (current.next != null) {
            builder.append(" ").append(current.next.key);
            current = current.next;
        }
        builder.append(" ]");
        System.out.println(builder.toString());
    }

    public LinkedListNode deleteNode(String key) {
        LinkedListNode current = head.next;
        LinkedListNode previuous = head;
        while (current != null && !current.key.equals(key)) {
            previuous = current;
            current = current.next;
        }
        if (current == null) {
            return head;
        } else {
            previuous.next = current.next;
        }
        return head;
    }

    static class LinkedListNode {
        private final String key;
        private LinkedListNode next;

        public LinkedListNode(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }

        public LinkedListNode getNext() {
            return next;
        }

        public void setNext(LinkedListNode next) {
            this.next = next;
        }
    }
}
