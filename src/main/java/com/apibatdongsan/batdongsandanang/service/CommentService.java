package com.apibatdongsan.batdongsandanang.service;

import com.apibatdongsan.batdongsandanang.dto.CommentDTO;
import com.apibatdongsan.batdongsandanang.dto.CommentResponse;
import com.apibatdongsan.batdongsandanang.entity.Comment;
import com.apibatdongsan.batdongsandanang.entity.Post;
import com.apibatdongsan.batdongsandanang.entity.UserAccount;
import com.apibatdongsan.batdongsandanang.respository.CommentRespository;

import com.apibatdongsan.batdongsandanang.respository.PostRepository;
import com.apibatdongsan.batdongsandanang.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CommentService {

    @Autowired
    CommentRespository commentRespository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;


    public List<Comment> getCommentByIdPost(Long idPost) {
        Post post = postRepository.findById(idPost).get();
        return commentRespository.findByOfPostOrderByCommentDateDesc(post);
    }

    public List<CommentResponse> getAllCommentResponseById (Long postId) {
        List<CommentResponse> commentResponses = new ArrayList<>();
        try {
            List<Comment> comments = getCommentByIdPost(postId);
            for (Comment comment: comments) {
                CommentResponse commentResponse;
                commentResponse = convertToCommentResponse(comment);
                commentResponses.add(commentResponse);
            }
            return commentResponses;
        } catch (Exception e) {
            e.printStackTrace();
            return commentResponses;
        }

    }

    public CommentResponse convertToCommentResponse(Comment comment) {
        try {
            CommentResponse commentResponse = new CommentResponse();
            commentResponse.setId(comment.getId());
            commentResponse.setIdUser(comment.getUserId());
            UserAccount userAccount = userRepository.findById(comment.getUserId()).get();
            commentResponse.setUsername(userAccount.getUsername());
            commentResponse.setAddress(userAccount.getAddress());
            commentResponse.setContent(comment.getContent());
            commentResponse.setCreateDate(comment.getCommentDate());
            return commentResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Comment create(CommentDTO commentDTO) {
        return commentRespository.save(convertToComment(commentDTO));

    }

    public Comment convertToComment( CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setUserId(commentDTO.getUserId());
        comment.setOfPost(postRepository.findById(commentDTO.getPostId()).get());
        comment.setContent(commentDTO.getContent());
        comment.setCommentDate(new Date());
        return comment;
    }

    public void delete(Long idComment) {
        commentRespository.deleteById(idComment);
    }
}
