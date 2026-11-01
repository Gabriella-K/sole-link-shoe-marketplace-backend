
package rw.ac.auca.sole_link_shoe_marketplace.domain;

import jakarta.persistence.*;
import java.util.UUID;
import java.time.LocalDate;

@Entity
@Table(name = "receipt")
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String paymentMethod;

    private String note;

    private int amountPaid;

    private LocalDate issuedDate;

    @ManyToOne
    @JoinColumn(name = "shoe_order_id")
    private ShoeOrder shoeOrder;

    public Receipt() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(int amountPaid) {
        this.amountPaid = amountPaid;
    }

    public LocalDate getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(LocalDate issuedDate) {
        this.issuedDate = issuedDate;
    }

    public ShoeOrder getShoeOrder() {
        return shoeOrder;
    }

    public void setShoeOrder(ShoeOrder shoeOrder) {
        this.shoeOrder = shoeOrder;
    }
}
