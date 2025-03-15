package com.fiap.tech_challenge_03.infra.gateway;

import com.fiap.tech_challenge_03.infra.interfaces.IDomainEventPub;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;

@Slf4j
@RequiredArgsConstructor
public class DomainEventPubImpl implements IDomainEventPub {

    @NonNull
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void publish(Object event) {
        log.info("Publishing event: {}", event);
        applicationEventPublisher.publishEvent(event);
    }

}