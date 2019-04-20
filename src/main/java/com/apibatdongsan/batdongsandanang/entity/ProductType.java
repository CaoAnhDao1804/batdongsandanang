package com.apibatdongsan.batdongsandanang.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
public class ProductType {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull(message = "Name couldn't be Null")
    String name;

    Long status;

    @OneToMany(mappedBy = "productType",fetch = FetchType.LAZY)
    @JsonIgnore
    List<Post> posts;

    @Override
    public String toString(){
        return "ProductType: " +id;
    }
}
