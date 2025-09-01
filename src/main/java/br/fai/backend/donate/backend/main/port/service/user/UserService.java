package br.fai.backend.donate.backend.main.port.service.user;

import br.fai.backend.donate.backend.main.domain.UserModel;
import br.fai.backend.donate.backend.main.port.service.crud.CrudService;

public interface UserService extends CrudService<UserModel>, ReadByEmailService, UpdatePasswordService {
}
