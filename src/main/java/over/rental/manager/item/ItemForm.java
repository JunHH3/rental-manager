package over.rental.manager.item;

import jakarta.validation.constraints.NotBlank;

public class ItemForm {

    @NotBlank(message = "상품명을 입력해주세요")
    private String name;
    private String description;


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
