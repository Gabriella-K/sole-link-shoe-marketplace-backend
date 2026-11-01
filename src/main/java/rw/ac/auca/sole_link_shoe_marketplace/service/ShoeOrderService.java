
package rw.ac.auca.sole_link_shoe_marketplace.service;

import rw.ac.auca.sole_link_shoe_marketplace.domain.OrderStatus;
import rw.ac.auca.sole_link_shoe_marketplace.domain.ShoeOrder;
import rw.ac.auca.sole_link_shoe_marketplace.repository.ShoeOrderRepository;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import rw.ac.auca.sole_link_shoe_marketplace.repository.ReceiptRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ShoeOrderService {

    private final ShoeOrderRepository shoeOrderRepository;
    private final ReceiptRepository receiptRepository;

    public ShoeOrderService(ShoeOrderRepository shoeOrderRepository, ReceiptRepository receiptRepository) {
        this.shoeOrderRepository = shoeOrderRepository;
        this.receiptRepository = receiptRepository;
    }

    public String save(ShoeOrder shoeOrder) {

        if (shoeOrder.getOrderDate().isBefore(LocalDate.now())) {
            return "Shoe order date cannot be in the past";
        }

        if (shoeOrderRepository.existsByVendor_IdAndOrderDateAndStatusNot(
                shoeOrder.getVendor().getId(),
                shoeOrder.getOrderDate(),
                OrderStatus.CANCELLED)) {

            return "Vendor already has an order on that date";
        }

        shoeOrder.setStatus(OrderStatus.PLACED);

        shoeOrderRepository.save(shoeOrder);

        return "Shoe order saved successfully";
    }

    public List<ShoeOrder> findAll() {
        return shoeOrderRepository.findAll();
    }

    public Optional<ShoeOrder> findById(UUID id) {
        return shoeOrderRepository.findById(id);
    }

    public String update(UUID id, ShoeOrder shoeOrder) {

        Optional<ShoeOrder> existingOrder = shoeOrderRepository.findById(id);

        if (existingOrder.isEmpty()) {
            return "The shoe order with that id does not exist";
        }

        ShoeOrder order = existingOrder.get();

        order.setOrderDate(shoeOrder.getOrderDate());
        order.setBrand(shoeOrder.getBrand());
        order.setPrice(shoeOrder.getPrice());
        order.setBuyer(shoeOrder.getBuyer());
        order.setVendor(shoeOrder.getVendor());

        shoeOrderRepository.save(order);

        return "Shoe order updated successfully";
    }

    public String delete(UUID id) {

        Optional<ShoeOrder> orderResult = shoeOrderRepository.findById(id);

        if (orderResult.isEmpty()) {
            return "The shoe order with that id does not exist";
        }

        ShoeOrder shoeOrder = orderResult.get();

        if (receiptRepository.existsByShoeOrder(shoeOrder)) {
            return "That shoe order has receipts, delete them first";
        }

        shoeOrderRepository.deleteById(id);

        return "Shoe order deleted successfully";
    }

    public List<ShoeOrder> findByStatus(OrderStatus status) {
        return shoeOrderRepository.findByStatusOrderByOrderDateAsc(status);
    }

    public List<ShoeOrder> findBetweenDates(
            LocalDate start,
            LocalDate end) {

        return shoeOrderRepository
                .findByOrderDateBetweenOrderByOrderDateAsc(start, end);
    }

    public String updateStatus(UUID id, OrderStatus newStatus) {

        Optional<ShoeOrder> existingOrder = shoeOrderRepository.findById(id);

        if (existingOrder.isEmpty()) {
            return "The shoe order with that id does not exist";
        }

        ShoeOrder order = existingOrder.get();

        OrderStatus currentStatus = order.getStatus();

        boolean allowed = false;

        if (currentStatus == OrderStatus.PLACED) {
            if (newStatus == OrderStatus.PACKED ||
                    newStatus == OrderStatus.CANCELLED) {
                allowed = true;
            }
        }

        else if (currentStatus == OrderStatus.PACKED) {
            if (newStatus == OrderStatus.DELIVERED ||
                    newStatus == OrderStatus.CANCELLED) {
                allowed = true;
            }
        }

        if (!allowed) {
            return "Cannot change status from "
                    + currentStatus + " to " + newStatus;
        }

        order.setStatus(newStatus);

        shoeOrderRepository.save(order);

        return "Shoe order status updated successfully";
    }

    public List<Object[]> countOrdersByStatus() {
        return shoeOrderRepository.countOrdersByStatus();
    }

    @Transactional
    public String cancelOrdersForVendorOnDate(UUID vendorId, LocalDate date) {

        int numberOfOrders = shoeOrderRepository.cancelOrdersForVendorOnDate(
                vendorId,
                date,
                OrderStatus.CANCELLED,
                OrderStatus.DELIVERED);

        return numberOfOrders + " shoe orders cancelled";
    }
}
