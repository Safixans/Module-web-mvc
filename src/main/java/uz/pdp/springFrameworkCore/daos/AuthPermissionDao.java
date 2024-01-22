package uz.pdp.springFrameworkCore.daos;


import lombok.NonNull;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import uz.pdp.springFrameworkCore.domains.AuthPermission;
import uz.pdp.springFrameworkCore.domains.AuthRole;
import uz.pdp.springFrameworkCore.domains.AuthUser;

import java.util.Collections;
import java.util.List;

@Component
public class AuthPermissionDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public AuthPermissionDao(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    public List<AuthPermission> findAllByRoleId(@NonNull Long roleId) {
        String sql = "select ap.* from authrole_authpermission arap inner join authpermission ap on ap.id=arap.permission_id where arap.role_id=:userId";
        var paramSource = new MapSqlParameterSource()
                .addValue("roleId", roleId);

//        var rowMapper = BeanPropertyRowMapper.newInstance(AuthUser.class);

        try {
       return  namedParameterJdbcTemplate.query(sql,paramSource, (rs, rowNum) -> AuthPermission.builder()
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
