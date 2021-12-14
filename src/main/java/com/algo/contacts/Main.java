package com.algo.contacts;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

import static java.util.Objects.isNull;

/**
 * We're going to make our own Contacts application! The application must perform two types of operations:
 * <p>
 * add name, where name is a string denoting a contact name. This must store name as a new contact in the application.
 * find partial, where partial is a string that denotes a partial name to search the application for. It must count the number of contacts starting with partial and print the count on a new line.
 * Given n sequential add and find operations, perform each operation in order.
 * <p>
 * <p>
 * Input Format
 * <p>
 * The first line contains a single integer, n, the number of operations to perform.
 * Each line i of the n subsequent lines contains an operation in one of the two forms defined above.
 * <p>
 * Output Format
 * <p>
 * For each find partial operation, print the number of contact names starting with  on a new line.
 * <p>
 * Sample Input
 * <p>
 * 4
 * add hack
 * add hackerrank
 * find hac
 * find hak
 * Sample Output
 * <p>
 * 2
 * 0
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());
        Trie trie = new Trie();
        IntStream.range(0, n).forEach(nItr -> {
            try {
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                String op = firstMultipleInput[0];

                String contact = firstMultipleInput[1];
                if (Objects.equals("add", op)) {
                    trie.add(contact);
                } else if (Objects.equals("find", op)) {
                    System.out.println(trie.countMatching(contact));
                } else {
                    throw new IllegalArgumentException("Operation not supported: " + op);
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
    }

    private static class Trie {

        private static class Node {
            private final Map<Character, Node> children;
            private int childrenCount;

            public Node() {
                children = new HashMap<>();
            }

            void incrementChildrenCount() {
                childrenCount++;
            }

            Node getChild(char value) {
                return children.computeIfAbsent(value, v -> new Node());
            }

            int getChildrenCount() {
                return childrenCount;
            }
        }

        private final Map<Character, Node> children;

        public Trie() {
            children = new HashMap<>();
        }

        void add(String word) {
            char[] letters = word.toCharArray();
            int idx = 0;
            Node current = children.computeIfAbsent(letters[idx], v -> new Node());
            current.incrementChildrenCount();
            idx++;
            while (idx != letters.length) {
                current = current.getChild(letters[idx]);
                idx++;
                current.incrementChildrenCount();
            }
        }

        int countMatching(String word) {
            char[] letters = word.toCharArray();
            int idx = 0;
            Node current = children.get(letters[idx++]);
            while (idx != letters.length) {
                if (isNull(current)) {
                    return 0;
                }
                current = current.getChild(letters[idx++]);
            }
            if (isNull(current)) {
                return 0;
            }
            return current.getChildrenCount();
        }
    }
}
