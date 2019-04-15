package com.apibatdongsan.batdongsandanang.controller;

import com.apibatdongsan.batdongsandanang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


}
