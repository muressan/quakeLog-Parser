package com.danilogimenes.quakeLogParser.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.danilogimenes.quakeLogParser.domain.KillDictionary;
import com.danilogimenes.quakeLogParser.domain.Player;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe para representar um Game
 * @author Danilo Gimenes Tasso
 *
 */

@NoArgsConstructor
public class Game {

	@Getter @Setter private Long id;	
	@Getter private Integer totalKills = 0;
	@Getter private List<Player> players = new ArrayList<>();
	@Getter private List<KillDictionary> kills = new ArrayList<>();
	
	/**
	 * Adiciona novos jogadores a lista
	 */
	public Player addPlayer(String name) {
		
		return null;

	}

	public void addKill(String killer, String victim, String reason) {
		
	}

	// lista de quantidade de vezes que cada jogador matou
	public Map<String, Integer> totalKillsByPlayers() {
		return null;
	}
	
	// lista de quantidade de vezes que cada jogador morreu
	public Map<String, Integer> totalDeathsByPlayers() {
		return null;
	}

}
