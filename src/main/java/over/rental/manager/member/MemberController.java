package over.rental.manager.member;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/login")
    public String login(String username, String password, HttpSession session) {
        Member loginMember = memberService.login(username, password);
        session.setAttribute("loginMember", loginMember);
        return "redirect:/";
    }
}
