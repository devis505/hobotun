package hobotun.model;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import hobotun.core.Misc;
import hobotun.db.DBUtil;
import hobotun.db.category.CategoryDao;
import hobotun.db.category.table.Category2Tbl;
import hobotun.db.category.table.CategoryTbl;

@ManagedBean(name = "modelCategory")
@ApplicationScoped
public class ModelCategory implements Serializable {

	private static final long serialVersionUID = 7813421328620243358L;
	private CategoryDao categoryDao = DBUtil.getInstance().getBean("categoryDao", CategoryDao.class);
	
	public void addCategory(Category2Tbl category) {
		categoryDao.insertCategory2(category);
		Misc.redirect("/pages/admin/directoryes.jsf");
	}
	
	public void addSubCategory(CategoryTbl category) {
		categoryDao.insertCategory(category);
		Misc.redirect("/pages/admin/directoryes.jsf");
	}
	
	public List<Category2Tbl> getAllCategory() {
		return categoryDao.getAllCategory2();
	}
	
	public List<CategoryTbl> getSubCategory(Integer id_parent) {
		return categoryDao.selectCategoryByIdParent(id_parent);
	}
	
}
