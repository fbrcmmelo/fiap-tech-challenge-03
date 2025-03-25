package com.fiap.tech_challenge_03.domain.cadastro.vo;

import com.fiap.tech_challenge_03.domain.DomainException;
import com.fiap.tech_challenge_03.domain.cadastro.enums.StatusMesa;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor
public class Mesa {

    private String id;
    private String tag;
    private StatusMesa statusMesa;

    public Mesa(Integer tagId) {
        Objects.requireNonNull(tagId, "Tag Id não pode ser nulo");

        if (tagId <= 0) {
            throw new DomainException("Tag id não pode ser menor ou igual a zero");
        }

        this.id = String.valueOf(tagId);
        this.tag = "Mesa-".concat(String.valueOf(tagId));
        this.statusMesa = StatusMesa.ATIVO;
    }
}
