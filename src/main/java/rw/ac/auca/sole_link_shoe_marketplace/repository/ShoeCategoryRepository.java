package rw.ac.auca.sole_link_shoe_marketplace.repository;

import rw.ac.auca.sole_link_shoe_marketplace.domain.ShoeCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.UUID;
import java.util.List;

public interface ShoeCategoryRepository extends JpaRepository<ShoeCategory, UUID> {
    @Query("SELECT c FROM ShoeCategory c WHERE c.vendors IS EMPTY")
    List<ShoeCategory> findUnusedCategories();
}
