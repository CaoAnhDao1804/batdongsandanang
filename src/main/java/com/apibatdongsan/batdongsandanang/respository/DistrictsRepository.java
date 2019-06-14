package com.apibatdongsan.batdongsandanang.respository;

import com.apibatdongsan.batdongsandanang.entity.Districts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictsRepository extends JpaRepository<Districts, Integer> {
    List<Districts> findAll();
    Districts findFirstByName(String name);
}
