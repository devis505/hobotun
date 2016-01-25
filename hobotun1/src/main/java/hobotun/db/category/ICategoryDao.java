package hobotun.db.category;

import hobotun.db.category.table.CategoryTbl;

import java.util.List;

public interface ICategoryDao {

    public List<CategoryTbl> getAllCategory();

}
