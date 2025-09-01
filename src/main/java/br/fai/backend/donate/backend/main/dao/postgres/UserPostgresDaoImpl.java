package br.fai.backend.donate.backend.main.dao.postgres;

import br.fai.backend.donate.backend.main.domain.UserModel;
import br.fai.backend.donate.backend.main.port.dao.user.UserDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

public class UserPostgresDaoImpl implements UserDao {

    private static final Logger logger = Logger
            .getLogger(UserPostgresDaoImpl.class.getName());

    private final Connection connection;

    public UserPostgresDaoImpl(Connection connection) {
        this.connection = connection;
    }


    @Override
    public int add(UserModel entity) { // ao adicionar é retornado o ID

        String sql = "INSERT INTO user_model(password, fullName, email) ";
        sql += " VALUES (?,?, ?);";

       // Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;


        try {
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(sql,
                    PreparedStatement.RETURN_GENERATED_KEYS); //sql entrega o id

            preparedStatement.setString(1, entity.getPassword());
            preparedStatement.setString(2, entity.getFullName());
            preparedStatement.setString(3, entity.getEmail());

            preparedStatement.execute();

            resultSet = preparedStatement.getGeneratedKeys();   // recebe o valor para consumo
            if (resultSet.next()){ // next se tem valor para consumo
                final int id = resultSet.getInt(1); //indice da coluna das chaves geradas (? <-, ?, ?) ou id
                entity.setId(id); //insere o valor na entidade
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
        //logger.log(Level.SEVERE,"teste");
        return entity.getId(); // entity.getId()
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public UserModel readByID(int id) {
        return null;
    }

    @Override
    public List<UserModel> readAll() {
        return null;
    }

    @Override
    public void updateInformation(int id, UserModel entity) {

    }

    @Override
    public UserModel readByEmail(String email) {
        return null;
    }

    @Override
    public boolean updatePassword(int id, String newPassword) {
        return false;
    }
}
