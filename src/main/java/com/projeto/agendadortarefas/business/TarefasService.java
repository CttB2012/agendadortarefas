package com.projeto.agendadortarefas.business;

import com.projeto.agendadortarefas.business.dto.TarefasDTO;
import com.projeto.agendadortarefas.business.mapper.TarefaMapper;
import com.projeto.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.projeto.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.projeto.agendadortarefas.infrastructure.repository.TarefasRepository;
import com.projeto.agendadortarefas.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasRepository _tarefasRepository;
    private final TarefaMapper _tarefaMapper;
    private final JwtUtil _jwtUtil;

    public TarefasDTO salvarNovaTarefa(String token, TarefasDTO tarefasDTO){

        String email = _jwtUtil.extrairEmailToken(token.substring(7));

        tarefasDTO.setEmailUsuario(email);
        tarefasDTO.setDataCriacao(LocalDateTime.now());
        tarefasDTO.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);

        TarefasEntity tarefasEntity = _tarefaMapper.tarefaDtoParaTarefaEntity(tarefasDTO);

        return _tarefaMapper.tarefaEntityParaTarefaDTO(
                _tarefasRepository.save(tarefasEntity));
    }

    public List<TarefasDTO> buscarTarefasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal){
        return _tarefaMapper.listTarefasEntityParaListTarefasDTO(
                _tarefasRepository.findByDataEventoBetween(dataInicial, dataFinal));
    }

    public List<TarefasDTO> buscarTarefasPorEmail(String token){
        String email = _jwtUtil.extrairEmailToken(token.substring(7));

        List<TarefasEntity> listaTarefas = _tarefasRepository.findByEmailUsuario(email);
        return _tarefaMapper.listTarefasEntityParaListTarefasDTO(listaTarefas);
    }

}
