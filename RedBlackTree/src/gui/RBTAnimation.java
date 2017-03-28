package gui;

import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import redblacktree.Node;
import redblacktree.RBTree;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class RBTAnimation<K extends Comparable<K>, V> extends Application {
	RBTree tree = new RBTree<>(); // Create a tree
	BTView view = new BTView(tree); // Create a View

	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {

		BorderPane pane = new BorderPane();
		pane.setCenter(view);

		TextField tfKey = new TextField("K, V");
		tfKey.setPrefColumnCount(3);
		tfKey.setAlignment(Pos.BASELINE_RIGHT);
		Button btReset = new Button("Reset");
		Button btInsert = new Button("Insert");
		Button btPreorder = new Button("Preorder");
		Button btInorder = new Button("Inorder");
		Button btPostorder = new Button("Postorder");
		Button btBreadthFirst = new Button("Breadth-First");

		HBox hBox = new HBox(5);
		hBox.setStyle("-fx-background: #000000;");
		Label l = new Label("Enter a key, value: ");
		l.setStyle("-fx-text-fill: #000000;");
		hBox.getChildren().addAll(l, tfKey, btInsert, btPreorder, btInorder, btPostorder, btBreadthFirst, btReset);
		hBox.setAlignment(Pos.CENTER);
		pane.setBottom(hBox);

		btReset.setOnAction(e -> {
			tree = new RBTree();
			view = new BTView(tree); // Create a View
			view.displayTree();
			view.setStatus("reset");
			pane.setCenter(view);
		});

		btInsert.setOnAction(e -> {
			List<String> vals = Arrays.asList(tfKey.getText().split(","));

			String key = vals.get(0).trim();
			int intkey = 0;
			String value = vals.get(1).trim();
			boolean isNumeric = key.chars().allMatch(Character::isDigit);
			if (isNumeric){
				intkey = Integer.parseInt(vals.get(0).trim());
			}
			
			
			if (isNumeric) {
				if (tree.find(intkey)) { // key is in the tree already
					view.displayTree();
					view.setStatus(key + " is already in the tree");
				} else {
					tree.insert(intkey, value); // Insert a new key
					view.displayTree();
					view.setStatus(key + " is inserted in the tree");
				}
			} else {
				if (tree.find(key)) { // key is in the tree already
					view.displayTree();
					view.setStatus(key + " is already in the tree");
				} else {
					tree.insert(key, value); // Insert a new key
					view.displayTree();
					view.setStatus(key + " is inserted in the tree");
				}
			}
		});

		btPreorder.setOnAction(e -> {
			List<Object> preorderList = tree.preorder();
			Alert dialog = new Alert(AlertType.INFORMATION);
			dialog.setTitle("Preorder List");
			dialog.setHeaderText("Preorder traversal");

			StringBuilder builder = new StringBuilder();
			for (Object node : preorderList) {
				builder.append(node + ", ");
			}
			dialog.setContentText(builder.toString().substring(0, builder.length() - 2));
			dialog.showAndWait();
		});

		btInorder.setOnAction(e -> {
			List<Object> preorderList = tree.inorder();
			Alert dialog = new Alert(AlertType.INFORMATION);
			dialog.setTitle("Preorder List");
			dialog.setHeaderText("Preorder traversal");

			StringBuilder builder = new StringBuilder();
			for (Object node : preorderList) {
				builder.append(node + ", ");
			}
			dialog.setContentText(builder.toString().substring(0, builder.length() - 2));
			dialog.showAndWait();
		});

		btPostorder.setOnAction(e -> {
			List<Object> preorderList = tree.postorder();
			Alert dialog = new Alert(AlertType.INFORMATION);
			dialog.setTitle("Preorder List");
			dialog.setHeaderText("Preorder traversal");

			StringBuilder builder = new StringBuilder();
			for (Object node : preorderList) {
				builder.append(node + ", ");
			}
			dialog.setContentText(builder.toString().substring(0, builder.length() - 2));
			dialog.showAndWait();
		});

		btBreadthFirst.setOnAction(e -> {
			List<Object> preorderList = tree.breadthFirst();
			Alert dialog = new Alert(AlertType.INFORMATION);
			dialog.setTitle("Preorder List");
			dialog.setHeaderText("Preorder traversal");

			StringBuilder builder = new StringBuilder();
			for (Object node : preorderList) {
				builder.append(node + ", ");
			}
			dialog.setContentText(builder.toString().substring(0, builder.length() - 2));
			dialog.showAndWait();
		});

		// Create a scene and place the pane in the stage
		Scene scene = new Scene(pane, 600, 250);
		pane.setStyle("-fx-background: #e0d1ff;");
		primaryStage.setTitle("Red Black Tree"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage
	}

	/**
	 * The main method is only needed for the IDE with limited JavaFX support.
	 * Not needed for running from the command line.
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
