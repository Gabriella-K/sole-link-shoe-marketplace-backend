package rw.ac.auca.sole_link_shoe_marketplace.repository;

import rw.ac.auca.sole_link_shoe_marketplace.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.UUID;
import java.util.List;

public interface VendorRepository extends JpaRepository<Vendor, UUID> {

    @Query("SELECT DISTINCT v FROM Vendor v JOIN v.shoeCategories c " +
       "WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Vendor> findByShoeCategoryName(@Param("name") String name);
}
