package com.danilogimenes.quakeLogParser.handleException;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

/**
 * Objetivo: Implementar exceção especializada para
 * 			 para ser propagada ou tratada pela aplicação.
 * @author Danilo Gimenes Tasso - 07/2020
 *
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class QuakeLogParserException extends Exception{

	private static final long serialVersionUID = 1L;
	private HttpStatus httpStatus;

    public QuakeLogParserException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}