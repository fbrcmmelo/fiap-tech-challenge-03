package com.fiap.tech_challenge_03.application;

public interface IUseCaseIO<I, O> {
    O execute(I input);
}
