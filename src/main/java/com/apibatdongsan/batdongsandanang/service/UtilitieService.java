package com.apibatdongsan.batdongsandanang.service;

import com.apibatdongsan.batdongsandanang.entity.ProductType;
import com.apibatdongsan.batdongsandanang.entity.Utilities;
import com.apibatdongsan.batdongsandanang.respository.UtilitieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilitieService {
    @Autowired
    UtilitieRepository utilitieRepository;

    public List<Utilities> getAllUtilities() {
        return utilitieRepository.findAllOrderByIdAsc();
    }

    public Utilities creatNew(Utilities utilities) {
        utilities.setStatus(1L);
        return utilitieRepository.save(utilities);
    }

    public Utilities getById(Long id) {
        return utilitieRepository.findById(id).get();
    }

    public Utilities changeName(Utilities utilities) {
        Utilities oldUtilities = utilitieRepository.findById(utilities.getId()).get();
        oldUtilities.setName(utilities.getName());

        return utilitieRepository.save(oldUtilities);
    }

    public Utilities changeStatus(Long id) {
        Utilities oldProductType = utilitieRepository.findById(id).get();
        if (oldProductType.getStatus() == 0) {
            oldProductType.setStatus(1L);
            return utilitieRepository.save(oldProductType);
        } else {
            oldProductType.setStatus(0L);
            return utilitieRepository.save(oldProductType);
        }
    }
}
