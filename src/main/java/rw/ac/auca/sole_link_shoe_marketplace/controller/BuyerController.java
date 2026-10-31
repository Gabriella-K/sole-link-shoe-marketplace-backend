package rw.ac.auca.sole_link_shoe_marketplace.controller;

import rw.ac.auca.sole_link_shoe_marketplace.domain.Buyer;
import rw.ac.auca.sole_link_shoe_marketplace.service.BuyerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/api/buyers")
public class BuyerController {

    private final BuyerService buyerService;

    public BuyerController(BuyerService buyerService) {
        this.buyerService = buyerService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Buyer buyer) {
        String message = buyerService.save(buyer);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(buyerService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        if (buyerService.findById(id).isEmpty()) {
            return new ResponseEntity<>(
                    "The buyer with that id does not exist",
                    HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(buyerService.findById(id).get(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(
            @PathVariable UUID id,
            @RequestBody Buyer buyer) {

        String message = buyerService.update(id, buyer);

        if (message.contains("does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = buyerService.delete(id);

        if (message.contains("does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/by-last-name")
    public ResponseEntity<List<Buyer>> findByLastName(@RequestParam String lastName) {

        List<Buyer> buyers = buyerService.findByLastName(lastName);

        return new ResponseEntity<>(buyers, HttpStatus.OK);
    }
}