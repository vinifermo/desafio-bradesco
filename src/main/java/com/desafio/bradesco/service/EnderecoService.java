package com.desafio.bradesco.service;

import com.desafio.bradesco.dto.EnderecoRequestDTO;
import com.desafio.bradesco.dto.EnderecoResponseDTO;
import com.desafio.bradesco.dto.ViaCepDTO;
import com.desafio.bradesco.enums.Regiao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final ViaCepService viaCEPService;

    public EnderecoResponseDTO buscarEnderecoPorCep(EnderecoRequestDTO enderecoRequestDTO) {
        ViaCepDTO response = viaCEPService.buscarEnderecoPorCep(enderecoRequestDTO.getCep());

        BigDecimal frete = Regiao.getValorFrete(response.getUf());
        response.setFrete(frete);

        EnderecoResponseDTO enderecoResponseDTO = new EnderecoResponseDTO();
        enderecoResponseDTO.setCep(response.getCep());
        enderecoResponseDTO.setRua(response.getLogradouro());
        enderecoResponseDTO.setComplemento(response.getComplemento());
        enderecoResponseDTO.setCidade(response.getLocalidade());
        enderecoResponseDTO.setEstado(response.getUf());
        enderecoResponseDTO.setBairro(response.getBairro());
        enderecoResponseDTO.setFrete(response.getFrete());

        return enderecoResponseDTO;
    }

}
