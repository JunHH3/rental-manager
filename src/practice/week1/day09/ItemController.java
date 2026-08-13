package over.rental.manager.item;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    //등록
    @PostMapping("/items")
    public String createItem(ItemForm form){

        Item item = new Item(form.getName(), form.getDescription());

        itemRepository.save(item);

        System.out.println("물건 번호 " + item.getId());
        System.out.println("상품명 " + item.getName());
        System.out.println("설명 " + item.getDescription());
        return "redirect:/items/new";
    }

    //조회
    @GetMapping("/items")
    public String items(Model model){
        Iterable<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "/items/index";
    }

    //상세조회
    @GetMapping("/items/{id}")
    public String itemDetail(@PathVariable Long id, Model model){
        Item item = itemRepository.findById(id).orElseThrow();
        model.addAttribute("item", item);
        return "items/detail";
    }

    //수정 화면
    @GetMapping("/items/{id}/edit")
    public String editItem(@PathVariable Long id, Model model) {
        Item item = itemRepository.findById(id).orElseThrow();
        model.addAttribute("item", item);
        return "/items/edit";
    }

    //수정
    @PostMapping("/items/{id}/edit")
    public String updateItem(@PathVariable Long id, ItemForm form) {
        Item item = itemRepository.findById(id).orElseThrow();
        item.update(form.getName(), form.getDescription());
        itemRepository.save(item);
        return "redirect:/items/" + id;
    }


}
