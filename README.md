# Consulta de Endereço e Cálculo de Frete

## Essa é uma API desenvolvida com Java 11 e Spring Boot para ser usado como base para o desafio a seguir:
O objetivo principal do desafio é construir uma API REST de consulta de endereço e cálculo de frete para um determinado CEP

## Especificação da API
A API possui um endpoint para consulta do endereço e cálculo do frete, que pode ser acessado através da URL:

```bash
POST /consulta-endereco
```
## Request
A requisição deve ser feita com um JSON no corpo da mensagem, contendo o campo cep com o valor do CEP desejado. O CEP pode ser passado com ou sem máscara na entrada.

Exemplo de request:

```bash
{
    "cep": "01001000"
}
```

## Response
O servidor responde com um JSON no corpo da mensagem, contendo as informações do endereço correspondente ao CEP informado e o valor do frete para a região.

Se o CEP não for encontrado, o servidor responde com um código HTTP 404 Not Found e uma mensagem informativa.

Exemplo de response bem sucedido:

```bash
{    
    "cep": "01001-000",
    "rua": "Praça da Sé",
    "complemento": "lado ímpar",
    "bairro": "Sé",
    "cidade": "São Paulo",
    "estado": "SP",
    "frete": 7.85
}
```

## Valor do frete
O valor do frete é fixo de acordo com as regiões do Brasil:

Sudeste: R$ 7,85
Centro-Oeste: R$ 12,50
Nordeste: R$ 15,98
Sul: R$ 17,30
Norte: R$ 20,83

# Execução do projeto
Para executar o projeto, é necessário ter o Java 11 e o Maven instalados na máquina.

Clone o repositório:
