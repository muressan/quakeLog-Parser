package com.danilogimenes.quakeLogParser.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
		
		// verifica se já existe o jogador e retorna o indice da lista
		int idx = IntStream.range(0, players.size())
								.filter(i -> players.get(i).getName().equals(name))
								.findAny()
								.orElse(-1);
		if (idx >= 0)
			return players.get(idx);
		else {
			
			//adicionar jogador 
			
			Player p = new Player(name);
			//2. '<world>' não é jogador e não aparece no dicionário
			if (!p.getName().equals("<world>"))
				players.add(p);
			return p;
		}
	}

	public void addKill(String killer, String victim, String reason) {
		
		Player pKiller = addPlayer(killer); // assassino
		Player pVictim = addPlayer(victim); // vítima
		
		// adicionar morte para a vítima
		pVictim.addDeaths();

		if (killer.equals("<world>")) {
			pVictim.decreaseKills();
		} 
		else {
			// adiciona morte para o assassino
			pKiller.addKills();
			kills.add(new KillDictionary(pKiller, pVictim, reason));
		}

		totalKills++;
	}

	// lista de quantidade de vezes que cada jogador matou
	public Map<String, Integer> totalKillsByPlayers() {
		return players.stream()
				.collect(
					Collectors.groupingBy(
						Player::getName, 
						Collectors.reducing(
							0, 
							Player::getKills, 
							Integer::sum)));
	}
	
	// lista de quantidade de vezes que cada jogador morreu
	public Map<String, Integer> totalDeathsByPlayers() {
		return players.stream()
				.collect(
					Collectors.groupingBy(
						Player::getName, 
						Collectors.reducing(
							0, 
							Player::getDeaths, 
							Integer::sum)));
	}

}
