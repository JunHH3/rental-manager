package over.rental.manager.item;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ItemController {

    @GetMapping("/items/new")
    public String items(){
        return "items/new";
    }

    @PostMapping("/items")
    public String createItem(){
        System.out.println("물건 등록 요청 도착");
        return "redirect:/items/new";
    }
}
