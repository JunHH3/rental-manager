package over.rental.manager.member;

import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Member join(String username, String password) {
        Member member = new Member(username, password);
        return memberRepository.save(member);
    }

    public Member login(String username, String password) {
        Optional<Member> member = memberRepository.findByUsername(username);
        Member foundMember = member.orElseThrow(
                () -> new NullPointerException("아이디를 찾을 수 없습니다.")
        );

        if (password.equals(foundMember.getPassword())) {
            return foundMember;
        }
        throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
    }
}
