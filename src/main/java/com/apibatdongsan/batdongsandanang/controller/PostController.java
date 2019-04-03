package com.apibatdongsan.batdongsandanang.controller;

import com.apibatdongsan.batdongsandanang.dto.ApiResponseDTO;
import com.apibatdongsan.batdongsandanang.dto.PostDTO;
import com.apibatdongsan.batdongsandanang.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping(value = "/api/post")
    public ApiResponseDTO create(@RequestBody PostDTO postDTO) {
        return new ApiResponseDTO(200, "Success", postService.create(postDTO));
    }

    @PutMapping(value = "/api/post/{id}")
    public ApiResponseDTO update(@RequestBody PostDTO postDTO, @PathVariable("id") Long id) {
        return new ApiResponseDTO(200, "Success", postService.update(postDTO, id));
    }
}
