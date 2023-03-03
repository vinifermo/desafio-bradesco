Feature: Consulta de endereço

  Scenario: Consultar endereço válido
    Given que um usuário consultou um endereço válido com CEP "01001000"
    Then o endereço deve ser retornado corretamente
    And o valor do frete deve ser calculado corretamente de acordo com a região