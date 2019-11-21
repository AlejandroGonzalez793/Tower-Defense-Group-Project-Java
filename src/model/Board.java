package model;

public class Board {
	private Entity board[][];
	
	public Board(int width, int height) {
		this.board = new Entity[height][width];
	}
	
	public Entity[][] getBoard() {
		return board;
	}
	
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
