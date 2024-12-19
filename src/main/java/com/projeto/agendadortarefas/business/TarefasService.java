package com.projeto.agendadortarefas.business;

import com.projeto.agendadortarefas.business.dto.TarefasDTO;
import com.projeto.agendadortarefas.business.mapper.TarefaMapper;
import com.projeto.agendadortarefas.business.mapper.TarefaUpdateConverter;
import com.projeto.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.projeto.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.projeto.agendadortarefas.infrastructure.exceptions.ResourceNotFoundException;
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
    private final TarefaUpdateConverter _tarefaUpdateConverter;

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

    public void excluirPelaId(String id){

        try {
            _tarefasRepository.deleteById(id);
        }catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro: Id não encontrado." + id, e.getCause());
        }
    }

    public TarefasDTO alterarStatusTarefa(StatusNotificacaoEnum status, String id){
        try{
            TarefasEntity tarefasEntity = _tarefasRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("Erro: Id não encontrado." + id ));
            tarefasEntity.setStatusNotificacaoEnum(status);
            TarefasEntity entidadeSalva =  _tarefasRepository.save(tarefasEntity);
            return   _tarefaMapper.tarefaEntityParaTarefaDTO(entidadeSalva);
        }catch (ResourceNotFoundException e ){
            throw new ResourceNotFoundException("Erro ao alterar o status da tarefa" + e.getCause());
        }
    }

    public TarefasDTO atualizarTarefa(TarefasDTO tarefasDTO, String id){

        try{
            TarefasEntity tarefasEntity = _tarefasRepository.findById(id).orElseThrow(() ->
                    new ResourceNotFoundException("Erro: Id não encontrado." + id ));

            _tarefaUpdateConverter.updateTarefa(tarefasDTO, tarefasEntity);
            TarefasEntity entidadeSalva = _tarefasRepository.save(tarefasEntity);
            return _tarefaMapper.tarefaEntityParaTarefaDTO(tarefasEntity);
        }catch (ResourceNotFoundException e ){
            throw new ResourceNotFoundException("Erro ao alterar o status da tarefa" + e.getCause());
        }
    }

}
