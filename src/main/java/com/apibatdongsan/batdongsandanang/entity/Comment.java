package com.apibatdongsan.batdongsandanang.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Comment {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    Long userId;

    String content;

    Date commentDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "post_id", nullable = false)
    private Post ofPost;


}
