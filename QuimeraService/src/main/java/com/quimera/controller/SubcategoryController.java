package com.quimera.controller;

import com.quimera.model.Subcategory;
import com.quimera.services.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Manu on 6/2/16.
 */
@RestController
@CrossOrigin
@RequestMapping("/subcategory")
public class SubcategoryController {

    @Autowired
    private SubcategoryService subcategoryService;

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public void insert(@RequestBody Subcategory subCategory) {
        subcategoryService.insert(subCategory);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(@RequestBody Subcategory subCategory) {
        subcategoryService.update(subCategory);
    }

    @RequestMapping("/getAll")
    public List<Subcategory> getAll() {
        return subcategoryService.findAll();
    }

    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    public Subcategory get(@RequestBody String id) {
        return subcategoryService.find(id);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void delete(@RequestBody Subcategory subCategory) {
        subcategoryService.delete(subCategory);
    }

    @RequestMapping("/deleteAll")
    public void deleteAll() {
        subcategoryService.deleteAll();
    }

}
