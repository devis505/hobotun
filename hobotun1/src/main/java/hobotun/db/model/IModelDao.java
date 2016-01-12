package hobotun.db.model;

import java.util.List;

import hobotun.db.model.tbl.CountModelTbl;
import hobotun.db.model.tbl.ModelTbl;

public interface IModelDao {

	public List<ModelTbl> selectModelById(Long id);

	public void insertModelReturnId(ModelTbl modele);

	public List<ModelTbl> selectModelByIdUser(Long id, Integer isModeration);
	
	public List<ModelTbl> selectModelByIdUserBay(Long id);

	public List<CountModelTbl> getCountModel();
	
	public List<ModelTbl> selectModelOrderByDate(Integer isModeration, Integer idCategory, String keyWord);

	public List<ModelTbl> selectModelOrderByPopular(Integer isModeration, Integer idCategory, String keyWord);

	public List<ModelTbl> selectModelOrderByCost(Integer isModeration, Integer idCategory, String keyWord);

	public List<ModelTbl> selectModelOrderByFree(Integer isModeration, Integer idCategory, String keyWord);

}
