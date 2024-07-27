package uz.cluster.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.cluster.entity.main.Product;
import uz.cluster.enums.Category;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query(value = "select * from product where category = :category ",nativeQuery = true)
    List<Product> findAllByCategory(@Param("category") String category);

}
