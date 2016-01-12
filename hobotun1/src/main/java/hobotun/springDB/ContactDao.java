package hobotun.springDB;

import hobotun.springDB.SelectAllModel;
import hobotun.springDB.SelectModelById;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

@Repository("contactDao")
public class ContactDao implements IContactDao {

    private DataSource dataSource;
    private SelectAllModel selectAllModel;
    private SelectModelById selectmodelById;

    public void setDataSource(DataSource dataSource) {
	this.dataSource = dataSource;
	this.selectAllModel = new SelectAllModel(this.dataSource);
	this.selectmodelById = new SelectModelById(this.dataSource);
    }

    @Override
    public List<Model> findAll() {
	return selectAllModel.execute();
    }

    @Override
    public List<Model> findLastNameById(Integer id) {
	Map<String, Object> param = new HashMap<String, Object>();
	param.put("id_model", id);

	return selectmodelById.executeByNamedParam(param);
    }

}
