package com.fiap.tech_challenge_03.infra.cadastro.entity;

import com.fiap.tech_challenge_03.domain.cadastro.entity.Restaurante;
import com.fiap.tech_challenge_03.domain.cadastro.vo.Fucionamento;
import com.fiap.tech_challenge_03.domain.cadastro.vo.Localidade;
import com.fiap.tech_challenge_03.domain.cadastro.vo.NomeRestaurante;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Data
@Document("restaurante")
public class RestauranteEntity {

    @Id
    private String id;
    private NomeRestaurante nome;
    private Localidade localidade;
    private Fucionamento funcionamento;
    private Integer capacidade;
    private String tipoDeCozinha;

    public RestauranteEntity(Restaurante restaurante) {
        Objects.requireNonNull(restaurante);

        this.id = restaurante.getId();
        this.nome = restaurante.getNome();
        this.localidade = restaurante.getLocalidade();
        this.funcionamento = restaurante.getFuncionamento();
        this.capacidade = restaurante.getCapacidade();
        this.tipoDeCozinha = restaurante.getTipoDeCozinha();
    }

    public Restaurante toRestaurante() {
        return new Restaurante(this.getNome().nome(), this.getLocalidade(), this.getFuncionamento(),
                this.capacidade, this.tipoDeCozinha);
    }
}
