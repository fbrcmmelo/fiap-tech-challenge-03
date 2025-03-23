package com.fiap.tech_challenge_03.utils;

import com.fiap.tech_challenge_03.domain.cadastro.vo.Funcionamento;
import com.fiap.tech_challenge_03.domain.cadastro.vo.Localidade;

import java.util.Set;

public class ValueObjectBuilder {

    public static Funcionamento getFuncionamento() {
        return new Funcionamento("08:00", "22:00", Set.of(1, 2));
    }

    public static Localidade getLocalidade() {
        return new Localidade(123, "Casa", "Cidade Teste", "Estado Teste");
    }
}
