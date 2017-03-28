package redblacktree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class RBTree<K extends Comparable<K>, V> {

	public Node<K, V> root = null;

	protected final Node<K, V> nil_Leaf = new Node<>(null, null, 'B');

	public int size = 0;

	public RBTree() {

	}

	public void insert(K key, V value) {
		insertByKey(key, value);

	}

	protected boolean insertByKey(K key, V value) {// should be private
		Node<K, V> newNode = new Node<K, V>(key, value, 'R'); // all nodes start
																// off as red
		newNode.leftChild = nil_Leaf;
		newNode.rightChild = nil_Leaf;

		if (root == null) {// if tree is empty create the node for Node<K,V> as
							// the root;

			root = newNode;
			root.setColor('B');
		} else {
			Node<K, V> parent = null;
			Node<K, V> current = root;

			while (current.key != null && current.value != null) {
				if (key.compareTo(current.key) < 0) {
					parent = current;
					current = current.leftChild;
				} else if (key.compareTo(current.key) > 0) {
					parent = current;
					current = current.rightChild;
				} else {
					return false;
				}
			}

			if (key.compareTo(parent.key) < 0) {
				parent.leftChild = newNode;
				newNode.parent = parent;
			} else {
				parent.rightChild = newNode;
				newNode.parent = parent;
			}

		}
		checkCases(newNode);
		this.size++;

		return true;

	}

	protected void checkCases(Node<K, V> node) {
		if (node == this.root) { // case 1
			node.setColor('B');
			return;
		}
		if (node.parent.color == 'B') { // case 2
			if (node.leftChild != this.nil_Leaf && node.rightChild != this.nil_Leaf) {
				if (node.leftChild.color != 'R' && node.rightChild.color != 'R' && node.parent.color == 'B') {
					node.color = 'R';
				}
			}
			return;
		}

		if (node.parent.color == 'R' && getUncleNode(node).color == 'R') { // case
																			// //
																			// 3
			node.parent.color = 'B';
			getUncleNode(node).color = 'B';
			getGrandParentNode(node).color = 'B';
			checkCases(getGrandParentNode(node));
			return;
		}

		if (node.parent.color == 'R' && getUncleNode(node).color == 'B') {// case
																			// //
																			// 4
			if (node == node.parent.rightChild && node.parent == getGrandParentNode(node).leftChild) {
				leftRotate(node); // node is pivot, method gets root as parent
				node = node.leftChild;
			} else if (node == node.parent.leftChild && node.parent == getGrandParentNode(node).rightChild) {
				rightRotate(node);
				node = node.rightChild;
			}
		}

		if (node.parent.color == 'R' && getUncleNode(node).color == 'B') { // case
																			// 5
																			// //
																			// 5
			if (node == node.parent.leftChild && node.parent == getGrandParentNode(node).leftChild) {
				node.parent.color = 'B';
				getGrandParentNode(node).color = 'R';
				rightRotate(node.parent);
			} else if (node == node.parent.rightChild && node.parent == getGrandParentNode(node).rightChild) {
				node.parent.color = 'B';
				getGrandParentNode(node).color = 'R';
				leftRotate(node.parent);
			}

		}

	}

	protected Node<K, V> getGrandParentNode(Node<K, V> node) {// protected for
																// unit test,
																// otherwise
																// private
		if (node.parent.parent != null) {
			return node.parent.parent;
		}

		return null;

	}

	protected Node<K, V> getUncleNode(Node<K, V> node) {// protected for unit
														// test, otherewise
														// private

		if (node.parent.parent.rightChild == node.parent) {
			return node.parent.parent.leftChild;
		} else if (node.parent.parent.leftChild == node.parent) {
			return node.parent.parent.rightChild;
		} else {
			return null;
		}
	}

	protected void leftRotate(Node<K, V> pivot) {// protected for unit tests,
													// otherwise private
		Node<K, V> root = pivot.parent;
		Node<K, V> leftChild = pivot.leftChild;

		root.rightChild = leftChild;// now right child of root
		leftChild.parent = root;// link with new parent

		pivot.parent = getGrandParentNode(pivot);// get grandpa node
													// (potentially null value)

		if (pivot.parent != null) { // Link grandparent to pivot depending on
									// root position
			if (pivot.parent.rightChild == root) {
				pivot.parent.rightChild = pivot;
			} else if (pivot.parent.leftChild == root) {
				pivot.parent.leftChild = pivot;
			}
		}

		root.parent = pivot;

		pivot.leftChild = root;

		if (root == this.root) {
			this.root = pivot;
		}
	}

	protected void rightRotate(Node<K, V> pivot) {// protected for unit tests,
													// otherwise private
		Node<K, V> root = pivot.parent;
		root.leftChild = pivot;
		Node<K, V> rightChild = pivot.rightChild;
		rightChild.parent = pivot;

		root.leftChild = rightChild;
		rightChild.parent = root;

		pivot.parent = getGrandParentNode(pivot);
		if (pivot.parent != null) {
			if (pivot.parent.rightChild == root) {
				pivot.parent.rightChild = pivot;
			} else if (pivot.parent.leftChild == root) {
				pivot.parent.leftChild = pivot;
			}

		}

		root.parent = pivot;

		pivot.rightChild = root;

		if (root == this.root) {
			this.root = pivot;
		}
	}

	public boolean find(K key) {
		if (root != null) {
			Node<K, V> current = root;

			while (current.key != null) {
				if (key.compareTo(current.key) < 0) {
					current = current.leftChild;
				} else if (key.compareTo(current.key) > 0) {
					current = current.rightChild;
				} else if (key.compareTo(current.key) == 0) {
					return true;
				}
			}
		}
		return false;
	}

	public List<Node<K, V>> preorder() {
		List<Node<K, V>> preorderList = new ArrayList<>();
		Stack<Node<K, V>> stack = new Stack<Node<K, V>>();
		Node<K, V> curr = root;

		while (curr != nil_Leaf && curr != null) {
			preorderList.add(curr);

			if (curr.rightChild != nil_Leaf) {
				stack.push(curr.rightChild);
			}

			if (curr.leftChild != nil_Leaf) {
				curr = curr.leftChild;
			} else if (!stack.isEmpty()) {
				curr = (Node<K, V>) stack.pop();
			} else {
				curr = null;
			}

		}
		return preorderList;
	}

	public List<Node<K, V>> inorder() {
		List<Node<K, V>> inorderList = new ArrayList<>();
		Stack<Node<K, V>> stack = new Stack<Node<K, V>>();
		Node<K, V> curr = this.root;

		while (!stack.empty() || curr != nil_Leaf && curr != null) {
			if (curr != nil_Leaf) {
				stack.push(curr);
				curr = curr.leftChild;
			} else {
				if (!stack.empty()) {
					curr = stack.pop();
				}
				inorderList.add(curr);
				curr = curr.rightChild;
			}
		}

		return inorderList;

	}

	public List<Node<K, V>> postorder() {
		List<Node<K, V>> postorderList = new ArrayList<>();
		Stack<Node<K, V>> stack = new Stack<Node<K, V>>();
		Node<K, V> curr = this.root;

		Set<Node<K, V>> visited = new HashSet<>();

		stack.push(curr);

		while (!stack.empty()) {
			curr = stack.peek();

			if (curr.leftChild != nil_Leaf && !visited.contains(curr.leftChild)) {
				stack.push(curr.leftChild);
			} else if (curr.rightChild != nil_Leaf && !visited.contains(curr.rightChild)) {
				stack.push(curr.rightChild);
			} else {
				postorderList.add(curr);
				visited.add(curr);
				stack.pop();
			}
		}
		return postorderList;
	}

	public List<Node<K, V>> breadthFirst() {
		List<Node<K, V>> breadthFirstList = new ArrayList<>();
		Node<K, V> curr = this.root;

		List<Node<K, V>> level = new ArrayList<>();
		breadthFirstList.add(curr);
		level.add(curr);

		while (level.size() > 0) {
			List<Node<K, V>> level1 = new ArrayList<>();

			for (Node<K, V> n : level) {
				if (n.leftChild != nil_Leaf) {
					level1.add(n.leftChild);
					breadthFirstList.add(n.leftChild);
				}

				if (n.rightChild != nil_Leaf) {
					level1.add(n.rightChild);
					breadthFirstList.add(n.rightChild);
				}

			}

			level = level1;
		}

		return breadthFirstList;
	}

}
