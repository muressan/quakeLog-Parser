package com.danilogimenes.quakeLogParser;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.danilogimenes.quakeLogParser.repository.parser.QuakeLogParserFile;

@SpringBootApplication
public class QuakeLogParserApplication {

	private static final Logger logger = LoggerFactory.getLogger(QuakeLogParserApplication.class);

	@Autowired
	private QuakeLogParserFile quakeLogParser; // = new QuakeLogParserTXT();
	
	public static void main(String[] args) {
		SpringApplication.run(QuakeLogParserApplication.class, args);
	}
	
	
	/**
	 * Metodo que vai fazer o parseamento do arquivo na inicialização do aplicação no container 
	 * */
	@PostConstruct
	private void quakeLogParser() {
		Path path = Paths.get("raw/games.log");
		
		try {
			quakeLogParser.setPath(path);
			quakeLogParser.parse();
		} catch (FileNotFoundException e) {
			logger.error(String.format("Erro ao configurar o caminho do arquivo de parser %s", e));
		}
	}

}
