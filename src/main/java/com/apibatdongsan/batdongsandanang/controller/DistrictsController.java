package com.apibatdongsan.batdongsandanang.controller;

import com.apibatdongsan.batdongsandanang.entity.Districts;
import com.apibatdongsan.batdongsandanang.exception.InternalErrorServerException;
import com.apibatdongsan.batdongsandanang.service.DistrictsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/districts")
public class DistrictsController {
    @Autowired
    private DistrictsService districtsService;

    @GetMapping("/danang")
    public ResponseEntity getByProvinceId() throws InternalErrorServerException {
        try {
            List<Districts> districtsEntities = districtsService.getAllByProvince();
            return ResponseEntity.ok(districtsEntities);
        } catch (InternalError | NullPointerException e){
            throw new InternalErrorServerException("Internal Server Error");
        }
    }
}
