package com.sarsila.model.dao;

import com.sarsila.model.*;

public interface ClickItemDao {
	
	public Long saveClickItem(GoGame game, ClickItem item);
	public ClickItem getClickItem(Long id);

}
