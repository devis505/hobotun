package hobotun.db.Image;

import java.util.List;

import hobotun.db.Image.tbl.ImageTbl;

public interface IImageDao {

    public List<ImageTbl> getImageById(Integer id);

    public void Insert(ImageTbl image);

}
