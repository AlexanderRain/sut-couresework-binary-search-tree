package coursework.controller;

import coursework.model.BST;
import coursework.view.BTView;

import java.util.List;

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
            view.recreateStatus(key + " is already in the tree");
        } else {
            tree.insert(key); // Insert a new key
            view.displayTree();
            view.recreateStatus(key + " is inserted in the tree");
        }
    }

    public void OnDelete(int key) {
        view.stopAnimation();
        if (!tree.search(key)) { // key is not in the tree
            view.displayTree();
            view.recreateStatus(key + " is not in the tree");
        } else {
            tree.delete(key); // Delete a key
            view.displayTree();
            view.recreateStatus(key + " is deleted from the tree");
        }
    }

    public void OnInorder() {
        view.stopAnimation();
        List<Integer> traversal = tree.inorder();
        view.setStatus("Inorder traversal: " + traversal.toString());
        view.animateTraversal(traversal);
    }

    public void OnPreorder() {
        view.stopAnimation();
        List<Integer> traversal = tree.preorder();
        view.setStatus("Preorder traversal: " + traversal.toString());
        view.animateTraversal(traversal);
    }

    public void OnPostorder() {
        view.stopAnimation();
        List<Integer> traversal = tree.postorder();
        view.setStatus("Postorder traversal: " + traversal.toString());
        view.animateTraversal(traversal);
    }

    public void InvalidInput(String msg) {
        view.setStatus(msg);
    }
}
