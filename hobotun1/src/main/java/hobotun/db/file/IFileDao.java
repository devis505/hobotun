package hobotun.db.file;

import hobotun.db.file.tbl.FileTbl;

import java.util.List;

public interface IFileDao {

    public List<FileTbl> getImageById(Integer id);

    public void Insert(FileTbl file);

}
