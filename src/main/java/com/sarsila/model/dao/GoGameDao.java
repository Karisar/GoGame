package com.sarsila.model.dao;

import com.sarsila.model.GoGame;

public interface GoGameDao {
	public Long saveNewGame(GoGame game);
	public GoGame getGame(Long id);
}
