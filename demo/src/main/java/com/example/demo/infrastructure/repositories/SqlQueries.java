package com.example.demo.infrastructure.repositories;

public class SqlQueries {
    public static final String CREATE_USER = """
            INSERT INTO users
            (userid, username, email, gender, birthday)
            VALUES (?, ?, ?, ?, ?)
            """;

    public static final String READ_USER_BY_ID = """
            SELECT * FROM users WHERE userid = ?
            """;

    public static final String READ_ALL_USERS = """
            SELECT * FROM users
            """;

    public static final String UPDATE_USER = """
            UPDATE users
            SET username = ?,
                email = ?,
                gender = ?,
                birthday = ?
            WHERE userid = ?
            """;

    public static final String DELETE_USER = """
            DELETE FROM users WHERE userid = ?
            """;
}
