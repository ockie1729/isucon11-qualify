package isucon11_qualify.isucondition.repository;

import isucon11_qualify.isucondition.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    // TODO 未実装
    RowMapper<User> rowMapper = (rs, i) -> {
        User user = new User();
        user.setJiaUserID(rs.getString("jia_user_id"));
        // user.setCreatedAt(rs.getDate("created_at"));

        return user;
    };

    public List<User> findByJIAUserID(String jiaUserId) {
        SqlParameterSource source = new MapSqlParameterSource().addValue("jia_user_id",
                jiaUserId);
        return jdbcTemplate.query("SELECT * FROM user WHERE jia_user_id = :jia_user_id",
                source, rowMapper);
    }
}
