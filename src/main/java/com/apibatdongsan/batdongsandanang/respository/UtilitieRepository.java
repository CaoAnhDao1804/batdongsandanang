package com.apibatdongsan.batdongsandanang.respository;

import com.apibatdongsan.batdongsandanang.entity.Utilities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilitieRepository extends JpaRepository<Utilities, Long> {
    @Query(value = "select utilities.* from utilities order by id ASC ", nativeQuery = true)
    List<Utilities> findAllOrderByIdAsc();

    Optional<Utilities> findFirstByNameIgnoreCase(String name);

}
