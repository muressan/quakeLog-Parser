 package com.danilogimenes.quakeLogParser.converter.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.danilogimenes.quakeLogParser.converter.ParserConverter;
import com.danilogimenes.quakeLogParser.domain.Game;
import com.danilogimenes.quakeLogParser.dto.GameDTO;

public class GameConverterImpl implements ParserConverter<Game,GameDTO>{

	@Override
	public GameDTO ParseToDTO(Game origin) {
		if (origin == null) return null;
		
		// setando a lista de jogadores
		List<String> players = new ArrayList<>();
		origin.getPlayers().forEach(player -> players.add(player.getName()));
		
		// setando lista ranking de matadores 
		Map<String,Integer> kills = new HashMap<>();
		origin.getKills().forEach(dk -> kills.put(
											dk.getKiller().getName(),
											dk.getKiller().getKills()));
		
		GameDTO gameDto = new GameDTO();
		gameDto.setId(origin.getId());
		gameDto.setPlayers(players);
		gameDto.setKills(kills);
		gameDto.setTotalKills(origin.getTotalKills());
							
		return gameDto;
	}
	
	@Override
	public Game ParseToEntity(GameDTO origin) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Game> ParseListToEntity(List<GameDTO> origin) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<GameDTO> ParseListToDTO(List<Game> origin) {
		if (origin == null) return null;
        List<GameDTO> list = new ArrayList<GameDTO>();  
        origin.forEach(item -> list.add(ParseToDTO(item)));
        
		return list;
	}

}

