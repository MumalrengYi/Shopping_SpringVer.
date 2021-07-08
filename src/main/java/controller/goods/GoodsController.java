package controller.goods;

import command.GoodsCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.goods.GoodsListService;
import service.goods.GoodsNumberService;
import service.goods.GoodsWriteService;
import validator.GoodsCommandValidate;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("goods")
public class GoodsController {
    @Autowired
    GoodsNumberService goodsNumberService; //goodsRegist사용
    @Autowired
    GoodsWriteService goodsWriteService;

    @RequestMapping(value="goodsJoin", method = RequestMethod.POST)
    public String join(GoodsCommand goodsCommand, Errors errors, HttpSession session){
        new GoodsCommandValidate().validate(goodsCommand,errors);
        if(errors.hasErrors()){
            //에러가 있으면 상픔등록 페이지로..
            return "goods/goodsJoin";
        }
        goodsWriteService.goodsWrite(goodsCommand,session);
        return "redirect:/goods/goodsList";
    }
@Autowired
    GoodsListService goodsListService;
    @RequestMapping("goodsList")
    public String list(Model model){
        goodsListService.goodsList(model);
        return "goods/goodsList";
    }

    //java에서 만들어진 값을 jsp로 전달할 때 사용하는게 MODEL
    // 서비스에서 만든 값 --> JSP(뷰)로 값을 전달
    @RequestMapping("goodsRegist")
    public String regist(Model model){
        goodsNumberService.goodsNum(model);
        return "goods/goodsJoin";
    }
}
