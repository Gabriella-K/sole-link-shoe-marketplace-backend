package rw.ac.auca.sole_link_shoe_marketplace.domain;

import jakarta.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "vendor")
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "business_name")
    private String businessName;

    @Column(name = "owner_name")
    private String ownerName;

    @Column(name = "phone")
    private String phone;

    @ManyToOne
    @JoinColumn(name = "market_stall_id")
    private MarketStall marketStall;

    @ManyToMany
    @JoinTable(name = "vendor_shoe_category", 
    joinColumns = @JoinColumn(name = "vendor_id"), 
    inverseJoinColumns = @JoinColumn(name = "shoe_category_id"))
    private List<ShoeCategory> shoeCategories;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public MarketStall getMarketStall() {
        return marketStall;
    }

    public void setMarketStall(MarketStall marketStall) {
        this.marketStall = marketStall;
    }

    public List<ShoeCategory> getShoeCategories() {
        return shoeCategories;
    }

    public void setShoeCategories(List<ShoeCategory> shoeCategories) {
        this.shoeCategories = shoeCategories;
    }

    
}
