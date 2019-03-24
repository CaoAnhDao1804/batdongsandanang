package com.apibatdongsan.batdongsandanang.service;

import com.apibatdongsan.batdongsandanang.entity.ProductType;
import com.apibatdongsan.batdongsandanang.respository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductTypeService {

    @Autowired
    ProductTypeRepository productTypeRepository;

    public List<ProductType> getAllProductType() {
        return productTypeRepository.findAll();
    }

    public ProductType creatNew(ProductType productType) {
        return productTypeRepository.save(productType);
    }

    public ProductType getById(Long id) {
        return productTypeRepository.findById(id).get();
    }

    public ProductType update(ProductType productType) {
        return productTypeRepository.save(productType);
    }
}
