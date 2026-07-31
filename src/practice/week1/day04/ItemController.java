package over.rental.manager.item;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ItemController {

    @GetMapping("/items/new")
    public String item(){
        return "items/new";
    }

    @PostMapping("/items")
    public String createItem(ItemForm form){
        System.out.println("물건명: " + form.getName());
        System.out.println("설명: " + form.getDescription());
        return "redirect:items/new";
    }
}
