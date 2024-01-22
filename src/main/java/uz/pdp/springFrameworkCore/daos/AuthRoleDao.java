package uz.pdp.springFrameworkCore.daos;


import lombok.NonNull;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;
import uz.pdp.springFrameworkCore.domains.AuthRole;
import uz.pdp.springFrameworkCore.domains.AuthUser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class AuthRoleDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public AuthRoleDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    public List<AuthRole> findAllByUserId(@NonNull Long userId) {
        String sql = "select ar.* from authuser_authrole auar inner join authrole ar on ar.id=auar.role_id where auar.user_id=:userId";
        var paramSource = new MapSqlParameterSource()
                .addValue("username", userId);


        var rowMapper = BeanPropertyRowMapper.newInstance(AuthUser.class);
        try {
       return  namedParameterJdbcTemplate.query(sql, paramSource,(rs, rowNum) -> AuthRole.builder()
                .id(rs.getLong("id"))
                .name(rs.getNString("name"))
                .code("code")
                .build());
        }catch (Exception e){
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

}
