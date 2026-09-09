package over.rental.manager.rental;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import over.rental.manager.item.Item;
import over.rental.manager.item.ItemForm;
import over.rental.manager.item.ItemService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class RentalServiceTest {

    @Autowired
    private ItemService itemService;

    @Autowired
    private RentalService rentalService;

    @Test
    @Transactional
    void createRentalTest(){
        ItemForm form = new ItemForm();
        form.setName("카메라");
        form.setDescription("테스트 카메라");

        Item savedItem = itemService.createItem(form);
        Rental savedRental = rentalService.createRental("홍길동", savedItem);
        assertNotNull(savedRental.getId());
        assertEquals("홍길동", savedRental.getRenterName());
        assertNotNull(savedRental.getRentedAt());
        assertEquals(savedItem.getId(), savedRental.getItem().getId());
    }
}
