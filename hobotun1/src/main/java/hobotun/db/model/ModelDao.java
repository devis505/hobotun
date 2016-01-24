package hobotun.db.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import hobotun.db.model.action.GetCountModel;
import hobotun.db.model.action.InsertModelReturnId;
import hobotun.db.model.action.SelectAllModelOrderByCost;
import hobotun.db.model.action.SelectAllModelOrderByDate;
import hobotun.db.model.action.SelectAllModelOrderByFree;
import hobotun.db.model.action.SelectAllModelOrderByPopular;
import hobotun.db.model.action.SelectModelById;
import hobotun.db.model.action.SelectModelByIdUser;
import hobotun.db.model.action.SelectModelByIdUserBay;
import hobotun.db.model.tbl.CountModelTbl;
import hobotun.db.model.tbl.ModelTbl;

public class ModelDao implements IModelDao {

	private DataSource dataSource;
	private SelectModelById selectModelById;
	private InsertModelReturnId insertModel;
	private SelectModelByIdUser selectModelByIdUser;
	private SelectModelByIdUserBay selectModelByIdUserBay;
	private GetCountModel getCountModel;

	private SelectAllModelOrderByDate selectAllModelOrderByDate;
	private SelectAllModelOrderByPopular selectAllModelOrderByPopular;
	private SelectAllModelOrderByCost selectAllModelOrderByCost;
	private SelectAllModelOrderByFree selectAllModelOrderByFree;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		selectModelById = new SelectModelById(dataSource);
		insertModel = new InsertModelReturnId(dataSource);
		selectModelByIdUser = new SelectModelByIdUser(dataSource);
		getCountModel = new GetCountModel(dataSource);

		selectAllModelOrderByDate = new SelectAllModelOrderByDate(dataSource);
		selectAllModelOrderByPopular = new SelectAllModelOrderByPopular(dataSource);
		selectAllModelOrderByCost = new SelectAllModelOrderByCost(dataSource);
		selectAllModelOrderByFree = new SelectAllModelOrderByFree(dataSource);
		selectModelByIdUserBay = new SelectModelByIdUserBay(dataSource);
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	@Override
	public List<ModelTbl> selectModelById(Long id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("idModel", id);

		return selectModelById.executeByNamedParam(paramMap);
	}

	@Override
	public void insertModelReturnId(ModelTbl modele) {

		KeyHolder keyHolder = new GeneratedKeyHolder();

		insertModel.updateByNamedParam(modele.getAllParam(), keyHolder);

		modele.setIdModel(keyHolder.getKey().longValue());
	}

	@Override
	public List<ModelTbl> selectModelByIdUser(Long id, Integer isModeration) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("idUser", id);
		paramMap.put("is_moderation", isModeration);

		return selectModelByIdUser.executeByNamedParam(paramMap);
	}
	
	@Override
	public List<ModelTbl> selectModelByIdUserBay(Long id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("idUser", id);
		
		return selectModelByIdUserBay.executeByNamedParam(paramMap);
	}

	@Override
	public List<CountModelTbl> getCountModel() {
		return getCountModel.execute();
	}

	@Override
	public List<ModelTbl> selectModelOrderByDate(Integer isModeration, Integer idCategory, String keyWord) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("is_moderation", isModeration);
		paramMap.put("idCategory", idCategory);
		paramMap.put("keyWord", keyWord);

		return selectAllModelOrderByDate.executeByNamedParam(paramMap);
	}

	@Override
	public List<ModelTbl> selectModelOrderByPopular(Integer isModeration, Integer idCategory, String keyWord) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("is_moderation", isModeration);
		paramMap.put("idCategory", idCategory);
		paramMap.put("keyWord", keyWord);

		return selectAllModelOrderByPopular.executeByNamedParam(paramMap);
	}

	@Override
	public List<ModelTbl> selectModelOrderByCost(Integer isModeration, Integer idCategory, String keyWord) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("is_moderation", isModeration);
		paramMap.put("idCategory", idCategory);
		paramMap.put("keyWord", keyWord);

		return selectAllModelOrderByCost.executeByNamedParam(paramMap);
	}

	@Override
	public List<ModelTbl> selectModelOrderByFree(Integer isModeration, Integer idCategory, String keyWord) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("is_moderation", isModeration);
		paramMap.put("idCategory", idCategory);
		paramMap.put("keyWord", keyWord);

		return selectAllModelOrderByFree.executeByNamedParam(paramMap);
	}

}
