package com.fiap.tech_challenge_03.infra.avaliacao.entity;

import com.fiap.tech_challenge_03.domain.avaliacao.entity.Avaliacao;
import com.fiap.tech_challenge_03.infra.cadastro.entity.RestauranteJpaEntity;
import com.fiap.tech_challenge_03.infra.cadastro.entity.UsuarioJpaEntity;
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
    @DBRef
    private UsuarioJpaEntity avaliador;
    @DBRef
    private RestauranteJpaEntity restaurante;

    public AvaliacaoEntity(Avaliacao avaliacao) {
        Objects.requireNonNull(avaliacao);

        this.id = avaliacao.getId();
        this.nota = avaliacao.getNota().getValorNota();
        this.comentario = avaliacao.getComentario();
        this.avaliador = new UsuarioJpaEntity(avaliacao.getAvaliador());
        this.restaurante = new RestauranteJpaEntity(avaliacao.getRestaurante());
    }

    public Avaliacao toAvaliacao() {
        return new Avaliacao(this.id, this.nota, this.comentario, this.avaliador.toUsuario(),
                this.restaurante.toRestaurante()
        );
    }

}
