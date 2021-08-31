package isucon11q.repository;

import isucon11q.model.Isu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class IsuRepository {
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    RowMapper<Isu> rowMapper = (rs, i) -> {
        Isu isu = new Isu();
        isu.setId(rs.getInt("id"));
        return isu;
    };
}
