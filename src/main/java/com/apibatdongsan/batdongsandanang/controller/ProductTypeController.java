package com.apibatdongsan.batdongsandanang.controller;

import com.apibatdongsan.batdongsandanang.dto.ApiResponseDTO;
import com.apibatdongsan.batdongsandanang.entity.ProductType;
import com.apibatdongsan.batdongsandanang.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product-type")
public class ProductTypeController {

    @Autowired
    ProductTypeService productTypeService;

    @GetMapping(value = "/")
    public ApiResponseDTO getAllProductType() {
        return new ApiResponseDTO(200, "Success", productTypeService.getAllProductType());
    }

    @PostMapping(value = "/")
    public ApiResponseDTO creat(@RequestBody ProductType productType) {
        return new ApiResponseDTO(200, "Success", productTypeService.creatNew(productType));
    }

    @GetMapping(value = "/{id}")
    public ApiResponseDTO getById(@PathVariable Long id) {
        return new ApiResponseDTO(200, "Success", productTypeService.getById(id));
    }

    @PutMapping(value = "/")
    public ApiResponseDTO update(@RequestBody ProductType productType) {
        return new ApiResponseDTO(200, "Success", productTypeService.update(productType));
    }





}
