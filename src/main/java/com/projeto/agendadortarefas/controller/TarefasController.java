package com.projeto.agendadortarefas.controller;


import com.projeto.agendadortarefas.business.TarefasService;
import com.projeto.agendadortarefas.business.dto.TarefasDTO;
import com.projeto.agendadortarefas.infrastructure.entity.TarefasEntity;
import com.projeto.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefasController {

    private final TarefasService _tarefasService;

    @PostMapping
    public ResponseEntity<TarefasDTO> salvarNovaTarefa(@RequestBody TarefasDTO tarefasDTO,
                                                       @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(_tarefasService.salvarNovaTarefa(token, tarefasDTO));
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefasDTO>> buscarTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal){

        return ResponseEntity.ok(_tarefasService.buscarTarefasPorPeriodo(dataInicial, dataFinal));
    }

    @GetMapping
    public ResponseEntity<List<TarefasDTO>> buscarTarefasPorEmail(@RequestHeader("Authorization") String token) {

        List<TarefasDTO> tarefas = _tarefasService.buscarTarefasPorEmail(token);
        return ResponseEntity.ok(tarefas);
    }

    @DeleteMapping
    public ResponseEntity<Void> excluirPorId(@RequestParam("id") String id) {
        _tarefasService.excluirPelaId(id);

        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<TarefasDTO> alterarStatusTarefa(@RequestParam("status") StatusNotificacaoEnum status,
                                                          @RequestParam("id") String id){
        return ResponseEntity.ok(_tarefasService.alterarStatusTarefa(status, id));
    }

    @PutMapping
    public ResponseEntity<TarefasDTO> atualizarTarefa(@RequestBody TarefasDTO tarefasDTO, @RequestParam("id") String id){

        return ResponseEntity.ok(_tarefasService.atualizarTarefa(tarefasDTO, id));
    }


}
