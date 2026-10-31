package rw.ac.auca.sole_link_shoe_marketplace.repository;

import rw.ac.auca.sole_link_shoe_marketplace.domain.ShoeCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ShoeCategoryRepository extends JpaRepository<ShoeCategory, UUID> {

}
