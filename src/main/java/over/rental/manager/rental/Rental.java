package over.rental.manager.rental;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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
}
