package rw.ac.auca.sole_link_shoe_marketplace.service;

import rw.ac.auca.sole_link_shoe_marketplace.domain.ShoeCategory;
import rw.ac.auca.sole_link_shoe_marketplace.repository.ShoeCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ShoeCategoryService {

    private final ShoeCategoryRepository shoeCategoryRepository;

    public ShoeCategoryService(ShoeCategoryRepository shoeCategoryRepository) {
        this.shoeCategoryRepository = shoeCategoryRepository;
    }

    public String save(ShoeCategory shoeCategory) {
        shoeCategoryRepository.save(shoeCategory);
        return "Shoe category saved successfully";
    }

    public List<ShoeCategory> findAll() {
        return shoeCategoryRepository.findAll();
    }

    public Optional<ShoeCategory> findById(UUID id) {
        return shoeCategoryRepository.findById(id);
    }

    public String update(UUID id, ShoeCategory shoeCategory) {
        if (shoeCategoryRepository.findById(id).isEmpty()) {
            return "The shoe category with that id does not exist";
        }

        shoeCategory.setId(id);
        shoeCategoryRepository.save(shoeCategory);

        return "Shoe category updated successfully";
    }

    public String delete(UUID id) {
        if (shoeCategoryRepository.findById(id).isEmpty()) {
            return "The shoe category with that id does not exist";
        }

        shoeCategoryRepository.deleteById(id);
        return "Shoe category deleted successfully";
    }
}