package com.apibatdongsan.batdongsandanang.controller;

import com.apibatdongsan.batdongsandanang.entity.CarePost;
import com.apibatdongsan.batdongsandanang.entity.Favourite;
import com.apibatdongsan.batdongsandanang.entity.Post;
import com.apibatdongsan.batdongsandanang.exception.CustomizeDuplicatedException;
import com.apibatdongsan.batdongsandanang.exception.InternalErrorServerException;
import com.apibatdongsan.batdongsandanang.service.CareService;
import com.apibatdongsan.batdongsandanang.service.FavouriteService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/favorite")
public class PublicFavoriteController {
    @Autowired
    FavouriteService favouriteService;

    @GetMapping("/user/{user_id}/post/{post_id}")
    public ResponseEntity getByUserIdAndBookId(@PathVariable(value = "user_id") Long userId, @PathVariable(value = "post_id") Long postId) {

        Favourite favourite= null;
        Long id = favouriteService.getIdByUserIdAndBookId(userId, postId);
        if(id != null) {
            favourite = favouriteService.getById(id);
        }
        return favourite != null ? ResponseEntity.ok(favourite) : ResponseEntity.status(400).build();
    }

    @PostMapping("/change")
    @Procedure("application/json")
    public ResponseEntity add(@Valid @RequestBody JSONObject body) throws InternalErrorServerException, CustomizeDuplicatedException {
        try {
            Integer result = 0;
            Object ob = body.get("userId");
            Object ob2 = body.get("postId");

            Long userId = Long.valueOf(ob.toString());
            Long postId = Long.valueOf(ob2.toString());

            Long id = favouriteService.getIdByUserIdAndBookId(userId, postId);
            if(id != null) {
                throw new CustomizeDuplicatedException("This favorite book is exist", "favorite");
            }
            Favourite  favourite = favouriteService.save(userId, postId);
            if(favourite == null) {
                throw new InternalErrorServerException("Internal Server Error");
            }
            return ResponseEntity.ok(favourite);
        } catch (InternalError | NullPointerException e){
            throw new InternalErrorServerException("Internal Server Error");
        }
    }

    @GetMapping("/posts/{userId}")
    public ResponseEntity getListFavoritePost(@PathVariable("userId") Long userId) throws InternalErrorServerException {
        try {
            List<Post> favoritePosts = favouriteService.getFavortitePostByUserId(userId);
            return ResponseEntity.ok(favoritePosts);
        } catch (InternalError | NullPointerException e){
            throw new InternalErrorServerException("Internal Server Error");
        }
    }

    @GetMapping("/all/{postId}")
    public ResponseEntity getNumberFavoritePerson(@PathVariable("postId") Long postId) throws InternalErrorServerException {
        try {
            int sum = favouriteService.numberFavoritePersonByIdPost(postId);
            return ResponseEntity.ok(sum);
        } catch (InternalError | NullPointerException e){
            throw new InternalErrorServerException("Internal Server Error");
        }
    }



}
