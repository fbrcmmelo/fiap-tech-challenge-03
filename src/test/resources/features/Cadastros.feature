Feature: API - Cadastros

  Scenario: Cadastrar um novo restaurante
    When submeter um novo restaurante
    Then o restaurante é cadastrado com sucesso

  Scenario: Busca de restaurantes
    Given que haja restaurantes cadastrados
    When submeter uma busca com parâmetros
    And os parâmetros serem equivalentes à algum restaurante
    Then os restaurantes encontrados serão exibidos

  Scenario: Cadastrar um novo usuario
    When submeter um novo usuario
    Then o usuario é cadastrado com sucesso