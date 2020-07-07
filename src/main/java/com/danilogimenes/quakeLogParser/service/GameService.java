package com.danilogimenes.quakeLogParser.service;

import java.util.List;

import com.danilogimenes.quakeLogParser.domain.Game;

public interface GameService {
	public List<Game> getGames() throws Exception;
	public Game getGameById(Long id);
}