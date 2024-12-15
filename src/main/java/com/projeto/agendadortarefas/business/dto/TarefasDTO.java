package com.projeto.agendadortarefas.business.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projeto.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TarefasDTO {

    private String id;
    private String nomeDescricao;
    private String descricao;
    private String emailUsuario;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAlteracao;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataEvento;

    private StatusNotificacaoEnum statusNotificacaoEnum;

}
