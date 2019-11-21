package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Board;

public class TDTests {
	
	@Test
	void testBoard() {
		Board board = new Board(10, 10);		
		assertNull(board.getTile(0, 0));
	}
}
