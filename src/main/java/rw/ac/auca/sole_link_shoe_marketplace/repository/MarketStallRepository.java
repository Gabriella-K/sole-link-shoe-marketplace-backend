package rw.ac.auca.sole_link_shoe_marketplace.repository;

import rw.ac.auca.sole_link_shoe_marketplace.domain.MarketStall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.UUID;
import java.util.List;
import java.util.Optional;

public interface MarketStallRepository extends JpaRepository<MarketStall, UUID> {
    @Query("SELECT m FROM MarketStall m " +
       "WHERE NOT EXISTS " +
       "(SELECT v FROM Vendor v WHERE v.marketStall = m) " +
       "ORDER BY m.stallNumber ASC")
    List<MarketStall> findFreeMarketStalls();

    Optional<MarketStall> findByStallNumber(int stallNumber);
}
