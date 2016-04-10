package hobotun.admin;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import hobotun.db.DBUtil;
import hobotun.db.model.ModelDao;
import hobotun.db.model.tbl.ModelTbl;

@ManagedBean(name="modeleNotModer")
@ViewScoped
public class ModeleNotModer {

	private List<ModelTbl> modeles;
	private ModelDao modelDao;
	
	public ModeleNotModer() {
		modelDao = DBUtil.getInstance().getBean("modelDao", ModelDao.class);
		
		modeles = modelDao.selectModeleNotModer();
	}

	public List<ModelTbl> getModeles() {
		return modeles;
	}

	public void setModeles(List<ModelTbl> modeles) {
		this.modeles = modeles;
	}
	
}
