package com.quimera.controller;

import com.quimera.model.Banner;
import com.quimera.services.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Manu on 6/2/16.
 */
@RestController
@CrossOrigin
@RequestMapping("/banners")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public void insert(@RequestBody Banner banner) {
        bannerService.insert(banner);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void update(@RequestBody Banner banner) {
        bannerService.update(banner);
    }

    @RequestMapping("/getAll")
    public List<Banner> getAll() {
        return bannerService.findAll();
    }

    @RequestMapping(value = "/getById", method = RequestMethod.POST)
    public Banner get(@RequestBody String id) {
        return bannerService.find(id);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void delete(@RequestBody Banner banner) {
        bannerService.delete(banner);
    }
    @RequestMapping("/deleteAll")
    public void deleteAll() {
        bannerService.deleteAll();
    }
}
