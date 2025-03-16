Feature: API - Cadastros

  Scenario: Cadastrar um novo restaurante
    When submeter um novo restaurante
    Then o restaurante é cadastrado com sucesso
