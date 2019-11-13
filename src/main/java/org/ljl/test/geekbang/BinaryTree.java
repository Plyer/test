package org.ljl.test.geekbang;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lvjinglu
 * created at 2019/10/21
 */
public class BinaryTree<T extends Comparable<T>> {

    private Node<T> root;

    static class Node<T> {
        private T data;
        private Node<T> left;
        private Node<T> right;

        public Node(T data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    public void put(T data) {
        if (root == null) {
            root = new Node<T>(data);
            return;
        }

        Node<T> node = root;
        Node<T> pNode = null;
        while (node != null) {
            pNode = node;
            if (data.compareTo(node.data) <= 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }

        if (data.compareTo(pNode.data) <= 0) {
            pNode.left = new Node<T>(data);
        } else {
            pNode.right = new Node<T>(data);
        }
    }

    public List<Node<T>> get(T data) {
        List<Node<T>> result = new ArrayList<>();
        Node<T> node = root;
        while (node != null) {
            if (data.compareTo(node.data) <= 0) {
                if (data.compareTo(node.data) == 0) {
                    result.add(node);
                }
                node = node.left;
            } else {
                node = node.right;
            }
        }

        return result;
    }

    public void remove(T data) {
        Node<T> node = root;
        Node<T> pNode = null;
        while (node != null) {
            pNode = node;
            if (data.compareTo(node.data) == 0) {
                break;
            } else if (data.compareTo(node.data) > 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }

        if (node == null) {
            return;
        }

        while (true) {
            Node<T> left = node.left;
            Node<T> pLeft = node;
            if (left != null) {
                while (left.right != null) {
                    pLeft = left;
                    left = left.right;
                }
            } else {
                if (node.data.compareTo(pNode.data) < 0) {
                    pNode.left = node.right;
                } else {
                    pNode.right = node.right;
                }
                return;
            }

            T tmp = node.data;
            node.data = left.data;
            boolean canReturn = false;
            if (tmp.compareTo(left.data) != 0) {
                canReturn = true;
            }
            left.data = tmp;

            pLeft.right = left.left;
            if (canReturn) {
                return;
            }
        }
    }

    public Node<T> max() {
        return max(root);
    }

    private Node<T> max(Node<T> node) {
        Node<T> pNode = null;
        while (node != null) {
            pNode = node;
            node = node.right;
        }
        return pNode;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Node node = root;
        List<Node> list = Arrays.asList(node);
        toString(result, node);
        return result.toString();
    }

    private void toString(StringBuilder result, Node node) {
        if (node == null) {
            return;
        }
        toString(result, node.left);
        result.append("  ");
        result.append(node.data);
        toString(result, node.right);
    }

    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<>();
        int[] initData = {10, 5, 15, 2, 7, 12, 17, 5, 4, 3};
        for (int i : initData) {
            tree.put(i);
        }

        System.out.println(tree);
        tree.remove(5);
        System.out.println(tree);
    }
}
