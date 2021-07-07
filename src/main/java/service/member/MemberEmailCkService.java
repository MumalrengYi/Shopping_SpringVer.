package service.member;

import Model.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import repository.MemberRepository;

public class MemberEmailCkService {
    //Email을 통한 인증을 진행하기 위한 서비스페이지
    //memberRepository에서 리턴받은 결과값을 다시 리턴
    @Autowired
    MemberRepository memberRepository;
    public int emailCk(String email, String ckOk){
        MemberDTO dto = new MemberDTO(); //email, ckOk값을 memberDTO에 저장해준다.
        dto.setMemEmail(email); //받아온 email값을 dto에 저장
        dto.setCkOk(ckOk); //받아온 ckOk값을 dto에 저장
        return memberRepository.updateCkok(dto);
        //받아온 모든 값을 dto에 넣어줬으니 dto를 memberRepository로 보내
        //DB로 저장해준다.
    }
}
