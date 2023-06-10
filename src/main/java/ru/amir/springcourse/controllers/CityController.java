package ru.amir.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.amir.springcourse.dao.CityDAO;
import ru.amir.springcourse.models.City;
import ru.amir.springcourse.util.CityValidator;

import javax.validation.Valid;

/**
 * @author Amir
 */
@Controller
@RequestMapping("/city")
public class CityController {

    private final CityDAO cityDAO;
    private final CityValidator cityValidator;

    @Autowired
    public CityController(CityDAO cityDAO, CityValidator cityValidator) {
        this.cityDAO = cityDAO;
        this.cityValidator = cityValidator;
    }

    @GetMapping()
    public String list(Model model) {
        model.addAttribute("cities", cityDAO.list());
        return "city/list";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("city", cityDAO.show(id));
        return "city/show";
    }

    @GetMapping("/new")
    public String newCity(Model model) {
        model.addAttribute("city", new City());
        return "city/new";
    }

//    or
//    @GetMapping("/new")
//    public String newCity(@ModelAttribute("city") City city) {
//        return "city/new";
//    }

    @PostMapping
    public String create(@ModelAttribute("city") @Valid City city,
                         BindingResult bindingResult) {
        cityValidator.validate(city, bindingResult);

        if (bindingResult.hasErrors()) {
            return "city/new";
        }

        cityDAO.save(city);
        return "redirect:/city";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("city", cityDAO.show(id));
        return "city/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("city") City city, @PathVariable("id") int id) {
        cityDAO.update(id, city);
        return "redirect:/city";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        cityDAO.delete(id);
        return "redirect:/city";
    }

}
