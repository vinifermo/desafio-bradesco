package com.example.desafiobradesco.cucumber.test;

import com.desafio.bradesco.dto.EnderecoRequestDTO;
import com.desafio.bradesco.dto.EnderecoResponseDTO;
import com.desafio.bradesco.enums.Regiao;
import com.desafio.bradesco.service.EnderecoService;
import com.desafio.bradesco.service.ViaCepService;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnderecoSteps {

    private String cep;
    private EnderecoResponseDTO response;

    @Given("^que um usuário consultou um endereço válido com CEP \"([^\"]*)\"$")
    public void consultarEnderecoValido(EnderecoRequestDTO enderecoRequestDTO) {
        this.cep = enderecoRequestDTO.getCep();
        EnderecoService enderecoService = new EnderecoService(new ViaCepService());
        response = enderecoService.buscarEnderecoPorCep(enderecoRequestDTO);
    }

    @Then("^o endereço deve ser retornado corretamente$")
    public void verificarEnderecoRetornado() {
        assertEquals("Rua Direita", response.getRua());
        assertEquals("Sé", response.getBairro());
        assertEquals("São Paulo", response.getCidade());
        assertEquals("SP", response.getEstado());
        assertEquals("01001000", response.getCep());
    }

    @And("^o valor do frete deve ser calculado corretamente de acordo com a região$")
    public void verificarValorFrete() {
        BigDecimal valorEsperado = Regiao.getValorFrete(response.getEstado());
        assertEquals(valorEsperado, response.getFrete());
    }
}
