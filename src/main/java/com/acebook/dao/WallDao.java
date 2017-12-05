package com.acebook.dao;

import com.acebook.entities.Wall;

public interface WallDao {

	Wall save(Wall Wall);
	Wall persist(Wall Wall);
	
	Wall get(int id);
	Wall load(int id);
	
	Wall update(Wall Wall);
	Wall merge(Wall Wall);
	
	boolean delete(Wall Wall);
}
