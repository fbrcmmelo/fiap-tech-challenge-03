package com.fiap.tech_challenge_03.infra.interfaces;

public interface IUseCaseIO<I, O> {
    O execute(I input);
}
