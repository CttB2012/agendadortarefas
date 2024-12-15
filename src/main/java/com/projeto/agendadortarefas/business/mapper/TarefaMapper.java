package com.projeto.agendadortarefas.business.mapper;

import com.projeto.agendadortarefas.business.dto.TarefasDTO;
import com.projeto.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefaMapper {

    TarefasEntity tarefaDtoParaTarefaEntity(TarefasDTO tarefasDTO);

    TarefasDTO tarefaEntityParaTarefaDTO(TarefasEntity tarefasEntity);

}
