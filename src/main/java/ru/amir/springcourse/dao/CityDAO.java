package ru.amir.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.amir.springcourse.models.City;

import java.util.List;
import java.util.Optional;

/**
 * @author Amir
 */
@Component
public class CityDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CityDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<City> list() {
        return jdbcTemplate.query("SELECT * FROM City", new CityMapper());
    }

    public City show(int id) {
        return jdbcTemplate.query("SELECT * FROM City WHERE id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(City.class))
                .stream().findAny().orElse(null);
    }

    public Optional<City> show(String code) {
        return jdbcTemplate.query("SELECT * FROM City WHERE code = ?", new Object[]{code}, new CityMapper())
                .stream().findAny();
    }

    public void save(City city) {
        jdbcTemplate.update("INSERT INTO City(name, population, code) VALUES(?, ?, ?)", city.getName(),
                city.getPopulation(), city.getCode());
    }

    public void update(int id, City updatedCity) {
        jdbcTemplate.update("UPDATE City SET name = ?, population = ?, code = ? WHERE id = ?", updatedCity.getName(),
                updatedCity.getPopulation(), updatedCity.getCode(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM City WHERE id = ?", id);
    }

}
