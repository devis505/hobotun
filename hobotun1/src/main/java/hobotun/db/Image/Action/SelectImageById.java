package hobotun.db.Image.Action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import hobotun.db.Image.tbl.ImageTbl;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

public class SelectImageById extends MappingSqlQuery<ImageTbl> {

    private static final String SQL_SELECT_IMAGE_BY_ID = "select * from hb_image where idImage = :id_image";

    public SelectImageById(DataSource dataSource) {
	super(dataSource, SQL_SELECT_IMAGE_BY_ID);
	super.declareParameter(new SqlParameter("id_image", Types.INTEGER));
    }

    @Override
    protected ImageTbl mapRow(ResultSet rs, int rowNum) throws SQLException {
	return new ImageTbl(rs);
    }

}
