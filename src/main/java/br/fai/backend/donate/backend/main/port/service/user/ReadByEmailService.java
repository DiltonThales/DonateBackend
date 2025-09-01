package br.fai.backend.donate.backend.main.port.service.user;

import br.fai.backend.donate.backend.main.domain.UserModel;

public interface ReadByEmailService {
    UserModel findByEmail(final String email);
}
