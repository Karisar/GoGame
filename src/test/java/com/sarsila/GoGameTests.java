package com.sarsila;

import com.sarsila.model.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class GoGameTests {

	@Test
	public void testSetId() {
		GoGame game = new GoGame();
		game.setId(new Long(5));
		assertEquals(new Long(5), game.getId());
	}

	@Test
	public void testGetWhitesCount() {
		GoGame game = new GoGame();
		assertEquals(0, game.getWhitesCount());
	}

	@Test
	public void testGetBlacksCount() {
		GoGame game = new GoGame();
		assertEquals(0, game.getBlacksCount());
		}

	@Test
	public void testStartNewGame() {
		GoGame game = new GoGame();
		assertNotNull(game.startNewGame());
	}

	@Test
	public void testSwitchTurns() {
		GoGame game = new GoGame();
		game.startNewGame();
		assertEquals(1, game.getTurn());
		game.switchTurns();
		assertEquals(2, game.getTurn());
	}

	@Test
	public void testGetMarker() {
		GoGame game = new GoGame();
		game.addClick(new ClickItem(1,1,1, new Long(1)));
		game.addClick(new ClickItem(1,2,2, new Long(2)));
		assertEquals("X", game.getMarker(1, 1));
		assertEquals("O", game.getMarker(1, 2));
		
		
	}

	@Test
	public void testIsCellEmpty() {
		GoGame game = new GoGame();
		game.addClick(new ClickItem(1,1,1, new Long(1)));
		assertEquals(true, game.isCellEmpty(2, 2));
		assertEquals(false, game.isCellEmpty(1, 1));
	}

}
