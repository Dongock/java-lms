package nextstep.users.infrastructure;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import nextstep.users.domain.UserSessionType;
import nextstep.users.domain.UserType;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository("userRepository")
public class JdbcUserRepository implements UserRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcUserRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<NsUser> findByUserId(String userId) {
        String sql = "select id, user_id, password, name, email, type, session_type, created_at, updated_at " +
                "from ns_user where user_id = ?";
        RowMapper<NsUser> rowMapper = (rs, rowNum) -> new NsUser(
                rs.getLong(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                UserType.valueOf(rs.getString(6)),
                UserSessionType.valueOf(rs.getString(7)),
                toLocalDateTime(rs.getTimestamp(8)),
                toLocalDateTime(rs.getTimestamp(9)));
        return Optional.of(jdbcTemplate.queryForObject(sql, rowMapper, userId));
    }

    @Override
    public Optional<NsUser> findById(Long id) {
        String sql = "select id, user_id, password, name, email, type, session_type, created_at, updated_at " +
                "from ns_user where id = ?";
        RowMapper<NsUser> rowMapper = (rs, rowNum) -> new NsUser(
                rs.getLong(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                UserType.valueOf(rs.getString(6)),
                UserSessionType.valueOf(rs.getString(7)),
                toLocalDateTime(rs.getTimestamp(8)),
                toLocalDateTime(rs.getTimestamp(9)));
        return Optional.of(jdbcTemplate.queryForObject(sql, rowMapper, id));
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
