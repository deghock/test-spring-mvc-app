package ru.amir.springcourse.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.amir.springcourse.models.City;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Amir
 */
public class CityMapper implements RowMapper<City> {
    @Override
    public City mapRow(ResultSet resultSet, int i) throws SQLException {
        City city = new City();

        city.setId(resultSet.getInt("id"));
        city.setName(resultSet.getString("name"));
        city.setPopulation(resultSet.getInt("population"));
        city.setCode(resultSet.getString("code"));

        return city;
    }
}
