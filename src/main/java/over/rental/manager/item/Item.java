package over.rental.manager.item;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private RentalStatus rentalStatus;

    protected Item(){}

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
        this.rentalStatus = RentalStatus.AVAILABLE;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public RentalStatus getRentalStatus() {
        return rentalStatus;
    }

    public void update(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void rent() {
        this.rentalStatus = RentalStatus.RENTED;
    }

    public void returnRent() {
        this.rentalStatus = RentalStatus.AVAILABLE;
    }

    public boolean isRented() {
       return this.rentalStatus == RentalStatus.RENTED;
    }

    public boolean isAvailable() {
        return this.rentalStatus == RentalStatus.AVAILABLE;
    }
}
