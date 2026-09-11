package over.rental.manager.member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class MemberControllerTest {

    @Autowired MemberController memberController;
    @Autowired MemberService memberService;

    @Test
    @Transactional
    void loginSessionTest() {
        memberService.join("test", "1234");
        MockHttpSession session = new MockHttpSession();

        memberController.login("test", "1234", session);
        Member loginMember = (Member) session.getAttribute("loginMember");

        assertNotNull(loginMember);
        assertEquals("test", loginMember.getUsername());
        assertEquals("1234", loginMember.getPassword());
    }
}
