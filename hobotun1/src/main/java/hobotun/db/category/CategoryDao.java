package hobotun.db.category;

import hobotun.db.category.action.SelectAllCategory;
import hobotun.db.category.table.CategoryTbl;

import java.io.Serializable;
import java.util.List;

import javax.sql.DataSource;

public class CategoryDao implements ICategoryDao, Serializable {

	private static final long serialVersionUID = 6758246860262697891L;
	private DataSource dataSource;
	private SelectAllCategory selectAllCategory;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		selectAllCategory = new SelectAllCategory(dataSource);
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	@Override
	public List<CategoryTbl> getAllCategory() {
		return selectAllCategory.execute();
	}

	@Override
	public CategoryTbl getCategoryById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateCatery(CategoryTbl categiry) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insertCategiry(CategoryTbl category) {
		// TODO Auto-generated method stub

	}

}
