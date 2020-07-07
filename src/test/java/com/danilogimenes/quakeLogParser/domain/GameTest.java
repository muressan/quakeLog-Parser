package com.danilogimenes.quakeLogParser.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Scope;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Objetivo: Classe que realiza testes unitários para a entidade Game 
 * 
 * @author Danilo Gimenes Tasso - 07/2020
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Scope("test")
@TestMethodOrder(OrderAnnotation.class)
public class GameTest {
	
	private static Game game;
	
	@Before
	public void setUp() {
		game = new Game();
	}
	
	@Test
	@Order(1)
	public void shouldAddDiferentPlayers() {
		game.addPlayer("Danilo");
		game.addPlayer("Tiago");
		assertTrue("Game tem 2 jogadores?", game.getPlayers().size()==2);
		assertTrue("Danilo cadastrado ?", game.getPlayers().get(0).getName().equals("Danilo"));
		assertTrue("Tiago cadastrado ?", game.getPlayers().get(1).getName().equals("Tiago"));
	}
	
	@Test
	@Order(2)
	public void shouldAddRepeatedPlayers() {
		game.addPlayer("Danilo");
		game.addPlayer("Danilo");
		game.addPlayer("Danilo");
		assertEquals(1, game.getPlayers().size());
	}
	
	@Test
	@Order(3)
	public void shouldGetTotalGamePlayers() {
		game.addPlayer("Danilo");
		game.addPlayer("Tiago");
		assertEquals(2, game.getPlayers().size());
		
		game.addKill("Isgalamido", "Dono da Bola", "MOD_RAILGUN");		
		// testando com player <world>
		game.addKill("<world>", "Isgalamido", "MOD_TRIGGER_HURT");
		
		// total de players deve ser 4
	    assertEquals(4, game.getPlayers().size());
	}
	
	@Test
	@Order(4)
	public void shouldNotAddInvalidPlayer() {
		game.addPlayer("<world>");
		// <world> não deve ser inserido 
	    assertEquals(0, game.getPlayers().size());
	}
	
	@Test
	@Order(5)
	public void shouldGetPlayerByName(){
		Player p1 = game.addPlayer("Danilo Gimenes");
		assertEquals("Danilo Gimenes", p1.getName());
		
		// white spaces
		p1 = game.addPlayer(" Danilo Gimenes ");
		assertNotEquals(" Danilo Gimenes ", p1.getName());
		
		// white spaces
		p1 = game.addPlayer("Danilo Gimenes ");
		assertNotEquals("Danilo Gimenes ", p1.getName());
		
		// white spaces
		p1 = game.addPlayer(" Danilo Gimenes");
		assertNotEquals(" Danilo Gimenes", p1.getName());
	}
	
	@Test
	@Order(6)
	public void shouldGetTotalKillsOnGame() {

		game.addKill("Isgalamido", "Dono da Bola", "MOD_RAILGUN");		
		
		// testando com player <world>
		game.addKill("<world>", "Isgalamido", "MOD_TRIGGER_HURT");
		
		// Total Mortes no jogo deve ser 2
		assertEquals(2, game.getTotalKills().intValue());
	}
	
	@Test
	@Order(7)
	public void shouldGetTotalKillsOfPlayer() {

		game.addKill("Isgalamido", "Dono da Bola", "MOD_RAILGUN");		
		
		// testando com player <world>
		game.addKill("<world>", "Isgalamido", "MOD_TRIGGER_HURT");
		
		// Total de vezes que o "Isgalamido" matou
		assertEquals((Integer)0,game.getPlayers().get(0).getKills());
		
		// Total de vezes que o "Dono da Bola" matou
		assertEquals((Integer)0,game.getPlayers().get(1).getKills());
	}
	
	@Test
	@Order(8)
	public void shouldGetTotalDeathsOfPlayer() {

		// verificar quantas vezes o jogador morreu
		
		game.addKill("Isgalamido", "Dono da Bola", "MOD_RAILGUN");		
		game.addKill("Isgalamido", "Dono da Bola", "MOD_RAILGUN");
		game.addKill("<world>", "Isgalamido", "MOD_TRIGGER_HURT");
		game.addKill("Mocinha", "Isgalamido", "MOD_ROCKET_SPLASH");
		game.addKill("Dono da Bola", "Isgalamido", "MOD_RAILGUN");
		// exemplo de suicídio
		game.addKill("Isgalamido", "Isgalamido", "MOD_ROCKET_SPLASH");
		
		// Total de vezes que o "Isgalamido" morreu
		assertEquals(4, game.addPlayer("Isgalamido").getDeaths().intValue());
		// Total de vezes que o "Dono da Bola" morreu
		assertEquals(2, game.addPlayer("Dono da Bola").getDeaths().intValue());
		// Total de vezes que o "Mocinha" morreu
		assertEquals(0, game.addPlayer("Mocinha").getDeaths().intValue());
	}

	@Test  
	@Order(9)
	public void shouldCheckTotalKillsOfPlayer()  {
		
		// verificar quantas vezes o jogador matou
		
		game.addKill("Isgalamido", "Dono da Bola", "MOD_RAILGUN");		
		game.addKill("Isgalamido", "Dono da Bola", "MOD_RAILGUN");
		game.addKill("<world>", "Isgalamido", "MOD_TRIGGER_HURT");
		game.addKill("Mocinha", "Isgalamido", "MOD_ROCKET_SPLASH");
		game.addKill("Dono da Bola", "Isgalamido", "MOD_RAILGUN");
		// exemplo de suicídio
		game.addKill("Isgalamido", "Isgalamido", "MOD_ROCKET_SPLASH");
		
		// Total de vezes que o "Isgalamido" matou
		assertEquals(2, game.addPlayer("Isgalamido").getKills().intValue());
		// Total de vezes que o "Dono da Bola" matou
		assertEquals(1, game.addPlayer("Dono da Bola").getKills().intValue());
		// Total de vezes que o "Mocinha" matou
		assertEquals(1, game.addPlayer("Mocinha").getKills().intValue());
		
		
	}
	
	@Test  
	@Order(10)
	public void shouldCheckTotalKillsOfPlayer2()  {
	
		game.addKill("Isgalamido", "Dono da Bola", "MOD_RAILGUN");		
		game.addKill("Isgalamido", "Dono da Bola", "MOD_RAILGUN");
		game.addKill("<world>", "Isgalamido", "MOD_TRIGGER_HURT");
		game.addKill("Mocinha", "Isgalamido", "MOD_ROCKET_SPLASH");
		game.addKill("Dono da Bola", "Isgalamido", "MOD_RAILGUN");
		// exemplo de suicídio
		game.addKill("Isgalamido", "Isgalamido", "MOD_ROCKET_SPLASH");
				
		for (String player: game.totalKillsByPlayers().keySet()) {
			assertEquals(game.addPlayer(player).getKills(), game.totalKillsByPlayers().get(player));
		}			
		
	}
	
	@Test
	@Order(11)
	public void shouldGetTotalDeathsOfPlayer2()  {
	
		game.addKill("Isgalamido", "Dono da Bola", "MOD_RAILGUN");		
		game.addKill("Isgalamido", "Dono da Bola", "MOD_RAILGUN");
		game.addKill("<world>", "Isgalamido", "MOD_TRIGGER_HURT");
		game.addKill("Mocinha", "Isgalamido", "MOD_ROCKET_SPLASH");
		game.addKill("Dono da Bola", "Isgalamido", "MOD_RAILGUN");
		// exemplo de suicídio
		game.addKill("Isgalamido", "Isgalamido", "MOD_ROCKET_SPLASH");
				
		for (String player: game.totalKillsByPlayers().keySet()) {
			assertEquals(game.addPlayer(player).getDeaths(), game.totalDeathsByPlayers().get(player));
		}			
		
	}
	
	@Test
	@Order(12)
	public void shouldTestDeathAndKillsBySuicide() {
		game.addKill("Mocinha", "Mocinha", "MOD_ROCKET_SPLASH");
		
		assertEquals(1, game.addPlayer("Mocinha").getKills().intValue());
		assertEquals(1, game.addPlayer("Mocinha").getDeaths().intValue());
		assertEquals(1, game.getTotalKills().intValue());
	}
	
}

