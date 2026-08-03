package over.rental.manager.item;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //실제로 관리하고 db에 저장할 물건
    //물건명과 설명을 저장할 필드
    private String name;
    private String description;

    //jpa가 db 데이터를 item 객체로 만들 때 사용하는 기본 생성자
    protected Item(){}

    //우리가 입력값으로 실제 물건 객체를 만들 때 사용하는 생성자, 생성자로 값을 받음
    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // Item의 값을 밖에서 확인할 수 있음
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
