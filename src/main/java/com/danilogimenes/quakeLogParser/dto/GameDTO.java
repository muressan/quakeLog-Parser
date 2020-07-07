package com.danilogimenes.quakeLogParser.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Objetivo: Classe DTO que irá padronizar os dados de Games
 * 			 nas requisições de entrada e saída da API.
 * 
 * @author Danilo Gimenes Tasso - 07/2020
 *
 */

@Data()
@NoArgsConstructor
@AllArgsConstructor
@JsonRootName(value="game")
public class GameDTO implements Serializable {
	
	private static final long serialVersionUID = -5754246207015712518L;

	@JsonProperty("game_id")
	private Long id;
	
	@JsonProperty("total_kills")
	private Integer totalKills;
	
	@JsonProperty("players")
	private List<String> players;
	
	@JsonProperty("kills")
	private Map<String, Integer> kills;
}
