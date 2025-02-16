package ru.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class DBAuthService implements AuthService {
    private final Set<Record> records;

    public DBAuthService() throws RuntimeException {
        records = new HashSet<>();
        ResultSet results;
        Connection connection = DBConnector.getConnection("jdbc:postgresql:///database?currentSchema=");
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM schema.chat_clients");
            results = preparedStatement.executeQuery();
            while (results.next()) {
                records.add(new Record(
                                results.getInt("id"),
                                results.getString("name"),
                                results.getString("login"),
                                results.getString("password")
                        )
                );
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException("Cannot get sql records");
        } finally {
            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                throw new RuntimeException("Cannot close connection");
            }
        }
    }

    @Override
    public Record findRecord(String login, String password) {
        for (Record record : records) {
            if (record.getLogin().equals(login) && record.getPassword().equals(password)) {
                return record;
            }
        }
        return null;
    }
}
