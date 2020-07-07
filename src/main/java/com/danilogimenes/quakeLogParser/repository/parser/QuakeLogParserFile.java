package com.danilogimenes.quakeLogParser.repository.parser;


import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import com.danilogimenes.quakeLogParser.domain.Game;
import lombok.Getter;
import lombok.Setter;

/**
 * *********** Fonte de dados: Arquivo ***********
 * Objetivo: Esta classe definie regras (contrato) que devem ser seguidas por qualquer
 *           outra classe que implementará lógicas de parseamento de arquivo (TXT,XML,JSON,etc) QuakeLog. 
 *           
 * @author Danilo Gimenes Tasso - 07/2020
 *
 */

public abstract class QuakeLogParserFile {
	
	@Getter protected static Path path = null;
	@Getter @Setter protected Game game;
	@Getter @Setter protected List<Game> games = new ArrayList<>();
	protected final AtomicLong counter = new AtomicLong();
	
	/**
	 * Realiza o parseamento do arquivo informado no path
	 */
	public abstract void parse();
	 
	/**
	 * Detecta com base no input, se é um início de jogo.
	 * @param input Tag/linha/artefato do arquivo de log 
	 * @return verdadeiro ou falso
	 */
	public abstract Boolean detectInitGame(Object input);
	
	/**
	 * Detecta e retorna um registro de morte no arquivo.
	 * @param input parte/tag/linha do arquivo de log 
	 * @return
	 */
	public abstract String[] detectKillLine(Object input);
	
	/**
	 * Detecta quando o player entra no jogo.
	 * @param input parte/tag/linha do arquivo de log 
	 * @return
	 */
	public abstract String detectPlayer(Object input);
	
	/**
	 * Valida se o arquivo de log é válido e existente.
	 * @param path Caminho do arquivo no disco
	 * @return verdadeiro/falso
	 * @throws FileNotFoundException
	 */
	public void setPath(Path path) throws FileNotFoundException {
		if (Files.notExists(path))
			throw new FileNotFoundException("Arquivo inexistente ou inválido. '" + path.toString());
		QuakeLogParserFile.path = path;
	}
}
