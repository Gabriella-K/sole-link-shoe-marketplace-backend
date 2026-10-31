package rw.ac.auca.sole_link_shoe_marketplace.controller;

import rw.ac.auca.sole_link_shoe_marketplace.domain.ShoeCategory;
import rw.ac.auca.sole_link_shoe_marketplace.service.ShoeCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/shoe-categories")
public class ShoeCategoryController {

    private final ShoeCategoryService shoeCategoryService;

    public ShoeCategoryController(ShoeCategoryService shoeCategoryService) {
        this.shoeCategoryService = shoeCategoryService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody ShoeCategory shoeCategory) {
        String message = shoeCategoryService.save(shoeCategory);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        return new ResponseEntity<>(
                shoeCategoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        if (shoeCategoryService.findById(id).isEmpty()) {
            return new ResponseEntity<>(
                    "The shoe category with that id does not exist",
                    HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(
                shoeCategoryService.findById(id).get(), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(
            @PathVariable UUID id,
            @RequestBody ShoeCategory shoeCategory) {

        String message = shoeCategoryService.update(id, shoeCategory);

        if (message.contains("does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = shoeCategoryService.delete(id);

        if (message.contains("does not exist")) {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}