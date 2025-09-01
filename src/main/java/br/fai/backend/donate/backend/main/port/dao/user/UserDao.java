package br.fai.backend.donate.backend.main.port.dao.user;

import br.fai.backend.donate.backend.main.domain.UserModel;
import br.fai.backend.donate.backend.main.port.dao.crud.CrudDao;
import br.fai.backend.donate.backend.main.port.dao.crud.UpdateDao;

public interface UserDao extends CrudDao<UserModel>, ReadByEmailDao, UpdatePasswordDao {
}
