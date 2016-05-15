package hobotun.db.category.table;

import java.sql.ResultSet;
import java.sql.SQLException;

import hobotun.core.Misc;
import hobotun.core.ParamsForQuery;
import hobotun.db.DBUtil;
import hobotun.db.category.CategoryDao;

public class Category2Tbl implements CategoryAll{

	private String nm_category_p;
	private Integer id_category_p;

	public Category2Tbl() {
		nm_category_p = "";
	}
	
	public Category2Tbl(ResultSet rs) throws SQLException {
		this.id_category_p = rs.getInt("id_category_p");
		this.nm_category_p = rs.getString("nm_category_p");
	}
	
	public void onDelete() {
		CategoryDao categoryDao = DBUtil.getInstance().getBean("categoryDao", CategoryDao.class);
		
		ParamsForQuery inParams = new ParamsForQuery();
		inParams.setParam("id_category_p", id_category_p);
		
		categoryDao.deleteCategory2(inParams.getAllParam());
		Misc.redirect("/pages/admin/directoryes.jsf");
	}

	public Integer getId_category_p() {
		return id_category_p;
	}

	public void setId_category_p(Integer id_category_p) {
		this.id_category_p = id_category_p;
	}

	public String getNm_category_p() {
		return nm_category_p;
	}

	public void setNm_category_p(String nm_category_p) {
		this.nm_category_p = nm_category_p;
	}
}
