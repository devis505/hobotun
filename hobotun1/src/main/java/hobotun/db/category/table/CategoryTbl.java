package hobotun.db.category.table;

import java.sql.ResultSet;
import java.sql.SQLException;

import hobotun.core.Misc;
import hobotun.core.ParamsForQuery;
import hobotun.db.DBUtil;
import hobotun.db.category.CategoryDao;

public class CategoryTbl implements CategoryAll {

	private Integer idCategory;
	private String nmCategory;
	private Integer id_category_p;

	public CategoryTbl() {
		nmCategory = "";
	}
	
	public CategoryTbl(ResultSet rs) throws SQLException {
		this.idCategory = rs.getInt("idCategory");
		this.nmCategory = rs.getString("nmCategory");
		this.id_category_p = rs.getInt("id_category_p");
	}
	
	public Integer getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(Integer idCategory) {
		this.idCategory = idCategory;
	}

	public String getNmCategory() {
		return nmCategory;
	}

	public void setNmCategory(String nmCategory) {
		this.nmCategory = nmCategory;
	}

	public void onDelete() {
		CategoryDao categoryDao = DBUtil.getInstance().getBean("categoryDao", CategoryDao.class);
		
		ParamsForQuery inParams = new ParamsForQuery();
		inParams.setParam("idCategory", idCategory);
		
		categoryDao.deleteCategory(inParams.getAllParam());
		Misc.redirect("/pages/admin/directoryes.jsf");
	}

	public Integer getId_category_p() {
		return id_category_p;
	}

	public void setId_category_p(Integer id_category_p) {
		this.id_category_p = id_category_p;
	}
}
