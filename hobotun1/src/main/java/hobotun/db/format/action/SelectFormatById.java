package hobotun.db.format.action;

import hobotun.db.format.table.FormatTabl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

public class SelectFormatById extends MappingSqlQuery<FormatTabl> {

	private static final String SQL_SELECT_ALL_FORMAT = "SELECT * FROM hb_format f where f.idFormat = :id_format";

	public SelectFormatById(DataSource dataSource) {
		super(dataSource, SQL_SELECT_ALL_FORMAT);
		super.declareParameter(new SqlParameter("id_format", Types.INTEGER));
	}

	@Override
	protected FormatTabl mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new FormatTabl(rs);
	}

}
