package com.mycompany.csc229_bst_example;

public class BinarySearchTree {
    private BstNode root;

    public boolean isEmpty() {
        return this.root == null;
    }

    public void insert(Integer data) {
        System.out.print("[input: " + data + "]");
        if (root == null) {
            this.root = new BstNode(data);
            System.out.println(" -> inserted: " + data);
            return;
        }
        insertNode(this.root, data);
        System.out.print(" -> inserted: " + data);
        System.out.println();
    }

    private BstNode insertNode(BstNode root, Integer data) {
        BstNode tmpNode = null;
        System.out.print(" ->" + root.getData());
        if (root.getData() >= data) {
            System.out.print(" [L]");
            if (root.getLeft() == null) {
                root.setLeft(new BstNode(data));
                return root.getLeft();
            } else {
                tmpNode = root.getLeft();
            }
        } else {
            System.out.print(" [R]");
            if (root.getRight() == null) {
                root.setRight(new BstNode(data));
                return root.getRight();
            } else {
                tmpNode = root.getRight();
            }
        }
        return insertNode(tmpNode, data);
    }

    // start the in order thing from the root
    public void inOrderTraversal() {
        doInOrder(this.root);
        System.out.println();
    }

    // in order = go left, print current, then go right
    private void doInOrder(BstNode node) {
        if (node == null) {
            return; // nothing here so just stop
        }
        doInOrder(node.getLeft());            // hit all the left stuff first
        System.out.print(node.getData() + " "); // then print this node
        doInOrder(node.getRight());           // then go to the right side
    }

    // start pre order from the root
    public void preOrderTraversal() {
        doPreOrder(this.root);
        System.out.println();
    }

    // pre order = print now, then left, then right
    private void doPreOrder(BstNode node) {
        if (node == null) {
            return;
        }
        System.out.print(node.getData() + " "); // print this one first
        doPreOrder(node.getLeft());             // then all left side
        doPreOrder(node.getRight());            // then all right side
    }

    // just calls helper to get height of whole tree
    public Integer findHeight() {
        return height(root);
    }

    // height = how long the longest path is from this node down
    private int height(BstNode node) {
        if (node == null) {
            return 0; // no nodes here so height is 0
        }
        int leftH = height(node.getLeft());   // height of left side
        int rightH = height(node.getRight()); // height of right side
        if (leftH > rightH) {
            return leftH + 1; // pick bigger side and add this node
        } else {
            return rightH + 1;
        }
    }

    // finds how deep a node is (root is 0, its child is 1, etc)
    public int getDepth(BstNode node) {
        if (node == null || root == null) {
            return -1;
        }
        BstNode cur = root;
        int depth = 0;

        // just walk down the tree using the value
        while (cur != null) {
            if (node.getData().equals(cur.getData())) {
                return depth; // we found it
            }
            if (node.getData() <= cur.getData()) {
                cur = cur.getLeft(); // go left if value is smaller
            } else {
                cur = cur.getRight(); // go right if value is bigger
            }
            depth++; // went one level down
        }
        return -1; // not found in the tree
    }

    // prints the tree kinda sideways so u can see the shape
    public void print() {
        System.out.println("\n==== BST Print ===== \n");
        print("", root, false);
    }

    // prefix is just spaces so kids show under their parent
    private void print(String prefix, BstNode node, boolean isLeft) {
        if (node == null) {
            return;
        }
        if (prefix.isEmpty()) {
            System.out.println(node.getData()); // this is the root
        } else {
            if (isLeft) {
                System.out.println(prefix + "l-- " + node.getData()); // left child
            } else {
                System.out.println(prefix + "r-- " + node.getData()); // right child
            }
        }
        print(prefix + "   ", node.getLeft(), true);   // print left side
        print(prefix + "   ", node.getRight(), false); // print right side
    }
}
