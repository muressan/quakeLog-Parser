package com.danilogimenes.quakeLogParser.handleException;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.danilogimenes.quakeLogParser.dto.ExceptionDTO;

/**
 * Objetivo: Personalizar e direcionar para o retorno do endpoint (cliente)
 * 			 qualquer tipo de exceção gerada pelo container (Server - 500). 
 * 			 
 * @author Danilo Gimenes Tasso 07/2020
 *
 */

@ControllerAdvice
@Order(0)
public class HandlerException {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDTO> generalException(Exception e){
        return new ResponseEntity<>(new ExceptionDTO(1, 500, e.getMessage(), e),
                					HttpStatus.INTERNAL_SERVER_ERROR);
    }
}