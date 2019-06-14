package com.apibatdongsan.batdongsandanang.service;

import com.apibatdongsan.batdongsandanang.entity.Districts;
import com.apibatdongsan.batdongsandanang.entity.Wards;
import com.apibatdongsan.batdongsandanang.respository.DistrictsRepository;
import com.apibatdongsan.batdongsandanang.respository.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WardsService {

    @Autowired
    private DistrictsRepository districtsRepository;

    @Autowired
    private WardRepository wardRepository;

    public List<Wards> getAllByWardWithIdAndName(Integer districtId) {
        return wardRepository.findAllByPhuong(districtId);
    }

    public List<Wards> getAllWardsByNameDistricts(String nameDistricts) {
        List<Wards> wards = new ArrayList<>();
        Districts districts = districtsRepository.findFirstByName(nameDistricts);
        if (districts != null) {
            wards = wardRepository.findAllByPhuong(districts.getId());
        }
        return wards;
    }

}