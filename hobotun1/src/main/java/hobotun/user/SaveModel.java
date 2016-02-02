package hobotun.user;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;

import javax.faces.bean.ManagedBean;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.imageio.ImageIO;
import javax.servlet.http.Part;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import hobotun.core.UserSession;
import hobotun.db.DBUtil;
import hobotun.db.Image.ImageDao;
import hobotun.db.Image.tbl.ImageTbl;
import hobotun.db.file.FileDao;
import hobotun.db.file.tbl.FileTbl;
import hobotun.db.model.ModelDao;
import hobotun.db.model.tbl.ModelTbl;
import hobotun.db.userModel.UserModelDao;
import hobotun.db.userModel.table.UserModelTbl;
import hobotun.user.modelParams.ModeleHints;
import hobotun.util.SystemParams;

@ManagedBean(name = "saveModel1")
@ViewScoped
public class SaveModel implements Serializable {

	private static final long serialVersionUID = 5823856868775631334L;
	private Integer percent = 0;
	private BigDecimal price = BigDecimal.valueOf(0);

	private ImageDao imageDao;
	private FileDao fileDao;
	private ModelDao modelDao;

	private ModelTbl modelTbl;
	private String name = "";

	private UploadedFile modelFile = null;
	private FileTbl file = new FileTbl();
	private String textModeleFile;

	private String tegs = "";

	private Category category;
	private Format format;

	private String description = "";

	private boolean checkObj;
	private boolean checkFbx;
	private boolean altruist;

	private String textImgFile1;
	private String textImgFile2;
	private String textImgFile3;
	private String textImgFile4;
	private String textImgFile5;

	private UploadedFile imgFile1 = null;
	private UploadedFile imgFile2 = null;
	private UploadedFile imgFile3 = null;
	private UploadedFile imgFile4 = null;
	private UploadedFile imgFile5 = null;

	private ImageTbl img1 = new ImageTbl();
	private ImageTbl img2 = new ImageTbl();
	private ImageTbl img3 = new ImageTbl();
	private ImageTbl img4 = new ImageTbl();
	private ImageTbl img5 = new ImageTbl();

	private ImageTbl img1min = new ImageTbl();
	private ImageTbl img2min = new ImageTbl();
	private ImageTbl img3min = new ImageTbl();
	private ImageTbl img4min = new ImageTbl();
	private ImageTbl img5min = new ImageTbl();
	

	public ImageTbl getImg1min() {
		return img1min;
	}

	public ImageTbl getImg2min() {
		return img2min;
	}

	public ImageTbl getImg3min() {
		return img3min;
	}

	public ImageTbl getImg4min() {
		return img4min;
	}

	public ImageTbl getImg5min() {
		return img5min;
	}

	private Part file11;

	public Part getFile11() {
		return file11;
	}

	public void setFile11(Part file) {
		file11 = file;
	}

	public void handleFileUpload11(AjaxBehaviorEvent event) {
		System.out.println("file size: " + file11.getSize());
		System.out.println("file type: " + file11.getContentType());
	}

	public SaveModel() {
		category = new Category();
		category.init();

		format = new Format();
		format.init();

		percent = Integer.valueOf(SystemParams.getInstance().getParam(new Long(1)));

		imageDao = DBUtil.getInstance().getBean("imageDao", ImageDao.class);
		fileDao = DBUtil.getInstance().getBean("fileDao", FileDao.class);
		modelDao = DBUtil.getInstance().getBean("modelDao", ModelDao.class);
	}

	private void AutoUploadFile(FileUploadEvent event, UploadedFile imgFile, ImageTbl imgTbl, ImageTbl imgMiniTbl) {
		imgFile = event.getFile();

		if (imgFile != null) {

			if (!isValidSize(imgFile)) {
				textImgFile1 = "Размер картинки не соответсвует 640х640";
			} else {

				imgTbl.setImage(imgFile.getContents());
				imageDao.Insert(imgTbl);

				CreateMiniJpg(imgFile, imgMiniTbl, IMG_WIDTH, IMG_HEIGHT);
			}
		}
	}

	public void handleChange4(ValueChangeEvent event) {
		System.out.println(event.getNewValue().getClass());
	}

	public void handleFile1Upload(FileUploadEvent event) {
		AutoUploadFile(event, imgFile1, img1, img1min);
	}

	public void handleFile2Upload(FileUploadEvent event) {
		AutoUploadFile(event, imgFile2, img2, img2min);
	}

	public void handleFile3Upload(FileUploadEvent event) {
		AutoUploadFile(event, imgFile3, img3, img3min);
	}

	public void handleFile4Upload(FileUploadEvent event) {
		AutoUploadFile(event, imgFile4, img4, img4min);
	}

	public void handleFile5Upload(FileUploadEvent event) {
		AutoUploadFile(event, imgFile5, img5, img5min);
	}

	public void getPrice() {

		BigDecimal tmpPrice = BigDecimal.ZERO;

		if (!tegs.isEmpty()) {
			tmpPrice = tmpPrice.add(BigDecimal.valueOf(5));
		}

		if (altruist) {
			tmpPrice = BigDecimal.ZERO;
		}

		if (category.getCategory() != null) {
			if (category.getCategory().intValue() > 0) {
				tmpPrice = tmpPrice.add(BigDecimal.valueOf(5));
			}
		}

		if (format.getFormat() != null) {
			if (format.getFormat().intValue() > 0) {
				tmpPrice = tmpPrice.add(BigDecimal.valueOf(5));
			}
		}

		if (!description.isEmpty()) {
			tmpPrice = tmpPrice.add(BigDecimal.valueOf(5));
		}

		if (imgFile1 != null) {
			tmpPrice = tmpPrice.add(BigDecimal.valueOf(5));
		}

		if (checkFbx || checkObj) {
			tmpPrice = tmpPrice.add(BigDecimal.valueOf(5));
		}

		price = tmpPrice;

		// BigDecimal sum =
		// price.multiply(BigDecimal.valueOf(percent)).divide(BigDecimal.valueOf(100));
		// return "Цена модели составляет: " + price +
		// ". за каждое скачивание вы получите: " +
		// sum;
	}

	public UploadedFile getImgFile1() {
		return imgFile1;
	}

	public void setImgFile1(UploadedFile file) {
		if (!file.getFileName().isEmpty()) {
			this.imgFile1 = file;
			img1.setImage(this.imgFile1.getContents());
		}
	}

	public UploadedFile getImgFile2() {
		return imgFile2;
	}

	public void setImgFile2(UploadedFile file) {
		if (!file.getFileName().isEmpty()) {
			this.imgFile2 = file;
			img2.setImage(this.imgFile2.getContents());
		}
	}

	public UploadedFile getImgFile3() {
		return imgFile3;
	}

	public void setImgFile3(UploadedFile file) {
		if (!file.getFileName().isEmpty()) {
			this.imgFile3 = file;
			img3.setImage(this.imgFile3.getContents());
		}
	}

	public UploadedFile getImgFile4() {
		return imgFile4;
	}

	public void setImgFile4(UploadedFile file) {
		if (!file.getFileName().isEmpty()) {
			this.imgFile4 = file;
			img4.setImage(this.imgFile4.getContents());
		}
	}

	public UploadedFile getImgFile5() {
		return imgFile5;
	}

	public void setImgFile5(UploadedFile file) {
		if (!file.getFileName().isEmpty()) {
			this.imgFile5 = file;
			img5.setImage(this.imgFile5.getContents());
		}
	}

	public void save() {

		boolean err = false;

		if (modelFile == null) {
			err = true;
			textModeleFile = "Укажите архив с моделью";
		}

		if (imgFile1 == null) {
			err = true;
			textImgFile1 = "Выберете основную картинку";
		}

		if (!err) {
			saveAll();
			getPrice();
			saveModel();

			UserModelTbl userModelTbl = new UserModelTbl();
			userModelTbl.setIdEntityType(Long.valueOf(1));
			userModelTbl.setIdModel(modelTbl.getIdModel());
			userModelTbl.setIdUser(UserSession.getInstance().getUser().getUserTbl().getId_user());

			UserModelDao userModel = DBUtil.getInstance().getBean("userModelDao", UserModelDao.class);
			userModel.insertUserModel(userModelTbl);

		}

	}

	private void saveModel() {
		modelTbl = new ModelTbl();

		modelTbl.setDescription(description);
		modelTbl.setIdCategory(category.getCategory());
		modelTbl.setIdFile(file.getIdFile());
		modelTbl.setIdFormat(format.getFormat());

		modelTbl.setIdImg1(img1.getIdImage());
		modelTbl.setIdImg1min(img1min.getIdImage());

		modelTbl.setIdImg2(img2.getIdImage());
		modelTbl.setIdImg2min(img2min.getIdImage());

		modelTbl.setIdImg3(img3.getIdImage());
		modelTbl.setIdImg3min(img3min.getIdImage());

		modelTbl.setIdImg4(img4.getIdImage());
		modelTbl.setIdImg4min(img4min.getIdImage());

		modelTbl.setIdImg5(img5.getIdImage());
		modelTbl.setIdImg5min(img5min.getIdImage());

		modelTbl.setNmModel(name);
		modelTbl.setPrice(price);
		modelTbl.setTegs(tegs);

		if (checkFbx) {
			modelTbl.setFbx(1);
		} else {
			modelTbl.setFbx(1);
		}

		if (checkObj) {
			modelTbl.setObj(1);
		} else {
			modelTbl.setObj(1);
		}

		modelDao.insertModelReturnId(modelTbl);
	}

	private void saveAll() {
		fileDao.Insert(file);
	}

	private void CreateMiniJpg(UploadedFile file, ImageTbl imgMini, int width, int height) {
		try {
			BufferedImage img = ImageIO.read(file.getInputstream());

			BufferedImage scaled = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = scaled.createGraphics();

			Image scaled1 = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);

			g.drawImage(scaled1, 0, 0, null);
			g.dispose();

			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
			g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

			ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
			ImageIO.write(scaled, "JPEG", byteArray);

			imgMini.setImage(byteArray.toByteArray());
			imageDao.Insert(imgMini);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean isValidSize(UploadedFile file) {

		if (file != null) {
			BufferedImage img;
			try {
				img = ImageIO.read(file.getInputstream());

				if ((img.getWidth() != 640) && (img.getHeight() != 640)) {
					return false;
				}

			} catch (IOException | NullPointerException e) {
				return false;
			}
		}

		return true;
	}

	public String getTextImgFile1() {
		return textImgFile1;
	}

	public void setTextImgFile1(String textImgFile1) {
		this.textImgFile1 = textImgFile1;
	}

	public String getTextImgFile2() {
		return textImgFile2;
	}

	public void setTextImgFile2(String textImgFile2) {
		this.textImgFile2 = textImgFile2;
	}

	public String getTextImgFile3() {
		return textImgFile3;
	}

	public void setTextImgFile3(String textImgFile3) {
		this.textImgFile3 = textImgFile3;
	}

	public String getTextImgFile4() {
		return textImgFile4;
	}

	public void setTextImgFile4(String textImgFile4) {
		this.textImgFile4 = textImgFile4;
	}

	public String getTextImgFile5() {
		return textImgFile5;
	}

	public String getTextImgFile55() {
		return "test";
	}

	public void setTextImgFile5(String textImgFile5) {
		this.textImgFile5 = textImgFile5;
	}

	public boolean isCheckObj() {
		return checkObj;
	}

	public void setCheckObj(boolean checkObj) {
		this.checkObj = checkObj;
	}

	public boolean isCheckFbx() {
		return checkFbx;
	}

	public void setCheckFbx(boolean checkFbx) {
		this.checkFbx = checkFbx;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Format getFormat() {
		return format;
	}

	public void setFormat(Format format) {
		this.format = format;
	}

	public String getTegs() {
		return tegs;
	}

	public void setTegs(String tegs) {
		this.tegs = tegs;
	}

	public UploadedFile getModelFile() {
		return modelFile;
	}

	public void setModelFile(UploadedFile modelFile) {
		if (!modelFile.getFileName().isEmpty()) {
			this.modelFile = modelFile;
			file.setFile(this.modelFile.getContents());
		}
	}

	public String getTextModeleFile() {
		return textModeleFile;
	}

	public void setTextModeleFile(String textModeleFile) {
		this.textModeleFile = textModeleFile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPercent() {
		return percent;
	}

	public boolean isAltruist() {
		return altruist;
	}

	public void setAltruist(boolean altruist) {
		this.altruist = altruist;
	}

	private static final int IMG_WIDTH = 170;
	private static final int IMG_HEIGHT = 170;

}
