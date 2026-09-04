package over.rental.manager.item;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/items/new")
    public String item(){
        return "items/new";
    }

    //상품등록화면
    @PostMapping("/items")
    public String createItem(@Valid ItemForm form, BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()) {
            String error = bindingResult.getFieldError("name").getDefaultMessage();
            model.addAttribute("error", error);
            return "items/new";
        }

       Item item = itemService.createItem(form);

        System.out.println("상품번호 " + item.getId());
        System.out.println("상품명 " + item.getName());
        System.out.println("설명 " + item.getDescription());
        return "redirect:/items/new";
    }

    //조회
    @GetMapping("/items")
    public String items(@RequestParam(defaultValue = "") String keyword,
                        @RequestParam(defaultValue = "0") int page,
                        Model model){

        Pageable pageable = PageRequest.of(page, 5);
        Page<Item> itemPage = itemService.findItems(keyword, pageable);


        model.addAttribute("items", itemPage.getContent());
        model.addAttribute("currentPage", itemPage.getNumber() + 1);
        model.addAttribute("totalPages", itemPage.getTotalPages());
        model.addAttribute("hasPrevious", itemPage.hasPrevious());
        model.addAttribute("hasNext", itemPage.hasNext());
        model.addAttribute("previousPage", itemPage.getNumber() - 1);
        model.addAttribute("nextPage", itemPage.getNumber() + 1);
        model.addAttribute("keyword", keyword);

        return "items/index";
    }

    //상세조회
    @GetMapping("/items/{id}")
    public String detailItem(@PathVariable Long id, Model model){
       Item item = itemService.findItem(id);
        model.addAttribute("item", item);
        return "items/detail";
    }

    //수정화면
    @GetMapping("/items/{id}/edit")
    public String editItem(@PathVariable Long id, Model model){
        Item item = itemService.findItem(id);
        model.addAttribute("item", item);
        return "items/edit";
    }

    //수정
    @PostMapping("/items/{id}/edit")
    public String updateItem(@PathVariable Long id, ItemForm form) {
       itemService.updateItem(id, form);
        return "redirect:/items/" + id;
    }

    //삭제
    @PostMapping("/items/{id}/delete")
    public String deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return "redirect:/items";
    }

    //대여
    @PostMapping("/items/{id}/rent")
    public String rentItem(@PathVariable Long id) {
        itemService.rentItem(id);
        return "redirect:/items/" + id;
    }

    //반납
    @PostMapping("/items/{id}/return")
    public String returnItem(@PathVariable Long id) {
        itemService.returnItem(id);
        return "redirect:/items/" + id;
    }
}
