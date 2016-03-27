package hobotun.db.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import hobotun.db.model.action.GetCountModel;
import hobotun.db.model.action.InsertModelReturnId;
import hobotun.db.model.action.InsertModeleMsg;
import hobotun.db.model.action.SelectAllModelOrderByCost;
import hobotun.db.model.action.SelectAllModelOrderByDate;
import hobotun.db.model.action.SelectAllModelOrderByFree;
import hobotun.db.model.action.SelectAllModelOrderByPop;
import hobotun.db.model.action.SelectAllModelOrderByPopular;
import hobotun.db.model.action.SelectModelById;
import hobotun.db.model.action.SelectModelByIdUser;
import hobotun.db.model.action.SelectModelByIdUserBay;
import hobotun.db.model.action.SelectModeleMsgByIdModele;
import hobotun.db.model.tbl.CountModelTbl;
import hobotun.db.model.tbl.ModelTbl;
import hobotun.db.model.tbl.ModeleMsgTbl;

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
	private SelectAllModelOrderByPop selectAllModelOrderByPop;
	
	private InsertModeleMsg insertModeleMsg;
	private SelectModeleMsgByIdModele selectModeleMsgByIdModele;

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
		selectAllModelOrderByPop = new SelectAllModelOrderByPop(dataSource);
		
		insertModeleMsg = new InsertModeleMsg(dataSource);
		selectModeleMsgByIdModele = new SelectModeleMsgByIdModele(dataSource);
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
	
	public List<ModelTbl> selectModelOrderByPop(Integer isModeration, Integer idCategory, String keyWord) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("is_moderation", isModeration);
		paramMap.put("idCategory", idCategory);
		paramMap.put("keyWord", keyWord);

		return selectAllModelOrderByPop.executeByNamedParam(paramMap);
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
	
	public void insertModeleMsg(ModeleMsgTbl modeleMsg) {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("id_user", modeleMsg.getId_user());
		paramMap.put("id_modele", modeleMsg.getId_modele());
		paramMap.put("vl_msg", modeleMsg.getVl_msg());
		paramMap.put("dt_msg", modeleMsg.getDt_msg());
		
		KeyHolder keyHolder = new GeneratedKeyHolder();

		insertModeleMsg.updateByNamedParam(paramMap, keyHolder);

		modeleMsg.setId_modele_msg(keyHolder.getKey().longValue());
	}
	
	public List<ModeleMsgTbl> selectModeleMsgByIdModele (Long id_modele) {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id_modele", id_modele);
		
		return selectModeleMsgByIdModele.executeByNamedParam(paramMap);
	}

}
