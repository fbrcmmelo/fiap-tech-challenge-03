package com.fiap.tech_challenge_03.infra.reserva.repository;

import com.fiap.tech_challenge_03.infra.reserva.entity.ReservaJpaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaMongoRepository extends MongoRepository<ReservaJpaEntity, String> {

}
