package gui;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import redblacktree.Node;
import redblacktree.RBTree;

public class BTView <K extends Comparable<K>, V>extends Pane {
	private RBTree<Integer, String> tree = new RBTree<>();
	private double radius = 15; // Tree node radius
	private double vGap = 50; // Gap between two levels in a tree

	BTView(RBTree<Integer, String> tree) {
		this.tree = tree;
		setStatus("Tree is empty");
	}

	public void setStatus(String msg) {
		getChildren().add(new Text(20, 20, msg));
	}

	public void displayTree() {
		this.getChildren().clear(); // Clear the pane
		if (tree.root != null) {
			// Display tree recursively
			displayTree(tree.root, getWidth() / 2, vGap, getWidth() / 4);
		}
	}

	/** Display a subtree rooted at position (x, y) */
	private void displayTree(Node<Integer, String> root, double x, double y, double hGap) {
		if (root.leftChild != null) {
			// Draw a line to the left node
			getChildren().add(new Line(x - hGap, y + vGap, x, y));
			// Draw the left subtree recursively
			displayTree(root.leftChild, x - hGap, y + vGap, hGap / 2);
		}

		if (root.rightChild != null) {
			// Draw a line to the right node
			getChildren().add(new Line(x + hGap, y + vGap, x, y));
			// Draw the right subtree recursively
			displayTree(root.rightChild, x + hGap, y + vGap, hGap / 2);
		}

		// Display a node
		Circle circle = new Circle(x, y, radius);
		Text circleText = new Text(x - 4, y + 4, root.key + "");
		if (root.color == 'B') {
			circle.setFill(Color.BLACK);
			circle.setStroke(Color.BLACK);
			circleText.setFill(Color.WHITE);
		} else if (root.color == 'R') {
			circle.setFill(Color.RED);
			circle.setStroke(Color.BLACK);
			circleText.setFill(Color.WHITE);
		}
		getChildren().addAll(circle, circleText);
	}
}