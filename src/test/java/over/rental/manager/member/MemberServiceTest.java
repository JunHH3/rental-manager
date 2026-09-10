package over.rental.manager.member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    @Transactional
    void joinTest(){
        Member savedMember = memberService.join("test", "1234");

        assertNotNull(savedMember.getId());
        assertEquals("test", savedMember.getUsername());
        assertEquals("1234", savedMember.getPassword());
    }

    @Test
    @Transactional
    void loginTest(){
        memberService.join("test", "1234");
        Member loginMember = memberService.login("test", "1234");

        assertNotNull(loginMember.getId());
        assertEquals("test", loginMember.getUsername());
        assertEquals("1234", loginMember.getPassword());
    }

    @Test
    @Transactional
    void loginFailTest(){
        memberService.join("test", "1234");

        assertThrows(IllegalArgumentException.class,
                () -> memberService.login("test", "1122"));
    }
}
