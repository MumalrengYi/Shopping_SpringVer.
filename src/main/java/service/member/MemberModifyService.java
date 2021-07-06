
package service.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.Errors;

import Model.AuthInfoDTO;
import Model.MemberDTO;
import command.MemberCommand;
import repository.MemberRepository;

//회원정보수정 서비스페이지
public class MemberModifyService {
    @Autowired
    MemberRepository memberRepository; //SQL에 접근을 도와주는 MemberRepository Injection.
    @Autowired
    BCryptPasswordEncoder bcryptPasswordEncoder; // 비밀번호를 암호와 시켜 보내주는 클래스 Injection.

    public void memUpdate(HttpSession session, MemberCommand memberCommand,Errors errors) {
        AuthInfoDTO authInfo = (AuthInfoDTO)session.getAttribute("authInfo");
        String memId = authInfo.getUserId();
        memberRepository.memInfo(memId);
        if(bcryptPasswordEncoder.matches(memberCommand.getMemPw(), authInfo.getUserPw())) {
            MemberDTO memberDTO = new MemberDTO();
            memberDTO.setDetailAdd(memberCommand.getDetailAdd());
            memberDTO.setMemAccount(memberCommand.getMemAccount());
            memberDTO.setMemAddress(memberCommand.getMemAddress());
            memberDTO.setMemEmail(memberCommand.getMemEmail());
            memberDTO.setMemEmailCk(memberCommand.getMemEmailCk());
            memberDTO.setMemPhone(memberCommand.getMemPhone());
            memberDTO.setPostNumber(memberCommand.getPostNumber());
            memberDTO.setMemId(memberCommand.getMemId());
            memberRepository.memUpdate(memberDTO);
        }else {
            //만약(입력비번 != 기존비번) 이면, notPw 에러 출력
            errors.rejectValue("memPw", "notPw");
        }
    }
}