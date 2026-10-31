
package rw.ac.auca.sole_link_shoe_marketplace.service;

import rw.ac.auca.sole_link_shoe_marketplace.domain.Vendor;
import rw.ac.auca.sole_link_shoe_marketplace.domain.ShoeCategory;
import rw.ac.auca.sole_link_shoe_marketplace.domain.MarketStall;

import rw.ac.auca.sole_link_shoe_marketplace.repository.VendorRepository;
import rw.ac.auca.sole_link_shoe_marketplace.repository.ShoeCategoryRepository;
import rw.ac.auca.sole_link_shoe_marketplace.repository.MarketStallRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.ArrayList;

@Service
public class VendorService {

    private final VendorRepository vendorRepository;
    private final ShoeCategoryRepository shoeCategoryRepository;
    private final MarketStallRepository marketStallRepository;

    public VendorService(
            VendorRepository vendorRepository,
            ShoeCategoryRepository shoeCategoryRepository,
            MarketStallRepository marketStallRepository) {

        this.vendorRepository = vendorRepository;
        this.shoeCategoryRepository = shoeCategoryRepository;
        this.marketStallRepository = marketStallRepository;
    }

    public String save(Vendor vendor) {
        vendorRepository.save(vendor);
        return "Vendor saved successfully";
    }

    public List<Vendor> findAll() {
        return vendorRepository.findAll();
    }

    public Optional<Vendor> findById(UUID id) {
        return vendorRepository.findById(id);
    }

    public String update(UUID id, Vendor vendor) {

        if (vendorRepository.findById(id).isEmpty()) {
            return "The vendor with that id does not exist";
        }

        vendor.setId(id);
        vendorRepository.save(vendor);

        return "Vendor updated successfully";
    }

    public String delete(UUID id) {

        if (vendorRepository.findById(id).isEmpty()) {
            return "The vendor with that id does not exist";
        }

        vendorRepository.deleteById(id);

        return "Vendor deleted successfully";
    }

    public List<Vendor> findByShoeCategoryName(String name) {
        return vendorRepository.findByShoeCategoryName(name);
    }

    public String addShoeCategory(UUID vendorId, UUID shoeCategoryId) {

        Optional<Vendor> vendorResult = vendorRepository.findById(vendorId);

        if (vendorResult.isEmpty()) {
            return "The vendor with that id does not exist";
        }

        Optional<ShoeCategory> categoryResult = shoeCategoryRepository.findById(shoeCategoryId);

        if (categoryResult.isEmpty()) {
            return "That shoe category does not exist";
        }

        Vendor vendor = vendorResult.get();
        ShoeCategory category = categoryResult.get();

        if (vendor.getShoeCategories() == null) {
            vendor.setShoeCategories(new ArrayList<>());
        }

        if (vendor.getShoeCategories().contains(category)) {
            return "The vendor already has that shoe category";
        }

        vendor.getShoeCategories().add(category);
        vendorRepository.save(vendor);

        return "Shoe category added to the vendor";
    }

    public String changeMarketStall(UUID vendorId, int stallNumber) {

        Optional<Vendor> vendorResult = vendorRepository.findById(vendorId);

        if (vendorResult.isEmpty()) {
            return "The vendor with that id does not exist";
        }

        Optional<MarketStall> stallResult = marketStallRepository.findByStallNumber(stallNumber);

        if (stallResult.isEmpty()) {
            return "The market stall with that stallNumber does not exist";
        }

        Vendor vendor = vendorResult.get();
        MarketStall newStall = stallResult.get();

        if (vendor.getMarketStall() != null &&
                vendor.getMarketStall().getId().equals(newStall.getId())) {
            return "Market stall changed successfully";
        }

        if (vendorRepository.existsByMarketStall(newStall)) {
            return "That market stall is taken by another vendor";
        }

        vendor.setMarketStall(newStall);
        vendorRepository.save(vendor);

        return "Market stall changed successfully";
    }
}
