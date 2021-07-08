package service.goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import repository.GoodsRepository;

//Service가 하는역할 : 데이터를 DB에 전달 하거나 DB로부터 가져온 값을 Model에 저장해줍니다.
public class GoodsNumberService {
    //DB로부터 값을 가지고 오기 위해서는 Repository가 필요하다
    @Autowired
    GoodsRepository goodsRepository;

    public void goodsNum(Model model){
        //DB로부터 번호를 받아옴 --> 페이지로 전송
        String goodsNum = goodsRepository.goodsNum(); //Repository에 저장된 goodsNum 받아서 여기있는 goodsNum에 넣어줌
        model.addAttribute("goodsNum",goodsNum); //goodsNum 어트리뷰트 이름에 goodsNum 값을 넣음
    }

}
