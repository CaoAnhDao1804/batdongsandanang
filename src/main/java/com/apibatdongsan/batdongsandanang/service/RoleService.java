package com.apibatdongsan.batdongsandanang.service;

import com.apibatdongsan.batdongsandanang.respository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

}
