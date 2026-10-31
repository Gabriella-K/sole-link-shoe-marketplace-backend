package rw.ac.auca.sole_link_shoe_marketplace.service;

import rw.ac.auca.sole_link_shoe_marketplace.domain.Vendor;
import rw.ac.auca.sole_link_shoe_marketplace.repository.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VendorService {

    private final VendorRepository vendorRepository;

    public VendorService(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
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
}