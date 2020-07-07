package com.danilogimenes.quakeLogParser.repository.impl;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.danilogimenes.quakeLogParser.domain.Game;
import com.danilogimenes.quakeLogParser.repository.GameRepository;
import com.danilogimenes.quakeLogParser.repository.parser.QuakeLogParserFile;

/**
 * Objetivo: Classe que implementa a GameRepository. Responsável pelo mecanismo de 
 * 			 acesso à dados utilizando como fonte um arquivo TXT. 
 * @author Danilo Gimenes Tasso - 07/2020
 *
 */

@Repository
public class GameRepositoryImpl implements GameRepository {
	
	@Autowired
	private QuakeLogParserFile quakeLogParserTXT; 
	
	private static final Logger logger = LoggerFactory.getLogger(GameRepositoryImpl.class);
	
	@Override
	public List<Game> getAll(){
		if (quakeLogParserTXT.getGames().isEmpty())
			logger.error("A lista de games está vazia. O Parser ainda não foi executado.");
		return quakeLogParserTXT.getGames();
	}

	@Override
	public Game getById(Long id){
		List<Game> gamesLst = quakeLogParserTXT.getGames();
		Game game = new Game();
		
		if (gamesLst.isEmpty())
			logger.error("A lista de games está vazia. O Parser ainda não foi executado.");
		
		Optional<Game> resultLst = gamesLst.stream().filter(g -> g.getId() == id).findAny(); 
		
		if (resultLst.isPresent())
			game =  resultLst.get();
		
		return game;
	}

}
