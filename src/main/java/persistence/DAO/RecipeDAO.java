package persistence.DAO;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import persistence.DTO.MemberDTO;
import persistence.DTO.RecipeDTO;
import persistence.mapper.MemberMapper;
import persistence.mapper.RecipeMapper;

import java.util.List;

public class RecipeDAO {

    private SqlSessionFactory sqlSessionFactory = null;

    public RecipeDAO(SqlSessionFactory sqlSessionFactory){
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public List<RecipeDTO> getRandom(){
        SqlSession session = sqlSessionFactory.openSession();
        RecipeMapper mapper = session.getMapper(RecipeMapper.class);
        List<RecipeDTO> rnd = mapper.getRandom();
        return rnd;
    }

    public int selectNumber(String foodName) {
        RecipeDTO recipeDTO = null;
        SqlSession session = sqlSessionFactory.openSession();
        RecipeMapper mapper = session.getMapper(RecipeMapper.class);
        try {
            recipeDTO = mapper.selectNumber(foodName);
            session.commit();
        }catch (Exception e){
            e.printStackTrace();
            session.rollback();
        } finally {
            session.close();
        }
        return recipeDTO.getFoodNum();
    }

//    final DataSource ds = PooledDataSource.getDataSource();
//
//    private Connection conn;
//    PreparedStatement pstmt = null;
//    ResultSet rs = null;
//    Statement stmt = null;
//
//    public TestDAO(SqlSessionFactory sqlSessionFactory) {
//        try {
//            conn = ds.getConnection();
//        }catch(Exception e) {
//            try {
//                conn.close();
//            }catch(SQLException e1) {}
//        }
//    }
//
//    public List<TestDTO> getRandom(){
//        SqlSession session = sqlSessionFactory.openSession();
//        TestMapper mapper = session.getMapper(TestMapper.class);
//        List<TestDTO> rnd = mapper.getRandom();
//        return rnd;
//    }

}
