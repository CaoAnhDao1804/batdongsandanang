package com.apibatdongsan.batdongsandanang.service;

import com.apibatdongsan.batdongsandanang.entity.PostType;
import com.apibatdongsan.batdongsandanang.respository.PostTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostTypeService {

    @Autowired
    PostTypeRepository postTypeRepository;

    public List<PostType> findAll() {
        return postTypeRepository.findAll();
    }

    public PostType findById(Long postTypeId) {
        return postTypeRepository.findById(postTypeId).get();
    }
}
