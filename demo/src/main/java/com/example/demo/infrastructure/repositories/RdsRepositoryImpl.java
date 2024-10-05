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

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class RdsRepositoryImpl implements RdsRepository {
    private final JdbcTemplate jdbc;

    @Override
    public int create(DemoUser demoUser) {
        try {
            int rowsAffected = jdbc.update(SqlQueries.CREATE_USER, demoUser.getUserid(), demoUser.getUsername(),
                    demoUser.getEmail(), demoUser.getGender(), demoUser.getBirthday());
            return rowsAffected;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<DemoUser> read(int userid) {
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        try {
            User user = jdbc.queryForObject(SqlQueries.READ_USER_BY_ID, rowMapper, userid);
            return Optional.ofNullable(user).map(User::toDemoUser);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<DemoUser> readAll() {
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        try {
            return jdbc.query(SqlQueries.READ_ALL_USERS, rowMapper).stream().map(User::toDemoUser).toList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(DemoUser demoUser) {
        try {
            int rowsAffected = jdbc.update(SqlQueries.UPDATE_USER, demoUser.getUsername(), demoUser.getEmail(),
                    demoUser.getGender(), demoUser.getBirthday(), demoUser.getUserid());
            return rowsAffected;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int delete(int userid) {
        try {
            int rowsAffected = jdbc.update(SqlQueries.DELETE_USER, userid);
            return rowsAffected;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
