package rw.ac.auca.sole_link_shoe_marketplace.repository;

import rw.ac.auca.sole_link_shoe_marketplace.domain.MarketStall;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface MarketStallRepository extends JpaRepository<MarketStall, UUID> {

}
