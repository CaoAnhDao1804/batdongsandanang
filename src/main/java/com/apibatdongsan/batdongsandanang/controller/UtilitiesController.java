package com.apibatdongsan.batdongsandanang.controller;

import com.apibatdongsan.batdongsandanang.entity.Utilities;
import com.apibatdongsan.batdongsandanang.exception.BatDongSanException;
import com.apibatdongsan.batdongsandanang.service.UtilitieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/utilities")
public class UtilitiesController {

    @Autowired
    UtilitieService utilitieService;

    @GetMapping(value = "/")
    public ResponseEntity getAllUtilities() {
        return ResponseEntity.ok(utilitieService.getAllUtilities());
    }

    @PostMapping(value = "/")
    public ResponseEntity creat(@RequestBody Utilities utilities) {
        if (utilitieService.isExist(utilities.getName())){
            throw new BatDongSanException("Loại bất động sản này đã tồn tại");
        }
        return ResponseEntity.ok(utilitieService.creatNew(utilities));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        return ResponseEntity.ok(utilitieService.getById(id));
    }

    //change Name
    @PutMapping(value = "/")
    public ResponseEntity update(@RequestBody Utilities utilities) {
        return ResponseEntity.ok(utilitieService.changeName(utilities));
    }

    //change status
    @PutMapping(value = "/{id}")
    public ResponseEntity changeStatus(@PathVariable Long id) {
        return ResponseEntity.ok(utilitieService.changeStatus(id));
    }
}
