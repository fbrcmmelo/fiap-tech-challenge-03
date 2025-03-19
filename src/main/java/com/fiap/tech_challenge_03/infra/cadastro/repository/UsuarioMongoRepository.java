package com.fiap.tech_challenge_03.infra.cadastro.repository;

import com.fiap.tech_challenge_03.infra.cadastro.entity.UsuarioJpaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioMongoRepository extends MongoRepository<UsuarioJpaEntity, String> {
}
