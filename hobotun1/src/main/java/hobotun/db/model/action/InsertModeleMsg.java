package hobotun.db.model.action;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class InsertModeleMsg extends SqlUpdate  {

	private static final String SQL_INSERT_FORUM = "INSERT INTO hb_modele_msg(vl_msg, id_user, dt_msg, id_modele) VALUES (:vl_msg, :id_user, :dt_msg, :id_modele)";
	
	public InsertModeleMsg(DataSource dataSource) {
		super(dataSource, SQL_INSERT_FORUM);

		super.declareParameter(new SqlParameter("vl_msg", Types.VARCHAR));
		super.declareParameter(new SqlParameter("id_user", Types.INTEGER));
		super.declareParameter(new SqlParameter("dt_msg", Types.DATE));
		super.declareParameter(new SqlParameter("id_modele", Types.INTEGER));
		
		super.setGeneratedKeysColumnNames(new String[] { "id_modele_msg" });
		super.setReturnGeneratedKeys(true);
	}
}
