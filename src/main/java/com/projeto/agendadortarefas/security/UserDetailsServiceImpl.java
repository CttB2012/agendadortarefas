package com.projeto.agendadortarefas.security;


import com.projeto.agendadortarefas.business.dto.UsuarioDTO;
import com.projeto.agendadortarefas.client.UsuarioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl {

    @Autowired
    private UsuarioClient usuarioClient;

//    // Implementação do método para carregar detalhes do usuário pelo e-mail
//
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        // Busca o usuário no banco de dados pelo e-mail
//        Usuario usuario = usuarioRepository.findByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));
//
//        // Cria e retorna um objeto UserDetails com base no usuário encontrado
//        return org.springframework.security.core.userdetails.User
//                .withUsername(usuario.getEmail()) // Define o nome de usuário como o e-mail
//                .password(usuario.getSenha()) // Define a senha do usuário
//                .build(); // Constrói o objeto UserDetails
//    }


    public UserDetails loadUserInfo(String email, String token) {

        UsuarioDTO usuarioDTO = usuarioClient.buscarUsuarioByEmail(email, token);
        return User.withUsername(usuarioDTO.getEmail())
                .password(usuarioDTO.getSenha())
                .build();
    }

}
