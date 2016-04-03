package hobotun.db.format;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import hobotun.db.format.action.DeleteFormat;
import hobotun.db.format.action.InsertFormat;
import hobotun.db.format.action.SelectAllFormat;
import hobotun.db.format.action.SelectFormatById;
import hobotun.db.format.table.FormatTabl;

public class FormatDao implements IFormat, Serializable {

	private static final long serialVersionUID = 1306113412384610944L;
	private DataSource dataSource;
	private SelectAllFormat selectAllFormat;
	private SelectFormatById selectFormatById;
	
	private DeleteFormat deleteFormat;
	private InsertFormat insertFormat;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		selectAllFormat = new SelectAllFormat(dataSource);
		selectFormatById = new SelectFormatById(dataSource);
		
		deleteFormat = new DeleteFormat(dataSource);
		insertFormat = new InsertFormat(dataSource);
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
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id_format", id);

		return selectFormatById.executeByNamedParam(paramMap).get(0);
	}

	@Override
	public void updateFormat(FormatTabl format) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insertFormat(FormatTabl format) {
		
	}
	
	public void deleteFormat(Map<String, Object> paramMap) {
		deleteFormat.updateByNamedParam(paramMap);
	}
	
	public void insertFormat(Map<String, Object> paramMap) {
		insertFormat.updateByNamedParam(paramMap);
	}

}
