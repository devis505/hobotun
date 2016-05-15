package hobotun.request;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import hobotun.db.DBUtil;
import hobotun.db.model.ModelDao;
import hobotun.db.model.tbl.ModelTbl;
import hobotun.model.Category;

@ManagedBean(name = "findModele")
@ViewScoped
public class FindModele implements Serializable{
	
	private static final long serialVersionUID = 4759632650445905080L;
	private List<ModelTbl> models;
	private ModelDao modelDao;
	
	@ManagedProperty("#{category}")
	private Category categoryes;
	
	private String keyWord = ""; 
	private String option = "1";
	
	private Integer countModel;
	
	@PostConstruct
	public void init() {
		modelDao = DBUtil.getInstance().getBean("modelDao", ModelDao.class);
		changeSort();
	}
	
	private void changeSort() {
		if ("1".equals(option)) {
			setModels(modelDao.selectModelOrderByDate(1, Integer.valueOf(categoryes.getCategory()), "%" + keyWord + "%"));
		}
		
		if ("2".equals(option)) {
			setModels(modelDao.selectModelOrderByPopular(1, Integer.valueOf(categoryes.getCategory()), "%" + keyWord + "%"));
		}
		
		if ("3".equals(option)) {
			setModels(modelDao.selectModelOrderByCost(1, Integer.valueOf(categoryes.getCategory()), "%" + keyWord + "%"));
		}
		
		if ("4".equals(option)) {
			setModels(modelDao.selectModelOrderByFree(1, Integer.valueOf(categoryes.getCategory()), "%" + keyWord + "%"));
		}
		
		if ("5".equals(option)) {
			setModels(modelDao.selectModelOrderByPop(1, Integer.valueOf(categoryes.getCategory()), "%" + keyWord + "%"));
		}
		
		countModel = models.size();
	}
	
	public void onClickFindButton() {
		changeSort();
	}

	public void onChangeOption() {
		changeSort();
	}
	
	public Category getCategoryes() {
		return categoryes;
	}

	public void setCategoryes(Category categoryes) {
		this.categoryes = categoryes;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public List<ModelTbl> getModels() {
		return models;
	}

	public void setModels(List<ModelTbl> models) {
		this.models = models;
	}

	public Integer getCountModel() {
		return countModel;
	}

	public void setCountModel(Integer countModel) {
		this.countModel = countModel;
	}
}
