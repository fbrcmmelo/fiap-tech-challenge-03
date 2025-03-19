package com.fiap.tech_challenge_03.infra.adapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.tech_challenge_03.application.ApplicationException;

public class Presenter {

    public static String jsonFrom(Object response) {
        try {
            return new ObjectMapper().writeValueAsString(response);
        } catch (JsonProcessingException e) {
            throw new ApplicationException(e.getMessage());
        }
    }
}
