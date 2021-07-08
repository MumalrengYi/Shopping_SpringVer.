package service.goods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import Model.GoodsDTO;
import repository.GoodsRepository;

import java.util.List;

public class GoodsListService {

    @Autowired
    GoodsRepository goodsRepository;

    public void goodsList(Model model){
        goodsRepository.goodsList();
        List<GoodsDTO> list = goodsRepository.goodsList();
        model.addAttribute("lists", list);
    }
}
