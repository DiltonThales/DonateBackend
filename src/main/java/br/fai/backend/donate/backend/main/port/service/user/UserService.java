package br.fai.backend.donate.backend.main.port.service.user;

import br.fai.backend.donate.backend.main.domain.UsuarioModel;
import br.fai.backend.donate.backend.main.port.dao.crud.CrudDao;
import br.fai.backend.donate.backend.main.port.dao.user.ReadByEmailDao;
import br.fai.backend.donate.backend.main.port.dao.user.UpdatePasswordDao;
import br.fai.backend.donate.backend.main.port.service.authentication.AuthenticationService;
import br.fai.backend.donate.backend.main.port.service.crud.CrudService;


public interface UserService extends CrudService<UsuarioModel>, UpdatePasswordService, ReadByEmailService
        , AuthenticationService, RecoveryPasswordService {
}
