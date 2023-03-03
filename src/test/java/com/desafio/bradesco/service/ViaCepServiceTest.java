package com.desafio.bradesco.service;

import com.desafio.bradesco.dto.ViaCepDTO;
import com.desafio.bradesco.exception.EnderecoNaoEncontrado;
import com.desafio.bradesco.service.ViaCepService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@RequiredArgsConstructor
class ViaCepServiceTest {
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ViaCepService viaCepService;

    @Test
    public void whenValidCepProvidedThenReturnValidAddress() {
        ViaCepDTO mockResponse = new ViaCepDTO();
        mockResponse.setCep("01001-000");
        mockResponse.setLogradouro("Praça da Sé");
        mockResponse.setComplemento("lado ímpar");
        mockResponse.setBairro("Sé");
        mockResponse.setLocalidade("São Paulo");
        mockResponse.setUf("SP");

        String cep = "01001-000";
        String url = String.format(ViaCepService.VIA_CEP_URL, cep);

        restTemplate = Mockito.mock(RestTemplate.class);
        Mockito.when(restTemplate.getForObject(url, ViaCepDTO.class)).thenReturn(mockResponse);

        viaCepService = new ViaCepService();
        ViaCepDTO response = viaCepService.buscarEnderecoPorCep(cep);
        Assertions.assertEquals(mockResponse.getCep(), response.getCep());
        Assertions.assertEquals(mockResponse.getLogradouro(), response.getLogradouro());
        Assertions.assertEquals(mockResponse.getComplemento(), response.getComplemento());
        Assertions.assertEquals(mockResponse.getBairro(), response.getBairro());
        Assertions.assertEquals(mockResponse.getLocalidade(), response.getLocalidade());
        Assertions.assertEquals(mockResponse.getUf(), response.getUf());
    }

    @Test
    public void whenInvalidCepProvidedThenReturnEnderecoNotFound() {
        String cep = "99999-999";
        String url = String.format(ViaCepService.VIA_CEP_URL, cep);

        restTemplate = Mockito.mock(RestTemplate.class);
        Mockito.when(restTemplate.getForObject(url, ViaCepDTO.class)).thenReturn(null);

        viaCepService = new ViaCepService();
        Assertions.assertThrows(EnderecoNaoEncontrado.class, () -> {
            viaCepService.buscarEnderecoPorCep(cep);
        });
    }
}
