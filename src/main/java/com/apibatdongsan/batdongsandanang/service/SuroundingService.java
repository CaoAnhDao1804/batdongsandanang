package com.apibatdongsan.batdongsandanang.service;

import com.apibatdongsan.batdongsandanang.entity.Surounding;
import com.apibatdongsan.batdongsandanang.exception.BatDongSanException;
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
        if (isChangeName(surounding, oldSurounding) && isExistName(surounding.getName())){
            throw new BatDongSanException("Loại môi trường xung quanh này đã tồn tại");
        }
        oldSurounding.setName(surounding.getName());
        return suroundingRepository.save(oldSurounding);
    }

    private boolean isChangeName(Surounding surounding, Surounding oldSurounding) {
        return !surounding.getName().equalsIgnoreCase(oldSurounding.getName());
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

    public boolean isExistName(String name) {
        return suroundingRepository.findFirstByNameIgnoreCase(name).isPresent();
    }
}
