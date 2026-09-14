package over.rental.manager.item;

import jakarta.persistence.Lob;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemApiController {

    private final ItemService itemService;

    public ItemApiController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/api/items/{id}")
    public ItemDto getItem(@PathVariable Long id) {
     return itemService.findItemDto(id);
    }
}
