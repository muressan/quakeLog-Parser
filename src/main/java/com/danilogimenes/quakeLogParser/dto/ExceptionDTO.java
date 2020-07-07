package com.danilogimenes.quakeLogParser.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * Objetivo: Classe DTO que irá padronizar objetos de entrega de
 * 			 exceções produzidas pelo lado servidor.
 * 
 * @author Danilo Gimenes Tasso - 07/2020
 *
 */

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionDTO implements Serializable {
    public static final long serialVersionUID = 1L;

    private int errorCode;
    private int httpCode;
    private String message;
    private Exception exception;
    
}
