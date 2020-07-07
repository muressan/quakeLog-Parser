package com.danilogimenes.quakeLogParser.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Scope;
import org.springframework.test.context.junit4.SpringRunner;
import com.danilogimenes.quakeLogParser.repository.parser.QuakeLogParserFile;
import com.danilogimenes.quakeLogParser.repository.parser.QuakeLogParserTXT;

/**
 * Objetivo: Testes unitários para verificar arquivo de parser TXT
 * 			 
 * 
 * @author Danilo Gimenes Tasso - 07/2020
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Scope("test")
public class QuakeLogParserTXTTest {

	private QuakeLogParserFile log;
	
	@Before
	public void setUp() {
		log =  new QuakeLogParserTXT();		
	}
	
	@Test
    public void shouldFileExists() throws IOException {
		File x = new File("../quakeLog-Parser/raw/games.log");
        assertTrue("game.log existe?", x.exists());
    }
			
	@Test
	public void shouldDoOneGameParser() throws FileNotFoundException {
		log.setPath(Paths.get("../quakeLog-Parser/raw/onegame.log"));		
		log.parse();
		assertEquals(1, log.getGames().size());        
	}
	
	@Test
	public void shouldDoTwoGamesParser() throws FileNotFoundException {
		log.setPath(Paths.get("../quakeLog-Parser/raw/twogames.log"));
		log.parse();	
		assertEquals(2, log.getGames().size());        
	}
}
