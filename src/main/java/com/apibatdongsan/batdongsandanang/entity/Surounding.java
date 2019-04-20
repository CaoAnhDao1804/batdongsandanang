package com.apibatdongsan.batdongsandanang.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@Data
@Entity
public class Surounding {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull(message = "Name couldn't be Null")
    @Size(min=5, max = 20, message="Name should have at least 5 characters and maximum 20 charaters")
    String name;

    Long status;

    @ManyToMany(mappedBy = "suroundings")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    List<Post> posts;
}
