package br.fai.backend.donate.backend.main.configuration;

import br.fai.backend.donate.backend.main.dao.h2.UserH2DaoImpl;
import br.fai.backend.donate.backend.main.dao.postgres.UserPostgresDaoImpl;
import br.fai.backend.donate.backend.main.port.dao.user.UserDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;

@Configuration
public class AppConfiguration {

    public AppConfiguration(){
        System.out.println("AppConfiguration inicializada");
    }

    // --- H2 (dev)
    @Bean
    @Profile("dev")
    public UserDao getH2Dao(final JdbcTemplate jdbcTemplate){
        return new UserH2DaoImpl(jdbcTemplate);
    }

    // --- Postgres (prod)
    @Bean
    @Profile("prod")
    public UserDao getUserDao(final Connection connection){
        return new UserPostgresDaoImpl(connection);
    }

    // --- Bean padrão (fallback) se nenhum profile estiver ativo
    @Bean
    public UserDao getDefaultDao(final JdbcTemplate jdbcTemplate){
        return new UserH2DaoImpl(jdbcTemplate); // ou Postgres, o que fizer mais sentido
    }

}
