package service.goods;

// 장바구니 삭제를 위한 Service 페이지

import Model.AuthInfoDTO;
import Model.CartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import repository.GoodsRepository;

import javax.servlet.http.HttpSession;

// 장바구니 삭제를 위해 기준이 되는 번호 즉, prodNum를 가져와야한다 (String prodNum)

//GoodsRepository 인젝션

public class CartProdDeleteService {
    @Autowired
    GoodsRepository goodsRepository;
    public void cartProdDel(String prodNum, HttpSession session){
        AuthInfoDTO authInfo = (AuthInfoDTO) session.getAttribute("authInfo");
        String memId = authInfo.getUserId();
        CartDTO dto = new CartDTO();
        dto.setProdNum(prodNum);
        dto.setMemId(memId);

        goodsRepository.cartProdDel(dto);
    }
}
