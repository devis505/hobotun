package hobotun.db.format.table;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FormatTabl {
    
    private Integer idFormat;
    private String nmFormat;
    
    public FormatTabl(ResultSet rs) throws SQLException {
	idFormat = rs.getInt("idFormat");
	nmFormat = rs.getString("nmFormat");
    }
    
    public Integer getIdFormat() {
        return idFormat;
    }
    public void setIdFormat(Integer idFormat) {
        this.idFormat = idFormat;
    }
    public String getNmFormat() {
        return nmFormat;
    }
    public void setNmFormat(String nmFormat) {
        this.nmFormat = nmFormat;
    }
    
}
