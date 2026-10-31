package rw.ac.auca.sole_link_shoe_marketplace.repository;

import rw.ac.auca.sole_link_shoe_marketplace.domain.OrderStatus;
import rw.ac.auca.sole_link_shoe_marketplace.domain.ShoeOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.time.LocalDate;
import java.util.List;

public interface ShoeOrderRepository extends JpaRepository<ShoeOrder, UUID> {
    List<ShoeOrder> findByStatusOrderByOrderDateAsc(OrderStatus status);
    List<ShoeOrder> findByOrderDateBetweenOrderByOrderDateAsc(
        LocalDate start,
        LocalDate end
    );

    boolean existsByVendor_IdAndOrderDateAndStatusNot(
        UUID vendorId,
        LocalDate orderDate,
        OrderStatus status
    );
}
