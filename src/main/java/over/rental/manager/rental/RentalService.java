package over.rental.manager.rental;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RentalService {

    private final RentalRepository rentalRepository;

    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    @Transactional
    public Rental createRental(String renterName) {
        Rental rental = new Rental(renterName);
        return rentalRepository.save(rental);
    }

    @Transactional(readOnly = true)
    public Rental findRental(Long id) {
        Rental rental = rentalRepository.findById(id).orElseThrow(
                () -> new RentalNotFoundException("대여 기록을 찾을 수 없습니다. id: " + id)
        );
        return rental;
    }
}
