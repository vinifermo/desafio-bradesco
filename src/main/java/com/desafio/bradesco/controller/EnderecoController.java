package com.desafio.bradesco.controller;

import com.desafio.bradesco.dto.EnderecoRequestDTO;
import com.desafio.bradesco.dto.EnderecoResponseDTO;
import com.desafio.bradesco.service.EnderecoService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/consultar-endereco")
public class EnderecoController {

    private final EnderecoService service;

    @ApiOperation(value = "Buscar Endereço Via Cep")
    @PostMapping
    public EnderecoResponseDTO buscarEnderecoViaCep(@Valid @RequestBody EnderecoRequestDTO enderecoRequestDTO) {
        return service.buscarEnderecoPorCep(enderecoRequestDTO);
    }

}
