package com.danilogimenes.quakeLogParser.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Objetivo: Classe DTO que irá padronizar o objeto de resposta que será
 * 			 devolvido para o client-side que bater na API.
 * 
 * @author Danilo Gimenes Tasso - 07/2020
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResultContentDTO<T> implements Serializable {
    public static final long serialVersionUID = 1L;

    private int code;
    private int httpCode;
    private String message;
    private T content;
}
