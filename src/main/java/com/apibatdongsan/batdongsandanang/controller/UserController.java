package com.apibatdongsan.batdongsandanang.controller;

import com.apibatdongsan.batdongsandanang.entity.UserAccount;
import com.apibatdongsan.batdongsandanang.exception.CustomizeDuplicatedException;
import com.apibatdongsan.batdongsandanang.exception.InternalErrorServerException;
import com.apibatdongsan.batdongsandanang.service.UserService;
import com.apibatdongsan.batdongsandanang.util.EncrytedPasswordUtils;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")

public class UserController {

    @Autowired
    UserService userService;

//    @PostMapping(value = "/api/user")
//    public ApiResponseDTO createNewUserForCompany(@RequestBody UserDTO userDTO) {
//        return new ApiResponseDTO(200, "Created", userService.createNewUser(userDTO, (long) 2));
//    }
//
//    @PostMapping(value = "/api/register")
//    public ApiResponseDTO registerNewUser(@RequestBody UserDTO userDTO) {
//        return new ApiResponseDTO(200, "Created", userService.createNewUser(userDTO, (long) 3));
//    }

    @GetMapping(value = "/")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

//    @PostMapping(value = "/")
//    public ResponseEntity creat(@RequestBody ProductType productType) {
//        return ResponseEntity.ok(userService.creatNew(productType));
//    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

//    //change Name
//    @PutMapping(value = "/")
//    public ResponseEntity update(@RequestBody ProductType productType) {
//        return ResponseEntity.ok(userService.changeName(productType));
//    }

    //change status
    @PutMapping(value = "/{id}")
    public ResponseEntity changeStatus(@PathVariable Long id) {
        return ResponseEntity.ok(userService.changeStatus(id));
    }

    @PostMapping("/login/check")
    public ResponseEntity checkLogin(@RequestBody JSONObject body) throws CustomizeDuplicatedException, InternalErrorServerException {
        try {
            String username = body.get("username").toString();
            String password = body.get("password").toString();
            UserAccount usersEntity = userService.getByName(username);
            if(usersEntity == null) {
                throw new CustomizeDuplicatedException("Tài khoản không tồn tại trong hệ thống", "username");
            } else {
                if(!EncrytedPasswordUtils.checkMatchPassword(password, usersEntity.getPassword())) {
                    throw new CustomizeDuplicatedException("Mật khẩu không đúng", "password");
                } else {
                    return ResponseEntity.ok(usersEntity);
                }
            }
        } catch (InternalError | NullPointerException e){
            throw new InternalErrorServerException("Internal Server Error");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity add(@Valid @RequestBody UserAccount usersEntity) throws CustomizeDuplicatedException, InternalErrorServerException {
        try {
            if(usersEntity == null) {
                throw new CustomizeDuplicatedException("User is not blank", "user");
            }
            if(usersEntity != null && userService.getByName(usersEntity.getUsername()) != null) {
                throw new CustomizeDuplicatedException("Username of user is exist", "username");
            }

            if(usersEntity.getPassword() == null || "".equals(usersEntity.getPassword())) {
                throw new CustomizeDuplicatedException("length must be between 5 and 70", "password");
            }
            usersEntity.setPassword(EncrytedPasswordUtils.encrytePassword(usersEntity.getPassword()));
            usersEntity.setIdRole(3L);

            usersEntity = userService.add(usersEntity);

            if(usersEntity == null) {
                throw new InternalErrorServerException("Internal Server Error");
            }

        } catch (InternalError | NullPointerException e) {
            throw new InternalErrorServerException("Internal Server Error");
        }
        return ResponseEntity.ok(usersEntity);
    }

    @PostMapping("/mod")
    public ResponseEntity createNewMod(@Valid @RequestBody UserAccount usersEntity) throws CustomizeDuplicatedException, InternalErrorServerException {
        try {
            if(usersEntity == null) {
                throw new CustomizeDuplicatedException("User is not blank", "user");
            }
            if(usersEntity != null && userService.getByName(usersEntity.getUsername()) != null) {
                throw new CustomizeDuplicatedException("Username of user is exist", "username");
            }

            if(usersEntity.getPassword() == null || "".equals(usersEntity.getPassword())) {
                throw new CustomizeDuplicatedException("length must be between 5 and 70", "password");
            }
            usersEntity.setPassword(EncrytedPasswordUtils.encrytePassword(usersEntity.getPassword()));
            usersEntity.setIdRole(2L);

            usersEntity = userService.add(usersEntity);

            if(usersEntity == null) {
                throw new InternalErrorServerException("Internal Server Error");
            }

        } catch (InternalError | NullPointerException e) {
            throw new InternalErrorServerException("Internal Server Error");
        }
        return ResponseEntity.ok(usersEntity);
    }
}
