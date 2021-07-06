
package service.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import Model.AuthInfoDTO;
import Model.MemberDTO;
import repository.MemberRepository;

public class MemberInfoService {
    @Autowired
    MemberRepository memberRepository; //dto에서 값을 받아와 값을 넣어주기 위해 injection.
    public void memInfo(Model model, HttpSession session) {
        AuthInfoDTO authInfo = (AuthInfoDTO)session.getAttribute("authInfo"); //authinfo 세션값을 가져옴
        String memId = authInfo.getUserId();
        MemberDTO dto = memberRepository.memInfo(memId);
        model.addAttribute("memberCommand", dto);
    }
}