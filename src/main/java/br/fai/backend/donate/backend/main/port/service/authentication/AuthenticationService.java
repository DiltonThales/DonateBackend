package br.fai.backend.donate.backend.main.port.service.authentication;

import br.fai.backend.donate.backend.main.domain.UserModel;

public interface AuthenticationService {
    UserModel authentication(final String email, final String password);
}
