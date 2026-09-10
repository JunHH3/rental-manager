package over.rental.manager.member;

import org.springframework.stereotype.Service;

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
}
