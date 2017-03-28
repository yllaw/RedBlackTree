package redblacktree;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;


public class RedBlackTreeTests {

	@Test
	public void Node_constructorTest() {
		//Arrange
		Integer key = 1;
		String name = "Marco";
		char nodeColor = 'B';
		
		
		//Act
		Node<Integer, String> n1 = new Node<>(key, name, nodeColor);
		Node<Double, Character> n2 = new Node<>(2.0, 'H', 'R');
		
		//Assert
		assertEquals(key, n1.getKey());
		assertEquals(name, n1.getValue());
		assertEquals(nodeColor, n1.getColor());
	
		
		assertEquals(Double.valueOf(2.0), n2.key);
		assertEquals(Character.valueOf('H'), n2.value);
		assertEquals('R', n2.color);
	}
	
	@Test
	public void Node_illegalArgumentTest(){
		// Arrange
		int key = 1;
		String name = "Marco";
		char color = 'P';

		// Act
		try {
			Node n = new Node(key, name, color);
			fail("Expected exception");
		} // Assert
		catch (IllegalArgumentException e) {
			assertEquals("Color must be R or B", e.getMessage());
		}

	}
	
	@Test
	public void RBTree_getGrandParentNodeTest(){
		//Arrange
		RBTree tree = new RBTree();
		
		Node G = new Node(5, "Marco", 'R');
		Node P= new Node(3, "John", 'B');
		Node C = new Node(4, "Walter", 'R');

		tree.root = G;
		
		tree.root.leftChild = P;
		P.parent = tree.root;
		P.rightChild = C;
		C.parent = P;
		
		
	
		//Act
		Node testVal = tree.getGrandParentNode(C);
		
		
		//Assert
		assertEquals(G, testVal);
	}
	
	
	@Test
	public void RBTree_getUncleNodeTest(){
		//Arrange
		RBTree tree = new RBTree();
		
		Node G = new Node(7, "Marco", 'R');
		Node LU= new Node(5, "John", 'B');
		Node RU = new Node(8, "David", 'R');
		Node LU_LC = new Node(6, "Smith", 'R');
		Node LU_RC = new Node(2, "Walter", 'R');
		
		tree.root = G;
		
		tree.root.leftChild = LU;
		tree.root.rightChild = RU;
		LU.parent = tree.root;
		RU.parent = tree.root;
		
		LU.rightChild = LU_RC;
		LU_RC.parent = LU;
		
		LU.leftChild = LU_LC;
		LU_LC.parent = LU;
		
		
	
		//Act
		Node testVal = tree.getUncleNode(LU_RC);
		
		
		//Assert
		assertEquals(RU, testVal);
	}
	
	
	@Test
	public void RBTree_leftRotationTest(){
		//Arrange
		RBTree tree = new RBTree();
		
		Node grandparent = new Node(11, "Adam", 'R');
		Node root = new Node(7, "Marco", 'R');
		Node pivot= new Node(9, "John", 'B');
		Node leftUncle = new Node(6, "David", 'R');
		Node leftChildOfPivot = new Node(8, "Smith", 'R');
		Node rightChildOfPivot = new Node(10, "Walter", 'R');
		
		grandparent.leftChild = root;
		root.parent = grandparent;
		
		root.rightChild = pivot;
		pivot.parent = root;
		
		root.leftChild = leftUncle;
		leftUncle.parent = root;
		
		
		pivot.rightChild = rightChildOfPivot;
		rightChildOfPivot.parent = pivot;
		
		pivot.leftChild = leftChildOfPivot;
		leftChildOfPivot.parent = pivot;
		
		
		//Act
		tree.leftRotate(pivot);
		
		
		//Assert
		assertEquals(pivot,root.parent);
		//assertEquals(null, pivot.parent); //testing for null grandparent
		
		assertEquals(leftChildOfPivot ,root.rightChild);
		assertEquals(root, leftChildOfPivot.parent);
		
		assertEquals(leftUncle, root.leftChild);
		assertEquals(root, leftUncle.parent);
		
		assertEquals(rightChildOfPivot, pivot.rightChild);
		assertEquals(pivot, rightChildOfPivot.parent);
		
		assertEquals(pivot, grandparent.leftChild);
		assertEquals(grandparent, pivot.parent);
							
	}
	
	@Test
	public void RBTree_rightRotationTest(){
		//Arrange
		RBTree tree = new RBTree();
		
		Node grandparent = new Node(11, "Adam", 'R');
		Node root = new Node(7, "Marco", 'R');
		Node pivot= new Node(5, "John", 'B');
		Node rightUncle = new Node(9, "David", 'R');
		Node leftChildOfPivot = new Node(3, "Smith", 'R');
		Node rightChildOfPivot = new Node(6, "Walter", 'R');
		
		grandparent.leftChild = root;
		root.parent = grandparent;
		
				
		root.leftChild = pivot;
		pivot.parent = root;
		
		root.rightChild = rightUncle;
		rightUncle.parent = root;
		
		
		pivot.rightChild = rightChildOfPivot;
		rightChildOfPivot.parent = pivot;
		
		pivot.leftChild = leftChildOfPivot;
		leftChildOfPivot.parent = pivot;
		
		
		//Act
		tree.rightRotate(pivot);
		
		
		//Assert
		assertEquals(pivot,root.parent);
		//assertEquals(null, pivot.parent); //testing for null grandparent
		
		assertEquals(rightChildOfPivot ,root.leftChild);
		assertEquals(root, rightChildOfPivot.parent);
		
		assertEquals(rightUncle, root.rightChild);
		assertEquals(root, rightUncle.parent);
		
		assertEquals(leftChildOfPivot, pivot.leftChild);
		assertEquals(pivot, leftChildOfPivot.parent);
		
		assertEquals(pivot, grandparent.leftChild);
		assertEquals(grandparent, pivot.parent);			
	}
	
	
	@Test
	public void RBTree_insertTest(){
		//Arrange
		RBTree tree = new RBTree();
		
		Node grandparent = new Node(11, "Adam", 'R');
		Node root = new Node(7, "Marco", 'R');
		Node pivot= new Node(5, "John", 'R');
		Node rightUncle = new Node(9, "David", 'R');
		Node leftChildOfPivot = new Node(3, "Smith", 'R');
		Node rightChildOfPivot = new Node(6, "Walter", 'R');
		Node leftestNode = new Node(2, "James", 'R');
		
		//Act + Assert
//		tree.insert(11, "Adam");
//		tree.insert(7, "Marco");
//		assertEquals(root.toString(), tree.root.leftChild.toString());
//		tree.insert(5, "John");
//		assertEquals(pivot.toString(), tree.root.leftChild.toString());
//		assertEquals(grandparent.toString(), tree.root.rightChild.toString());
//		tree.insert(9, "David");
//		assertEquals(rightUncle.toString(), tree.root.rightChild.leftChild.toString());
//		assertEquals('B', tree.root.rightChild.color);
//		tree.insert(3, "Smith");
//		assertEquals(leftChildOfPivot.toString(), tree.root.leftChild.leftChild.toString());
//		assertEquals('R', tree.root.leftChild.leftChild.color);
//		tree.insert(6, "Walter");
//		assertEquals(rightChildOfPivot.toString(), tree.root.leftChild.rightChild.toString());
//		assertEquals('R', tree.root.leftChild.rightChild.color);
//		tree.insert(2, "James");
//		assertEquals('B', tree.root.leftChild.leftChild.color);
//		assertEquals('B', tree.root.leftChild.rightChild.color);
//     	assertEquals('R', tree.root.leftChild.getColor());
//		assertEquals('R', tree.root.leftChild.leftChild.leftChild.color);
		
		tree.insert(11, "Adam");
		tree.insert(7, "Adam");
		tree.insert(5, "Adam");
		tree.insert(4, "Adam");
		tree.insert(3, "Adam");


		tree.insert(13, "Adam");
		tree.insert(16, "Adam");
		

		
		
	}
	
	
	@Test
	public void RBTree_preorderTest(){
		//Arrange
		RBTree tree = new RBTree();

		
		tree.insert(11, "Adam");
		tree.insert(15, "Marco");
		tree.insert(7, "John");
		tree.insert(6, "Wally");
		tree.insert(5, "Kobe");
		tree.insert(14, "Smith");
		tree.insert(16, "Harold");

		
		
		//Act
		List<Node<Integer, String>> results = tree.preorder(); 
		
		//Assert
		assertEquals("11", results.get(0).key.toString());
		assertEquals("6", results.get(1).key.toString());
		assertEquals("5", results.get(2).key.toString());
		assertEquals("7", results.get(3).key.toString());
		assertEquals("15", results.get(4).key.toString());
		assertEquals("14", results.get(5).key.toString());
		assertEquals("16", results.get(6).key.toString());

		
		
	}
	
	@Test
	public void RBTree_inorderTest(){
		//Arrange
		RBTree tree = new RBTree();
		
		tree.insert(11, "Adam");
		tree.insert(7, "Marco");
		tree.insert(5, "John");
		tree.insert(3, "Walter");
		tree.insert(4, "Harold");
		tree.insert(10, "Kobe");
		tree.insert(9, "Lebron");
		
		//Act
		List<Node<Integer, String>> results = tree.inorder(); 
		
		//Assert
		assertEquals("3", results.get(0).key.toString());
		assertEquals("4", results.get(1).key.toString());
		assertEquals("5", results.get(2).key.toString());
		assertEquals("7", results.get(3).key.toString());
		assertEquals("9", results.get(4).key.toString());
		assertEquals("10", results.get(5).key.toString());
		assertEquals("11", results.get(6).key.toString());

	}
	
	@Test
	public void RBTree_postorderTest(){
		//Arrange
		RBTree tree = new RBTree();
		
		tree.insert(11, "Adam");
		tree.insert(7, "Marco");
		tree.insert(5, "John");
		tree.insert(3, "Walter");
		tree.insert(4, "Harold");
		tree.insert(10, "Kobe");
		tree.insert(9, "Lebron");
		
		//Act
		List<Node<Integer, String>> results = tree.postorder(); 
		
		//Assert
		assertEquals("3", results.get(0).key.toString());
		assertEquals("5", results.get(1).key.toString());
		assertEquals("4", results.get(2).key.toString());
		assertEquals("9", results.get(3).key.toString());
		assertEquals("11", results.get(4).key.toString());
		assertEquals("10", results.get(5).key.toString());
		assertEquals("7", results.get(6).key.toString());

	}
	
	
	@Test
	public void RBTree_breadthFirstTest(){
		//Arrange
		RBTree tree = new RBTree();
		
		tree.insert(11, "Adam");
		tree.insert(7, "Marco");
		tree.insert(5, "John");
		tree.insert(3, "Walter");
		tree.insert(4, "Harold");
		tree.insert(10, "Kobe");
		tree.insert(9, "Lebron");
		
		//Act
		List<Node<Integer, String>> results = tree.breadthFirst(); 
		
		//Assert
		assertEquals("7", results.get(0).key.toString());
		assertEquals("4", results.get(1).key.toString());
		assertEquals("10", results.get(2).key.toString());
		assertEquals("3", results.get(3).key.toString());
		assertEquals("5", results.get(4).key.toString());
		assertEquals("9", results.get(5).key.toString());
		assertEquals("11", results.get(6).key.toString());

	}
	
	
}
