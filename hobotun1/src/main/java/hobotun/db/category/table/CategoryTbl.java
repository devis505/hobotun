package hobotun.db.category.table;

import java.sql.ResultSet;
import java.sql.SQLException;

import hobotun.core.Misc;
import hobotun.core.ParamsForQuery;
import hobotun.db.DBUtil;
import hobotun.db.category.CategoryDao;

public class CategoryTbl {

	private Integer idCategory;
	private String nmCategory;

	public CategoryTbl(ResultSet rs) throws SQLException {
		this.idCategory = rs.getInt("idCategory");
		this.nmCategory = rs.getString("nmCategory");
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
}
