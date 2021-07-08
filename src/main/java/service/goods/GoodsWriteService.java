package service.goods;

import Model.AuthInfoDTO;
import Model.GoodsDTO;
import command.GoodsCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import repository.GoodsRepository;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class GoodsWriteService {
    @Autowired
    GoodsRepository goodsRepository;

    public void goodsWrite(GoodsCommand goodsCommand, HttpSession session){
        GoodsDTO dto = new GoodsDTO();
        dto.setCtgr(goodsCommand.getCtgr());
        dto.setProdCapacity(goodsCommand.getProdCapacity());
        dto.setProdDelFee(goodsCommand.getProdDelFee());
        dto.setProdDetail(goodsCommand.getProdDetail());
        dto.setProdName(goodsCommand.getProdName());
        dto.setProdNum(goodsCommand.getGoodsNum());
        dto.setProdPrice(goodsCommand.getProdPrice());
        dto.setProdSupplyer(goodsCommand.getProdSupplyer());
        dto.setRecommend(goodsCommand.getRecommend());
        //employeeId는 로그인시 session 저장
        AuthInfoDTO authInfo = (AuthInfoDTO)session.getAttribute("authInfo");
        dto.setEmployeeId(authInfo.getGrade());
        //배열로 받아온건 forEach로 처리

        String prodImage=""; //DB에 파일 이름만 저장해주기 위해 변수 선언
        for(MultipartFile mf : goodsCommand.getProdImage1()){
            //image1에 있는 파일들을 mf에 하나씩 넣어준다.
            //DB에는 파일 이름만 저장하게 된다. (prodImage 선언)

            String original = mf.getOriginalFilename(); //확장자를 알기 위해서 원래 파일 이름을 가져와야한다
            //가져와서 확장자만 추출 한다

            String originalExt = original.substring(original.lastIndexOf(".")); //뒤에있는 .부터 잘라내기
            String store = UUID.randomUUID().toString().replace("-","")+originalExt; //UUID 클래스: 전세계적으로 유니크한 아이디를 부여해줄수있는 클래스
            // dash (" - ") 가 있는경우에는 지워주도록 하겠습니다.

            //DB에 저장할 파일명을 추출하여 prodImage에 저장
            prodImage += store+",";

            //파일을 시스템에 저장 , 저장할 경로가 필요하다.
            // goods패키지 내에 upload 폴더 생성,.
            String filePath = session.getServletContext().getRealPath("WEB-INF/view/goods/upload");
            File file = new File(filePath+"/"+store);
            //파일저장
            try {
                mf.transferTo(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        dto.setProdImage(prodImage);

        System.out.println(dto.getCtgr());
        System.out.println(dto.getProdCapacity());
        System.out.println(dto.getProdDelFee());
        System.out.println(dto.getProdDetail());
        System.out.println(dto.getProdName());
        System.out.println(dto.getProdPrice());
        System.out.println(dto.getProdSupplyer());
        System.out.println(dto.getRecommend());
        System.out.println(dto.getEmployeeId());
        System.out.println(dto.getProdImage());


        goodsRepository.goodsWrite(dto);
    }
}
