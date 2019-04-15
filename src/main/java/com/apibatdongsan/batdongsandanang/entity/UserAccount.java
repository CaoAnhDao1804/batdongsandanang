package com.apibatdongsan.batdongsandanang.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class UserAccount {

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    List<Post> posts;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String fullname;
    private String email;
    private String password;
    private String address;
    private String phone;
    private Integer enable;
    private Date dateCreate;
    private Long idRole;
    private Long status;
}
