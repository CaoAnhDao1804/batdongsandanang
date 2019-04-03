package com.apibatdongsan.batdongsandanang.service;

import com.apibatdongsan.batdongsandanang.dto.UserDTO;
import com.apibatdongsan.batdongsandanang.entity.User;
import com.apibatdongsan.batdongsandanang.respository.RoleRepository;
import com.apibatdongsan.batdongsandanang.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService  {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    public User createNewUser(UserDTO userDTO, Long idRole) {
        User user = convertUserDTOToUser(userDTO, idRole);
        return userRepository.save(user);
    }

    public User convertUserDTOToUser(UserDTO userDTO, Long idRole) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setFullname(userDTO.getUsername());
        user.setAddress(userDTO.getAddress());
        user.setEmail(userDTO.getAddress());
        user.setEnable(1);
        user.setPhone(userDTO.getPhone());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setDateCreate(new Date());
        user.setIdRole(idRole);
        return user;
    }
}
