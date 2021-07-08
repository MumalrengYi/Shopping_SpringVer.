package repository;

import Model.GoodsDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GoodsRepository {

    @Autowired
    SqlSession sqlSession;
    String namespace = "mappers.goodsMapper";
    String statement;

    public List<GoodsDTO> goodsList(){
        statement = namespace+".goodsList";
        return sqlSession.selectList(statement);
    }
    public void goodsWrite(GoodsDTO dto){
        statement = namespace + ".goodsWrite";
        sqlSession.insert(statement,dto);
    }


    //String data type return
    public String goodsNum(){
        statement = namespace + ".goodsNum";
        return sqlSession.selectOne(statement); //paramater없기때문에 state구문만 전달해주면 된다.
    }

}
