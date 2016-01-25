package hobotun.db.file;

import hobotun.db.file.Action.InsertFileReturnId;
import hobotun.db.file.Action.SelectFileById;
import hobotun.db.file.tbl.FileTbl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class FileDao implements IFileDao {

	private DataSource dataSource;
	private SelectFileById selectFileById;
	private InsertFileReturnId insertFile;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;

		selectFileById = new SelectFileById(dataSource);
		insertFile = new InsertFileReturnId(dataSource);
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	@Override
	public List<FileTbl> getImageById(Integer id) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id_file", id);

		return selectFileById.executeByNamedParam(paramMap);
	}

	@Override
	public void Insert(FileTbl file) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("file", file.getFile());

		KeyHolder keyHolder = new GeneratedKeyHolder();

		insertFile.updateByNamedParam(paramMap, keyHolder);

		file.setIdFile(keyHolder.getKey().longValue());
	}
	
	public List<FileTbl> SelectFileById(Long id) {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id_file", id);
		
		return selectFileById.executeByNamedParam(paramMap);
	}

}
