package hobotun.db.format;

import hobotun.db.format.action.SelectAllFormat;
import hobotun.db.format.table.FormatTabl;

import java.io.Serializable;
import java.util.List;

import javax.sql.DataSource;

public class FormatDao implements IFormat, Serializable {

    private static final long serialVersionUID = 1306113412384610944L;
    private DataSource dataSource;
    private SelectAllFormat selectAllFormat;

    public void setDataSource(DataSource dataSource) {
	this.dataSource = dataSource;
	selectAllFormat = new SelectAllFormat(dataSource);
    }

    public DataSource getDataSource() {
	return dataSource;
    }

    @Override
    public List<FormatTabl> getAllFormat() {
	return selectAllFormat.execute();
    }

    @Override
    public FormatTabl getFormatById(Integer id) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void updateFormat(FormatTabl format) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void insertFormat(FormatTabl format) {
	// TODO Auto-generated method stub
	
    }

    
}
