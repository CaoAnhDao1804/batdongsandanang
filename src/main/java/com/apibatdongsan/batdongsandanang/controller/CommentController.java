package com.apibatdongsan.batdongsandanang.controller;

import com.apibatdongsan.batdongsandanang.dto.ApiResponseDTO;
import com.apibatdongsan.batdongsandanang.dto.CommentDTO;
import com.apibatdongsan.batdongsandanang.dto.CommentResponse;
import com.apibatdongsan.batdongsandanang.dto.PostRequestDTO;
import com.apibatdongsan.batdongsandanang.entity.Comment;
import com.apibatdongsan.batdongsandanang.entity.Post;
import com.apibatdongsan.batdongsandanang.service.CommentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import java.io.IOException;
import java.util.List;


@RestController
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping(value = "/api/comment/{idPost}")
    public ApiResponseDTO findAllCommentByIdPost(@PathVariable() Long idPost) {
        return new ApiResponseDTO(200, "Success", commentService.getCommentByIdPost(idPost));
    }

//    @PostMapping(value = "/api/comment/")
//    public ApiResponseDTO createComment(@RequestBody CommentDTO commentDTO) {
//        return new ApiResponseDTO(200, "Success", commentService.create(commentDTO));
//    }

    @PostMapping(value = "/api/comment/")
    @Consumes({"application/json", MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Object> createNewComment(@RequestParam("commentDTO") String stringCommentDTO) {

        ObjectMapper mapper = new ObjectMapper();
        CommentDTO commentDTO;
        try {
            commentDTO = mapper.readValue(stringCommentDTO, CommentDTO.class);
            Comment comment = commentService.create(commentDTO);
            return ResponseEntity.ok(comment);
        } catch (InternalError | NullPointerException | IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @DeleteMapping(value = "/api/comment/{idComment}")
    public ApiResponseDTO delete(@PathVariable Long idComment){
        commentService.delete(idComment);
        return new ApiResponseDTO(200, "Deleted!", true);
    }

    @GetMapping("/api/comment/post/{postId}")
    public ResponseEntity getAllCommentOfPost(@PathVariable("postId") Long postId) {
        List<CommentResponse> commentResponses = commentService.getAllCommentResponseById(postId);
        return ResponseEntity.ok(commentResponses);
    }


}
