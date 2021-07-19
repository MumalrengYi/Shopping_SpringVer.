package controller.goods;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import command.GoodsOrderCommand;
import command.ReviewCommand;
import service.goods.*;

@Controller
@RequestMapping("cart")
public class GoodsCartController {
    @Autowired
    GoodsCartAddService goodsCartAddService;
    @Autowired
    GoodsCartListService goodsCartListService;
    @Autowired
    GoodsCartQtyDownService goodsCartQtyDownService;
    @Autowired
    GoodsBuyService goodsBuyService;
    @Autowired
    GoodsOrderService goodsOrderService;
    @Autowired
    OrderProcessListService orderProcessListService;
    @Autowired
    DoPaymentService doPaymentService;
    @Autowired
    ReviewWriteService reviewWriteService;
    @Autowired
    GoodsReviewUpdateService goodsReviewUpdateService;
    @Autowired
    CartProdDeleteService cartProdDeleteService;
    @Autowired
    GoodsCartRemoveService goodsCartRemoveService;

    @RequestMapping("goodsCartRemove")
    public String goodsCartRemove(@RequestParam(value="prodNum") String prodNums, HttpSession session){
        goodsCartRemoveService.cartRemove(prodNums,session);
        return "redirect:goodsCartList"; //삭제 후 goodsCartList로
    }


    //request param으로 프로덕트 번호를 받아옴
    //카트을 삭제하면 list페이지로 간다 (redirect: 가야되는 리스트페이지 주소를 입력)
    //Delete를 하기위해서 Repository를 가야한다. - Reposiotry로 가기 위해서는 Service가 필요하다.-Service생성(CartProdDeleteService)
        //service생성
        // [service] 장바구니 삭제를 위해 기준이 되는 번호 즉, prodNum를 가져와야한다 (String prodNum)
            //service를 생성했으니 [Context에 등록] - <bean class="service.goods.CartProdDeleteService" /> 추가
    //[Controller] GoodsCartController에 @Autowired로 cartProdDeleteService 인젝션
    //[Service] GoodsRepository - AutoWired로 인젝션
        @RequestMapping("cartProdDel")
    public String cartProdDel(@RequestParam(value="prodNum")String prodNum, HttpSession session){
        cartProdDeleteService.cartProdDel(prodNum,session);
        return "redirect:goodsCartList";
    }

    @RequestMapping("reviewUpdate")
    public String reviewUpdate(ReviewCommand reviewCommand) {
        goodsReviewUpdateService.reviewUpdate(reviewCommand);
        return "redirect:OrderProcessList";
    }
    @RequestMapping("goodsReviewUpdate")
    public String reviewUpdate(
            @RequestParam(value="purchaseNum") String purchaseNum,
            @RequestParam(value="prodNum") String prodNum,
            HttpSession session, Model  model) {
        goodsReviewUpdateService.reviewInfo(purchaseNum, prodNum,
                session , model);
        return "goods/goodsReviewModify";
    }

    @RequestMapping(value = "reviewWrite", method = RequestMethod.POST)
    public String reviewWrite(ReviewCommand reviewCommand,
                              HttpSession session) {
        reviewWriteService.reviewWrite(reviewCommand,session);
        return "redirect:OrderProcessList";
    }
    @RequestMapping("goodsReview")
    public String goodsReview(
            @ModelAttribute(value = "purchaseNum") String purchaseNum,
            @ModelAttribute(value = "prodNum") String prodNum) {
        return "goods/goodsReview";
    }
    @RequestMapping(value="doPayment",  method = RequestMethod.POST)
    public String doPayment(
            @RequestParam(value="purchaseNum") String purchaseNum,
            @RequestParam(value = "paymentApprPrice") String paymentApprPrice,
            @RequestParam(value = "paymentNumber") String paymentNumber,
            HttpSession session,Model model) {
        doPaymentService.payment(purchaseNum,paymentApprPrice,
                paymentNumber, session, model);
        return "goods/buyfinished";
    }


    @RequestMapping("OrderProcessList")
    public String purchaseCon(HttpSession session, Model model) {
        orderProcessListService.orderList(session, model);
        return "goods/purchaseCon";
    }
    @RequestMapping("goodsOrder")
    public String goodsOrder(GoodsOrderCommand goodsOrderCommand,
                             HttpSession session) {
        // GoodsOrderCommand가 가지고 있는 값을 구매 테이블에 전달
        String purchaseNum =
                goodsOrderService.goodsOrder(goodsOrderCommand, session);
        return "redirect:paymentOk?purchNo="+purchaseNum
                +"&payPrice="+goodsOrderCommand.getPurchaseTotPrice();
    }
    @RequestMapping("paymentOk")
    public String paymentOk(
            @ModelAttribute(value="purchNo") String purchNo,
            @ModelAttribute(value="payPrice") String payPrice) {
        return "goods/payment";
    }
    @RequestMapping(value = "goodsBuy" , method = RequestMethod.POST)
    public String goodsBuy(
            @RequestParam(value="prodCk") String [] prodNums,
            HttpSession session, Model model) {
        goodsBuyService.goodsBuy(prodNums, session, model);
        return "goods/order";
    }
    @RequestMapping(value="goodsCartQtyDown" , method = RequestMethod.GET)
    public String goodsCartQtyDown(
            @RequestParam(value="prodNum") String prodNum,
            @RequestParam(value = "prodPrice") int prodPrice,
            HttpSession session) {
        goodsCartQtyDownService.goodsQtyDown(prodNum, prodPrice, session);
        return "redirect:goodsCartList";
    }
    @RequestMapping("goodsCartList")
    public String goodsCartList(HttpSession session, Model model) {
        goodsCartListService.cartList(session, model);
        return "goods/goodsCart";
    }
    @RequestMapping(value = "goodsCartAdd", method = RequestMethod.GET)
    public String goodsCartQtyAdd(
            @RequestParam(value="cartQty") int cartQty,
            @RequestParam(value="prodNum") String prodNum,
            @RequestParam(value="prodPrice") int prodPrice,
            Model model,HttpSession session) {
        goodsCartAddService.cartAdd(cartQty, prodNum,prodPrice,session,model);
        return "redirect:goodsCartList";
    }
    @RequestMapping(value = "goodsCartAdd" ,method = RequestMethod.POST)
    public String goodsCartAdd(
            @RequestParam(value="cartQty") int cartQty,
            @RequestParam(value="prodNum") String prodNum,
            @RequestParam(value="prodPrice") int prodPrice,
            Model model,HttpSession session) {
        goodsCartAddService.cartAdd(cartQty, prodNum,prodPrice,session,model);
        return "goods/cartAdd";
    }
}
