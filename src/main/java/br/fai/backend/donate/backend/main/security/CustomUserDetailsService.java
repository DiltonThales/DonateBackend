package br.fai.backend.donate.backend.main.security;

import br.fai.backend.donate.backend.main.domain.UsuarioModel;
import br.fai.backend.donate.backend.main.port.service.user.UserService;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

//@Profile("sec")
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (email == null || email.isEmpty()) {
            throw new RuntimeException("Email nulo ou vazio");
        }

        UsuarioModel usuario = userService.findByEmail(email);

        if (usuario == null) {
            throw new UsernameNotFoundException("Email não encontrado: " + email);
        }

        // Define a role automaticamente se ainda não estiver definida
        if (usuario.getUserRole() == null) {
            usuario.definirUserRole();
        }

        // Cria a lista de authorities do usuário
        List<GrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority(usuario.getUserRole().name())
        );

        // Retorna o User do Spring Security
        return new User(usuario.getEmail(), usuario.getSenha(), authorities);
    }
}
