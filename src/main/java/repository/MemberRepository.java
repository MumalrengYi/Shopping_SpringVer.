package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import Model.MemberDTO;

public class MemberRepository {
    @Autowired
    SqlSession sqlSession;

    String namespace = "mappers.memberMapper";
    String statement;

    //회원삭제 (memId기준)
    public void memDelete(String memId) {
        statement = namespace +".memDelete";
        sqlSession.delete(statement, memId);
    }

    //회원비밀번호수정
    public void memPwUpdate(MemberDTO dto) {
        statement = namespace +".memPwUpdate";
        sqlSession.update(statement, dto);
    }

    //회원정보가져오기 (수정할 값을 memberDTO 에서 가져옴)
    public MemberDTO memInfo(String memId) {
        statement = namespace + ".memInfo";
        return sqlSession.selectOne(statement, memId);
    }

    //회원삭제2 (memId기준)
    public void memDel(String memId) {
        statement = namespace +".memDel";
        sqlSession.delete(statement, memId);
    }

    //훠왼정보수정 (수정할 값을 MemberDTO에서 가져옴)
    public void memUpdate(MemberDTO dto) {
        statement = namespace + ".memUpdate";
        sqlSession.update(statement, dto);
    }

    //회원정보가져오기 (여러명) - List로 배열 사용, memId로 검색
    public List<MemberDTO> memList(String memId) {
        statement = namespace +".memList";
        return sqlSession.selectList(statement,memId);
    }

    //회원가입 (MemberDTO로 정보를 가져옴)
    public void memJoin(MemberDTO dto) {
        statement = namespace + ".memJoin";
        sqlSession.insert(statement, dto);
    }
}