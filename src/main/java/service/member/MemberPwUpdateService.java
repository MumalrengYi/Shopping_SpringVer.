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
    MemberRepository memberRepository; //비밀번호 변경을 위해 가져옴

    public void memPwUpdate(MemberCommand memberCommand, Errors errors,HttpSession session) {
        //memberCommand : 기존 비밀번호 값을 가지고 있는 객체
        //error: 발생할 에러 정보를 담을 객체
        //session: 비밀번호를 비교하기위해 가져오는 객체
        AuthInfoDTO authInfo = (AuthInfoDTO)session.getAttribute("authInfo");
        MemberDTO mem = memberRepository.memInfo(authInfo.getUserId()); //DB로부터 내 정보를 가져옴
        if(bcryptPasswordEncoder.matches(memberCommand.getOldPw(), mem.getMemPw())) {
//암호화 된 비밀번호 비교.(사용자가 입력한 기존 비밀번호(memberCommand에 담겨짐), DB에 담겨진 회원비밀번호(authInfo에 담겨짐))
            //기존 session (authinfo.getMemPw())로 가져왔지만, 회원수정뒤에 바로 회원수정이 안되는 문제가
            //있어 session값을 비교하는것에서 DB에 담긴 값을 비교하는것으로 바꿔주었음.

            //위에서 비교한 비밀번호가 맞다고 한다면
            MemberDTO dto = new MemberDTO(); //DTO객체를 불러와서
            dto.setMemId(authInfo.getUserId()); //세션에서 불러온 아이디를 dto에 set.
            dto.setMemPw(bcryptPasswordEncoder.encode(memberCommand.getMemPw())); //입력받은 기존 비밀번호를 암호화 시켜 set.
            memberRepository.memPwUpdate(dto); //dao에 set된 Id,Pw값을 memberReposity에 있는 memPwPudate에 전달.
        }else {
            errors.rejectValue("oldPw", "notPw");
        }
    }
}