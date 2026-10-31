package rw.ac.auca.sole_link_shoe_marketplace.controller;

import rw.ac.auca.sole_link_shoe_marketplace.domain.Vendor;
import rw.ac.auca.sole_link_shoe_marketplace.service.VendorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Vendor vendor) {
        String message = vendorService.save(vendor);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(vendorService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        if (vendorService.findById(id).isEmpty()) {
            return new ResponseEntity<>(
                    "The vendor with that id does not exist",
                    HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(vendorService.findById(id).get(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(
            @PathVariable UUID id,
            @RequestBody Vendor vendor) {

        String message = vendorService.update(id, vendor);

        if (message.contains("does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = vendorService.delete(id);

        if (message.contains("does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/by-shoe-category")
    public ResponseEntity<List<Vendor>> findByShoeCategoryName(
            @RequestParam String name) {

        List<Vendor> vendors = vendorService.findByShoeCategoryName(name);

        return new ResponseEntity<>(vendors, HttpStatus.OK);
    }
}