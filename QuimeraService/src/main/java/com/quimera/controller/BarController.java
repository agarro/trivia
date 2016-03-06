package com.quimera.controller;

import com.quimera.model.Bar;
import com.quimera.services.BarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Manu on 6/2/16.
 */
@RestController
@RequestMapping("/bar")
public class BarController {

    @Autowired
    private BarService barService;

    @RequestMapping("/insertOrUpdate")
    public void insert(@RequestBody Bar bar) {
        barService.insert(bar);
    }

    @RequestMapping("/getAll")
    public List<Bar> getAll() {
        return barService.findAll();
    }

    @RequestMapping("/getById")
    public Bar get(String id) {
        return barService.find(id);
    }

    @RequestMapping("/delete")
    public void delete(@RequestBody Bar bar) {
        barService.delete(bar);
    }

}
