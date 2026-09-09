package over.rental.manager.member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Transactional
    void saveMemberTest(){
        Member member = new Member("test", "1234");
        memberRepository.save(member);
        assertNotNull(member.getId());
        assertEquals("test", member.getUsername());
        assertEquals("1234", member.getPassword());

    }
}
