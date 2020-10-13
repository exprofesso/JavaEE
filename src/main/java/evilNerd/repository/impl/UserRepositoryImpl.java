package evilNerd.repository.impl;

import evilNerd.domain.Gender;
import evilNerd.domain.User;
import evilNerd.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {

    public static final String PORTGRES_DRIVER_NAME = "org.postgresql.Driver";
    public static final String DATABASE_URL = "jdbc:postgresql://localhost:";
    public static final int DATABASE_PORT = 5432;
    public static final String DATABASE_NAME = "/webinar_database";
    public static final String  DATABASE_LOGIN = "test";
    public static final String DATABASE_PASSWORD = "root";


    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String BIRTH_DAY = "birth_date";
    public static final String GENDER = "gender";
    public static final String CREATED = "created";
    public static final String CHANGESD = "changed";
    public static final String WEIGHT = "weigth";

    @Override
    public List<User> search(String query) {
        return null;
    }

    @Override
    public User save(User object) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return String findAllQuery = "select * from m_user order by id";
        List<User> result = new ArrayList<>();
        Connection connection;
        Statement statement;
        ResultSet rs;
        try {
            Class.forName(PORTGRES_DRIVER_NAME);
        } catch (ClassNotFoundException e){
            System.err.println("JDBC Driver Cannot be loaded!");
            throw new RuntimeException("JDBC Driver Cannot be loaded!");
        }
        String jdbcUrl = StringUtils.join(DATABASE_URL, DATABASE_PORT, DATABASE_NAME);
        try {
            connection = DriverManager.getConnection(jdbcUrl, DATABASE_LOGIN, DATABASE_PASSWORD);
            statement = connection.createStatement();
            rs = statement.executeQuery(findAllQuery);

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong(ID));
                user.setName(rs.getString(NAME));
                user.setSurname(rs.getString(SURNAME));
                user.setbirthDate(rs.getDate(BIRTH_DAY));
                user.setGender(Gender.valueOf(rs.getString(GENDER)));
                user.setCreated(rs.getTimestamp(CREATED));
                user.setChanged(rs.getTimestamp(CHANGESD));
                user.setWeight(rs.getFloat(WEIGHT));
                result.add(user);
            }
            return result;
        } catch (SQLException e){
            System.err.println(e.getMessage());
            throw  new RuntimeException("SQL Issues");
        }
    }

    @Override
    public User findById(Long key) {
        return null;
    }

    @Override
    public Optional<User> findOne(Long key) {
        return Optional.empty();
    }

    @Override
    public User update(User object) {
        return null;
    }

    @Override
    public Long delete(User object) {
        return null;
    }
}
