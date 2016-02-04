package hobotun.user;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import hobotun.core.UserSession;
import hobotun.db.DBUtil;
import hobotun.db.model.ModelDao;
import hobotun.db.model.tbl.ModelTbl;

@ManagedBean(name = "modelNotModer")
@ViewScoped
public class ModelNotModer {

	private List<ModelTbl> models;
	private ModelDao modelDao;

	@PostConstruct
	public void init() {
		modelDao = DBUtil.getInstance().getBean("modelDao", ModelDao.class);
		setModels(modelDao.selectModelByIdUser(UserSession.getInstance().getUser().getUserTbl().getId_user(), 0));
	}

	public List<ModelTbl> getModels() {
		return models;
	}

	public void setModels(List<ModelTbl> models) {
		this.models = models;
	}

}
