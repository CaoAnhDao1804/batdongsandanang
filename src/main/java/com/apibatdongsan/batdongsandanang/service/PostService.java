package com.apibatdongsan.batdongsandanang.service;

import com.apibatdongsan.batdongsandanang.dto.PostDTO;
import com.apibatdongsan.batdongsandanang.entity.Post;
import com.apibatdongsan.batdongsandanang.mapper.PostMapper;
import com.apibatdongsan.batdongsandanang.respository.PostRepository;
import com.apibatdongsan.batdongsandanang.respository.PostTypeRepository;
import com.apibatdongsan.batdongsandanang.respository.ProductTypeRepository;
import com.apibatdongsan.batdongsandanang.respository.UserRepository;
import javafx.geometry.Pos;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostTypeRepository postTypeRepository;

    @Autowired
    ProductTypeRepository productTypeRepository;

    @Autowired
    UserRepository userRepository;


    public Post create(PostDTO postDTO) {
        return postRepository.save(convertPostDTOToPost(postDTO));
    }

    public Post convertPostDTOToPost(PostDTO postDTO) {
        Post post = new Post();
        post.setName(postDTO.getName());
        post.setAcreage(postDTO.getAcreage());
        post.setBathrooms(postDTO.getBathrooms());
        post.setBedrooms(postDTO.getBedrooms());
        post.setDesciption(postDTO.getDesciption());
        post.setNumberFloor(postDTO.getNumberFloor());
        post.setRoadInFrontOf(postDTO.getRoadInFrontOf());

        post.setPostType(postTypeRepository.findById(postDTO.getPostTypeId()).get());
        post.setProductType(productTypeRepository.findById(postDTO.getProductTypeId()).get());
        post.setUser(userRepository.findById(postDTO.getUserId()).get());

        return post;
    }


    public Post update(PostDTO postDTO, Long id) {
        Post post = convertPostDTOToPost(postDTO);
        post.setId(id);
        return postRepository.save(post);
    }
}
