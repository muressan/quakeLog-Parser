package com.danilogimenes.quakeLogParser.domain;

import com.danilogimenes.quakeLogParser.domain.Player;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 
 * Objetivo: Classe para representar o dicionário de morte(s)
 * @author Danilo Gimenes Tasso
 */

@Data
@AllArgsConstructor
public class KillDictionary {
	private Player killer;
	private Player victim;
	private String reason;
}
