package model;

import javafx.scene.shape.Rectangle;

public class Node {
	private Rectangle rect;
	private Node next;
	
	public Node(Rectangle rect) {
		this.rect = rect;
		this.next = null;
	}
	
	public Rectangle getRectangle() {
		return rect;
	}
	
	public Node getNext() {
		return next;
	}
	
	public void setNext(Node next) {
		this.next = next;
	}
}
