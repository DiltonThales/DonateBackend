package br.fai.backend.donate.backend.main.dao.h2;

import br.fai.backend.donate.backend.main.domain.UsuarioModel;
import br.fai.backend.donate.backend.main.port.dao.user.UserDao;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Repository
public class UserH2DaoImpl implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserH2DaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        System.out.println("Ganhei uma instância do UserH2Dao");
    }

    @Override
    public int add(UsuarioModel entity) {
        final SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("usuario_model")
                .usingGeneratedKeyColumns("id");

        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("nome", entity.getNome());
        parameters.put("telefone", entity.getTelefone());
        parameters.put("senha", entity.getSenha());
        parameters.put("email", entity.getEmail());
        parameters.put("cpf", entity.getCpf());
        parameters.put("doadora", entity.getDoadora() != null ? entity.getDoadora() : false);
        parameters.put("receptora", entity.getReceptora() != null ? entity.getReceptora() : false);
        parameters.put("profissional", entity.getProfissional() != null ? entity.getProfissional() : false);
        parameters.put("admin", entity.getAdmin() != null ? entity.getAdmin() : false);
        parameters.put("latitude", entity.getLatitude());
        parameters.put("longitude", entity.getLongitude());

        final Number id = simpleJdbcInsert.executeAndReturnKey(parameters);
        return id.intValue();
    }

    @Override
    public void remove(int id) {
        final String sql = "DELETE FROM usuario_model WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public UsuarioModel readByID(int id) {
        final String sql = "SELECT * FROM usuario_model WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id},
                (rs, rowNum) -> {
                    UsuarioModel usuario = new UsuarioModel();
                    usuario.setId(rs.getInt("id"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setTelefone(rs.getString("telefone"));
                    usuario.setSenha(rs.getString("senha"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setCpf(rs.getString("cpf"));
                    usuario.setDoadora(rs.getBoolean("doadora"));
                    usuario.setReceptora(rs.getBoolean("receptora"));
                    usuario.setProfissional(rs.getBoolean("profissional"));
                    usuario.setAdmin(rs.getBoolean("admin"));
                    usuario.setLatitude(rs.getDouble("latitude"));
                    usuario.setLongitude(rs.getDouble("longitude"));
                    return usuario;
                });
    }

    @Override
    public List<UsuarioModel> readAll() {
        final String sql = "SELECT * FROM usuario_model";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            UsuarioModel usuario = new UsuarioModel();
            usuario.setId(rs.getInt("id"));
            usuario.setNome(rs.getString("nome"));
            usuario.setTelefone(rs.getString("telefone"));
            usuario.setSenha(rs.getString("senha"));
            usuario.setEmail(rs.getString("email"));
            usuario.setCpf(rs.getString("cpf"));
            usuario.setDoadora(rs.getBoolean("doadora"));
            usuario.setReceptora(rs.getBoolean("receptora"));
            usuario.setProfissional(rs.getBoolean("profissional"));
            usuario.setAdmin(rs.getBoolean("admin"));
            usuario.setLatitude(rs.getDouble("latitude"));
            usuario.setLongitude(rs.getDouble("longitude"));
            return usuario;
        });
    }

    @Override
    public void updateInformation(int id, UsuarioModel entity) {
        final String sql = "UPDATE usuario_model SET nome = ?, telefone = ?, email = ?, cpf = ?, " +
                "doadora = ?, receptora = ?, profissional = ?, admin = ?, latitude = ?, longitude = ? " +
                "WHERE id = ?";
        jdbcTemplate.update(sql,
                entity.getNome(),
                entity.getTelefone(),
                entity.getEmail(),
                entity.getCpf(),
                entity.getDoadora() != null ? entity.getDoadora() : false,
                entity.getReceptora() != null ? entity.getReceptora() : false,
                entity.getProfissional() != null ? entity.getProfissional() : false,
                entity.getAdmin() != null ? entity.getAdmin() : false,
                entity.getLatitude(),
                entity.getLongitude(),
                id
        );
    }

    @Override
    public UsuarioModel readByEmail(String email) {
        final String sql = "SELECT * FROM usuario_model WHERE email = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{email},
                (rs, rowNum) -> {
                    UsuarioModel usuario = new UsuarioModel();
                    usuario.setId(rs.getInt("id"));
                    usuario.setNome(rs.getString("nome"));
                    usuario.setTelefone(rs.getString("telefone"));
                    usuario.setSenha(rs.getString("senha"));
                    usuario.setEmail(rs.getString("email"));
                    usuario.setCpf(rs.getString("cpf"));
                    usuario.setDoadora(rs.getBoolean("doadora"));
                    usuario.setReceptora(rs.getBoolean("receptora"));
                    usuario.setProfissional(rs.getBoolean("profissional"));
                    usuario.setAdmin(rs.getBoolean("admin"));
                    usuario.setLatitude(rs.getDouble("latitude"));
                    usuario.setLongitude(rs.getDouble("longitude"));
                    return usuario;
                });
    }

    @Override
    public boolean updatePassword(int id, String newPassword) {
        final String sql = "UPDATE usuario_model SET senha = ? WHERE id = ?";
        final int updatedItems = jdbcTemplate.update(sql, newPassword, id);
        return updatedItems != 0;
    }
}
