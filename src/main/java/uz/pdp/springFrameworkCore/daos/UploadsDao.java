package uz.pdp.springFrameworkCore.daos;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import uz.pdp.springFrameworkCore.domains.Uploads;

import javax.sql.DataSource;
import java.util.Map;

@Component
public class UploadsDao {

    private final NamedParameterJdbcTemplate template;

    @Autowired
    public UploadsDao(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5433/springdata");
        dataSource.setUsername("postgres");
        dataSource.setPassword("123");
        dataSource.setSchema("spring_jdbc");
//spring.datasource.jdbc.driver
        return dataSource;

    }

    public void save(Uploads uploads) {
        String sql = "Insert into uploads(originalname,generatedname,size,mimetype) values(:originalName,:generatedName,:size,:mimeType)";
        var params = new BeanPropertySqlParameterSource(uploads);
        template.update(sql, params);

    }

    public Uploads findByGeneratedName(String filename) {
        String sql="select * from uploads where generatedname =:generatedname";
        Map<String,Object> parameterSource = Map.of("generatedname", filename);
        return template.queryForObject(sql,parameterSource, BeanPropertyRowMapper.newInstance(Uploads.class));


    }
}
