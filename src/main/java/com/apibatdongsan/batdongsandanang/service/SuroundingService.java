package com.apibatdongsan.batdongsandanang.service;

import com.apibatdongsan.batdongsandanang.entity.Surounding;
import com.apibatdongsan.batdongsandanang.respository.SuroundingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Service
public class SuroundingService {

    @Autowired
    SuroundingRepository suroundingRepository;

    public List<Surounding> getAll() {
        return suroundingRepository.findAll();
    }

    public Surounding create(Surounding surounding) {
        surounding.setStatus(1L);
        return suroundingRepository.save(surounding);
    }

    public Surounding changeName(Surounding surounding) {
        Surounding oldSurounding = suroundingRepository.findById(surounding.getId()).get();
        oldSurounding.setName(surounding.getName());
        return suroundingRepository.save(oldSurounding);
    }


    public Surounding changeStatus(Long id) {
        Surounding surounding = suroundingRepository.findById(id).get();
        if (surounding.getStatus() == 1) {
            surounding.setStatus(0L);
        } else {
            surounding.setStatus(1L);
        }
        return suroundingRepository.save(surounding);
    }

    public Surounding getById(Long id) {
        return suroundingRepository.findById(id).get();
    }
}
