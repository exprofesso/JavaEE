package evilNerd.repository.impl;

import evilNerd.domain.Cars;
import evilNerd.repository.CarsRepository;
import evilNerd.util.DatabasePropertiesReader;
import evilNerd.exception.EntityNotFoundException;
import org.springframework.stereotype.Repository;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.sql.PreparedStatement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static evilNerd.util.DatabasePropertiesReader.DATABASE_DRIVER_NAME;
import static evilNerd.util.DatabasePropertiesReader.DATABASE_LOGIN;
import static evilNerd.util.DatabasePropertiesReader.DATABASE_PASSWORD;
import static evilNerd.util.DatabasePropertiesReader.DATABASE_URL;


//@Repository
public class CarsRepositorylmpl implements CarsRepository {

    public static final DatabasePropertiesReader reader = DatabasePropertiesReader.getInstance();

    public static final String ID = "id";
    public static final String MODEL = "model";
    public static final String CREATION_YEAR = "creation_year";
    public static final String USER_ID = "user_id";
    public static final String PRICE = "price";
    public static final String COLOR = "color";


    @Override
    public List<Cars> search(String query) {
        return null;
    }
    @Override
    public Cars save(Cars cars) {

        final String findByIdQuery = "insert into m_cars (model, creation_year, user_id, price, color)" + "values (?,?,?,?,?)";

        Connection connection;
        PreparedStatement statement;

        try {
            Class.forName(reader.getProperty(DATABASE_DRIVER_NAME));
        } catch (ClassNotFoundException r) {
            System.err.println("JDBC Driver CARS Cannot be loaded!");
            throw new RuntimeException("JDBC Driver CARS Cannot be loaded!");
        }
        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL), reader.getProperty(DATABASE_LOGIN), reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(findByIdQuery);
            PreparedStatement lastInsertId = connection.prepareStatement("select currval('m_cars_id_seq') as last_insert_id;");
            statement.setString(1, cars.getModel());
            statement.setInt(2, cars.getCreationYear());
            statement.setLong(3, cars.getUserId());
            statement.setFloat(4, cars.getPrice());
            statement.setString(5, cars.getColor());

            statement.executeUpdate();

            Long insertedId;
            ResultSet lasrIdResultSet = lastInsertId.executeQuery();
            if (lasrIdResultSet.next()) {
                insertedId = lasrIdResultSet.getLong("last_insert_id");
            } else {
                throw new RuntimeException("We cannot read sequence last value during User creation!");
            }
            return findById(insertedId);
        } catch (SQLException r) {
            System.err.println(r.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public List<Cars> findAll() {
        final String findAllQuery = "select * from m_cars order by id";
        List<Cars> result = new ArrayList<>();
        Connection connection;
        Statement statement;
        ResultSet rs;
        try {
            Class.forName(reader.getProperty(DATABASE_DRIVER_NAME));
        } catch (ClassNotFoundException e){
            System.err.println("JDBC Driver CARS Cannot be loaded!");
            throw new RuntimeException("JDBC Driver CARS Cannot be loaded!");
        }
        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL), reader.getProperty(DATABASE_LOGIN), reader.getProperty(DATABASE_PASSWORD));
            statement = connection.createStatement();
            rs = statement.executeQuery(findAllQuery);
            while (rs.next()){
                result.add(parseResultSet(rs));
            }
            return result;
        } catch (SQLException e){
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues");
        }

    }

    private Cars parseResultSet (ResultSet rs) throws SQLException {

        Cars cars = new Cars();
        cars.setId(rs.getLong(ID));
        cars.setModel(rs.getString(MODEL));
        cars.setCreationYear(rs.getInt(CREATION_YEAR));
        cars.setUserId(rs.getLong(USER_ID));
        cars.setPrice(rs.getFloat(PRICE));
        cars.setColor(rs.getString(COLOR));
        return cars;

    }

    @Override
    public Cars findById(Long key) {
        final String findByIdQuery = "select * from m_cars where id = ?";

        Connection connection;
        PreparedStatement statement;
        ResultSet rs;

        try {
            Class.forName(reader.getProperty(DATABASE_DRIVER_NAME));
        } catch (ClassNotFoundException r){
            System.err.println("JDBC Driver Cannot be loaded!");
            throw new RuntimeException("JDBC Driver Cannot be loaded!");
        }

        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL), reader.getProperty(DATABASE_LOGIN), reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(findByIdQuery);
            statement.setLong(1, key);
            rs = statement.executeQuery();
            if(rs.next()){
                return parseResultSet(rs);
            } else {
                throw new EntityNotFoundException("User with ID:" + key + "not found");
            }
        } catch (SQLException r){
            System.err.println(r.getMessage());
            throw new RuntimeException("SQL Issues!");
        }


    }

    @Override
    public Optional<Cars> findOne(Long key) {
        return Optional.of(findById(key));
    }

    @Override
    public Cars update(Cars cars) {

       final String findByIdQuery = "update m_cars " +
       "set model = ?, creation_year = ?, user_id = ?, price = ?, color = ? where id = ?";

       Connection connection;
       PreparedStatement statement;
            try {
                Class.forName(reader.getProperty(DATABASE_DRIVER_NAME));
            } catch (ClassNotFoundException e) {
                System.err.println("JDBC Driver Cannot be loaded!");
                throw new RuntimeException("JDBC Driver Cannot be loaded!");
            }
            try {

                connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL), reader.getProperty(DATABASE_LOGIN), reader.getProperty(DATABASE_PASSWORD));
                statement = connection.prepareStatement(findByIdQuery);
                statement.setString(1, cars.getModel());
                statement.setInt(2, cars.getCreationYear());
                statement.setLong(3, cars.getUserId());
                statement.setFloat(4, cars.getPrice());
                statement.setString(5, cars.getColor());
                statement.executeUpdate();
                return findById(cars.getId());

            } catch (SQLException r) {
                System.err.println(r.getMessage());
                throw new RuntimeException("SQL Issues!");
            }

    }

    @Override
    public Long delete(Cars cars) {
        final String findByIdQuery = "delete from m_cars where id = ?";

        Connection connection;
        PreparedStatement statement;

        try {
            Class.forName(reader.getProperty(DATABASE_DRIVER_NAME));
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC Driver Cannot be loaded!");
            throw new RuntimeException("JDBC Driver Cannot be loaded!");
        }
        try {
            connection = DriverManager.getConnection(reader.getProperty(DATABASE_URL), reader.getProperty(DATABASE_LOGIN), reader.getProperty(DATABASE_PASSWORD));
            statement = connection.prepareStatement(findByIdQuery);
            statement.setLong(1, cars.getId());
            int deletedRows = statement.executeUpdate();
            return (long)deletedRows;
        } catch (SQLException r) {
            System.err.println(r.getMessage());
            throw new RuntimeException("SQL Issues!");
        }


    }

}
