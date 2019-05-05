package com.apibatdongsan.batdongsandanang.controller;

import com.apibatdongsan.batdongsandanang.entity.CarePost;
import com.apibatdongsan.batdongsandanang.entity.Favourite;
import com.apibatdongsan.batdongsandanang.entity.Post;
import com.apibatdongsan.batdongsandanang.exception.CustomizeDuplicatedException;
import com.apibatdongsan.batdongsandanang.exception.InternalErrorServerException;
import com.apibatdongsan.batdongsandanang.service.CareService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/care")
public class PublicCareController {

    @Autowired
    CareService careService;

    @GetMapping("/user/{user_id}/post/{post_id}")
    public ResponseEntity getByUserIdAndBookId(@PathVariable(value = "user_id") Long userId, @PathVariable(value = "post_id") Long postId) {

        CarePost carePost = null;
        Long id = careService.getIdByUserIdAndBookId(userId, postId);
        if (id != null) {
            carePost = careService.getById(id);
        }
        return carePost != null ? ResponseEntity.ok(carePost) : ResponseEntity.status(400).build();
    }

    @PostMapping("/change")
    @Procedure("application/json")
    public ResponseEntity add(@Valid @RequestBody JSONObject body) throws InternalErrorServerException, CustomizeDuplicatedException {
        try {
            Integer result = 0;
            Object obUser = body.get("userId");
            Object obPost = body.get("postId");

            Long userId = Long.valueOf(obUser.toString());
            Long postId = Long.valueOf(obPost.toString());

            Long id = careService.getIdByUserIdAndBookId(userId, postId);
            if(id != null) {
                throw new CustomizeDuplicatedException("This favorite book is exist", "favorite");
            }
            CarePost carePost = careService.save(userId, postId);
            if(carePost == null) {
                throw new InternalErrorServerException("Internal Server Error");
            }
            return ResponseEntity.ok(carePost);
        } catch (InternalError | NullPointerException e){
            throw new InternalErrorServerException("Internal Server Error");
        }
    }

    @GetMapping("/posts/{userId}")
    public ResponseEntity getListCarePost(@PathVariable("userId") Long userId) throws InternalErrorServerException {
        try {
            List<Post> carePosts = careService.getCarePostByUserId(userId);
            return ResponseEntity.ok(carePosts);
        } catch (InternalError | NullPointerException e){
            throw new InternalErrorServerException("Internal Server Error");
        }
    }
}
