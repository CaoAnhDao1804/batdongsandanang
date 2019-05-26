package com.apibatdongsan.batdongsandanang.respository;

import com.apibatdongsan.batdongsandanang.entity.Surounding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional

public interface SuroundingRepository extends JpaRepository<Surounding, Long> {
 Optional<Surounding> findFirstByNameIgnoreCase(String name);
}
