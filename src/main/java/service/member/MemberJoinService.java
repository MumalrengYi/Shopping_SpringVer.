package service.member;

import controller.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import Model.MemberDTO;
import command.MemberCommand;
import repository.MemberRepository;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MemberJoinService {
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    BCryptPasswordEncoder bcryptPasswordEncoder;
    @Autowired
    MailService mailService;

    public void memJoin(MemberCommand memberCommand) {
        MemberDTO dto = new MemberDTO();
        dto.setDetailAdd(memberCommand.getDetailAdd());
        dto.setMemAccount(memberCommand.getMemAccount());
        dto.setMemAddress(memberCommand.getMemAddress());
        dto.setMemBirth(memberCommand.getMemBirth());
        dto.setMemEmail(memberCommand.getMemEmail());
        dto.setMemEmailCk(memberCommand.getMemEmailCk());
        dto.setMemGender(memberCommand.getMemGender());
        dto.setMemId(memberCommand.getMemId());
        dto.setMemName(memberCommand.getMemName());
        dto.setMemPhone(memberCommand.getMemPhone());
        dto.setMemPw(bcryptPasswordEncoder.encode(memberCommand.getMemPw()));
        dto.setPostNumber(memberCommand.getPostNumber());
        memberRepository.memJoin(dto);
        SimpleDateFormat dateForm = new SimpleDateFormat("yyyyMMddHHmmss"); //날짜형식포맷받아옴
        String num = dateForm.format(new Date());//받은 날짜형식포맷을 num에 재정의
        String subject = "가입환영인사";
        String content ="<html><body>"
                +"안녕하세요."+dto.getMemName()+"님 가입을 환영합니다."
                +"하단의 링크를 눌러 이메일 인증을 완료 해 주세요."
                +"<a href='http://182.172.237.93:9000/SpringMybatisProject2/register/memberMail?num="+num+"&receiver="+dto.getMemEmail()+"'> 이메일 인증하기 </a>"
                //주소에는 num값과 userEmail값이 함께 담겨 memberController에 있는 memberMail메서드로 이동
                +"</body></html>";
        try {
            mailService.sendMail(dto.getMemEmail(), content, subject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //dto.getMemEmail: 이메일 받는사람(가입하는 회원의 이메일주소)


    }
}