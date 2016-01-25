package hobotun.db.file.Action;

import hobotun.db.file.tbl.FileTbl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

public class SelectFileById extends MappingSqlQuery<FileTbl> {

	private static final String SQL_SELECT_FILE_BY_ID = "select f.*, round(length(f.file) / 1024 / 1024, 2) mb from hb_file f where f.idFile = :id_file";

	public SelectFileById(DataSource dataSource) {
		super(dataSource, SQL_SELECT_FILE_BY_ID);
		super.declareParameter(new SqlParameter("id_file", Types.INTEGER));
	}

	@Override
	protected FileTbl mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new FileTbl(rs);
	}

}
