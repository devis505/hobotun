package hobotun.db.category;

import hobotun.db.category.table.CategoryTbl;

import java.util.List;

public interface ICategoryDao {

    public List<CategoryTbl> getAllCategory();

    public CategoryTbl getCategoryById(Integer id);

    public void updateCatery(CategoryTbl categiry);

    public void insertCategiry(CategoryTbl category);

}
