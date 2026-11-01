
package rw.ac.auca.sole_link_shoe_marketplace.controller;

import rw.ac.auca.sole_link_shoe_marketplace.domain.Receipt;
import rw.ac.auca.sole_link_shoe_marketplace.service.ReceiptService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/api/receipts")
public class ReceiptController {

    private final ReceiptService receiptService;

    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(
            @RequestParam UUID shoeOrderId,
            @RequestBody Receipt receipt) {

        String message = receiptService.save(receipt, shoeOrderId);

        if (message.equals("The shoe order with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }

        if (message.equals("Receipts are only for DELIVERED shoe orders")) {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/buyer/{phone}")
    public ResponseEntity<List<Receipt>> findByBuyerPhone(
            @PathVariable String phone) {

        List<Receipt> receipts = receiptService.findByBuyerPhone(phone);

        return new ResponseEntity<>(receipts, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {

        String message = receiptService.delete(id);

        if (message.equals("The receipt with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
