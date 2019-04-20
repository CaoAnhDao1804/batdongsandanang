package com.apibatdongsan.batdongsandanang.service;

import com.apibatdongsan.batdongsandanang.respository.PicturesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PictureService {
    @Autowired
    PicturesRepository picturesRepository;

    public void deletePictureById(Long id) {
        picturesRepository.deleteById(Math.toIntExact(id));
    }
}
