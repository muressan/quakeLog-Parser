package com.danilogimenes.quakeLogParser.repository;

import java.util.List;
import com.danilogimenes.quakeLogParser.domain.Game;

/**
 * Objetivo: Interface de acesso a dados para camada de serviço.
 * 
 * @author Danilo Gimenes Tasso - 07/2020
 *
 */

public interface GameRepository {
	Game getById(Long id);
	List<Game> getAll();
}
