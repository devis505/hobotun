package hobotun.db.forum.action;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class UpdateCountView extends SqlUpdate {

	private static final String SQL_UPDATE_COUNT_VIEW = 
			"UPDATE hb_thema " + 
			"	SET nn_count_view = nn_count_view + 1 " +
			" WHERE id_thema = :id_thema";

	public UpdateCountView(DataSource dataSource) {
		super(dataSource, SQL_UPDATE_COUNT_VIEW);

		super.declareParameter(new SqlParameter("id_thema", Types.INTEGER));
	}
}
