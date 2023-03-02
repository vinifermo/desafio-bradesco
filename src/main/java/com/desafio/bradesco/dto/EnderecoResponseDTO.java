package com.desafio.bradesco.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoResponseDTO {

    private String cep;
    private String rua;
    private String complemento;
    private String cidade;
    private String bairro;
    private String estado;
    private BigDecimal frete;
}
