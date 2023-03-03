package com.desafio.bradesco.enums;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

public enum Regiao {

    SUDESTE(List.of("SP", "RJ", "ES", "MG"), BigDecimal.valueOf(7.85)),
    CENTRO_OESTE(List.of("MT", "MS", "GO", "DF"), BigDecimal.valueOf(12.50)),
    NORDESTE(List.of("MA", "PI", "CE", "RN", "PB", "PE", "AL", "SE", "BA"), BigDecimal.valueOf(15.98)),
    SUL(List.of("PR", "SC", "RS"), BigDecimal.valueOf(17.30)),
    NORTE(List.of("AC", "AM", "AP", "PA", "RO", "RR", "TO"), BigDecimal.valueOf(7.85));

    public final List<String> estados;
    private final BigDecimal valorFrete;

    Regiao(List<String> estados, BigDecimal valorFrete) {
        this.estados = estados;
        this.valorFrete = valorFrete;
    }

    public static BigDecimal getValorFrete(String estado) {
        return Stream.of(values())
                .filter(regiao -> regiao.estados.contains(estado))
                .findFirst()
                .map(regiao -> regiao.valorFrete)
                .orElseThrow(() -> new RuntimeException("Não foi possível determinar a região do endereço."));
    }
}
