package over.rental.manager.item;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ItemController {

    private final ItemRepository itemRepository;

    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping("/items/new")
    public String item(){
        return "items/new";
    }

    @PostMapping("/items")
    public String createItem(ItemForm form){

        Item item = new Item(form.getName(), form.getDescription());

        itemRepository.save(item);

        System.out.println("물건 번호 " +item.getId());
        System.out.println("물건명 " + item.getName());
        System.out.println("설명 " + item.getDescription());
        return "redirect:/items/new";
    }
}
