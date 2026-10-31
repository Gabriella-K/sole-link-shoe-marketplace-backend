package rw.ac.auca.sole_link_shoe_marketplace.controller;

import rw.ac.auca.sole_link_shoe_marketplace.domain.MarketStall;
import rw.ac.auca.sole_link_shoe_marketplace.service.MarketStallService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/market-stalls")
public class MarketStallController {

    private final MarketStallService marketStallService;

    public MarketStallController(MarketStallService marketStallService) {
        this.marketStallService = marketStallService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody MarketStall marketStall) {
        String message = marketStallService.save(marketStall);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(
                marketStallService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        if (marketStallService.findById(id).isEmpty()) {
            return new ResponseEntity<>(
                    "The market stall with that id does not exist",
                    HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(
                marketStallService.findById(id).get(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(
            @PathVariable UUID id,
            @RequestBody MarketStall marketStall) {

        String message = marketStallService.update(id, marketStall);

        if (message.contains("does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = marketStallService.delete(id);

        if (message.contains("does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}