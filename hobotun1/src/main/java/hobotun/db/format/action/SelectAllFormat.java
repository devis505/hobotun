package hobotun.db.format.action;

import hobotun.db.format.table.FormatTabl;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.object.MappingSqlQuery;

public class SelectAllFormat extends MappingSqlQuery<FormatTabl> {

    private static final String SQL_SELECT_ALL_FORMAT = "SELECT * FROM hb_format f where f.idFormat <> 0";

    public SelectAllFormat(DataSource dataSource) {
	super(dataSource, SQL_SELECT_ALL_FORMAT);
    }

    @Override
    protected FormatTabl mapRow(ResultSet rs, int rowNum) throws SQLException {
	return new FormatTabl(rs);
    }

}
