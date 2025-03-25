package com.fiap.tech_challenge_03.utils;

import com.fiap.tech_challenge_03.domain.cadastro.vo.Funcionamento;
import com.fiap.tech_challenge_03.domain.cadastro.vo.Localidade;
import com.fiap.tech_challenge_03.domain.cadastro.vo.Mesa;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ValueObjectBuilder {

    public static Funcionamento getFuncionamento() {
        return new Funcionamento("08:00", "22:00", Set.of(1, 2));
    }

    public static Localidade getLocalidade() {
        return new Localidade(123, "Casa", "Cidade Teste", "Estado Teste");
    }

    public static List<Mesa> getMesas() {
        List<Mesa> mesas = new ArrayList<>();

        for (var i = 1; i <= 10; i++) {
            mesas.add(new Mesa(i));
        }

        return mesas;
    }
}
