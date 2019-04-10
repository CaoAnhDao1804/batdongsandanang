package com.apibatdongsan.batdongsandanang.controller;

import com.apibatdongsan.batdongsandanang.entity.PostType;
import com.apibatdongsan.batdongsandanang.service.PostTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 1800)
@RestController
@RequestMapping("/api/post-type")
public class PostTypeController {

    @Autowired
    PostTypeService postTypeService;

    @GetMapping(value = "/")
    public ResponseEntity getAllPostType() {
        return ResponseEntity.ok(postTypeService.getAll());
    }

    @PostMapping(value = "/")
    public ResponseEntity creat(@RequestBody PostType postType) {
        return ResponseEntity.ok(postTypeService.creatNew(postType));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        return ResponseEntity.ok(postTypeService.getById(id));
    }

    //change Name
    @PutMapping(value = "/")
    public ResponseEntity update(@RequestBody PostType postType) {
        return ResponseEntity.ok(postTypeService.changeName(postType));
    }

    //change status
    @PutMapping(value = "/{id}")
    public ResponseEntity changeStatus(@PathVariable Long id) {
        return ResponseEntity.ok(postTypeService.changeStatus(id));
    }

}
