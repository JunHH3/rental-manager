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

    private final ItemRepository itemRepository;

    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping("/items/new")
    public String item(){
        return "items/new";
    }

    //상품등록화면
    @PostMapping("/items")
    public String createItem(@Valid ItemForm form, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            String error = bindingResult.getFieldError("name").getDefaultMessage();
            model.addAttribute("error", error);
            return "items/new";
        }

        Item item = new Item(form.getName(), form.getDescription());

        itemRepository.save(item);

        System.out.println("상품번호 " + item.getId());
        System.out.println("상품명 " + item.getName());
        System.out.println("설명 " + item.getDescription());
        return "redirect:/items/new";
    }

    //조회
    @GetMapping("/items")
    public String items(@RequestParam(defaultValue = "") String keyword, @RequestParam(defaultValue = "0")int page, Model model){

        Pageable pageable = PageRequest.of(page, 5);

        Page<Item> itemPage;

        if (keyword.isBlank()) {
            itemPage = itemRepository.findAll(pageable);
        } else {
            itemPage = itemRepository.findByNameContaining(keyword, pageable);
        }

        model.addAttribute("keyword", keyword);
        model.addAttribute("items", itemPage.getContent());
        model.addAttribute("currentPage", itemPage.getNumber());
        model.addAttribute("totalPages", itemPage.getTotalPages());
        model.addAttribute("hasNext", itemPage.hasNext());
        model.addAttribute("hasPrevious", itemPage.hasPrevious());
        model.addAttribute("previousPage", itemPage.getNumber() -1);
        model.addAttribute("nextPage", itemPage.getNumber() +1);
        return "items/index";
    }

    //상세조회
    @GetMapping("/items/{id}")
    public String detailItem(@PathVariable Long id, Model model){

        Item item = itemRepository.findById(id).orElseThrow(()-> new ItemNotFoundException("물건을 찾을 수 없습니다."));
        model.addAttribute("item", item);
        return "items/detail";
    }

    //수정화면
    @GetMapping("/items/{id}/edit")
    public String editItem(@PathVariable Long id, Model model){
        Item item = itemRepository.findById(id).orElseThrow();
        model.addAttribute("item", item);
        return "items/edit";
    }

    //수정
    @PostMapping("/items/{id}/edit")
    public String updateItem(@PathVariable Long id, ItemForm form) {
        Item item = itemRepository.findById(id).orElseThrow();
        item.update(form.getName(), form.getDescription());
        itemRepository.save(item);
        return "redirect:/items/" + id;
    }

    //삭제
    @PostMapping("/items/{id}/delete")
    public String deleteItem(@PathVariable Long id) {
        itemRepository.deleteById(id);
        return "redirect:/items";
    }

    // 렌트요청
    @PostMapping("/items/{id}/rent")
    public String rentItem(@PathVariable Long id) {
        Item item = itemRepository.findById(id).orElseThrow();
        item.rent();
        itemRepository.save(item);
        return "redirect:/items/" + id;
    }


    //렌트반납
    @PostMapping("/items/{id}/return")
    public String returnItem(@PathVariable Long id) {
        Item item = itemRepository.findById(id).orElseThrow();
        item.returnItem();
        itemRepository.save(item);
        return "redirect:/items/" + id;
    }

}
