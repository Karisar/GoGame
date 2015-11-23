package com.sarsila.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.sarsila.model.GoGame;

public class GoGameTest {

	@Test
	public void testGetId() {
		GoGame game = new GoGame();
		game.setId(new Long(1234));
		assertEquals(new Long(1234), game.getId());
	}

	@Test
	public void testSetId() {
		GoGame game = new GoGame();
		game.setId(new Long(1234));
		assertEquals(new Long(1234), game.getId());
	}

	@Test
	public void testStartNewGame() {
		GoGame game = new GoGame();
		assertNotNull(game.startNewGame());
	}

	@Test
	public void testIsCellEmpty() {
		GoGame game = new GoGame();
		assertTrue(game.isCellEmpty(1, 1));
	}
}
