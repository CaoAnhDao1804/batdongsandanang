package com.apibatdongsan.batdongsandanang.respository;

import com.apibatdongsan.batdongsandanang.entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface ProductTypeRepository extends JpaRepository<ProductType, Long> {
    List<ProductType> findAll();

    @Query(value = "select product_type.* from product_type order by id ASC ", nativeQuery = true)
    List<ProductType> findAllOrderByIdAsc();
}
