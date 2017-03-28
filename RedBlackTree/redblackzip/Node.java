package redblacktree;

public class Node<K extends Comparable<K>, V> {
	public K key;
	public V value;
	public char color;
	
	public Node<K,V> leftChild = null;
	public Node<K,V> rightChild = null;
	public Node<K,V> parent = null;
	
	protected Node(K key, V value, char color) throws IllegalArgumentException{
		if (!(color == 'R' || color == 'B')){
			throw new IllegalArgumentException("Color must be R or B");
		}
		
		this.key = key;
		this.value = value;
		this.color = color;
	}
	
	public void setKey(K key){
		this.key = key;
	}
	public K getKey(){
		return key;
	}
	
	public void setValue(V value){
		this.value = value;
	}
	public V getValue(){
		return value;
	}
	
	public void setColor(char color) throws IllegalArgumentException{
		if (!(color == 'R' || color == 'B')){
			throw new IllegalArgumentException("Color must be R or B");
		}
		this.color = color;
	}
	public char getColor(){
		return color;
	}
	
	public String toString(){
		return key + " " + value;
	}
	
	
	
	
	
	
	
}
