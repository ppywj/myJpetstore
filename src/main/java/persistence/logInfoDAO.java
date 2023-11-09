package persistence;



import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author pp
 * 日志信息数据库接口
 */
@SuppressWarnings({"ALL"})
public interface logInfoDAO {
    //添加访问种类日志
     public void addViewCategoryRecord(String userAccount, String category, Timestamp datetTime);
     //添加登录日志
     public void addLoginRecord(String userAccount,Timestamp dateTime,String ip);
     //添加注册日志
     public void addRegisterRecord(String userAccount,String ip,Timestamp dateTime);
     //添加订单日志
    public void addOrderRecord(String userAccount, BigDecimal total, String category, Timestamp dateTime);
     //添加访问商品日志
    public void addViewItemRecord(String userAccount,String itemName,Timestamp dateTime);
    //购物车日志--添加到购物车 移除购物车


}
