package service.goods;

import Model.AuthInfoDTO;
import Model.WishDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import repository.GoodsRepository;

import javax.servlet.http.HttpSession;

public class GoodsWishService {

    ///////////////////////////////////
    @Autowired
    GoodsRepository goodsRepository;
    ////////////////////////////////////

    public void goodsWishAdd(String prodNum, HttpSession session, Model model){
        AuthInfoDTO authInfo = (AuthInfoDTO) session.getAttribute("authInfo");
        String memId = authInfo.getUserId();
        WishDTO dto = new WishDTO();
        dto.setMemId(memId);
        dto.setProdNum(prodNum);
        int i = goodsRepository.wishAdd(dto);
        model.addAttribute("number", i);


    }
}
