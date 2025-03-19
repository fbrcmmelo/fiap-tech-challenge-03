package com.fiap.tech_challenge_03.infra.cadastro.entity;

import com.fiap.tech_challenge_03.domain.cadastro.entity.Usuario;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("usuario")
public class UsuarioJpaEntity {

    @Id
    private String id;
    private String nome;

    public Usuario toUsuario() {
        return new Usuario(id, nome);
    }
}
