package com.desafio.bradesco.service;

import com.desafio.bradesco.dto.ViaCepDTO;
import com.desafio.bradesco.exception.EnderecoNaoEncontrado;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static com.desafio.bradesco.exception.CustomExceptionHandler.ENDERECO_NAO_ENCONTRADO;

@Service
@RequiredArgsConstructor
public class ViaCepService {

    public static final String VIA_CEP_URL = "https://viacep.com.br/ws/%s/json/";

    private final RestTemplate restTemplate = new RestTemplate();

    public ViaCepDTO buscarEnderecoPorCep(String cep) {
        String url = String.format(VIA_CEP_URL,cep);
        ViaCepDTO response = restTemplate.getForObject(url, ViaCepDTO.class);

        if (response == null || response.getCep() == null) {
            throw new EnderecoNaoEncontrado(String.format(ENDERECO_NAO_ENCONTRADO));
        }

        return response;
    }

}
