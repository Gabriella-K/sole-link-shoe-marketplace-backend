package rw.ac.auca.sole_link_shoe_marketplace.repository;

import rw.ac.auca.sole_link_shoe_marketplace.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface VendorRepository extends JpaRepository<Vendor, UUID> {

}
