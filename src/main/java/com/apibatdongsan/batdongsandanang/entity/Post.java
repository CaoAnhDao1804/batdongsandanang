package com.apibatdongsan.batdongsandanang.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Post {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    String desciption;

    Long acreage;

    Long roadInFrontOf;

    Long numberFloor;

    Long bedrooms;

    Long bathrooms;

    @ManyToOne
    @JoinColumn(name = "post_type_id", nullable = false)
    @JsonIgnore
    PostType postType;

    @ManyToOne
    @JoinColumn(name = "product_type_id", nullable = false)
    @JsonIgnore
    ProductType productType;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    UserAccount user;

    @OneToMany(mappedBy = "ofPost")
    @JsonIgnore
    List<Comment> comments;






}
