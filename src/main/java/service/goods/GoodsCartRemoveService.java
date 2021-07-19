package service.goods;

import Model.AuthInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import repository.GoodsRepository;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GoodsCartRemoveService {
    @Autowired
    GoodsRepository goodsRepository;
    public void cartRemove(String prodNums, HttpSession session){
        AuthInfoDTO authInfo = (AuthInfoDTO) session.getAttribute("authInfo");
        String memId = authInfo.getUserId();
        String prodNumMap []= prodNums.split(",");
        List<String> cs = new ArrayList<String>();
        for(String s : prodNumMap){
            cs.add(s);
        }
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("prodNums",cs); //prodNums이름으로 cs를 저장
        condition.put("memId",memId); //prodNums이름으로 cs를 저장
        goodsRepository.cartRemove(condition);

    }
}
