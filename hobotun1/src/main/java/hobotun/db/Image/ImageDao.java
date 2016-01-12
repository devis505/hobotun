package hobotun.db.Image;

import hobotun.db.Image.Action.InsertImageReturnId;
import hobotun.db.Image.Action.SelectImageById;
import hobotun.db.Image.tbl.ImageTbl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class ImageDao implements IImageDao {

	private SelectImageById selectImageById;
	private InsertImageReturnId insertImage;
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		selectImageById = new SelectImageById(dataSource);
		insertImage = new InsertImageReturnId(dataSource);
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	@Override
	public List<ImageTbl> getImageById(Integer id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id_image", id);

		return selectImageById.executeByNamedParam(paramMap);
	}

	@Override
	public void Insert(ImageTbl image) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("image", image.getImage());

		KeyHolder keyHolder = new GeneratedKeyHolder();

		insertImage.updateByNamedParam(paramMap, keyHolder);

		image.setIdImage(keyHolder.getKey().longValue());
	}

}
