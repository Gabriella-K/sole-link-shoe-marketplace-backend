package rw.ac.auca.sole_link_shoe_marketplace.service;

import rw.ac.auca.sole_link_shoe_marketplace.domain.MarketStall;
import rw.ac.auca.sole_link_shoe_marketplace.repository.MarketStallRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MarketStallService {

    private final MarketStallRepository marketStallRepository;

    public MarketStallService(MarketStallRepository marketStallRepository) {
        this.marketStallRepository = marketStallRepository;
    }

    public String save(MarketStall marketStall) {
        marketStallRepository.save(marketStall);
        return "Market stall saved successfully";
    }

    public List<MarketStall> findAll() {
        return marketStallRepository.findAll();
    }

    public Optional<MarketStall> findById(UUID id) {
        return marketStallRepository.findById(id);
    }

    public String update(UUID id, MarketStall marketStall) {
        if (marketStallRepository.findById(id).isEmpty()) {
            return "The market stall with that id does not exist";
        }

        marketStall.setId(id);
        marketStallRepository.save(marketStall);

        return "Market stall updated successfully";
    }

    public String delete(UUID id) {
        if (marketStallRepository.findById(id).isEmpty()) {
            return "The market stall with that id does not exist";
        }

        marketStallRepository.deleteById(id);
        return "Market stall deleted successfully";
    }

    public List<MarketStall> findFreeMarketStalls() {
        return marketStallRepository.findFreeMarketStalls();
    }
}