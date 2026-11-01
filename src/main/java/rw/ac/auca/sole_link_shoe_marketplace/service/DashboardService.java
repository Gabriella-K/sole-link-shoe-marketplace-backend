package rw.ac.auca.sole_link_shoe_marketplace.service;

import rw.ac.auca.sole_link_shoe_marketplace.repository.BuyerRepository;
import rw.ac.auca.sole_link_shoe_marketplace.repository.VendorRepository;
import rw.ac.auca.sole_link_shoe_marketplace.repository.MarketStallRepository;
import rw.ac.auca.sole_link_shoe_marketplace.repository.ShoeOrderRepository;
import rw.ac.auca.sole_link_shoe_marketplace.repository.ReceiptRepository;
import rw.ac.auca.sole_link_shoe_marketplace.domain.OrderStatus;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class DashboardService {

    private final BuyerRepository buyerRepository;
    private final VendorRepository vendorRepository;
    private final MarketStallRepository marketStallRepository;
    private final ShoeOrderRepository shoeOrderRepository;
    private final ReceiptRepository receiptRepository;

    public DashboardService(
            BuyerRepository buyerRepository,
            VendorRepository vendorRepository,
            MarketStallRepository marketStallRepository,
            ShoeOrderRepository shoeOrderRepository,
            ReceiptRepository receiptRepository) {

        this.buyerRepository = buyerRepository;
        this.vendorRepository = vendorRepository;
        this.marketStallRepository = marketStallRepository;
        this.shoeOrderRepository = shoeOrderRepository;
        this.receiptRepository = receiptRepository;
    }

    public Map<String, Long> getDashboardSummary() {

        Map<String, Long> summary = new LinkedHashMap<>();

        summary.put("buyers", buyerRepository.count());

        summary.put("vendors", vendorRepository.count());

        summary.put("freeStalls",
                (long) marketStallRepository.findFreeMarketStalls().size());

        summary.put("ordersToday",
                shoeOrderRepository.countByOrderDate(LocalDate.now()));

        summary.put("placed",
                shoeOrderRepository.countByStatus(OrderStatus.PLACED));

        summary.put("receipts", receiptRepository.count());

        return summary;
    }
}