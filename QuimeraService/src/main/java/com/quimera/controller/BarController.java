package com.quimera.controller;

import com.quimera.model.Bar;
import com.quimera.services.BarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Manu on 6/2/16.
 */
@RestController
@CrossOrigin
@RequestMapping("/bars")
public class BarController {

    @Autowired
    private BarService barService;

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public void insert(@RequestBody Bar bar) {
        barService.insert(bar);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(@RequestBody Bar bar) {
        barService.update(bar);
    }

    @RequestMapping("/getAll")
    public List<Bar> getAll() {
        return barService.findAll();
    }

    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    public Bar get(@RequestBody String id) {
        return barService.find(id);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void delete(@RequestBody Bar bar) {
        barService.delete(bar);
    }

    @RequestMapping("/deleteAll")

    public void deleteAll() {
        barService.deleteAll();
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public Bar authenticate(@RequestBody Bar userBar) {
        return barService.authenticate(userBar.getUsername(), userBar.getPassword());
    }
}
