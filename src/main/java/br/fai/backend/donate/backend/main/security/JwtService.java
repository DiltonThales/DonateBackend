package br.fai.backend.donate.backend.main.security;

import br.fai.backend.donate.backend.main.domain.UsuarioModel;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public interface JwtService {

    String getEmailFromToken(String token);

    Date getExpirationDateFromToken(String token);

    <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver);

    Claims getAllClaimsFromToken(String token);

    boolean isTokenExpired(String token);

    boolean isTokenValid(String token, UserDetails userDetails);

    String generateToken(final UserDetails userDetails,
                         final String fullName,
                         final UsuarioModel.UserRole role,
                         final String email,
                         final int id);

    String createToken(Map<String, Object> claims, String subject);
}
