package com.apibatdongsan.batdongsandanang.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
public class Utilitie {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull(message = "Name couldn't be Null")
    @Size(min=5, max = 20, message="Name should have at least 5 characters and maximum 20 charaters")
    String name;

}
