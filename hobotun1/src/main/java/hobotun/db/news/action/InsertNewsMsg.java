package hobotun.db.news.action;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class InsertNewsMsg extends SqlUpdate  {

	private static final String SQL_INSERT_FORUM = "INSERT INTO hb_news_msg(vl_msg, id_user, dt_news_msg, id_news) VALUES (:vl_msg, :id_user, :dt_news_msg, :id_news)";
	
	public InsertNewsMsg(DataSource dataSource) {
		super(dataSource, SQL_INSERT_FORUM);

		super.declareParameter(new SqlParameter("vl_msg", Types.VARCHAR));
		super.declareParameter(new SqlParameter("id_user", Types.INTEGER));
		super.declareParameter(new SqlParameter("dt_news_msg", Types.DATE));
		super.declareParameter(new SqlParameter("id_news", Types.INTEGER));
		
		super.setGeneratedKeysColumnNames(new String[] { "id_news_msg" });
		super.setReturnGeneratedKeys(true);
	}
}
