package rw.ac.auca.sole_link_shoe_marketplace.service;

import rw.ac.auca.sole_link_shoe_marketplace.domain.OrderStatus;
import rw.ac.auca.sole_link_shoe_marketplace.domain.ShoeOrder;
import rw.ac.auca.sole_link_shoe_marketplace.repository.ShoeOrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ShoeOrderService {

    private final ShoeOrderRepository shoeOrderRepository;

    public ShoeOrderService(ShoeOrderRepository shoeOrderRepository) {
        this.shoeOrderRepository = shoeOrderRepository;
    }

    public String save(ShoeOrder shoeOrder) {
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
        if (shoeOrderRepository.findById(id).isEmpty()) {
            return "The shoe order with that id does not exist";
        }

        shoeOrder.setId(id);
        shoeOrderRepository.save(shoeOrder);

        return "Shoe order updated successfully";
    }

    public String delete(UUID id) {
        if (shoeOrderRepository.findById(id).isEmpty()) {
            return "The shoe order with that id does not exist";
        }

        shoeOrderRepository.deleteById(id);
        return "Shoe order deleted successfully";
    }

    public List<ShoeOrder> findByStatus(OrderStatus status) {
        return shoeOrderRepository.findByStatusOrderByOrderDateAsc(status);
    }

    public List<ShoeOrder> findBetweenDates(LocalDate start, LocalDate end) {
        return shoeOrderRepository.findByOrderDateBetweenOrderByOrderDateAsc(start, end);
    }
}
