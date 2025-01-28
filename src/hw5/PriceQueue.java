/***************************************************************
 * Below is the Queue code from the textbook, but modified to be
 * a queue of Price objects rather than a generic queue.
 ****************************************************************/

package hw5;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PriceQueue implements Iterable<Price> {
    private Node first; // beginning of queue
    private Node last; // end of queue
    private int n; // number of elements on queue
    private TreeNode root; // The root of the TreeMap
    // TODO - Add a TreeMap that maps a price to the node before that price in the
    // queue
    // and maps the first price (nothing before it) to null. You will need to
    // maintain this invariant on the TreeMap.
    //
    // NOTE1: You will use the TreeMap to improve the running time of enqueue and
    // delete
    // so that they run in logarithmic time.
    //
    // NOTE2: Because you add a new field (the TreeMap), you need to consider how
    // ALL
    // the methods in the PriceQueue class might need to change in order to
    // correctly maintain the invariants on the TreeMap.

    // helper linked list class
    private static class Node {
        private Price price;
        private Node next;
    }

    // Helper BST class
    private static class TreeNode {
        private Price price;
        private TreeNode left;
        private TreeNode right;
        private Node queuePosition;
    }

    /**
     * Initializes an empty queue.
     */
    public PriceQueue() {
        first = null;
        last = null;
        root = null;
        n = 0;
    }

    /**
     * Returns true if this queue is empty.
     *
     * @return {@code true} if this queue is empty; {@code false} otherwise
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Returns the number of Prices in this queue.
     *
     * @return the number of Prices in this queue
     */
    public int size() {
        return n;
    }

    /**
     * Returns the Price least recently added to this queue.
     *
     * @return the Price least recently added to this queue
     * @throws NoSuchElementException if this queue is empty
     */
    public Price peek() {
        if (isEmpty())
            throw new NoSuchElementException("Queue underflow");
        return first.price;
    }

    /**
     * Adds a Price to the front of the queue if it is not already present
     * in the queue.
     * 
     * @param price the Price to be added
     * @return {@code true} if the Price was added and {@code false} if it was
     *         not added (it was already present in the queue).
     */
    public boolean enqueue(Price price) {
        if (treeAdd(root, price) == true) {
            Node oldLast = last;
            last = new Node();
            last.price = price;
            last.next = null;
            if (isEmpty()) {
                first = last;
            } else {
                oldLast.next = last;
            }
            n++;
            return true;
        }
        return false;
    }

    private boolean treeAdd(TreeNode node, Price price) {
        if (node == null) {
            TreeNode newNode = new TreeNode();
            newNode.price = price;
            newNode.queuePosition = null;
            root = newNode;
            return true;
        }
        if (price.compareTo(node.price) == 0) {
            return false;
        }
        if (root == null) {
            TreeNode newNode = new TreeNode();
            newNode.price = price;
            newNode.queuePosition = null;
            root = newNode;
            return true;
        }
        TreeNode currNode = root;
        while (true) {
            if (price.compareTo(node.price) == 0) {
                return false;
            }
            if (price.compareTo(currNode.price) < 0) {
                if (currNode.left == null) {
                    TreeNode newNode = new TreeNode();
                    newNode.price = price;
                    newNode.queuePosition = last;
                    node.left = newNode;
                    return true;
                }
                currNode = currNode.left;
            } else {
                if (currNode.right == null) {
                    TreeNode newNode = new TreeNode();
                    newNode.price = price;
                    newNode.queuePosition = last;
                    node.left = newNode;
                    return true;
                }
                currNode = currNode.left;
            }
        }
    }

    /**
     * Removes and returns the Price in this queue that was least recently added.
     *
     * @return the Price on this queue that was least recently added
     * @throws NoSuchElementException if this queue is empty
     */
    public Price dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("Queue underflow");
        Price price = first.price;
        treeDelete(root, price);
        first = first.next;
        n--;
        TreeNode update = treeSearch(root, price);
        if (update != null) {
            update.queuePosition = null;
        }
        if (isEmpty())
            last = null; // to avoid loitering
        return price;
    }

    private TreeNode treeSearch(TreeNode node, Price price) {
        if (node == null) {
            return null;
        }

        TreeNode currNode = node;
        while (true) {
            if (currNode == null) {
                return null;
            }
            if (price.compareTo(currNode.price) == 0) {
                return currNode;
            } else if (price.compareTo(currNode.price) < 0) {
                currNode = currNode.left;
            } else {
                currNode = currNode.right;
            }
        }
    }

    private void treeDelete(TreeNode node, Price price) {
        if (root == null) {
            return;
        }
        if (price.compareTo(root.price) == 0) {
            root.queuePosition = null;
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
            if (root.left == null) {
                root = root.right;
                return;
            }
            if (root.right == null) {
                root = root.left;
                return;
            }

            TreeNode minChild = root.right;
            TreeNode parent = root;
            while (minChild.left != null) {
                parent = minChild;
                minChild = minChild.left;
            }

            root.price = minChild.price;
            root.queuePosition = minChild.queuePosition;
            if (parent.left == minChild) {
                parent.left = minChild.right;
            } else {
                parent.right = minChild.right;
            }
            return;
        }
        TreeNode currNode = node;
        TreeNode parent = root;

        while (true) {
            if (currNode == null) {
                return;
            }
            if (price.compareTo(currNode.price) < 0) {
                parent = currNode;
                currNode = currNode.left;
            } else if (price.compareTo(currNode.price) > 0) {
                parent = currNode;
                currNode = currNode.right;
            } else {
                currNode.queuePosition = null;
                if (currNode.left == null && currNode.right == null) {
                    if (currNode.equals(parent.left)) {
                        parent.left = null;
                    } else {
                        parent.right = null;
                    }
                    return;
                }
                if (currNode.left == null) {
                    if (currNode.equals(parent.left)) {
                        parent.left = currNode.right;
                    } else {
                        parent.right = currNode.right;
                    }
                    return;
                }
                if (currNode.right == null) {
                    if (currNode.equals(parent.left)) {
                        parent.left = currNode.left;
                    } else {
                        parent.right = currNode.left;
                    }
                    return;
                }

                TreeNode minChild = currNode.right;
                parent = currNode;
                while (minChild.left != null) {
                    parent = minChild;
                    minChild = minChild.left;
                }

                currNode.price = minChild.price;
                currNode.queuePosition = minChild.queuePosition;
                if (parent.left == minChild) {
                    parent.left = minChild.right;
                } else {
                    parent.right = minChild.right;
                }
                return;
            }
        }
    }

    /**
     * Deletes a Price from the queue if it was present.
     * 
     * @param price the Price to be deleted.
     * @return {@code true} if the Price was deleted and {@code false} otherwise
     */
    public boolean delete(Price price) {
        TreeNode removed = treeSearch(root, price);
        if (removed == null) {
            return false;
        }

        Node prevNode = removed.queuePosition;
        if (prevNode == null) {
            first = first.next;
            n--;
            treeDelete(root, price);
            if (first != null) {
                TreeNode update = treeSearch(root, first.price);
                update.queuePosition = null;
            }
            return true;
        }
        Node deleteMe = prevNode.next;
        prevNode.next = deleteMe.next;
        if (deleteMe == last) {
            last = prevNode;
        } else {
            TreeNode update = treeSearch(root, deleteMe.next.price);
            update.queuePosition = prevNode;
        }
        treeDelete(root, price);
        n--;
        if (n == 0) {
            first = null;
            last = null;
            root = null;
        }
        return true;
    }

    /**
     * Returns an iterator that iterates over the Prices in this queue in FIFO
     * order.
     *
     * @return an iterator that iterates over the Prices in this queue in FIFO order
     */
    public Iterator<Price> iterator() {
        return new PriceListIterator(first);
    }

    // an iterator, doesn't implement remove() since it's optional
    private class PriceListIterator implements Iterator<Price> {
        private Node current;

        public PriceListIterator(Node first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Price next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Price price = current.price;
            current = current.next;
            return price;
        }
    }
}

/******************************************************************************
 * Copyright 2002-2016, Robert Sedgewick and Kevin Wayne.
 *
 * This file is part of algs4.jar, which accompanies the textbook
 *
 * Algorithms, 4th edition by Robert Sedgewick and Kevin Wayne,
 * Addison-Wesley Professional, 2011, ISBN 0-321-57351-X.
 * http://algs4.cs.princeton.edu
 *
 *
 * algs4.jar is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * algs4.jar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with algs4.jar. If not, see http://www.gnu.org/licenses.
 ******************************************************************************/
