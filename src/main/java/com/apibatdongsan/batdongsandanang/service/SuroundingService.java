package com.apibatdongsan.batdongsandanang.service;

import com.apibatdongsan.batdongsandanang.entity.Surounding;
import com.apibatdongsan.batdongsandanang.respository.SuroundingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuroundingService {

    @Autowired
    SuroundingRepository suroundingRepository;

    public List<Surounding> getAll() {
        return suroundingRepository.findAll();
    }

    public Surounding create(Surounding surounding) {
        return suroundingRepository.save(surounding);
    }
}
