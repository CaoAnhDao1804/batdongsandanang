package com.apibatdongsan.batdongsandanang.controller;

import com.apibatdongsan.batdongsandanang.dto.PostRequestDTO;
import com.apibatdongsan.batdongsandanang.entity.Post;
import com.apibatdongsan.batdongsandanang.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.Consumes;
import java.io.IOException;

@RestController
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    ProductTypeService productTypeService;


    @Autowired
    PostTypeService postTypeService;

    @Autowired
    SuroundingService suroundingService;

    @Autowired
    UtilitieService utilitieService;

    @Autowired
    UserService userService;


    @GetMapping(value = "/api/post")
    public ResponseEntity getAll() {
        return ResponseEntity.ok(postService.getAll());
    }

    @GetMapping(value = "/api/post/{id}")
    public ResponseEntity getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(postService.findById(id));
    }

    @GetMapping(value = "/api/post/pictures/{id}")
    public ResponseEntity getPictureByIdPost(@PathVariable("id") Long id) {
        return ResponseEntity.ok(postService.findPicturesById(id));
    }


//


    @PostMapping(value = "/api/post")
    @Consumes({"application/json", MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Object> createNewPost(@RequestParam("postDTO") String stringPostEntity, @RequestParam(value = "files", required = false) MultipartFile[] files, @RequestParam(value = "fileCover", required = false) MultipartFile fileCover) {

        ObjectMapper mapper = new ObjectMapper();
        PostRequestDTO postDTO;
        try {
            postDTO = mapper.readValue(stringPostEntity, PostRequestDTO.class);
            Post post = postService.createNewPost(postDTO);
            postService.addImages(post, files, fileCover);
            return ResponseEntity.ok(post);
        } catch (InternalError | NullPointerException | IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @PutMapping(value = "/api/post")
    @Consumes({"application/json", MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Object> editPost(@RequestParam("postDTO") String stringPostEntity) {

        ObjectMapper mapper = new ObjectMapper();
        PostRequestDTO postDTO;
        try {
            postDTO = mapper.readValue(stringPostEntity, PostRequestDTO.class);
            Post post = postService.createNewPost(postDTO);
            return ResponseEntity.ok(post);
        } catch (InternalError | NullPointerException | IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    //    @PutMapping(value = "/api/post/{id}")
//    public ApiResponseDTO update(@RequestBody PostDTO postDTO, @PathVariable("id") Long id) {
//        return new ApiResponseDTO(200, "Success", postService.update(postDTO, id));
//    }
    @PutMapping(value = "/api/post/{id}")
    public ResponseEntity changeStatus(@PathVariable Long id) {
        return ResponseEntity.ok(postService.changeStatus(id));
    }


}
