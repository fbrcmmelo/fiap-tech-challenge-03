package com.fiap.tech_challenge_03.infra.cadastro.entity;

import com.fiap.tech_challenge_03.domain.cadastro.entity.Usuario;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@Data
@Document("usuario")
public class UsuarioJpaEntity {

    @Id
    private String id;
    private String nome;
    private String email;
    private String senha;
    private String cpf;

    public UsuarioJpaEntity(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail().getValue();
        this.senha = usuario.getSenha();
        this.cpf = usuario.getCpf().getCpfFormatado();
    }

    public Usuario toUsuario() {
        return new Usuario(id, nome, cpf, email, senha);
    }
}
