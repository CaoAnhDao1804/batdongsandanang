package com.apibatdongsan.batdongsandanang.service;

import com.apibatdongsan.batdongsandanang.dto.CommentDTO;
import com.apibatdongsan.batdongsandanang.entity.Comment;
import com.apibatdongsan.batdongsandanang.entity.Post;
import com.apibatdongsan.batdongsandanang.respository.CommentRespository;

import com.apibatdongsan.batdongsandanang.respository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CommentService {

    @Autowired
    CommentRespository commentRespository;

    @Autowired
    PostRepository postRepository;


    public List<Comment> getCommentByIdPost(Long idPost) {
        Post post = postRepository.findById(idPost).get();
        return commentRespository.findByOfPost(post);
    }

    public Comment create(CommentDTO commentDTO) {
        return commentRespository.save(convertToComment(commentDTO));

    }

    public Comment convertToComment( CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setUserId(commentDTO.getUserId());
        comment.setContent(commentDTO.getContent());
        comment.setCommentDate(new Date());
        return comment;
    }

    public void delete(Long idComment) {
        commentRespository.deleteById(idComment);
    }
}
