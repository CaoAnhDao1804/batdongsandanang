package com.apibatdongsan.batdongsandanang.controller;

import com.apibatdongsan.batdongsandanang.entity.Surounding;
import com.apibatdongsan.batdongsandanang.service.SuroundingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class SuroundingController {

    @Autowired
    SuroundingService suroundingService;

    @PostMapping(value = "/surounding")
    public Surounding create(@RequestBody Surounding surounding) {
        return suroundingService.create(surounding);
    }

    @GetMapping(value = "/surounding")
    public List<Surounding> getAll() {
        return suroundingService.getAll();
    }

}
