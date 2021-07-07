package service.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import Model.MemberDTO;
import Model.AuthInfoDTO;
import repository.MemberRepository;

//회원 비밀번호 유효성 확인을 위한 서비스페이지
public class MemberPwConfirmService {
    @Autowired
    BCryptPasswordEncoder bcryptPasswordEncoder;
    @Autowired
    MemberRepository memberRepository;


    public String memPw(String memPw,HttpSession session,Model model) {
        AuthInfoDTO authInfo = (AuthInfoDTO)session.getAttribute("authInfo");
        MemberDTO dto = memberRepository.memInfo(authInfo.getUserId());
        if(bcryptPasswordEncoder.matches(memPw, dto.getMemPw())) {
            return "member/pwChangeOk";
        }else {
            model.addAttribute("pwFail1", "비밀번호가 틀립니다");
            return "member/pwChang";
        }
    }
}