package service.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Errors;

import Model.AuthInfoDTO;
import Model.MemberDTO;
import command.MemberCommand;
import repository.MemberRepository;

//회원 비밀번호 수정을 위한 서비스페이지
public class MemberPwUpdateService {
    @Autowired
    BCryptPasswordEncoder bcryptPasswordEncoder;
    @Autowired
    MemberRepository memberRepository;
    public void memPwUpdate(MemberCommand memberCommand, Errors errors,HttpSession session) {
        AuthInfoDTO authInfo = (AuthInfoDTO)session.getAttribute("authInfo");
        if(bcryptPasswordEncoder.matches(memberCommand.getOldPw(),
                authInfo.getUserPw())) {
            MemberDTO dto = new MemberDTO();
            dto.setMemId(authInfo.getUserId());
            dto.setMemPw(bcryptPasswordEncoder.encode(memberCommand.getMemPw()));
            memberRepository.memPwUpdate(dto);
        }else {
            errors.rejectValue("oldPw", "notPw");
        }
    }
}