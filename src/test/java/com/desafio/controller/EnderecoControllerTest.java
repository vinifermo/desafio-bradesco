package com.desafio.controller;

import com.desafio.bradesco.controller.EnderecoController;
import com.desafio.bradesco.dto.EnderecoRequestDTO;
import com.desafio.bradesco.dto.EnderecoResponseDTO;
import com.desafio.bradesco.service.EnderecoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({MockitoExtension.class})
class EnderecoControllerTest {

    private MockMvc mockMvc;

    @Mock
    private EnderecoService enderecoService;

    @InjectMocks
    private EnderecoController enderecoController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(enderecoController).build();
    }

    @Test
    void buscarEnderecoViaCep_DeveRetornarEnderecoResponseDTO() throws Exception {
        EnderecoRequestDTO enderecoRequestDTO = new EnderecoRequestDTO();
        enderecoRequestDTO.setCep("01001-001");

        EnderecoResponseDTO enderecoResponseDTO = new EnderecoResponseDTO();
        enderecoResponseDTO.setCep("12345678");
        enderecoResponseDTO.setRua("Praça da Sé");
        enderecoResponseDTO.setComplemento("lado par");
        enderecoResponseDTO.setCidade("São Paulo");
        enderecoResponseDTO.setEstado("SP");
        enderecoResponseDTO.setBairro("Sé");
        enderecoResponseDTO.setFrete(new BigDecimal("7.85"));

        given(enderecoService.buscarEnderecoPorCep(enderecoRequestDTO)).willReturn(enderecoResponseDTO);

        mockMvc.perform(post("/consultar-endereco")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(enderecoRequestDTO)))
                .andExpect(status().isOk());
    }

}
