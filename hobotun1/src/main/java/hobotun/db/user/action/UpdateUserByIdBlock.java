package hobotun.db.user.action;

import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class UpdateUserByIdBlock extends SqlUpdate {
    
    private static final String SQL_UPDATE_USER = 
	    "UPDATE hb_user u "
	  + "   SET u.is_block=:is_block, u.is_block_forum=:is_block_forum"
	  + " WHERE u.id_user = :id_user";

    public UpdateUserByIdBlock(DataSource dataSource) {
    	super(dataSource, SQL_UPDATE_USER);
	
    	super.declareParameter(new SqlParameter("is_block", Types.INTEGER));
    	super.declareParameter(new SqlParameter("is_block_forum", Types.INTEGER));
    	super.declareParameter(new SqlParameter("id_user", Types.INTEGER));
    }
}
