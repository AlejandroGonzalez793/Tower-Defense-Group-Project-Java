package model;

/**
 * The main object used for tracking the game state. Contains a 2D array
 * of <tt>Entity</tt> objects that each contain their own method of
 * drawing to the screen.
 *  
 * @author Ethan Glasberg (glasberg@email.arizona.edu)
 * @author Jarod Bristol (jarodkylebristol@email.arizona.edu)
 * @author Alex Gonzalez (aegonzalez793@email.arizona.edu)
 * @author Patrick Dearborn (pdearborn@email.arizona.edu) 
 * 
 * @see Entity
 */
public class Board {
	private Entity board[][];
	
	public Board(int width, int height) {
		this.board = new Entity[height][width];
	}
	
	/**
	 * Directly get the underlying game array
	 * @return the 2D entity array that is the state of the game
	 */
	public Entity[][] getBoard() {
		return board;
	}
	
	/**
	 * Get the Entity at a specified coordinate
	 * 
	 * @param x the x position to use
	 * @param y the y position to use
	 * @return the Entity at position x,y
	 */
	public Entity getPosition(int x, int y) {
		// TODO: Throw a specific exception other than OutOfBounds?
		if (y < 0 || y > board.length) {
			return null;
		}
		
		if (x < 0 || x > board[0].length) {
			return null;
		}
		
		return board[y][x];
	}
	
	/**
	 * Set a position on the board to a specific Entity
	 * 
	 * @param entity the Entity to set the position with
	 * @param x the x position to set the entity at
	 * @param y the y position to set the entity at
	 */
	public void setPosition(Entity entity, int x, int y) {
		// TODO: Throw a specific exception other than OutOfBounds?
		if (y < 0 || y > board.length) {
			return;
		}
		
		if (x < 0 || x > board[0].length) {
			return;
		}
		
		board[y][x] = entity;
	}
}
