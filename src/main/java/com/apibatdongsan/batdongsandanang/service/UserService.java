package com.apibatdongsan.batdongsandanang.service;

import com.apibatdongsan.batdongsandanang.dto.UserDTO;
import com.apibatdongsan.batdongsandanang.entity.UserAccount;
import com.apibatdongsan.batdongsandanang.respository.RoleRepository;
import com.apibatdongsan.batdongsandanang.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    public UserAccount createNewUser(UserDTO userDTO, Long idRole) {
        UserAccount user = convertUserDTOToUser(userDTO, idRole);
        return userRepository.save(user);
    }

    public UserAccount convertUserDTOToUser(UserDTO userDTO, Long idRole) {
        UserAccount user = new UserAccount();
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

    public List<UserAccount> getAllUsers() {
        return userRepository.findAllOrderByIdAsc();
    }

    public UserAccount creatNew(UserAccount user) {
        user.setStatus(1L);
        return userRepository.save(user);
    }

    public UserAccount getById(Long id) {
        return userRepository.findById(id).get();
    }

    public UserAccount changeStatus(Long id) {
        UserAccount oldUser = userRepository.findById(id).get();
        if (oldUser.getStatus() == 0) {
            oldUser.setStatus(1L);
            return userRepository.save(oldUser);
        } else {
            oldUser.setStatus(0L);
            return userRepository.save(oldUser);
        }
    }


}
