package over.rental.manager.item;

public class ItemForm {

    //등록화면에서 전송한 값을 임시로 담는 객체
    private String name;
    private String description;


    public String getName() {
        return name;
    }

    // spring이 입력 값을 넣어야 함
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
