Feature: API - Avaliacoes

  Scenario: Cadastrar uma nova avaliação
    Given que eu tenha um restaurante cadastrado
    And um usuario cadastrado
    When o usuario submeter uma nova avaliação ao restaurante
    And a avaliacao for válida
    Then a avaliação é cadastrada com sucesso
