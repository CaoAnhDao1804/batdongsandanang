package com.apibatdongsan.batdongsandanang.service;

import com.apibatdongsan.batdongsandanang.entity.CarePost;
import com.apibatdongsan.batdongsandanang.entity.Favourite;
import com.apibatdongsan.batdongsandanang.entity.Post;
import com.apibatdongsan.batdongsandanang.respository.CarePostRespository;
import com.apibatdongsan.batdongsandanang.respository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CareService {

    @Autowired
    CarePostRespository carePostRespository;

    @Autowired
    PostRepository postRepository;


    public Long getIdByUserIdAndBookId(Long userId, Long postId) {
        return carePostRespository.getIdByPostIdandUserId(postId, userId);
    }

    public CarePost getById(Long id) {
        return carePostRespository.findById(id).get();
    }

    public CarePost save(Long userId, Long postId) {
        CarePost carePost = new CarePost();
        carePost.setUserId(userId);
        carePost.setPostId(postId);
        return  carePostRespository.save(carePost);
    }

    public List<Post> getCarePostByUserId(Long userId) {

        List<CarePost> caresOfUser = carePostRespository.findByUserId(userId);
        List<Post> posts = new ArrayList<>();

        for (CarePost carePost: caresOfUser) {
            Post post = postRepository.findById(carePost.getPostId()).get();
            posts.add(post);
        }

        return posts;
    }
}
