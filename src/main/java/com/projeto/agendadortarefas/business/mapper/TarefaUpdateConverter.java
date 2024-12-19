package com.projeto.agendadortarefas.business.mapper;


import com.projeto.agendadortarefas.business.dto.TarefasDTO;
import com.projeto.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefaUpdateConverter {

    void updateTarefa(TarefasDTO tarefasDTO, @MappingTarget TarefasEntity tarefasEntity);

}
