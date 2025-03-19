package com.fiap.tech_challenge_03.infra.cadastro.repository;

import com.fiap.tech_challenge_03.infra.cadastro.entity.RestauranteJpaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteMongoRepository extends MongoRepository<RestauranteJpaEntity, String> {
}
