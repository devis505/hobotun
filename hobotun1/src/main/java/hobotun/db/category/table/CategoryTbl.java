package hobotun.db.category.table;

import java.sql.ResultSet;
import java.sql.SQLException;

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
    
    
    
}
