package over.rental.manager.item;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice

public class ItemExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ItemNotFoundException.class)
    public String handleItemNotFound(ItemNotFoundException e, Model model) {
        model.addAttribute("error", e.getMessage());
        return "error/item-not-found";
    }
}
