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
        return productTypeRepository.findAllOrderByIdAsc();
    }

    public ProductType creatNew(ProductType productType) {
        productType.setStatus(1L);
        return productTypeRepository.save(productType);
    }

    public ProductType getById(Long id) {
        return productTypeRepository.findById(id).get();
    }

    public ProductType changeName(ProductType productType) {
        ProductType OldproductType = productTypeRepository.findById(productType.getId()).get();
        OldproductType.setName(productType.getName());

        return productTypeRepository.save(OldproductType);
    }

    public ProductType changeStatus(Long id) {
        ProductType oldProductType = productTypeRepository.findById(id).get();
        if (oldProductType.getStatus() == 0) {
            oldProductType.setStatus(1L);
            return productTypeRepository.save(oldProductType);
        } else {
            oldProductType.setStatus(0L);
            return productTypeRepository.save(oldProductType);
        }
    }
}
