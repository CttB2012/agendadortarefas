package com.projeto.agendadortarefas.infrastructure.repository;

import com.projeto.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TarefasRepository extends MongoRepository<TarefasEntity, String> {
}
