package service;

import domain.recommend;
import persistence.AccountDAO;
import persistence.Imp.AccountDAOImp;

import java.util.ArrayList;

/**
 * @author pp
 */
public class OtherService {
    private AccountDAOImp dao=new AccountDAOImp();
    //获取对应用户的搜索推荐列表
    public recommend getRecommendList(String username)
    {
        return dao.getRecommendList(username);
    }

}