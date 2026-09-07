package over.rental.manager.item;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Test
    @Transactional
    void createItemTest(){
        ItemForm form = new ItemForm();
        form.setName("카메라");
        form.setDescription("테스트 카메라");

        Item item = itemService.createItem(form);

        assertEquals("카메라", item.getName());
        assertEquals("테스트 카메라", item.getDescription());
    }

    @Test
    @Transactional
    void findItemTest(){
        ItemForm form = new ItemForm();
        form.setName("노트북");
        form.setDescription("조회 테스트");

        Item savedItem = itemService.createItem(form);

        Item foundItem = itemService.findItem(savedItem.getId());

        assertEquals(savedItem.getId(), foundItem.getId());
        assertEquals("노트북", foundItem.getName());
    }

    @Test
    @Transactional
    void updateItemTest(){
        ItemForm form = new ItemForm();
        form.setName("모니터");
        form.setDescription("수정 전");

        Item savedItem = itemService.createItem(form);

        ItemForm updatedForm = new ItemForm();
        updatedForm.setName("게이밍 모니터");
        updatedForm.setDescription("수정 후");

        itemService.updateItem(savedItem.getId(), updatedForm);

        Item updateItem = itemService.findItem(savedItem.getId());

        assertEquals("게이밍 모니터", updateItem.getName());
        assertEquals("수정 후", updateItem.getDescription());
    }

    @Test
    @Transactional
    void deleteItemTest(){
        ItemForm form = new ItemForm();
        form.setName("키보드");
        form.setDescription("삭제 테스트");

        Item savedItem = itemService.createItem(form);

        itemService.deleteItem(savedItem.getId());

        assertThrows(ItemNotFoundException.class,
                ()-> itemService.findItem(savedItem.getId()));
    }

}
