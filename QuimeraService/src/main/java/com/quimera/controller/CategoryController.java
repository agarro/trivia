package com.quimera.controller;

import com.quimera.model.Category;
import com.quimera.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Manu on 6/2/16.
 */
@RestController
@CrossOrigin
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public void insert(@RequestBody Category category) {
        categoryService.insert(category);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(@RequestBody Category category) {
        categoryService.update(category);
    }

    @RequestMapping("/getAll")
    public List<Category> getAll() {
        return categoryService.findAll();
    }

    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    public Category get(@RequestBody String id) {
        return categoryService.find(id);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void delete(@RequestBody Category category) {
        categoryService.delete(category);
    }

    @RequestMapping("/deleteAll")
    public void deleteAll() {
        categoryService.deleteAll();
    }

}
