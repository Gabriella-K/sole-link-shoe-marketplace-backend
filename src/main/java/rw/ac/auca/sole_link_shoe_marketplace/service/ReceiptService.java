
package rw.ac.auca.sole_link_shoe_marketplace.service;

import rw.ac.auca.sole_link_shoe_marketplace.domain.Receipt;
import rw.ac.auca.sole_link_shoe_marketplace.domain.ShoeOrder;
import rw.ac.auca.sole_link_shoe_marketplace.domain.OrderStatus;

import rw.ac.auca.sole_link_shoe_marketplace.repository.ReceiptRepository;
import rw.ac.auca.sole_link_shoe_marketplace.repository.ShoeOrderRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReceiptService {

    private final ReceiptRepository receiptRepository;
    private final ShoeOrderRepository shoeOrderRepository;

    public ReceiptService(
            ReceiptRepository receiptRepository,
            ShoeOrderRepository shoeOrderRepository) {

        this.receiptRepository = receiptRepository;
        this.shoeOrderRepository = shoeOrderRepository;
    }

    public String save(Receipt receipt, UUID shoeOrderId) {

        Optional<ShoeOrder> orderResult = shoeOrderRepository.findById(shoeOrderId);

        if (orderResult.isEmpty()) {
            return "The shoe order with that id does not exist";
        }

        ShoeOrder shoeOrder = orderResult.get();

        if (shoeOrder.getStatus() != OrderStatus.DELIVERED) {
            return "Receipts are only for DELIVERED shoe orders";
        }

        receipt.setIssuedDate(LocalDate.now());
        receipt.setShoeOrder(shoeOrder);

        receiptRepository.save(receipt);

        return "Receipt saved successfully";
    }

    public List<Receipt> findByBuyerPhone(String phone) {
        return receiptRepository.findByBuyerPhone(phone);
    }

    public String delete(UUID id) {

        if (receiptRepository.findById(id).isEmpty()) {
            return "The receipt with that id does not exist";
        }

        receiptRepository.deleteById(id);

        return "Receipt deleted successfully";
    }
}
