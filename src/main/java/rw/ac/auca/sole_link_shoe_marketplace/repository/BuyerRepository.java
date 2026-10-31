package rw.ac.auca.sole_link_shoe_marketplace.repository;

import rw.ac.auca.sole_link_shoe_marketplace.domain.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.List;

public interface BuyerRepository extends JpaRepository<Buyer, UUID> {

    List<Buyer> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);
}
