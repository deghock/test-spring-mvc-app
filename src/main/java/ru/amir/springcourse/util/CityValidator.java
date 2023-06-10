package ru.amir.springcourse.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.amir.springcourse.dao.CityDAO;
import ru.amir.springcourse.models.City;

/**
 * @author Amir
 */
@Component
public class CityValidator implements Validator {
    private final CityDAO cityDAO;

    @Autowired
    public CityValidator(CityDAO cityDAO) {
        this.cityDAO = cityDAO;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return City.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        City city = (City) o;

        if (cityDAO.show(city.getCode()).isPresent()) {
            errors.rejectValue("code", "", "City with this code already exists");
        }
    }
}
