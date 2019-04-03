package com.apibatdongsan.batdongsandanang.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Data

public class PostSurounding {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    Long post_id;
    Long surounding_id;
}
