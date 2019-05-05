package com.apibatdongsan.batdongsandanang.service;

import com.apibatdongsan.batdongsandanang.entity.CarePost;
import com.apibatdongsan.batdongsandanang.entity.Favourite;
import com.apibatdongsan.batdongsandanang.respository.CarePostRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CareService {

    @Autowired
    CarePostRespository carePostRespository;


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
}
