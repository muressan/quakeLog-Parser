package com.danilogimenes.quakeLogParser.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe para representar informações sobre o jogador
 * @author Danilo Gimenes Tasso
 **/

@NoArgsConstructor
@EqualsAndHashCode
public class Player {
	
	@Getter @Setter
	private String name;
	
	@Getter
	private Integer deaths = 0;
	
	@Getter
	private Integer kills = 0;
	
	//construtor
	public Player(String name) {
		this.name = name.trim();
	}
	
	public void decreaseKills() {
		if (this.kills > 0)
			this.kills--;
	}
	
	public void addDeaths() {
		this.deaths++;
	}
	
	public void addKills() {
		this.kills++;
	}
	
}
