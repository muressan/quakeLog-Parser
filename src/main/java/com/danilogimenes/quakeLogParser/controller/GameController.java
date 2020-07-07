package com.danilogimenes.quakeLogParser.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.danilogimenes.quakeLogParser.converter.impl.GameConverterImpl;
import com.danilogimenes.quakeLogParser.dto.GameDTO;
import com.danilogimenes.quakeLogParser.dto.ResultContentDTO;
import com.danilogimenes.quakeLogParser.service.GameService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v1/games")
public class GameController {
	
	@Autowired
	GameService gameService;
	GameConverterImpl gameConverter = new GameConverterImpl();

	@ApiOperation(value="Show especific game by id. ")
	@GetMapping("/{id}")
	public ResponseEntity<ResultContentDTO<GameDTO>> getGameById(@PathVariable Long id) throws Exception  {		
		
		GameDTO gameDto = gameConverter.ParseToDTO(gameService.getGameById(id));
	
		if(gameDto.getId()==null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ResultContentDTO
                            .<GameDTO>builder()
                            .code(1)
                            .httpCode(404)
                            .message("Game not found.")
                            .build());
        } else {
            return ResponseEntity.ok(
                    ResultContentDTO
                            .<GameDTO>builder()
                            .code(0)
                            .httpCode(200)
                            .message("Game found.")
                            .content(gameDto)
                            .build());
        }
	}

	@ApiOperation(value="List game(s) found by quakeParserLog in the log internal file.")
	@GetMapping
	public ResponseEntity<ResultContentDTO<List<GameDTO>>> getGames() throws Exception{
		
		List<GameDTO> gameDtoLst = gameConverter.ParseListToDTO(gameService.getGames());
		
		if(gameDtoLst.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ResultContentDTO
                            .<List<GameDTO>>builder()
                            .code(1)
                            .httpCode(404)
                            .message("Game(s) not found.")
                            .build());
        } 
		else {
            return ResponseEntity.ok(
                    ResultContentDTO
                            .<List<GameDTO>>builder()
                            .code(0)
                            .httpCode(200)
                            .message("Game(s) found.")
                            .content(gameDtoLst)
                            .build());
        }
	}
}