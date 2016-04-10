package hobotun.db.model.tbl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import hobotun.core.Misc;
import hobotun.core.ParamsForQuery;
import hobotun.db.DBUtil;
import hobotun.db.model.ModelDao;

public class ModelTbl {
	
	private static String OPACITY_50 = "opacity50";
	private static String OPACITY_100 = "opacity100";
	private String opacality = OPACITY_100;

	private Long idModel;
	private String nmModel;
	private String description;
	private Long idImg1;
	private Long idImg2;
	private Long idImg3;
	private Long idImg4;
	private Long idImg5;
	private Long idFile;
	private Integer idCategory;
	private Integer idFormat;
	private Integer obj;
	private Integer fbx;
	private Long idImg1min;
	private Long idImg2min;
	private Long idImg3min;
	private Long idImg4min;
	private Long idImg5min;
	private String tegs;
	private BigDecimal price;
	private Integer count_poligon;
	private Integer texture;
	private Integer is_moderation = 0;
	private Integer rating = 0;
	private Integer download = 0;
	private Integer msg = 0;
	
	private BigDecimal price_calc = BigDecimal.ZERO;

	public Map<String, Object> getAllParam() {

		Map<String, Object> res = new HashMap<String, Object>();

		res.put("nmModel", nmModel);
		res.put("description", description);
		res.put("idImg1", idImg1);
		res.put("idImg2", idImg2);
		res.put("idImg3", idImg3);
		res.put("idImg4", idImg4);
		res.put("idImg5", idImg5);
		res.put("idFile", idFile);
		res.put("idCategory", idCategory);
		res.put("idFormat", idFormat);
		res.put("obj", obj);
		res.put("fbx", fbx);
		res.put("idImg1min", idImg1min);
		res.put("idImg2min", idImg2min);
		res.put("idImg3min", idImg3min);
		res.put("idImg4min", idImg4min);
		res.put("idImg5min", idImg5min);
		res.put("tegs", tegs);
		res.put("price", price);
		res.put("count_poligon", count_poligon);
		res.put("texture", texture);
		res.put("is_moderation", is_moderation);

		return res;
	}

	public ModelTbl() {

	}
	
	public void onModerator() {
		ParamsForQuery inParam = new ParamsForQuery();
		inParam.setParam("idModel", idModel);
		
		ModelDao modelDao = DBUtil.getInstance().getBean("modelDao", ModelDao.class);
		modelDao.updateModeration(inParam.getAllParam());	
		
		Misc.redirect("/pages/admin/modeleNotModer.jsf");
	}
	
	public void onDelete() {
		ParamsForQuery inParam = new ParamsForQuery();
		inParam.setParam("idModel", idModel);
		
		ModelDao modelDao = DBUtil.getInstance().getBean("modelDao", ModelDao.class);
		modelDao.deleteModeleLink(inParam.getAllParam());	
		modelDao.deleteModele(inParam.getAllParam());		
		
		Misc.redirect("/pages/admin/modeleNotModer.jsf");
	}

	public ModelTbl(ResultSet rs) throws SQLException {

		nmModel = rs.getString("nmModel");
		description = rs.getString("description");
		idImg1 = rs.getLong("idImg1");
		idImg2 = rs.getLong("idImg2");
		idImg3 = rs.getLong("idImg3");
		idImg4 = rs.getLong("idImg4");
		idImg5 = rs.getLong("idImg5");
		idFile = rs.getLong("idFile");
		idCategory = rs.getInt("idCategory");
		idFormat = rs.getInt("idFormat");
		obj = rs.getInt("obj");
		fbx = rs.getInt("fbx");
		idImg1min = rs.getLong("idImg1min");
		idImg2min = rs.getLong("idImg2min");
		idImg3min = rs.getLong("idImg3min");
		idImg4min = rs.getLong("idImg4min");
		idImg5min = rs.getLong("idImg5min");
		tegs = rs.getString("tegs");
		price = rs.getBigDecimal("price");
		price_calc = rs.getBigDecimal("price_calc");
		count_poligon = rs.getInt("count_poligon");
		texture = rs.getInt("texture");
		is_moderation = rs.getInt("is_moderation");
		rating = rs.getInt("rating");
		download = rs.getInt("download");
		msg = rs.getInt("msg");
		
		if(is_moderation.equals(1))
			opacality = OPACITY_100;
		else 
			opacality = OPACITY_50;
	}

	public String getNmModel() {
		return nmModel;
	}

	public void setNmModel(String nmModel) {
		this.nmModel = nmModel;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getIdImg1() {
		return idImg1;
	}

	public void setIdImg1(Long idImg1) {
		this.idImg1 = idImg1;
	}

	public Long getIdImg2() {
		return idImg2;
	}

	public void setIdImg2(Long idImg2) {
		this.idImg2 = idImg2;
	}

	public Long getIdImg3() {
		return idImg3;
	}

	public void setIdImg3(Long idImg3) {
		this.idImg3 = idImg3;
	}

	public Long getIdImg4() {
		return idImg4;
	}

	public void setIdImg4(Long idImg4) {
		this.idImg4 = idImg4;
	}

	public Long getIdImg5() {
		return idImg5;
	}

	public void setIdImg5(Long idImg5) {
		this.idImg5 = idImg5;
	}

	public Long getIdFile() {
		return idFile;
	}

	public void setIdFile(Long idFile) {
		this.idFile = idFile;
	}

	public Integer getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(Integer idCategory) {
		this.idCategory = idCategory;
	}

	public Integer getIdFormat() {
		return idFormat;
	}

	public void setIdFormat(Integer idFormat) {
		this.idFormat = idFormat;
	}

	public Integer getObj() {
		return obj;
	}

	public void setObj(Integer obj) {
		this.obj = obj;
	}

	public Integer getFbx() {
		return fbx;
	}

	public void setFbx(Integer fbx) {
		this.fbx = fbx;
	}

	public Long getIdImg1min() {
		return idImg1min;
	}

	public void setIdImg1min(Long idImg1min) {
		this.idImg1min = idImg1min;
	}

	public Long getIdImg2min() {
		return idImg2min;
	}

	public void setIdImg2min(Long idImg2min) {
		this.idImg2min = idImg2min;
	}

	public Long getIdImg3min() {
		return idImg3min;
	}

	public void setIdImg3min(Long idImg3min) {
		this.idImg3min = idImg3min;
	}

	public Long getIdImg4min() {
		return idImg4min;
	}

	public void setIdImg4min(Long idImg4min) {
		this.idImg4min = idImg4min;
	}

	public Long getIdImg5min() {
		return idImg5min;
	}

	public void setIdImg5min(Long idImg5min) {
		this.idImg5min = idImg5min;
	}

	public String getTegs() {
		return tegs;
	}

	public void setTegs(String tegs) {
		this.tegs = tegs;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Long getIdModel() {
		return idModel;
	}

	public void setIdModel(Long idModel) {
		this.idModel = idModel;
	}

	public Integer getCount_poligon() {
		return count_poligon;
	}

	public void setCount_poligon(Integer count_poligon) {
		this.count_poligon = count_poligon;
	}

	public Integer getTexture() {
		return texture;
	}

	public void setTexture(Integer texture) {
		this.texture = texture;
	}

	public Integer getIs_moderation() {
		return is_moderation;
	}

	public void setIs_moderation(Integer is_moderation) {
		this.is_moderation = is_moderation;
	}

	public String getOpacality() {
		return opacality;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public Integer getDownload() {
		return download;
	}

	public void setDownload(Integer download) {
		this.download = download;
	}

	public Integer getMsg() {
		return msg;
	}

	public void setMsg(Integer msg) {
		this.msg = msg;
	}

	public BigDecimal getPrice_calc() {
		return price_calc;
	}

	public void setPrice_calc(BigDecimal price_calc) {
		this.price_calc = price_calc;
	}

}
