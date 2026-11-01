
package rw.ac.auca.sole_link_shoe_marketplace.repository;

import rw.ac.auca.sole_link_shoe_marketplace.domain.Receipt;
import rw.ac.auca.sole_link_shoe_marketplace.domain.ShoeOrder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;
import java.util.List;

public interface ReceiptRepository extends JpaRepository<Receipt, UUID> {

    @Query("SELECT r FROM Receipt r " +
            "WHERE r.shoeOrder.buyer.phone = :phone " +
            "ORDER BY r.issuedDate DESC")
    List<Receipt> findByBuyerPhone(@Param("phone") String phone);

    boolean existsByShoeOrder(ShoeOrder shoeOrder);
}
