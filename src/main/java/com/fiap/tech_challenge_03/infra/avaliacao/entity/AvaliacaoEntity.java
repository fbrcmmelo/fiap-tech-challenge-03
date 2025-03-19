package com.fiap.tech_challenge_03.infra.avaliacao.entity;

import com.fiap.tech_challenge_03.domain.avaliacao.entity.Avaliacao;
import com.fiap.tech_challenge_03.domain.cadastro.entity.Restaurante;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@NoArgsConstructor
@Data
@Document("avaliacao")
public class AvaliacaoEntity {

    @Id
    private String id;
    private Integer nota;
    private String comentario;
    private Object avaliador;
    @DBRef
    private Restaurante restaurante;

    public AvaliacaoEntity(Avaliacao avaliacao) {
        Objects.requireNonNull(avaliacao);

        this.id = avaliacao.getId();
        this.nota = avaliacao.getNota().getValorNota();
        this.comentario = avaliacao.getComentario();
        this.avaliador = avaliacao.getAvaliador();
        this.restaurante = avaliacao.getRestaurante();
    }

    public Avaliacao toAvaliacao() {
        return new Avaliacao(this.nota, this.comentario, this.avaliador, this.restaurante);
    }

}
