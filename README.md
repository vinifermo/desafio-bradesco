# Consulta de Endereço e Cálculo de Frete

## Essa é uma API desenvolvida com Java 11 e Spring Boot para ser usado como base para o desafio a seguir:
O objetivo principal do desafio é construir uma API REST de consulta de endereço e cálculo de frete para um determinado CEP

## Especificação da API
A API possui um endpoint para consulta do endereço e cálculo do frete, que pode ser acessado através da URL:

curl --location --request POST 'http://localhost:8080/consulta-endereco' \
--header 'Content-Type: application/json' \
--data-raw '{
    "cep": "01001000"
}
