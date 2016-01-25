package hobotun.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import hobotun.db.DBUtil;
import hobotun.db.category.CategoryDao;
import hobotun.db.category.table.CategoryTbl;

@ManagedBean(name = "category")
@ViewScoped
public class Category implements Serializable{

	private static final long serialVersionUID = 2009978316840459139L;
	private Integer category = 0;
	private List<SelectItem> categoryes;

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public List<SelectItem> getCategoryes() {
		return categoryes;
	}

	public void init() {

		categoryes = new ArrayList<SelectItem>();

		CategoryDao categoryDao = DBUtil.getInstance().getBean("categoryDao", CategoryDao.class);
		List<CategoryTbl> categoryTbl = categoryDao.getAllCategory();

		for (CategoryTbl categoryItem : categoryTbl) {
			categoryes.add(new SelectItem(categoryItem.getIdCategory(), categoryItem.getNmCategory()));
		}
	}

}
