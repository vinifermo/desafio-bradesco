package com.example.desafiobradesco.desafio.bradesco.service;

import com.desafio.bradesco.dto.EnderecoRequestDTO;
import com.desafio.bradesco.dto.EnderecoResponseDTO;
import com.desafio.bradesco.dto.ViaCepDTO;
import com.desafio.bradesco.service.EnderecoService;
import com.desafio.bradesco.service.ViaCepService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class EnderecoServiceTest {

    @Mock
    private ViaCepService viaCEPService;

    @InjectMocks
    private EnderecoService enderecoService;

    @Test
    void whenValidCepProvidedThenReturnValidAddress() {
        ViaCepDTO viaCepDTO = new ViaCepDTO();
        viaCepDTO.setCep("01000-000");
        viaCepDTO.setLogradouro("Praça da Sé");
        viaCepDTO.setComplemento("lado ímpar");
        viaCepDTO.setBairro("Sé");
        viaCepDTO.setLocalidade("São Paulo");
        viaCepDTO.setUf("SP");

        Mockito.when(viaCEPService.buscarEnderecoPorCep("01000-000")).thenReturn(viaCepDTO);

        EnderecoRequestDTO enderecoRequestDTO = new EnderecoRequestDTO();
        enderecoRequestDTO.setCep("01000-000");
        EnderecoResponseDTO enderecoResponseDTO = enderecoService.buscarEnderecoPorCep(enderecoRequestDTO);

        Assertions.assertEquals("01000-000", enderecoResponseDTO.getCep());
        Assertions.assertEquals("Praça da Sé", enderecoResponseDTO.getRua());
        Assertions.assertEquals("lado ímpar", enderecoResponseDTO.getComplemento());
        Assertions.assertEquals("Sé", enderecoResponseDTO.getBairro());
        Assertions.assertEquals("São Paulo", enderecoResponseDTO.getCidade());
        Assertions.assertEquals("SP", enderecoResponseDTO.getEstado());
        Assertions.assertEquals(new BigDecimal("7.85"), enderecoResponseDTO.getFrete());
    }
}
