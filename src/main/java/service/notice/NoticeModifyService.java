package service.notice;

import Model.NoticeDTO;
import command.NoticeCommand;
import org.springframework.beans.factory.annotation.Autowired;
import repository.NoticeRepository;

public class NoticeModifyService {
@Autowired
    NoticeRepository noticeRepository;

    public void noticeModify(NoticeCommand noticeCommand){
        NoticeDTO dto = new NoticeDTO();
        dto.setNoticeNo(noticeCommand.getNoticeNo());
        dto.setNoticeCon(noticeCommand.getNoticeCon());
        dto.setNoticeSub(noticeCommand.getNoticeSub());
        noticeRepository.noticeModify(dto);
    }
}
