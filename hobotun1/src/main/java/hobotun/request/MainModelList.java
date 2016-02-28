package hobotun.request;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import hobotun.db.DBUtil;
import hobotun.db.model.ModelDao;
import hobotun.db.model.tbl.CountModelTbl;
import hobotun.db.model.tbl.ModelTbl;

@ManagedBean(name = "mainModelModer")
@ViewScoped
public class MainModelList implements Serializable {

	private static final long serialVersionUID = 7919462944747329695L;
	private List<ModelTbl> models;
	private ModelDao modelDao;
	private CountModelTbl count;

	@PostConstruct
	public void init() {
		modelDao = DBUtil.getInstance().getBean("modelDao", ModelDao.class);
		setModels(modelDao.selectModelOrderByDate(1, 0, "%%"));
		setCount(modelDao.getCountModel().get(0));
	}

	public List<ModelTbl> getModels() {
		return models;
	}

	public void setModels(List<ModelTbl> models) {
		this.models = models;
	}

	public CountModelTbl getCount() {
		return count;
	}

	public void setCount(CountModelTbl count) {
		this.count = count;
	}


}
