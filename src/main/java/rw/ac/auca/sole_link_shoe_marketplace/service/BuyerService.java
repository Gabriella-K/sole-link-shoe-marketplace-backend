package rw.ac.auca.sole_link_shoe_marketplace.service;

import rw.ac.auca.sole_link_shoe_marketplace.domain.Buyer;
import rw.ac.auca.sole_link_shoe_marketplace.repository.BuyerRepository;
import rw.ac.auca.sole_link_shoe_marketplace.repository.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BuyerService {

    private final BuyerRepository buyerRepository;
    private final VendorRepository vendorRepository;

    public BuyerService(BuyerRepository buyerRepository, VendorRepository vendorRepository) {
        this.buyerRepository = buyerRepository;
        this.vendorRepository = vendorRepository;
    }

    public String save(Buyer buyer) {
        buyerRepository.save(buyer);
        return "Buyer saved successfully";
    }

    public List<Buyer> findAll() {
        return buyerRepository.findAll();
    }

    public Optional<Buyer> findById(UUID id) {
        return buyerRepository.findById(id);
    }

    public String update(UUID id, Buyer buyer) {
        if (buyerRepository.findById(id).isEmpty()) {
            return "The buyer with that id does not exist";
        }

        buyer.setId(id);
        buyerRepository.save(buyer);

        return "Buyer updated successfully";
    }

    public String delete(UUID id) {
        if (buyerRepository.findById(id).isEmpty()) {
            return "The buyer with that id does not exist";
        }

        buyerRepository.deleteById(id);
        return "Buyer deleted successfully";
    }

    public List<Buyer> findByLastName(String lastName) {
        return buyerRepository.findByLastNameIgnoreCaseOrderByFirstNameAsc(lastName);
    }

    public Optional<List<Buyer>> findBuyersOfVendor(UUID vendorId) {

        if (!vendorRepository.existsById(vendorId)) {
            return Optional.empty();
        }

        return Optional.of(buyerRepository.findBuyersOfVendor(vendorId));
    }    

}