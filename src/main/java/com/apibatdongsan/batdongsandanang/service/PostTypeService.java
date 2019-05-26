package com.apibatdongsan.batdongsandanang.service;

import com.apibatdongsan.batdongsandanang.entity.Post;
import com.apibatdongsan.batdongsandanang.entity.PostType;
import com.apibatdongsan.batdongsandanang.entity.ProductType;
import com.apibatdongsan.batdongsandanang.exception.BatDongSanException;
import com.apibatdongsan.batdongsandanang.respository.PostTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostTypeService {

    @Autowired
    PostTypeRepository postTypeRepository;

    public List<PostType> findAll() {
        return postTypeRepository.findAllOrderByIdAsc();
    }

    public PostType findById(Long postTypeId) {
        return postTypeRepository.findById(postTypeId).get();
    }

    public List<PostType> getAll() {
        return postTypeRepository.findAllOrderByIdAsc();
    }

    public PostType creatNew(PostType postType) {
        postType.setStatus(1L);
        return postTypeRepository.save(postType);
    }

    public PostType getById(Long id) {
        return postTypeRepository.findById(id).get();
    }

    public PostType changeName(PostType postType) {
        PostType oldPostType = postTypeRepository.findById(postType.getId()).get();
        if (isChangePostName(postType, oldPostType) && isExistPostTypeName(postType.getName())){
            throw new BatDongSanException("Loại bài đăng này đã tồn tại");
        }
        oldPostType.setName(postType.getName());
        return postTypeRepository.save(oldPostType);
    }

    private boolean isChangePostName(PostType postType, PostType oldPostType) {
        return !postType.getName().equalsIgnoreCase(oldPostType.getName());
    }

    public PostType changeStatus(Long id) {
        PostType oldPostType = postTypeRepository.findById(id).get();
        if (oldPostType.getStatus() == 0) {
            oldPostType.setStatus(1L);
        } else {
            oldPostType.setStatus(0L);
        }
        return postTypeRepository.save(oldPostType);
    }

    public boolean isExistPostTypeName(String name) {
        return postTypeRepository.findFirstByNameIgnoreCase(name).isPresent();
    }
}
