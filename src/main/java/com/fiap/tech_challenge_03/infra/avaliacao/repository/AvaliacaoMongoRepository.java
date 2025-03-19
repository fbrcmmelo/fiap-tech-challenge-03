package com.fiap.tech_challenge_03.infra.avaliacao.repository;

import com.fiap.tech_challenge_03.infra.avaliacao.entity.AvaliacaoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvaliacaoMongoRepository extends MongoRepository<AvaliacaoEntity, String> {
}
