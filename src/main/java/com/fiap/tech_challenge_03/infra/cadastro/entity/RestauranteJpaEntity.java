package com.fiap.tech_challenge_03.infra.cadastro.entity;

import com.fiap.tech_challenge_03.application.cadastro.input.BuscarRestauranteInput;
import com.fiap.tech_challenge_03.domain.cadastro.entity.Restaurante;
import com.fiap.tech_challenge_03.domain.cadastro.vo.Funcionamento;
import com.fiap.tech_challenge_03.domain.cadastro.vo.Localidade;
import com.fiap.tech_challenge_03.domain.cadastro.vo.Mesa;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@NoArgsConstructor
@Data
@Document("restaurante")
public class RestauranteJpaEntity {

    @Id
    private String id;
    private String nome;
    private Integer numero;
    private String lougradouro;
    private String cidade;
    private String estado;
    private String horaInicial;
    private String horaFinal;
    private String diasDaSemana;
    private Integer quantidadeMesas;
    private String tipoDeCozinha;
    private List<Mesa> mesas;

    public RestauranteJpaEntity(Restaurante restaurante) {
        Objects.requireNonNull(restaurante);

        this.id = restaurante.getId();
        this.nome = restaurante.getNome().nome();
        this.numero = restaurante.getLocalidade().getNumero();
        this.lougradouro = restaurante.getLocalidade().getLougradouro();
        this.cidade = restaurante.getLocalidade().getCidade();
        this.estado = restaurante.getLocalidade().getEstado();
        this.horaInicial = restaurante.getFuncionamento().getHoraInicial();
        this.horaFinal = restaurante.getFuncionamento().getHoraFinal();
        this.diasDaSemana = restaurante.getFuncionamento().getDiasDaSemana().stream().map(String::valueOf).collect(
                Collectors.joining(","));
        this.quantidadeMesas = restaurante.getQuantidadeMesas();
        this.tipoDeCozinha = restaurante.getTipoDeCozinha();
        this.mesas = restaurante.getMesas();
    }

    public RestauranteJpaEntity(BuscarRestauranteInput input) {
        Objects.requireNonNull(input);

        this.nome = input.nome();
        this.lougradouro = input.logradouro();
        this.cidade = input.cidade();
        this.estado = input.estado();
        this.tipoDeCozinha = input.tipoDeCozinha();
    }

    public Restaurante toRestaurante() {
        return new Restaurante(this.id, this.nome, new Localidade(this.numero, this.lougradouro,
                this.cidade, this.estado),
                new Funcionamento(this.horaInicial, this.horaFinal,
                        Stream.of(diasDaSemana.split(",")).map(Integer::parseInt).collect(Collectors.toSet())),
                this.quantidadeMesas,
                this.tipoDeCozinha);
    }

    public Example<RestauranteJpaEntity> toExample() {
        var matcher = ExampleMatcher.matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        return Example.of(this, matcher);
    }
}
