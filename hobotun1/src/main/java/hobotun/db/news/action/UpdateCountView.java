package hobotun.db.news.action;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class UpdateCountView extends SqlUpdate {

	private static final String SQL_UPDATE_COUNT_VIEW = 
			"UPDATE hb_news " + 
			"	SET nn_count_view = nn_count_view + 1 " +
			" WHERE id_news = :id_news";

	public UpdateCountView(DataSource dataSource) {
		super(dataSource, SQL_UPDATE_COUNT_VIEW);

		super.declareParameter(new SqlParameter("id_news", Types.INTEGER));
	}
}