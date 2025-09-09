package br.fai.backend.donate.backend.main.security;

import br.fai.backend.donate.backend.main.domain.UsuarioModel;
import br.fai.backend.donate.backend.main.dto.JwtTokenDto;
import br.fai.backend.donate.backend.main.port.service.authentication.AuthenticationService;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//@Profile("sec")
@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public AuthenticationController(AuthenticationService authenticationService,
                                    JwtService jwtService,
                                    UserDetailsService userDetailsService) {
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/auth")
    public ResponseEntity<JwtTokenDto> authentication(@RequestBody AuthenticationRequest request) {

        String email = request.getEmail();
        String senha = request.getSenha();

        // Autenticação via AuthenticationService
        UsuarioModel authenticatedUser = authenticationService.authentication(email, senha);
        if (authenticatedUser == null) {
            throw new BadCredentialsException("Email ou senha inválidos");
        }

        // Carrega UserDetails para Spring Security
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);
        if (userDetails == null) {
            throw new UsernameNotFoundException("Email não encontrado");
        }

        // Geração do token JWT
        String jwtToken = jwtService.generateToken(
                userDetails,
                authenticatedUser.getNome(),
                authenticatedUser.getUserRole(), // Role do usuário
                authenticatedUser.getEmail(),
                authenticatedUser.getId()
        );

        if (jwtToken == null || jwtToken.isEmpty()) {
            throw new InternalError("Token JWT inválido");
        }

        JwtTokenDto tokenDto = new JwtTokenDto(jwtToken);
        System.out.println("JWT criado: " + jwtToken);

        return ResponseEntity.ok(tokenDto);
    }
}
