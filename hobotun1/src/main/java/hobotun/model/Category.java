package hobotun.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;

import hobotun.db.category.table.Category2Tbl;
import hobotun.db.category.table.CategoryTbl;

@ManagedBean(name = "category")
@ViewScoped
public class Category implements Serializable {

	private static final long serialVersionUID = 2009978316840459139L;
	private Integer category = 0;
	private List<SelectItem> categoryes;

	@ManagedProperty("#{modelCategory}")
	private ModelCategory modelCategory;

	public void setModelCategory(ModelCategory modelCategory) {
		this.modelCategory = modelCategory;
	}

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

		for (Category2Tbl category : modelCategory.getAllCategory()) {

			SelectItemGroup group = new SelectItemGroup(category.getNm_category_p());

			int count = 0;

			List<CategoryTbl> subCategoryes = modelCategory.getSubCategory(category.getId_category_p());
			SelectItem[] selectItems = new SelectItem[subCategoryes.size()];

			for (CategoryTbl subCategory : subCategoryes) {
				selectItems[count] = new SelectItem(subCategory.getIdCategory(), subCategory.getNmCategory());
				count += 1;
			}

			if (count > 0) {
				group.setSelectItems(selectItems);
				categoryes.add(group);
			}

		}
	}

}
