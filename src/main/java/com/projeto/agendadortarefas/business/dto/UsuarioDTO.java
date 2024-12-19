package com.projeto.agendadortarefas.business.dto;


import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UsuarioDTO {

    private String email;
    private String senha;
}
