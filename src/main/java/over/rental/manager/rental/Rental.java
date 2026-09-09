package over.rental.manager.rental;

import jakarta.persistence.*;
import over.rental.manager.item.Item;

import java.time.LocalDateTime;

@Entity
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String renterName;
    private LocalDateTime rentedAt;

    protected Rental(){}

    public Rental(String renterName) {
        this.renterName = renterName;
        this.rentedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getRenterName() {
        return renterName;
    }

    public LocalDateTime getRentedAt() {
        return rentedAt;
    }

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    public Rental(String renterName, Item item) {
        this.renterName = renterName;
        this.rentedAt = LocalDateTime.now();
        this.item = item;
    }

    public Item getItem() {
        return item;
    }
}
