package evilNerd.repository.impl;

import evilNerd.domain.Cars;
import evilNerd.repository.CarsRepository;
import evilNerd.util.DatabasePropertiesReader;
import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static evilNerd.util.DatabasePropertiesReader.DATABASE_DRIVER_NAME;
import static evilNerd.util.DatabasePropertiesReader.DATABASE_LOGIN;
import static evilNerd.util.DatabasePropertiesReader.DATABASE_PASSWORD;
import static evilNerd.util.DatabasePropertiesReader.DATABASE_URL;

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
    public Cars save(Cars object) {
        return null;
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
        return null;
    }

    @Override
    public Optional<Cars> findOne(Long key) {
        return Optional.empty();
    }

    @Override
    public Cars update(Cars object) {
        return null;
    }

    @Override
    public Long delete(Cars object) {
        return null;
    }

}
