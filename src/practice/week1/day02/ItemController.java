package over.rental.manager.item;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ItemController {

    @GetMapping("/items/new")
    public String newItem(){
        return "items/new";
    }
}
