package com.sarsila.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.sarsila.model.ClickItem;

public class ClickItemTest {

	@Test
	public void testGetRow() {
		ClickItem item = new ClickItem(1,1,1);
		assertEquals(1, item.getRow());
	}

	@Test
	public void testGetColumn() {
		ClickItem item = new ClickItem(1,1,1);
		assertEquals(1, item.getColumn());
	}

	@Test
	public void testToString() {
		ClickItem item = new ClickItem(1,1,1);
		assertNotNull(item.toString());
	}

	@Test
	public void testGetMarker() {
		ClickItem item = new ClickItem(1,1,1);
		assertEquals("X", item.getMarker(1, 1));
	}

	@Test
	public void testGetTurn() {
		ClickItem item = new ClickItem(1,1,1);
		assertEquals(1, item.getTurn());
	}

}
