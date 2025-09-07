package br.fai.backend.donate.backend.main.dao.postgres;

import br.fai.backend.donate.backend.main.domain.UsuarioModel;
import br.fai.backend.donate.backend.main.port.dao.user.UserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class UserPostgresDaoImpl implements UserDao {

    private static final Logger logger =
            Logger.getLogger(UserPostgresDaoImpl.class.getName());

    private final Connection connection;

    public UserPostgresDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public int add(UsuarioModel entity) { // ao adicionar é retornado o ID

        String sql = "INSERT INTO usuario_model " +
                "(nome, telefone, senha, email, cpf, doadora, receptora, profissional, admin, latitude, longitude) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        PreparedStatement preparedStatement;
        ResultSet resultSet;

        try {
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql,
                    PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, entity.getNome());
            preparedStatement.setString(2, entity.getTelefone());
            preparedStatement.setString(3, entity.getSenha());
            preparedStatement.setString(4, entity.getEmail());
            preparedStatement.setString(5, entity.getCpf());
            preparedStatement.setBoolean(6, entity.getDoadora() != null ? entity.getDoadora() : false);
            preparedStatement.setBoolean(7, entity.getReceptora() != null ? entity.getReceptora() : false);
            preparedStatement.setBoolean(8, entity.getProfissional() != null ? entity.getProfissional() : false);
            preparedStatement.setBoolean(9, entity.getAdmin() != null ? entity.getAdmin() : false);
            if (entity.getLatitude() != null) {
                preparedStatement.setDouble(10, entity.getLatitude());
            } else {
                preparedStatement.setNull(10, java.sql.Types.DOUBLE);
            }
            if (entity.getLongitude() != null) {
                preparedStatement.setDouble(11, entity.getLongitude());
            } else {
                preparedStatement.setNull(11, java.sql.Types.DOUBLE);
            }

            preparedStatement.execute();

            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                final int id = resultSet.getInt(1);
                entity.setId(id);
            }

            connection.commit();

            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }

        return entity.getId();
    }

    @Override
    public void remove(int id) {
        // implementar delete
    }

    @Override
    public UsuarioModel readByID(int id) {
        return null;
    }

    @Override
    public List<UsuarioModel> readAll() {
        return null;
    }

    @Override
    public void updateInformation(int id, UsuarioModel entity) {
        // implementar update
    }

    @Override
    public UsuarioModel readByEmail(String email) {
        return null;
    }

    @Override
    public boolean updatePassword(int id, String newPassword) {
        return false;
    }
}
