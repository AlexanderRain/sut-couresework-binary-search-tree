package coursework.controller;

import coursework.model.BST;
import coursework.view.BTView;

public class MainController {

    private BTView view;
    private BST<Integer> tree;

    public MainController(BTView view, BST<Integer> tree) {
        this.view = view;
        this.tree = tree;
    }

    public void OnInsert(int key) {
        view.stopAnimation();
        if (tree.search(key)) { // key is in the tree already
            view.displayTree();
            view.setStatus(key + " is already in the tree");
        } else {
            tree.insert(key); // Insert a new key
            view.displayTree();
            view.setStatus(key + " is inserted in the tree");
        }
    }

    public void OnDelete(int key) {
        view.stopAnimation();
        if (!tree.search(key)) { // key is not in the tree
            view.displayTree();
            view.setStatus(key + " is not in the tree");
        } else {
            tree.delete(key); // Delete a key
            view.displayTree();
            view.setStatus(key + " is deleted from the tree");
        }
    }

    public void OnInorder() {
        view.stopAnimation();
        view.animateTraversal(tree.inorder());
    }

    public void OnPreorder() {
        view.stopAnimation();
        view.animateTraversal(tree.preorder());
    }

    public void OnPostrder() {
        view.stopAnimation();
        view.animateTraversal(tree.postorder());
    }
}
