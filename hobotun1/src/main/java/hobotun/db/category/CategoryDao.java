package hobotun.db.category;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import hobotun.db.category.action.DeleteCategory;
import hobotun.db.category.action.InsertCategory;
import hobotun.db.category.action.SelectAllCategory;
import hobotun.db.category.action.SelectCategoryById;
import hobotun.db.category.table.CategoryTbl;

public class CategoryDao implements ICategoryDao, Serializable {

	private static final long serialVersionUID = 6758246860262697891L;
	private DataSource dataSource;
	private SelectAllCategory selectAllCategory;
	private SelectCategoryById SelectCategoryById;
	private InsertCategory insertCategory;
	private DeleteCategory deleteCategory;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		selectAllCategory = new SelectAllCategory(dataSource);
		SelectCategoryById = new SelectCategoryById(dataSource);
		insertCategory = new InsertCategory(dataSource);
		deleteCategory = new DeleteCategory(dataSource);
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	@Override
	public List<CategoryTbl> getAllCategory() {
		return selectAllCategory.execute();
	}

	public CategoryTbl getCategoryById(Integer id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id_category", id);
		
		return SelectCategoryById.executeByNamedParam(paramMap).get(0);
	}
	
	public void insertCategory (Map<String, Object> inParams) {
		insertCategory.updateByNamedParam(inParams);
	}
	
	public void deleteCategory (Map<String, Object> inParams) {
		deleteCategory.updateByNamedParam(inParams);
	}

}
