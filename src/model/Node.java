package model;

import javafx.scene.shape.Rectangle;

/**
 * The Node class are used to indicate paths for the enemies to take. A linked list is 
 * created to keep track of the nodes.
 * 
 * @author Ethan Glasberg (glasberg@email.arizona.edu)
 * @author Jarod Bristol (jarodkylebristol@email.arizona.edu)
 * @author Alex Gonzalez (aegonzalez793@email.arizona.edu)
 * @author Patrick Dearborn (pdearborn@email.arizona.edu)
 */
public class Node {
	private Rectangle rect;
	private Node next;
	
	/**
	 * Node constructor makes a new linked list.
	 * 
	 * @param rect A rectangle object.
	 */
	public Node(Rectangle rect) {
		this.rect = rect;
		this.next = null;
	}
	
	/**
	 * Return a rectangle from the linked list.
	 * 
	 * @return Rectangle object
	 */
	public Rectangle getRectangle() {
		return rect;
	}
	
	/**
	 * Get and return the next Node object in the linked list.
	 * 
	 * @return the next Node object.
	 */
	public Node getNext() {
		return next;
	}
	
	/**
	 * Set what the next Node object is.
	 * 
	 * @param next The next Node object.
	 */
	public void setNext(Node next) {
		this.next = next;
	}
}
