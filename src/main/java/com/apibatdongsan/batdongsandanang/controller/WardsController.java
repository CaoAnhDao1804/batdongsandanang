package com.apibatdongsan.batdongsandanang.controller;

import com.apibatdongsan.batdongsandanang.entity.Wards;
import com.apibatdongsan.batdongsandanang.exception.InternalErrorServerException;
import com.apibatdongsan.batdongsandanang.service.WardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/wards/")
public class WardsController {
    @Autowired
    private WardsService wardsService;

    @GetMapping("/districts/{id}")
    public ResponseEntity getByDistrictId(@PathVariable(value = "id") Integer id) throws InternalErrorServerException {
        try {
            List<Wards> wardsBeansEntities = wardsService.getAllByWardWithIdAndName(id);
            return ResponseEntity.ok(wardsBeansEntities);
        } catch (InternalError | NullPointerException e) {
            throw new InternalErrorServerException("Internal Server Error");
        }
    }

    @GetMapping("/districts/by_name")
    public ResponseEntity getByDistrictName(@RequestParam(value = "name") String name) throws InternalErrorServerException {
        try {
            List<Wards> wardsBeansEntities = wardsService.getAllWardsByNameDistricts(name);
            return ResponseEntity.ok(wardsBeansEntities);
        } catch (InternalError | NullPointerException e) {
            throw new InternalErrorServerException("Internal Server Error");
        }
    }


}