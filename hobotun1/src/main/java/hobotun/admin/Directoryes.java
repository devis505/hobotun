package hobotun.admin;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import hobotun.core.ParamsForQuery;
import hobotun.db.DBUtil;
import hobotun.db.category.CategoryDao;
import hobotun.db.category.table.CategoryTbl;
import hobotun.db.format.FormatDao;
import hobotun.db.format.table.FormatTabl;

@ManagedBean(name = "directoryes")
@ViewScoped
public class Directoryes {

	private List<CategoryTbl> categoryes;
	private List<FormatTabl> formats;
	
	private CategoryDao categoryDao;
	private FormatDao formatDao; 
	 
	private String nmCategory;
	private String nmFormat;
	
	public String getNmCategory() {
		return nmCategory;
	}

	public void setNmCategory(String nmCategory) {
		this.nmCategory = nmCategory;
	}

	public Directoryes() {
		categoryDao = DBUtil.getInstance().getBean("categoryDao", CategoryDao.class);
		formatDao = DBUtil.getInstance().getBean("formatDao", FormatDao.class);
		
		fill();
	}
	
	private void fill() {
		categoryes = categoryDao.getAllCategory();
		setFormats(formatDao.getAllFormat());
	}

	public List<CategoryTbl> getCategoryes() {
		return categoryes;
	}

	public void setCategoryes(List<CategoryTbl> categoryes) {
		this.categoryes = categoryes;
	}
	
	public void onSave() {
		ParamsForQuery inParam = new ParamsForQuery();
		inParam.setParam("nmCategory", nmCategory);
		
		categoryDao.insertCategory(inParam.getAllParam());
		
		fill();
	}
	
	public void onSaveFormat() {
		ParamsForQuery inParam = new ParamsForQuery();
		inParam.setParam("nmFormat", nmFormat);
		
		formatDao.insertFormat(inParam.getAllParam());
		
		fill();
	}

	public List<FormatTabl> getFormats() {
		return formats;
	}

	public void setFormats(List<FormatTabl> formats) {
		this.formats = formats;
	}

	public String getNmFormat() {
		return nmFormat;
	}

	public void setNmFormat(String nmFormat) {
		this.nmFormat = nmFormat;
	}
			
}
