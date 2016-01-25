package hobotun.user;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.imageio.ImageIO;
import javax.servlet.http.Part;

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
import hobotun.user.modelParams.ModeleBooleanParam;
import hobotun.user.modelParams.ModeleIntegerParam;
import hobotun.user.modelParams.ModelePartParam;
import hobotun.user.modelParams.ModeleStringParam;

@ManagedBean(name = "saveModel")
@ViewScoped
public class SaveModelForm {

	private boolean saveButtonEnable = false;
	private String errColor = "Red";

	private boolean isAltruist = false;

	private ModelePartParam fileModel = new ModelePartParam();
	private FileTbl file = new FileTbl();

	private ModelePartParam bigImg1 = new ModelePartParam();
	private ModelePartParam bigImg2 = new ModelePartParam();
	private ModelePartParam bigImg3 = new ModelePartParam();
	private ModelePartParam bigImg4 = new ModelePartParam();
	private ModelePartParam bigImg5 = new ModelePartParam();

	private ModeleStringParam modelName = new ModeleStringParam();
	private ModeleStringParam modeleTegs = new ModeleStringParam();
	private ModeleStringParam modeleDescription = new ModeleStringParam();

	private ModeleBooleanParam fbx = new ModeleBooleanParam();
	private ModeleBooleanParam obj = new ModeleBooleanParam();
	private ModeleBooleanParam texture = new ModeleBooleanParam();
	private ModeleBooleanParam booleanParams = new ModeleBooleanParam();

	private ModeleIntegerParam countPoligons = new ModeleIntegerParam();

	private Integer costFlex = 5;
	private Integer cost = 0;

	private Format formats;
	private ModeleBooleanParam formatGreenVisible = new ModeleBooleanParam();

	private Category categoryes;
	private ModeleBooleanParam categoryGreenVisible = new ModeleBooleanParam();

	private String allErr = "";

	private ImageTbl miniImgTbl1 = new ImageTbl();
	private ImageTbl miniImgTbl2 = new ImageTbl();
	private ImageTbl miniImgTbl3 = new ImageTbl();
	private ImageTbl miniImgTbl4 = new ImageTbl();
	private ImageTbl miniImgTbl5 = new ImageTbl();

	private ImageTbl imgTbl1 = new ImageTbl();
	private ImageTbl imgTbl2 = new ImageTbl();
	private ImageTbl imgTbl3 = new ImageTbl();
	private ImageTbl imgTbl4 = new ImageTbl();
	private ImageTbl imgTbl5 = new ImageTbl();

	public SaveModelForm() {
		formats = new Format();
		formats.init();

		categoryes = new Category();
		categoryes.init();
	}

	public void onSaveModel() {

		boolean err = false;
		String errText = "";

		// TODO: ������� ��������

		if (err) {
			errColor = "Red";
			allErr = errText;
		} else {
			try {
				saveImage(imgTbl1);
				saveImage(imgTbl2);
				saveImage(imgTbl3);
				saveImage(imgTbl4);
				saveImage(imgTbl5);

				FileDao fileDao = DBUtil.getInstance().getBean("fileDao", FileDao.class);
				fileDao.Insert(file);

				ModelTbl model = new ModelTbl();

				model.setNmModel(modelName.getParam());
				model.setDescription(modeleDescription.getParam());
				model.setIdCategory(categoryes.getCategory());
				model.setIdFormat(formats.getFormat());
				model.setObj(obj.getIntParam());
				model.setFbx(fbx.getIntParam());
				model.setTegs(modeleTegs.getParam());
				model.setPrice(BigDecimal.valueOf(new Long(cost)));
				model.setCount_poligon(countPoligons.getParam());
				model.setTexture(texture.getIntParam());

				model.setIdFile(file.getIdFile());

				model.setIdImg1(imgTbl1.getIdImage());
				model.setIdImg2(imgTbl2.getIdImage());
				model.setIdImg3(imgTbl3.getIdImage());
				model.setIdImg4(imgTbl4.getIdImage());
				model.setIdImg5(imgTbl5.getIdImage());

				model.setIdImg1min(miniImgTbl1.getIdImage());
				model.setIdImg2min(miniImgTbl2.getIdImage());
				model.setIdImg3min(miniImgTbl3.getIdImage());
				model.setIdImg4min(miniImgTbl4.getIdImage());
				model.setIdImg5min(miniImgTbl5.getIdImage());

				ModelDao modelDao = DBUtil.getInstance().getBean("modelDao", ModelDao.class);
				modelDao.insertModelReturnId(model);
				
				UserModelTbl userEntity = new UserModelTbl();
				
				userEntity.setIdEntityType(new Long(1));
				userEntity.setIdModel(model.getIdModel());
				userEntity.setIdUser(UserSession.getInstance().getUser().getUserTbl().getId_user());
				
				UserModelDao userModel = DBUtil.getInstance().getBean("userModelDao", UserModelDao.class);
				userModel.insertUserModel(userEntity);

				allErr = "������ ������� ��������� � ������� ���������.";

				errColor = "Green";
				saveButtonEnable = true;
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	private void saveImage(ImageTbl img) {
		if (img.getImage() != null) {
			ImageDao imageDao = DBUtil.getInstance().getBean("imageDao", ImageDao.class);
			imageDao.Insert(img);
		}
	}

	public void onChangeFbx() {

		Integer f1 = 0;
		Integer f2 = 0;

		booleanParams.setOpacity50();

		if (obj.getParam()) {
			f1 = 5;
			booleanParams.setOpacity100();
		}

		if (fbx.getParam()) {
			f1 = 5;
			booleanParams.setOpacity100();
		}

		if (texture.getParam()) {
			f2 = 5;
			booleanParams.setOpacity100();
		}

		Integer f3 = f1 + f2;

		if (f3.equals(0))
			f3 = 5;

		setCostFlex(f3);
	}

	public void onChangeCategory() {
		if (!categoryes.getCategory().equals(0)) {
			categoryGreenVisible.setParam(true);
		} else {
			categoryGreenVisible.setParam(false);
		}
	}

	public void onChangeFormat() {
		if (!formats.getFormat().equals(0)) {
			formatGreenVisible.setParam(true);
		} else {
			formatGreenVisible.setParam(false);
		}
	}

	public byte[] getByteByStreem(Part file) {
		try (InputStream input = file.getInputStream()) {

			ByteArrayOutputStream buffer = new ByteArrayOutputStream();

			int nRead;
			byte[] data = new byte[16384];

			while ((nRead = input.read(data, 0, data.length)) != -1) {
				buffer.write(data, 0, nRead);
			}

			buffer.flush();

			return buffer.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	private void CreateMiniJpg(InputStream stream, ImageTbl imgMini, int width, int height) {
		try {
			BufferedImage img = ImageIO.read(stream);

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

			saveImage(imgMini);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onSaveModelFile(AjaxBehaviorEvent event) {
		file.setFile(getByteByStreem(fileModel.getParam()));
	}

	public void onSaveModelImg1(AjaxBehaviorEvent event) {
		saveMiniImg(bigImg1, miniImgTbl1);
		saveBigImg(imgTbl1, bigImg1);
	}

	public void onSaveModelImg2(AjaxBehaviorEvent event) {
		saveMiniImg(bigImg2, miniImgTbl2);
		saveBigImg(imgTbl2, bigImg2);
	}

	public void onSaveModelImg3(AjaxBehaviorEvent event) {
		saveMiniImg(bigImg3, miniImgTbl3);
		saveBigImg(imgTbl3, bigImg3);
	}

	public void onSaveModelImg4(AjaxBehaviorEvent event) {
		saveMiniImg(bigImg4, miniImgTbl4);
		saveBigImg(imgTbl4, bigImg4);
	}

	public void onSaveModelImg5(AjaxBehaviorEvent event) {
		saveMiniImg(bigImg5, miniImgTbl5);
		saveBigImg(imgTbl5, bigImg5);
	}

	private void saveBigImg(ImageTbl imateTbl, ModelePartParam param) {
		imateTbl.setImage(getByteByStreem(param.getParam()));
	}

	private void saveMiniImg(ModelePartParam param, ImageTbl img) {
		try {
			CreateMiniJpg(param.getParam().getInputStream(), img, IMG_WIDTH, IMG_HEIGHT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ModeleStringParam getModeleDescription() {
		return modeleDescription;
	}

	public void setModeleDescription(ModeleStringParam modeleDescription) {
		this.modeleDescription = modeleDescription;
	}

	public ModeleStringParam getModelName() {
		return modelName;
	}

	public void setModelName(ModeleStringParam modelName) {
		this.modelName = modelName;
	}

	public ModeleStringParam getModeleTegs() {
		return modeleTegs;
	}

	public void setModeleTegs(ModeleStringParam modeleTegs) {
		this.modeleTegs = modeleTegs;
	}

	public Format getFormats() {
		return formats;
	}

	public void setFormats(Format formats) {
		this.formats = formats;
	}

	public Category getCategoryes() {
		return categoryes;
	}

	public void setCategoryes(Category categoryes) {
		this.categoryes = categoryes;
	}

	public ModeleBooleanParam isFbx() {
		return fbx;
	}

	public void setFbx(ModeleBooleanParam fbx) {
		this.fbx = fbx;
	}

	public ModeleBooleanParam isObj() {
		return obj;
	}

	public void setObj(ModeleBooleanParam obj) {
		this.obj = obj;
	}

	public ModeleBooleanParam isTexture() {
		return texture;
	}

	public void setTexture(ModeleBooleanParam texture) {
		this.texture = texture;
	}

	public Integer getCostFlex() {
		return costFlex;
	}

	public void setCostFlex(Integer costFlex) {
		this.costFlex = costFlex;
	}

	public ModeleBooleanParam getFormatGreenVisible() {
		return formatGreenVisible;
	}

	public ModeleBooleanParam getCategoryGreenVisible() {
		return categoryGreenVisible;
	}

	public ModeleBooleanParam getBooleanParams() {
		return booleanParams;
	}

	public void setBooleanParams(ModeleBooleanParam booleanParams) {
		this.booleanParams = booleanParams;
	}

	public ModeleBooleanParam getFbx() {
		return fbx;
	}

	public ModeleBooleanParam getObj() {
		return obj;
	}

	public ModeleBooleanParam getTexture() {
		return texture;
	}

	public ModeleIntegerParam getCountPoligons() {
		return countPoligons;
	}

	public void setCountPoligons(ModeleIntegerParam countPoligons) {
		this.countPoligons = countPoligons;
	}

	public ModelePartParam getFileModel() {
		return fileModel;
	}

	public void setFileModel(ModelePartParam fileModel) {
		this.fileModel = fileModel;
	}

	public ModelePartParam getBigImg1() {
		return bigImg1;
	}

	public void setBigImg1(ModelePartParam bigImg1) {
		this.bigImg1 = bigImg1;
	}

	public ModelePartParam getBigImg2() {
		return bigImg2;
	}

	public void setBigImg2(ModelePartParam bigImg2) {
		this.bigImg2 = bigImg2;
	}

	public ModelePartParam getBigImg3() {
		return bigImg3;
	}

	public void setBigImg3(ModelePartParam bigImg3) {
		this.bigImg3 = bigImg3;
	}

	public ModelePartParam getBigImg4() {
		return bigImg4;
	}

	public void setBigImg4(ModelePartParam bigImg4) {
		this.bigImg4 = bigImg4;
	}

	public ModelePartParam getBigImg5() {
		return bigImg5;
	}

	public void setBigImg5(ModelePartParam bigImg5) {
		this.bigImg5 = bigImg5;
	}

	public Integer getCost() {
		calcCost();

		return cost;
	}

	private void calcCost() {

		cost = 0;

		if (!isAltruist) {

			if (!modeleTegs.isEmpty()) {
				cost += 5;
			}

			if (!modeleDescription.isEmpty()) {
				cost += 5;
			}

			if (!formatGreenVisible.isEmpty()) {
				cost += 5;
			}

			if (!categoryGreenVisible.isEmpty()) {
				cost += 5;
			}

			if (!fbx.isEmpty() || !obj.isEmpty()) {
				cost += 5;
			}

			if (!texture.isEmpty()) {
				cost += 5;
			}

			if (!countPoligons.isEmpty()) {
				cost += 5;
			}

			if (!bigImg4.isEmpty()) {
				cost += 10;
			}

			if (!bigImg5.isEmpty()) {
				cost += 20;
			}
		}
	}

	public String getAllErr() {
		return allErr;
	}

	public void setAllErr(String allErr) {
		this.allErr = allErr;
	}

	public boolean isSaveButtonEnable() {
		return saveButtonEnable;
	}

	public String getErrColor() {
		return errColor;
	}

	public boolean isAltruist() {
		return isAltruist;
	}

	public void setAltruist(boolean isAltruist) {
		this.isAltruist = isAltruist;
		/*
		fileModel.setAltruist(this.isAltruist);
		bigImg1.setAltruist(this.isAltruist);
		bigImg2.setAltruist(this.isAltruist);
		bigImg3.setAltruist(this.isAltruist);
		bigImg4.setAltruist(this.isAltruist);
		bigImg5.setAltruist(this.isAltruist);
		modelName.setAltruist(this.isAltruist);
		modeleTegs.setAltruist(this.isAltruist);
		modeleDescription.setAltruist(this.isAltruist);
		fbx.setAltruist(this.isAltruist);
		obj.setAltruist(this.isAltruist);
		texture.setAltruist(this.isAltruist);
		booleanParams.setAltruist(this.isAltruist);
		countPoligons.setAltruist(this.isAltruist);
		formatGreenVisible.setAltruist(this.isAltruist);
		categoryGreenVisible.setAltruist(this.isAltruist);*/
	}

	private static final int IMG_WIDTH = 170;
	private static final int IMG_HEIGHT = 170;
}
