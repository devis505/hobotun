package hobotun.user;

import hobotun.db.DBUtil;
import hobotun.db.category.CategoryDao;
import hobotun.db.category.table.CategoryTbl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;

@ManagedBean(name = "category")
@ViewScoped
public class Category {

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

	@PostConstruct
	public void init() {

		categoryes = new ArrayList<SelectItem>();

		CategoryDao categoryDao = DBUtil.getInstance().getBean("categoryDao", CategoryDao.class);
		List<CategoryTbl> categoryTbl = categoryDao.getAllCategory();

		for (CategoryTbl categoryItem : categoryTbl) {
			categoryes.add(new SelectItem(categoryItem.getIdCategory(), categoryItem.getNmCategory()));
		}
	}

}
