package com.danilogimenes.quakeLogParser.repository.parser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.danilogimenes.quakeLogParser.domain.Game;

/***
 * Objetivo: Implementar funções específicas para ler arquivo QuakeLog em formato TXT.
 * 			 Com base na classe abstrata (contrato) QuakeLogParserFile	
 * @author Danilo Gimenes Tasso
 *
 */

@Component
@Scope("singleton")
public class QuakeLogParserTXT extends QuakeLogParserFile {
	private static final Logger logger = LoggerFactory.getLogger(QuakeLogParserTXT.class);

	public void parse() {
		try (Stream<String> s = Files.lines(path)) {
			s.forEach(l -> processLine(l));
		} catch (FileNotFoundException e) { 
			logger.error(String.format("O arquivo não existe %s ", e.getMessage()));
		} catch (IOException e) {
			logger.error(String.format("Erro ao ler arquivo %s ", e.getMessage()));
		}
		
	}
	
	private void processLine(String line) {	
		
		//  Início de jogo (InitGame)
		if (detectInitGame(line)) {
			this.game = new Game();
			this.game.setId(this.counter.incrementAndGet());
			this.games.add(game);
		}
		else {
			// detectar se é linha com informações de kill
			String[] killLine = detectKillLine(line);
			if (killLine.length>0) {
				this.game.addKill(killLine[0],
								  killLine[1],
								  killLine[2]);
			}
			else {
				String player = detectPlayer(line);
				if (!player.isEmpty())
					this.game.addPlayer(player.trim());
			}
		}
	}
	
	@Override
	public String[] detectKillLine(Object input) {
		String line = (String) input;
		String[] killLineArray = new String[0];
		
		Pattern p = Pattern.compile(":\\s([^:]+)\\skilled\\s(.*?)\\sby\\s[a-zA-Z_]+");
		Matcher matcher = p.matcher(line);
		String killLine = "";
		while (matcher.find()) {
			killLine = matcher.group();	        
        }
		
		if ((killLine != null) && (!killLine.isEmpty())){
			
			// tirar ":" do início da string se existir
			killLine = (killLine.trim().startsWith(":")) ? killLine.trim().substring(2) : killLine;
			
			// identificar elementos e compor array de retorno
			killLineArray = Pattern.compile(",").split(killLine.replaceAll("( killed | by )", ","));	
		}
		
		return killLineArray;
	}
	
	@Override
	public Boolean detectInitGame(Object input) {
		String value = (String) input;
		return value.matches(".*InitGame.*");
	}

	@Override
	public String detectPlayer(Object input) {
		String line = (String) input;
		String player = "";
		
		Pattern p = Pattern.compile("ClientUserinfoChanged:\\s*([0-9]*)\\s*n\\\\(.*?)\\\\t\\\\");
		Matcher matcher = p.matcher(line);
		while (matcher.find()) {
			player = matcher.group(2);	        
        }
		
		return player;
	}

}
