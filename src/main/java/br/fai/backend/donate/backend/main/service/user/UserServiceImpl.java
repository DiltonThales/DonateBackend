package br.fai.backend.donate.backend.main.service.user;

import br.fai.backend.donate.backend.main.domain.UsuarioModel;
import br.fai.backend.donate.backend.main.domain.UsuarioModel.UserRole;
import br.fai.backend.donate.backend.main.port.dao.user.UserDao;
import br.fai.backend.donate.backend.main.port.service.user.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    // =================== CRUD ===================
    @Override
    public int create(UsuarioModel entity) {
        if (entity == null || entity.getNome().isEmpty() || entity.getSenha().isEmpty() || entity.getEmail().isEmpty()) {
            return 0;
        }
        entity.definirUserRole(); // define role automaticamente
        return userDao.add(entity);
    }

    @Override
    public void delete(int id) {
        if (id < 0) return;
        userDao.remove(id);
    }

    @Override
    public UsuarioModel findById(int id) {
        if (id < 0) return null;
        return userDao.readByID(id);
    }

    @Override
    public List<UsuarioModel> findAll() {
        return userDao.readAll();
    }

    @Override
    public void update(int id, UsuarioModel entity) {
        UsuarioModel usuarioExistente = findById(id);
        if (usuarioExistente == null) return;
        entity.definirUserRole();
        userDao.updateInformation(id, entity);
    }

    @Override
    public UsuarioModel findByEmail(String email) {
        if (email == null || email.isEmpty()) return null;
        return userDao.readByEmail(email);
    }

    // =================== Autenticação ===================
    @Override
    public UsuarioModel authentication(String email, String password) {
        UsuarioModel user = findByEmail(email);
        if (user == null || !user.getSenha().equals(password)) return null;
        user.definirUserRole();
        return user;
    }

    // =================== Senha ===================
    @Override
    public boolean updatePassword(int id, String oldPassword, String newPassword) {
        UsuarioModel user = findById(id);
        if (user == null || !user.getSenha().equals(oldPassword) || newPassword == null || newPassword.isEmpty()) {
            return false;
        }
        return userDao.updatePassword(id, newPassword);
    }

    @Override
    public boolean recoveryPassword(int id, String newPassword) {
        if (newPassword == null || newPassword.isEmpty()) return false;
        return userDao.updatePassword(id, newPassword);
    }

    // =================== Roles ===================
    public UserRole getUserRole(UsuarioModel usuario) {
        if (usuario == null) return null;
        return usuario.getUserRole();
    }
}
