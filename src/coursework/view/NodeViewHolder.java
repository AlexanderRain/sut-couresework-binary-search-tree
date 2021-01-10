package coursework.view;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
/** Vilkhovyk OO*/

public class NodeViewHolder<E> {

    private Circle treeNode = new Circle();
    private Text nodeText = new Text();
    private double radius = 15; // Tree node radius

    public NodeViewHolder() {}

    public void createNode(ObservableList<Node> root, double x, double y, E nodeElement) {
        // Display a node
        treeNode = new Circle(x, y, radius);

        // set default state to tree node
        defaultState();

        nodeText = new Text(x - 2 - (nodeElement.toString().length()*2), y + 4, nodeElement.toString());
        root.addAll(treeNode, nodeText);
    }

    public void markAsRed() {
        treeNode.setFill(Color.RED);
    }

    public void markAsCurrent() {
        treeNode.setStrokeWidth(3f);
        treeNode.setStroke(Color.RED);
    }

    public void markAsVisited() {
        treeNode.setStrokeWidth(1f);
        treeNode.setStroke(Color.GRAY);
        nodeText.setFill(Color.GRAY);
    }

    public void defaultState() {
        treeNode.setStroke(Color.BLACK);
        treeNode.setFill(Color.WHITE);
        treeNode.setStrokeWidth(1f);
        nodeText.setFill(Color.BLACK);
    }
}
