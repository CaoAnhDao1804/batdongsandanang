package com.apibatdongsan.batdongsandanang.controller;

import com.apibatdongsan.batdongsandanang.entity.Surounding;
import com.apibatdongsan.batdongsandanang.exception.BatDongSanException;
import com.apibatdongsan.batdongsandanang.service.SuroundingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class SuroundingController {

    @Autowired
    SuroundingService suroundingService;

    @PostMapping(value = "/surrounding")
    public Surounding create(@RequestBody Surounding surounding) {
        if (suroundingService.isExistName(surounding.getName())){
            throw new BatDongSanException("Loại môi trường xung quanh này đã tồn tại");
        }
        return suroundingService.create(surounding);
    }

    @GetMapping(value = "/surrounding")
    public List<Surounding> getAll() {
        return suroundingService.getAll();
    }

    @PutMapping(value = "/surrounding")
    public ResponseEntity updateName(@RequestBody Surounding surounding) {
        return ResponseEntity.ok(suroundingService.changeName(surounding));
    }

    @PutMapping(value = "/surrounding/{id}")
    public ResponseEntity changeStatus(@PathVariable Long id) {
        return ResponseEntity.ok(suroundingService.changeStatus(id));
    }
}
