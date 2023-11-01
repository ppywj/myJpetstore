package persistence;

import java.util.List;
import domain.Category;

public interface CategoryDAO {
    // 获取所有的大类
    List<Category> getCategoryList();

    // 用ID获取某一个大类
    Category getCategory(String categoryId);
}
