package com.projeto.agendadortarefas.controller;


import com.projeto.agendadortarefas.business.TarefasService;
import com.projeto.agendadortarefas.business.dto.TarefasDTO;
import com.projeto.agendadortarefas.infrastructure.entity.TarefasEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
