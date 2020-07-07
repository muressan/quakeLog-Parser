package com.danilogimenes.quakeLogParser.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Scope;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Objetivo: Classe que realiza testes de integração (end-points)
 * 
 * Tipos de teste(s) contemplados:
 * 		( ) Autenticação
 * 		( ) Autorização
 * 		( ) Teste de estresse
 * 		(X) Teste de resposta
 * @author Danilo Gimenes Tasso  07/2020
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Scope("test")
public class GameControllerTest {
	
	private static final String URL_BASE = "/v1/games";
	
	@Autowired
	private MockMvc mockMvc;

    @Test
    public void getGamesOk() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .get(URL_BASE)
                .header("Content-Type", "application/json"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    public void getGameByIdOk() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders
                .get(URL_BASE+"/2")
                .header("Content-Type", "application/json"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    public void getGameByIdNotFound() throws Exception {

    	ResultActions result = this.mockMvc.perform(MockMvcRequestBuilders
                .get(URL_BASE+"/9999999")
                .header("Content-Type", "application/json"));
    	ResultActions print = result.andDo(MockMvcResultHandlers.print());
    	print.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
    
}