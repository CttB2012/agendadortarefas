package com.projeto.agendadortarefas.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projeto.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("tarefa")
public class TarefasEntity {

    @Id
    private String id;
    private String nomeDescricao;
    private String descricao;
    private String emailUsuario;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAlteracao;
    private LocalDateTime dataEvento;
    private StatusNotificacaoEnum statusNotificacaoEnum;
}
