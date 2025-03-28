package com.fiap.tech_challenge_03.domain.cadastro.usecase;

import com.fiap.tech_challenge_03.application.cadastro.input.CadastrarRestauranteInput;
import com.fiap.tech_challenge_03.application.cadastro.output.RestauranteOutput;
import com.fiap.tech_challenge_03.application.cadastro.usecase.ICadastrarRestauranteUseCase;
import com.fiap.tech_challenge_03.domain.cadastro.entity.Restaurante;
import com.fiap.tech_challenge_03.domain.cadastro.service.RestauranteDomainService;
import com.fiap.tech_challenge_03.domain.cadastro.vo.Funcionamento;
import com.fiap.tech_challenge_03.domain.cadastro.vo.Localidade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CadastrarRestauranteUseCase implements ICadastrarRestauranteUseCase {

    private final RestauranteDomainService service;


    @Override
    public RestauranteOutput execute(final CadastrarRestauranteInput input) {
        final var localidade = new Localidade(input.numero(), input.logradouro(), input.cidade(), input.estado());
        final var funcionamento = new Funcionamento(input.horaIniFuncionamento(), input.horaFimFuncionamento(),
                input.diasDaSemanaFunc());
        final var restaurante = this.service.cadastrar(new Restaurante(null, input.nome(), localidade, funcionamento,
                input.quantidadeDeMesas(), input.tipoDeCozinha()));

        return RestauranteOutput.from(restaurante);
    }
}
