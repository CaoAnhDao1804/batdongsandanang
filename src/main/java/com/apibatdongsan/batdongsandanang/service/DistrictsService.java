package com.apibatdongsan.batdongsandanang.service;

import com.apibatdongsan.batdongsandanang.entity.Districts;
import com.apibatdongsan.batdongsandanang.respository.DistrictsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictsService {
    @Autowired
    private DistrictsRepository districtsRepository;

    public List<Districts> getAllByProvince() {
        return districtsRepository.findAll();
    }
}
