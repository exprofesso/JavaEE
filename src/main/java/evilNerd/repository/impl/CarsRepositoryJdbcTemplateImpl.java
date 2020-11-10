package evilNerd.repository.impl;

import evilNerd.domain.Cars;
import evilNerd.domain.User;
import evilNerd.repository.CarsColumns;
import evilNerd.repository.CarsRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

//@Slf4j
@Repository
@Primary
public class CarsRepositoryJdbcTemplateImpl implements CarsRepository {

    private static final Logger log = Logger.getLogger(CarsRepositoryJdbcTemplateImpl.class);

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public CarsRepositoryJdbcTemplateImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    @Override
    public List<Cars> search(String query) {
        return jdbcTemplate.query("select * from m_cars where model = ?", new Object[]{query}, this::getUserRowMapper);
    }

    @Override
    public Cars save(Cars entity) {
        final String createQuery = "insert into m_cars (model, creation_year, user_id, price, color)" +
                "values (:model, :creation_year, :user_id, :price, :color)";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("model", entity.getModel());
        params.addValue("creation_year", entity.getCreationYear());
        params.addValue("user_id", entity.getUserId());
        params.addValue("price", entity.getPrice());
        params.addValue("color", entity.getColor());

        namedParameterJdbcTemplate.update(createQuery, params, keyHolder, new String[]{"id"});
        long createdCarsId = Objects.requireNonNull(keyHolder.getKey().longValue());

        return findById(createdCarsId);
    }

    @Override
    public List<Cars> findAll() {
        return jdbcTemplate.query("select * from m_cars", this::getUserRowMapper );
    }

    private Cars getUserRowMapper(ResultSet rs, int i ) throws SQLException {

        Cars cars = new Cars();
        cars.setId(rs.getLong(CarsColumns.ID));
        cars.setModel(rs.getString(CarsColumns.MODEL));
        cars.setCreationYear(rs.getInt(CarsColumns.CREATION_YEAR));
        cars.setUserId(rs.getLong(CarsColumns.USER_ID));
        cars.setPrice(rs.getFloat(CarsColumns.PRICE));
        cars.setColor(rs.getString(CarsColumns.COLOR));
        return cars;

    }

    @Override
    public Cars findById(Long key) {
//        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
//        mapSqlParameterSource.addValue("carsId", key);
//
//        return namedParameterJdbcTemplate.queryForObject("select * from m_cars where id = :carsId", mapSqlParameterSource, this::getUserRowMapper);

        // второй вариант реализации
        return jdbcTemplate.queryForObject("select * from m_cars where id = ?", new Object[]{key}, this::getUserRowMapper);
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
