package hobotun.user;

import hobotun.core.UserSession;
import hobotun.db.DBUtil;
import hobotun.db.model.ModelDao;
import hobotun.db.model.tbl.ModelTbl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

@ManagedBean(name = "modelModer")
@ViewScoped
public class ModelModer {

	private List<ModelTbl> models;
	private ModelDao modelDao;

	@PostConstruct
	public void init() {
		modelDao = DBUtil.getInstance().getBean("modelDao", ModelDao.class);
		setModels(modelDao.selectModelMyIdUser(UserSession.getInstance().getUser().getUserTbl().getId_user(), 1));
	}

	public List<ModelTbl> getModels() {
		return models;
	}

	public void setModels(List<ModelTbl> models) {
		this.models = models;
	}

}
