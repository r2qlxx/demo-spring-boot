package com.example.demo.infrastructure.repositories;

import com.example.demo.domain.objects.DemoUser;
import com.example.demo.domain.repositories.RdsRepository;
import com.example.demo.infrastructure.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class RdsRepositoryImpl implements RdsRepository {
    private final JdbcTemplate jdbc;

    @Override
    public int create(DemoUser demoUser) {
        String sql = """
                INSERT INTO users
                (userid, username, email, gender, birthday)
                VALUES (?, ?, ?, ?, ?)
                """;
        try {
            int rowsAffected = jdbc.update(sql, demoUser.getUserid(), demoUser.getUsername(),
                    demoUser.getEmail(), demoUser.getGender(), demoUser.getBirthday());
            return rowsAffected;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<DemoUser> read(int userid) {
        String sql = """
                SELECT *
                FROM users
                WHERE userid = ?
                """;
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        try {
            User user = jdbc.queryForObject(sql, rowMapper, userid);
            if (ObjectUtils.isEmpty(user)) {
                return Optional.empty();
            }
            return Optional.ofNullable(user.toDemoUser());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<DemoUser> readAll() {
        String sql = """
                SELECT *
                FROM users
                """;
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        try {
            List<User> users = jdbc.query(sql, rowMapper);
            // OPTIMIZE
            List<DemoUser> demoUsers = new ArrayList<>();
            for (User user : users) {
                demoUsers.add(user.toDemoUser());
            }
            return demoUsers;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(DemoUser demoUser) {
        String selectSql = """
                SELECT *
                FROM users
                WHERE userid = ?
                """;
        String updateSql = """
                UPDATE users
                SET username = ?,
                    email = ?,
                    gender = ?,
                    birthday = ?
                WHERE userid = ?
                """;
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        try {
            User user = jdbc.queryForObject(selectSql, rowMapper, demoUser.getUserid());
            if (ObjectUtils.isEmpty(user)) {
                return 0;
            }

            int rowsAffected = jdbc.update(updateSql, demoUser.getUsername(), demoUser.getEmail(),
                    demoUser.getGender(), demoUser.getBirthday(), demoUser.getUserid());
            return rowsAffected;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(int userid) {
        String sql = """
                DELETE
                FROM users
                WHERE userid = ?
                """;
        try {
            int rowsAffected = jdbc.update(sql, userid);
            return rowsAffected;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
