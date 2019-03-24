package com.apibatdongsan.batdongsandanang.controller;

import com.apibatdongsan.batdongsandanang.dto.ApiResponseDTO;
import com.apibatdongsan.batdongsandanang.service.PostTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 1800)
@RestController
public class PostTypeController {

    @Autowired
    PostTypeService postTypeService;

    @GetMapping(value = "/post-type")
    public ApiResponseDTO findAll() {
        return new ApiResponseDTO(200, "Success", postTypeService.findAll());
    }

    @GetMapping(value = "/post-type/{id}")
    public ApiResponseDTO getById(@PathVariable Long id) {
        return new ApiResponseDTO(200, "Success", postTypeService.findById(id));
    }

}
