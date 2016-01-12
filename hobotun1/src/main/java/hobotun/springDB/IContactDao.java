package hobotun.springDB;

import java.util.List;

public interface IContactDao {
    
    public List<Model> findLastNameById(Integer id);

    public List<Model> findAll();
}
