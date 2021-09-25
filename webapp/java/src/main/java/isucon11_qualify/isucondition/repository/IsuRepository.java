package isucon11_qualify.isucondition.repository;

import isucon11_qualify.isucondition.model.Isu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IsuRepository {
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    // TODO 未実装
    RowMapper<Isu> rowMapper = (rs, i) -> {
        Isu isu = new Isu();
        isu.setId(rs.getInt("id"));
        isu.setJiaIsuUUID(rs.getString("jia_isu_uuid"));
        isu.setName(rs.getString("name"));
        //isu.setImage(rs.getBlob("image"));
        isu.setCharacter(rs.getString("character"));
        isu.setJiaUserID(rs.getString("jia_user_id"));
        //isu.setCreatedAt(rs.getDate("created_at"));
        //isu.setUpdatedAt(rs.getDate("updated_at"));
        return isu;
    };

    public List<Isu> findById(Integer id) {
        SqlParameterSource source = new MapSqlParameterSource().addValue("id",
                id);
        return jdbcTemplate.query("SELECT * FROM isu WHERE id = :id",
                source, rowMapper);
    }
}
