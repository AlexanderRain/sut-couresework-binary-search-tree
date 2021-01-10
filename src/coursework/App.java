package coursework;

import coursework.controller.MainController;
import coursework.model.BST;
import coursework.view.BTView;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        BST<Integer> tree = new BST<>(); // Create a tree
        BTView view = new BTView(tree); // Create a View
        MainController controller = new MainController(view, tree);

        BorderPane pane = new BorderPane();
        pane.setCenter(view);

        TextField tfKey = new TextField();
        tfKey.setPrefColumnCount(3);
        tfKey.setAlignment(Pos.BASELINE_RIGHT);
        Button insert = new Button("Insert");
        Button delete = new Button("Delete");
        Button inorder = new Button("Inorder");
        Button preorder = new Button("Preorder");
        Button postorder = new Button("Postorder");
        HBox hBox = new HBox(5);
        hBox.getChildren().addAll(new Label("Enter a key: "),
                tfKey, insert, delete, inorder, preorder, postorder);
        hBox.setAlignment(Pos.CENTER);
        pane.setBottom(hBox);

        insert.setOnAction(e -> {
            try {
                int key = Integer.parseInt(tfKey.getText());
                controller.OnInsert(key);
            } catch (NumberFormatException exception) {
                controller.InvalidInput("Invalid input " + exception);
            }
        });

        delete.setOnAction(e -> {
            try {
                int key = Integer.parseInt(tfKey.getText());
                controller.OnDelete(key);
            } catch (NumberFormatException exception) {
                controller.InvalidInput("Invalid input " + exception);
            }
        });
        inorder.setOnAction(e -> controller.OnInorder());
        preorder.setOnAction(e -> controller.OnPreorder());
        postorder.setOnAction(e -> controller.OnPostorder());

        // Create a scene and place the pane in the stage
        Scene scene = new Scene(pane, 800, 600);
        primaryStage.setTitle("Coursework by Aleksandr Vilkhovyk CSD-21");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
