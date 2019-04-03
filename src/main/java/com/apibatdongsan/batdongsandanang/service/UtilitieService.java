package com.apibatdongsan.batdongsandanang.service;

import com.apibatdongsan.batdongsandanang.respository.UtilitieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtilitieService {
    @Autowired
    UtilitieRepository utilitieRepository;
}
