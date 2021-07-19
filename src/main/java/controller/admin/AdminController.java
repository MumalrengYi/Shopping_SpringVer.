package controller.admin;

import command.NoticeCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.notice.NoticeWriteService;

import javax.servlet.http.HttpSession;



@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    NoticeWriteService noticeWriteService;

    @RequestMapping("noticeList")
    public String noticeList(){
        return "notice/noticeList";
    }

    @RequestMapping("noticeForm")
    public String noticeForm(){
        return "notice/noticeForm";
    }

    @RequestMapping("noticeWrite")
    public String noticeWrite(NoticeCommand noticeCommand, HttpSession session){
        noticeWriteService.noticeWrite(session, noticeCommand);
        return "redirect:noticeList";
    }

}
