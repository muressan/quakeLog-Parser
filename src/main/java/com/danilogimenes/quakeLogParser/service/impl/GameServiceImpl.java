package com.danilogimenes.quakeLogParser.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.danilogimenes.quakeLogParser.domain.Game;
import com.danilogimenes.quakeLogParser.repository.GameRepository;
import com.danilogimenes.quakeLogParser.service.GameService;

/**
 * 
 * Objetivo: Classe responsável por buscar os dados de Games no repositório e 
 * 			 determinar regra de negócio da aplicação. 
 * 			 
 * 
 * @author Danilo Gimenes Tasso - 07/2020
 *
 */

@Service
public class GameServiceImpl implements GameService{

	@Autowired
	private GameRepository gameRepository;
	
	@Override
	public List<Game> getGames() throws Exception {
		return gameRepository.getAll();
	}
 
	@Override
	public Game getGameById(Long id) {
		return gameRepository.getById(id);
	}

}
