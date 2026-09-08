package over.rental.manager.rental;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class RentalServiceTest {

    @Autowired
    private RentalService rentalService;

    @Test
    @Transactional
    void createRentalTest() {
        Rental savedRental = rentalService.createRental("홍길동");
        assertEquals("홍길동", savedRental.getRenterName());
        assertNotNull(savedRental.getId());
        assertNotNull(savedRental.getRentedAt());
    }
}
