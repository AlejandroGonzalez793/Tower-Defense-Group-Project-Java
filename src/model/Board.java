package model;

/**
 * The main object used for tracking the game state. Contains a 2D array
 * of <tt>Tile</tt> objects that each contain their own method of
 * drawing to the screen.
 *  
 * @author Ethan Glasberg (glasberg@email.arizona.edu)
 * @author Jarod Bristol (jarodkylebristol@email.arizona.edu)
 * @author Alex Gonzalez (aegonzalez793@email.arizona.edu)
 * @author Patrick Dearborn (pdearborn@email.arizona.edu) 
 * 
 * @see Tile
 */
public class Board {
	private Tile board[][];
	
	public Board(int width, int height) {
		this.board = new Tile[height][width];
	}
	
	/**
	 * Directly get the underlying game array
	 * @return the 2D entity array that is the state of the game
	 */
	public Tile[][] getBoard() {
		return board;
	}
	
	/**
	 * Get the Tile at a specified coordinate
	 * 
	 * @param row the row to get the Tile from
	 * @param col the col to get the Tile from
	 * @return the Tile at position (row,col) on the board
	 */
	public Tile getTile(int row, int col) {
		// TODO: Throw a specific exception other than OutOfBounds?
		if (row < 0 || row > board.length) {
			return null;
		}
		
		if (col < 0 || col > board[0].length) {
			return null;
		}
		
		return board[row][col];
	}
	
	/**
	 * Set a position on the board to a specific Tile
	 * 
	 * @param tile the Tile to set the position with
	 * @param row the row to set the Tile at
	 * @param col the col to set the Tile at
	 */
	public void setTile(Tile tile, int row, int col) {
		// TODO: Throw a specific exception other than OutOfBounds?
		if (row < 0 || row > board.length) {
			return;
		}
		
		if (col < 0 || col > board[0].length) {
			return;
		}
		
		board[row][col] = tile;
	}
	
	/**
	 * Get the entity at a specified coordinate
	 * 
	 * @param row the row to get the Entity from
	 * @param col the col to get the Entity from
	 * @return the Entity at position (row,col) on the board
	 */
	public Entity getEntity(int row, int col) {
		Tile tile = getTile(row, col);
		return tile.getEntity();
	}
	
	/**
	 * Set a position on the board to contain a specific Entity
	 * 
	 * @param entity the Entity to set the position with
	 * @param row the row to set the Entity at
	 * @param col the col to set the Entity at
	 */
	public void setEntity(Entity entity, int row, int col) {
		Tile tile = getTile(row, col);
		tile.setEntity(entity);
	}
}
