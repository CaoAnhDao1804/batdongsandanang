package com.apibatdongsan.batdongsandanang.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Post {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    String description;

    String address;

    Long acreage;

    Long roadInFrontOf;

    Long numberFloor;

    Long bedrooms;

    Long bathrooms;

    Long status;

    Date createDate;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
//    @JsonIgnore
    List<PictureEntity> pictureEntities;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_type_id", nullable = false)
//    @JsonIgnore
    PostType postType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_type_id", nullable = false)
//    @JsonIgnore
    ProductType productType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
//    @JsonIgnore
    UserAccount user;



    @OneToMany(mappedBy = "ofPost")
    @JsonIgnore
    List<Comment> comments;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "surroundings_posts", joinColumns = {
            @JoinColumn(name = "post_id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "surrounding_id",
                    nullable = false, updatable = false) })
    List<Surounding> suroundings;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "utilities_posts", joinColumns = {
            @JoinColumn(name = "post_id", nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "utilities_id",
                    nullable = false, updatable = false) })
    List<Utilities> utilities;

    Long favoritePersons;
    Long carePersons;

    @Override
    public String toString(){
        return "Post: " +id;
    }





}
