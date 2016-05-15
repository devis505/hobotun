package hobotun.db.category;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import hobotun.core.ParamsForQuery;
import hobotun.db.category.action.DeleteCategory;
import hobotun.db.category.action.DeleteCategory2;
import hobotun.db.category.action.InsertCategory;
import hobotun.db.category.action.InsertCategory2;
import hobotun.db.category.action.SelectAllCategory;
import hobotun.db.category.action.SelectAllCategory2;
import hobotun.db.category.action.SelectCategoryById;
import hobotun.db.category.action.SelectCategoryById2;
import hobotun.db.category.action.SelectCategoryByIdParent;
import hobotun.db.category.table.Category2Tbl;
import hobotun.db.category.table.CategoryTbl;

public class CategoryDao implements ICategoryDao, Serializable {

	private static final long serialVersionUID = 6758246860262697891L;
	private DataSource dataSource;
	private SelectAllCategory selectAllCategory;
	private SelectCategoryById SelectCategoryById;
	private InsertCategory insertCategory;
	private DeleteCategory deleteCategory;

	private DeleteCategory2 deleteCategory2;
	private InsertCategory2 insertCategory2;
	private SelectAllCategory2 selectAllCategory2;
	private SelectCategoryById2 selectCategoryById2;

	private SelectCategoryByIdParent selectCategoryByIdParent;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		selectAllCategory = new SelectAllCategory(dataSource);
		SelectCategoryById = new SelectCategoryById(dataSource);
		insertCategory = new InsertCategory(dataSource);
		deleteCategory = new DeleteCategory(dataSource);

		deleteCategory2 = new DeleteCategory2(dataSource);
		insertCategory2 = new InsertCategory2(dataSource);
		selectAllCategory2 = new SelectAllCategory2(dataSource);
		selectCategoryById2 = new SelectCategoryById2(dataSource);

		selectCategoryByIdParent = new SelectCategoryByIdParent(dataSource);
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	@Override
	public List<CategoryTbl> getAllCategory() {
		return selectAllCategory.execute();
	}

	public List<Category2Tbl> getAllCategory2() {
		return selectAllCategory2.execute();
	}

	public CategoryTbl getCategoryById(Integer id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id_category", id);

		return SelectCategoryById.executeByNamedParam(paramMap).get(0);
	}

	public List<CategoryTbl> selectCategoryByIdParent(Integer id_parent) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id_category_p", id_parent);

		return selectCategoryByIdParent.executeByNamedParam(paramMap);
	}

	public Category2Tbl getCategoryById2(Integer id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id_category", id);

		return selectCategoryById2.executeByNamedParam(paramMap).get(0);
	}

	public void insertCategory(CategoryTbl category) {
		
		ParamsForQuery params = new ParamsForQuery();
		params.setParam("nmCategory", category.getNmCategory());
		params.setParam("id_category_p", category.getId_category_p());

		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		insertCategory.updateByNamedParam(params.getAllParam(), keyHolder);
		
		category.setIdCategory(keyHolder.getKey().intValue());
	}

	public void insertCategory2(Category2Tbl category) {

		ParamsForQuery params = new ParamsForQuery();
		params.setParam("nm_category_p", category.getNm_category_p());

		KeyHolder keyHolder = new GeneratedKeyHolder();

		insertCategory2.updateByNamedParam(params.getAllParam(), keyHolder);

		category.setId_category_p(keyHolder.getKey().intValue());
	}

	public void deleteCategory(Map<String, Object> inParams) {
		deleteCategory.updateByNamedParam(inParams);
	}

	public void deleteCategory2(Map<String, Object> inParams) {
		deleteCategory2.updateByNamedParam(inParams);
	}

}
