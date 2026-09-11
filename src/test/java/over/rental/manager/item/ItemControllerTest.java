package over.rental.manager.item;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.transaction.annotation.Transactional;
import over.rental.manager.member.Member;
import over.rental.manager.member.MemberService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ItemControllerTest {

    @Autowired private ItemController itemController;
    @Autowired private ItemService itemService;
    @Autowired private MemberService memberService;

    @Test
    @Transactional
    void rentWithoutLoginTest() {
        ItemForm form = new ItemForm();
        form.setName("카메라");
        form.setDescription("테스트 카메라");

        Item savedItem = itemService.createItem(form);
        MockHttpSession session = new MockHttpSession();
        String result = itemController.rentItem(savedItem.getId(), session);
        assertEquals("redirect:/", result);
        assertFalse(savedItem.isRented());
    }

    @Test
    @Transactional
    void rentWithLoginTest() {
        Member member = memberService.join("test", "1234");
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("loginMember", member);

        ItemForm form = new ItemForm();
        form.setName("카메라");
        form.setDescription("테스트 카메라");
        Item savedItem = itemService.createItem(form);
        String result = itemController.rentItem(savedItem.getId(), session);
        assertEquals("redirect:/items/"+ savedItem.getId(), result);
        assertTrue(savedItem.isRented());
    }

}
