package controller.goods;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import command.GoodsCommand;
import service.goods.*;
import validator.GoodsCommandValidate;

@Controller
@RequestMapping("goods")
public class GoodsController {

/////////////////////////////////////////////////////////
    @Autowired
    GoodsNumberService goodsNumberService;
    GoodsWishService goodsWishService;
    GoodsWriteService goodsWriteService;
    GoodsDetailService goodsDetailService;
    GoodsUpdateService goodsUpdateService;
    GoodsDeleteService goodsDeleteService;
///////////////////////////////////////////////////////////

    @RequestMapping("goodsWishAdd")
    public String goodsWishAdd(@RequestParam (value="prodNum") String prodNum, HttpSession session, Model model){
        goodsWishService.goodsWishAdd(prodNum, session, model);
        return "goods/wish";
    }

    @RequestMapping("goodsDel")
    public String goodsDel(
            @RequestParam(value="prodNum")String prodNum,
            HttpSession session) {
        goodsDeleteService.goodsDel(prodNum, session);
        return "redirect:goodsList";
    }
    @RequestMapping("goodsUpdate")
    public String goodsUpdate(GoodsCommand goodsCommand,
                              Errors errors, HttpSession session) {
        new GoodsCommandValidate().validate(goodsCommand, errors);
        if(errors.hasErrors()) {
            // 값을 command로 받았으므로 오류 발생하여 값을 보낼때 다시
            // command로 전달된다.
            return "goods/goodsModify";
        }
        goodsUpdateService.goodsUpdate(goodsCommand, session);
        return "redirect:/goods/goodsList";
    }
    @RequestMapping("prodModify")
    public String prodModify(
            @RequestParam(value="prodNum") String prodNum,
            Model model) {
        goodsDetailService.goodsDetail(prodNum, model);
        return "goods/goodsModify";
    }
    @RequestMapping("prodDetail")
    public String prodDetail(
            @RequestParam(value = "prodNum") String prodNum,
            Model model) {
        goodsDetailService.goodsDetail(prodNum, model);
        return "goods/goodsDetail";
    }
    @RequestMapping("goodsJoin") /// IOC
    public String goodsJoin(GoodsCommand goodsCommand,
                            Errors errors,HttpSession session) {
        new GoodsCommandValidate().validate(goodsCommand, errors);
        if(errors.hasErrors()) {
            return "goods/goodsJoin";
        }
        goodsWriteService.goodsInsrt(goodsCommand, session);
        return "redirect:goodsList";
    }

    @RequestMapping("goodsRegist") /// IOC
    public String goodsRegist(Model model) {
        goodsNumberService.goodsNum(model);
        return "goods/goodsJoin";
    }
    @Autowired
    GoodsListService goodsListService;
    @RequestMapping("goodsList")
    public String list(
            @RequestParam(value="page", defaultValue = "1")Integer page,
            Model model) {
        goodsListService.goodsList(model,page);
        return "goods/goodsList";
    }
}