package controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import Model.AuthInfoDTO;
import command.MemberCommand;
import service.main.LoginService;
import service.member.MemberEmailCkService;
import service.member.MemberJoinService;
import validator.MemberCommandValidator;

@Controller
@RequestMapping("register")
public class MemberController {

    //이메일 인증을 위한 페이지 memberMail
    //이용자가 이메일을 받고 메일에 있는 이메일 인증버튼을 누르면
    //이 페이지로 이동하여 오게된다
    //이 페이지로 올 때 num값과 email값을 같이 물게오게 되는데
    //넘어올 때 RequestParam을 통해 num과 receiver에 각각의 값을 담아
    //String num과 String receiver에 넣어주게 된다.
    //String num과 String receiver는 넘겨받은 값을 repository를 통해 DB에 넣어주게된다.
    @Autowired
    MemberEmailCkService memberEmailCkService; //memberMail를 위해 추가

    @RequestMapping("memberMail")
    public String memberMail(@RequestParam(value="num") String num,
                             @RequestParam(value="receiver") String receiver){
        //파람으로 가져온 receiver값, num값을 emailCk메서드로 전송하여
        //DTO로 저장후 DB로 전송하게된다.
        int i = memberEmailCkService.emailCk(receiver, num);
        //repository 결과값 리턴 -> EmailCkService 리턴 -> memberMail(Controller) 리턴

        //리턴받은 결과값에 따라 경로를 달리 함
        if(i>0){
            return "member/welcome";
        }else{
            return "member/fail";
        }

    }



    @RequestMapping("agree")
    public String agree() {
        return "member/agree";
    }
    @RequestMapping(value="memRegist", method = RequestMethod.POST)
    public String  memRegist(
            @RequestParam(value="agree", defaultValue = "false")
                    Boolean agree,
            @ModelAttribute(value = "memberCommand")
                    MemberCommand memberCommand,Model model) {
        if(!agree) {
            model.addAttribute("err", "동의에 체크해주세요.");
            return "member/agree";
        }
        return "member/memberForm";
    }
    @Autowired
    MemberJoinService memberJoinService;
    @Autowired
    LoginService loginService;
    @RequestMapping(value="memJoin",method = RequestMethod.POST )
    public String memJoin(MemberCommand memberCommand,Errors errors) {
        new MemberCommandValidator().validate(memberCommand, errors);
        if(errors.hasErrors()) {
            return "member/memberForm";
        }
        AuthInfoDTO authInfo = loginService.logIn(memberCommand.getMemId(),
                memberCommand.getMemPw());
        if(authInfo != null) {
            errors.rejectValue("memId", "duplicate");
            return "member/memberForm";
        }
        memberJoinService.memJoin(memberCommand);
        return "redirect:/";
    }

}