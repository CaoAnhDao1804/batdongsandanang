package com.apibatdongsan.batdongsandanang.controller;

import com.apibatdongsan.batdongsandanang.dto.ApiResponseDTO;
import com.apibatdongsan.batdongsandanang.dto.CommentDTO;
import com.apibatdongsan.batdongsandanang.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping(value = "/api/comment/{idPost}")
    public ApiResponseDTO findAllCommentByIdPost(@PathVariable() Long idPost) {
        return new ApiResponseDTO(200, "Success", commentService.getCommentByIdPost(idPost));
    }

    @PostMapping(value = "/api/comment")
    public ApiResponseDTO createComment(@RequestBody CommentDTO commentDTO) {
        return new ApiResponseDTO(200, "Success", commentService.create(commentDTO));
    }

    @DeleteMapping(value = "/api/comment/{idComment}")
    public ApiResponseDTO delete(@PathVariable Long idComment){
        commentService.delete(idComment);
        return new ApiResponseDTO(200, "Deleted!", true);
    }
}
