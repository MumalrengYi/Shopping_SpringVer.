package service.notice;

import Model.NoticeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import repository.NoticeRepository;

import java.util.List;

public class NoticeListService {

    @Autowired
    NoticeRepository noticeRepository;

    public void noticeList(Model model){
        List<NoticeDTO> list = noticeRepository.noticeList();
        model.addAttribute("lists", list);
    }
}
