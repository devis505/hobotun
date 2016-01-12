package hobotun.db.Image.Action;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class InsertImageReturnId extends SqlUpdate {

    private static final String SQL_INSERT_IMAGE = "insert into hb_image (image) value (:image)";

    public InsertImageReturnId(DataSource dataSource) {
	super(dataSource, SQL_INSERT_IMAGE);

	super.declareParameter(new SqlParameter("image", Types.BLOB));
	super.setGeneratedKeysColumnNames(new String[] {"idImage"});
	super.setReturnGeneratedKeys(true);
    }

}
