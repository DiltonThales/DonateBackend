package br.fai.backend.donate.backend.main.port.dao.user;

import br.fai.backend.donate.backend.main.domain.UserModel;

public interface ReadByEmailDao {
    UserModel readByEmail(final String email);
}

