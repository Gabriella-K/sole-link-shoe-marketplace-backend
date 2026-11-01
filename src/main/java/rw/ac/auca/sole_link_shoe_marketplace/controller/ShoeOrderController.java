
package rw.ac.auca.sole_link_shoe_marketplace.controller;

import rw.ac.auca.sole_link_shoe_marketplace.domain.OrderStatus;
import rw.ac.auca.sole_link_shoe_marketplace.domain.ShoeOrder;
import rw.ac.auca.sole_link_shoe_marketplace.service.ShoeOrderService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/shoe-orders")
public class ShoeOrderController {

    private final ShoeOrderService shoeOrderService;

    public ShoeOrderController(ShoeOrderService shoeOrderService) {
        this.shoeOrderService = shoeOrderService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody ShoeOrder shoeOrder) {

        String message = shoeOrderService.save(shoeOrder);

        if (message.equals("Shoe order date cannot be in the past")) {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        if (message.equals("Vendor already has an order on that date")) {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(
                shoeOrderService.findAll(),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {

        if (shoeOrderService.findById(id).isEmpty()) {
            return new ResponseEntity<>(
                    "The shoe order with that id does not exist",
                    HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(
                shoeOrderService.findById(id).get(),
                HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(
            @PathVariable UUID id,
            @RequestBody ShoeOrder shoeOrder) {

        String message = shoeOrderService.update(id, shoeOrder);

        if (message.contains("does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {

        String message = shoeOrderService.delete(id);

        if (message.equals("The shoe order with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }

        if (message.equals("That shoe order has receipts, delete them first")) {
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/by-status")
    public ResponseEntity<List<ShoeOrder>> findByStatus(
            @RequestParam OrderStatus status) {

        List<ShoeOrder> orders = shoeOrderService.findByStatus(status);

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/between")
    public ResponseEntity<List<ShoeOrder>> findBetweenDates(
            @RequestParam String start,
            @RequestParam String end) {

        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);

        List<ShoeOrder> orders = shoeOrderService.findBetweenDates(startDate, endDate);

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<String> updateStatus(
            @PathVariable UUID id,
            @RequestParam OrderStatus status) {

        String message = shoeOrderService.updateStatus(id, status);

        if (message.equals("The shoe order with that id does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }

        if (message.startsWith("Cannot change status")) {
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/stats/by-status")
    public ResponseEntity<List<Object[]>> countOrdersByStatus() {

        List<Object[]> results = shoeOrderService.countOrdersByStatus();

        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @PatchMapping("/cancel-day")
    public ResponseEntity<String> cancelOrdersForVendorOnDate(
            @RequestParam UUID vendorId,
            @RequestParam String date) {

        LocalDate orderDate = LocalDate.parse(date);

        String message = shoeOrderService.cancelOrdersForVendorOnDate(
                vendorId,
                orderDate);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
