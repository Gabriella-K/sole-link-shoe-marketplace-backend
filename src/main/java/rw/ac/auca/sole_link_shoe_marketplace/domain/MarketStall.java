package rw.ac.auca.sole_link_shoe_marketplace.domain;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "market_stall")
public class MarketStall {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "stall_number")
    private int stallNumber;

    @Column(name = "name")
    private String name;

    public MarketStall() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getStallNumber() {
        return stallNumber;
    }

    public void setStallNumber(int stallNumber) {
        this.stallNumber = stallNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}