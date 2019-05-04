package com.apibatdongsan.batdongsandanang.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@Entity

public class CarePost {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long postId;

    Long userId;
}
