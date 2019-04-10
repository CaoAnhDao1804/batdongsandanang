package com.apibatdongsan.batdongsandanang.controller;

import com.apibatdongsan.batdongsandanang.dto.ApiResponseDTO;
import com.apibatdongsan.batdongsandanang.entity.ProductType;
import com.apibatdongsan.batdongsandanang.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product-type")
public class ProductTypeController {

    @Autowired
    ProductTypeService productTypeService;

    @GetMapping(value = "/")
    public ResponseEntity getAllProductType() {
        return ResponseEntity.ok(productTypeService.getAllProductType());
    }

    @PostMapping(value = "/")
    public ResponseEntity creat(@RequestBody ProductType productType) {
        return ResponseEntity.ok(productTypeService.creatNew(productType));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        return ResponseEntity.ok(productTypeService.getById(id));
    }

    //change Name
    @PutMapping(value = "/")
    public ApiResponseDTO update(@RequestBody ProductType productType) {
        return new ApiResponseDTO(200, "Success", productTypeService.changeName(productType));
    }

    //change status
    @PutMapping(value = "/{id}")
    public ApiResponseDTO changeStatus(@PathVariable Long id) {
        return new ApiResponseDTO(200, "Success", productTypeService.changeStatus(id));
    }







}
