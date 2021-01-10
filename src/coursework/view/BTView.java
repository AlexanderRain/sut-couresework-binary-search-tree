package coursework.view;

import coursework.model.BST;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/** Vilkhovyk OO*/

public class BTView extends Pane {
    private BST<Integer> tree = new BST<>();
    private Text text;
    // Keeps all instances of displayed tree nodes
    private Map<Integer, NodeViewHolder<Integer>> treeViewCash = new HashMap<>(8);
    private Thread animationThread;
    private double vGap = 50; // Gap between two levels in a tree

    public BTView(BST<Integer> tree) {
        this.tree = tree;
        recreateStatus("Tree is empty");
    }

    public void recreateStatus(String msg) {
        text = new Text(20, 20, msg);
        getChildren().add(text);
    }

    public void setStatus(String error) {
        text.setText(error);
    }

    public void displayTree() {
        this.getChildren().clear(); // Clear the pane
        treeViewCash.clear();
        if (tree.getRoot() != null) {
            // Display tree recursively
            displayTree(tree.getRoot(), getWidth() / 2, vGap,
                    getWidth() / 4);
        }
    }

    /** Display a subtree rooted at position (x, y) */
    private void displayTree(BST.TreeNode<Integer> root, double x, double y, double hGap) {
        if (root.getLeft() != null) {
            // Draw a line to the left node
            getChildren().add(new Line(x - hGap, y + vGap, x, y));
            // Draw the left subtree recursively
            displayTree(root.getLeft(), x - hGap, y + vGap, hGap / 2);
        }

        if (root.getRight() != null) {
            // Draw a line to the right node
            getChildren().add(new Line(x + hGap, y + vGap, x, y));
            // Draw the right subtree recursively
            displayTree(root.getRight(), x + hGap, y + vGap, hGap / 2);
        }

        // create and init view holder for tree node
        NodeViewHolder<Integer> viewHolder = new NodeViewHolder<>();
        viewHolder.createNode(getChildren(), x, y, root.getElement());
        // put view holder to view cash using element value as a kay
        treeViewCash.put(root.getElement(), viewHolder);
    }

    public void animateTraversal(List<Integer> animatedItems) {
        stopAnimation();
        animationThread = new Thread(() -> {
            try {
                for(Integer key : animatedItems) {
                    // highlight current item
                    treeViewCash.get(key).markAsCurrent();
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    treeViewCash.get(key).markAsVisited();
                }

                treeViewCash.forEach((key, viewHolder) -> {
                    viewHolder.markAsRed();
                });
                Thread.sleep(1000);
                treeViewCash.forEach((key, viewHolder) -> {
                    viewHolder.defaultState();
                });
            } catch (Exception e) {
                treeViewCash.forEach((key, viewHolder) -> {
                    viewHolder.defaultState();
                });
            }
        });
        animationThread.start();
    }

    public void stopAnimation() {
        if(animationThread != null) {
            animationThread.interrupt();
        }
    }
}