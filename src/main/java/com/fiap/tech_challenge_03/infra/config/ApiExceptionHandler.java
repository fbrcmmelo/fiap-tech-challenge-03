package com.fiap.tech_challenge_03.infra.config;

import com.fiap.tech_challenge_03.infra.exception.ApplicationException;
import com.fiap.tech_challenge_03.infra.exception.DomainException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(DomainException.class)
    ProblemDetail handleDomainException(DomainException ex) {
        var problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Exceção na regra de negócio");
        problemDetail.setDetail(ex.getMessage());
        problemDetail.setProperty("at", Instant.now());

        return problemDetail;
    }

    @ExceptionHandler(ApplicationException.class)
    ProblemDetail handleApplicationException(ApplicationException ex) {
        var problemDetail = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        problemDetail.setTitle("Exceção no processamento da aplicação");
        problemDetail.setDetail(ex.getMessage());
        problemDetail.setProperty("at", Instant.now());

        return problemDetail;
    }

}
