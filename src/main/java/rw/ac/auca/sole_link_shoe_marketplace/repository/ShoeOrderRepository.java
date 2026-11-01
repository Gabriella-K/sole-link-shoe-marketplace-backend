package rw.ac.auca.sole_link_shoe_marketplace.repository;

import rw.ac.auca.sole_link_shoe_marketplace.domain.OrderStatus;
import rw.ac.auca.sole_link_shoe_marketplace.domain.ShoeOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    @Query("SELECT s.status, COUNT(s) FROM ShoeOrder s GROUP BY s.status")
    List<Object[]> countOrdersByStatus();


    @Modifying
    @Query("UPDATE ShoeOrder s SET s.status = :cancelled " +
       "WHERE s.vendor.id = :vendorId " +
       "AND s.orderDate = :date " +
       "AND s.status <> :delivered")
        int cancelOrdersForVendorOnDate(
        @Param("vendorId") UUID vendorId,
        @Param("date") LocalDate date,
        @Param("cancelled") OrderStatus cancelled,
        @Param("delivered") OrderStatus delivered
    );
}
