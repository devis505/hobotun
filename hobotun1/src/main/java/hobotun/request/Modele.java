package hobotun.request;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.math.BigDecimal;

import javax.activation.MimetypesFileTypeMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import hobotun.core.Misc;
import hobotun.core.UserSession;
import hobotun.db.DBUtil;
import hobotun.db.category.CategoryDao;
import hobotun.db.category.table.CategoryTbl;
import hobotun.db.file.FileDao;
import hobotun.db.file.tbl.FileTbl;
import hobotun.db.format.FormatDao;
import hobotun.db.format.table.FormatTabl;
import hobotun.db.model.ModelDao;
import hobotun.db.model.tbl.ModelTbl;
import hobotun.db.user.UserDao;
import hobotun.db.user.table.UserTbl;
import hobotun.db.userModel.UserModelDao;
import hobotun.db.userModel.table.UserModelTbl;

@ManagedBean(name = "modele")
@ViewScoped
public class Modele implements Serializable {

	private String modeleId;

	private ModelTbl modele;
	private UserModelTbl userModel;
	private UserTbl user;
	private FileTbl fileModele;
	private CategoryTbl category;
	private FormatTabl format;

	private boolean isAllredyBay = false;
	private boolean isOwner = false;

	private Integer provider = 1;
	private boolean free = false;

	private boolean visibleImg1 = true;
	private boolean visibleImg2 = true;
	private boolean visibleImg3 = true;
	private boolean visibleImg4 = true;
	private boolean visibleImg5 = true;

	private String miniPic1Classes = "";
	private String miniPic2Classes = "Opac30";
	private String miniPic3Classes = "Opac30";
	private String miniPic4Classes = "Opac30";
	private String miniPic5Classes = "Opac30";

	private boolean isEnableAdd = false;

	private Long idBigImg;
	private Integer seceted = 1;
	private Integer rating = 0;

	private static final long serialVersionUID = -1726709996903225394L;

	public Modele() {
		
		modeleId = Misc.getRequestParam(FacesContext.getCurrentInstance(), "id");

		ModelDao modeleDao = DBUtil.getInstance().getBean("modelDao", ModelDao.class);
		modele = modeleDao.selectModelById(new Long(modeleId)).get(0);

		UserModelDao userModelDao = DBUtil.getInstance().getBean("userModelDao", UserModelDao.class);
		userModel = userModelDao.findUserModelByIdModel(new Long(modeleId)).get(0);

		UserDao userDao = DBUtil.getInstance().getBean("userDao", UserDao.class);
		user = userDao.getUserById(userModel.getIdUser()).get(0);
				
		FileDao fileDao = DBUtil.getInstance().getBean("fileDao", FileDao.class);
		fileModele = fileDao.SelectFileById(modele.getIdFile()).get(0);

		CategoryDao categoryDao = DBUtil.getInstance().getBean("categoryDao", CategoryDao.class);
		category = categoryDao.getCategoryById(modele.getIdCategory());

		FormatDao formatDao = DBUtil.getInstance().getBean("formatDao", FormatDao.class);
		format = formatDao.getFormatById(modele.getIdFormat());

		visibleImg1 = (modele.getIdImg1min() != 0);
		visibleImg2 = (modele.getIdImg2min() != 0);
		visibleImg3 = (modele.getIdImg3min() != 0);
		visibleImg4 = (modele.getIdImg4min() != 0);
		visibleImg5 = (modele.getIdImg5min() != 0);

		if (modele.getPrice().compareTo(BigDecimal.ZERO) > 0) {
			free = false;
		} else {
			free = true;
		}

		if (UserSession.getInstance().getUser() != null) {
			
			Long id_user_session = UserSession.getInstance().getUser().getUserTbl().getId_user();
			
			if (!userModelDao
					.checkUserModel(id_user_session, new Long(modeleId))
					.isEmpty()) {
				isAllredyBay = true;
				free = true;
			}
			
			if (!userModelDao.checkUserModelOwner(id_user_session, new Long(modeleId)).isEmpty()) {
				isAllredyBay = true;
				free = true;
			}
		}
		
		idBigImg = modele.getIdImg1();
	}

	public void select1() {
		seceted = 1;
		refreshComanents();
	}

	public void select2() {
		seceted = 2;
		refreshComanents();
	}

	public void select3() {
		seceted = 3;
		refreshComanents();
	}

	public void select4() {
		seceted = 4;
		refreshComanents();
	}

	public void select5() {
		seceted = 5;
		refreshComanents();
	}

	public String getMiniPic1Classes() {
		return miniPic1Classes;
	}

	public String getMiniPic2Classes() {
		return miniPic2Classes;
	}

	public String getMiniPic3Classes() {
		return miniPic3Classes;
	}

	public String getMiniPic4Classes() {
		return miniPic4Classes;
	}

	public String getMiniPic5Classes() {
		return miniPic5Classes;
	}

	private void refreshComanents() {

		if (seceted.equals(1)) {
			idBigImg = modele.getIdImg1();
			miniPic1Classes = "";
			miniPic2Classes = "Opac30";
			miniPic3Classes = "Opac30";
			miniPic4Classes = "Opac30";
			miniPic5Classes = "Opac30";
		} else if (seceted.equals(2)) {
			idBigImg = modele.getIdImg2();
			miniPic1Classes = "Opac30";
			miniPic2Classes = "";
			miniPic3Classes = "Opac30";
			miniPic4Classes = "Opac30";
			miniPic5Classes = "Opac30";
		} else if (seceted.equals(3)) {
			idBigImg = modele.getIdImg3();
			miniPic1Classes = "Opac30";
			miniPic2Classes = "Opac30";
			miniPic3Classes = "";
			miniPic4Classes = "Opac30";
			miniPic5Classes = "Opac30";
		} else if (seceted.equals(4)) {
			idBigImg = modele.getIdImg4();
			miniPic1Classes = "Opac30";
			miniPic2Classes = "Opac30";
			miniPic3Classes = "Opac30";
			miniPic4Classes = "";
			miniPic5Classes = "Opac30";
		} else {
			idBigImg = modele.getIdImg5();
			miniPic1Classes = "Opac30";
			miniPic2Classes = "Opac30";
			miniPic3Classes = "Opac30";
			miniPic4Classes = "Opac30";
			miniPic5Classes = "";
		}

	}

	public StreamedContent getFile() {

		if (!isAllredyBay) {
			UserModelTbl userModel = new UserModelTbl();

			userModel.setIdEntityType(new Long(2));
			userModel.setIdModel(new Long(modeleId));
			userModel.setIdUser(UserSession.getInstance().getUser().getUserTbl().getId_user());

			UserModelDao userModelDao = DBUtil.getInstance().getBean("userModelDao", UserModelDao.class);
			userModelDao.insertUserModel(userModel);
		}

		return new DefaultStreamedContent(new ByteArrayInputStream(fileModele.getFile()),
				new MimetypesFileTypeMap().getContentType(fileModele.getNm_file()), fileModele.getNm_file());

		// Misc.redirect("/pages/user/user.jsf?userPageId=5");
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public ModelTbl getModele() {
		return modele;
	}

	public UserTbl getUser() {
		return user;
	}

	public boolean isVisibleImg1() {
		return visibleImg1;
	}

	public void setVisibleImg1(boolean visibleImg1) {
		this.visibleImg1 = visibleImg1;
	}

	public boolean isVisibleImg2() {
		return visibleImg2;
	}

	public void setVisibleImg2(boolean visibleImg2) {
		this.visibleImg2 = visibleImg2;
	}

	public boolean isVisibleImg3() {
		return visibleImg3;
	}

	public void setVisibleImg3(boolean visibleImg3) {
		this.visibleImg3 = visibleImg3;
	}

	public boolean isVisibleImg5() {
		return visibleImg5;
	}

	public void setVisibleImg5(boolean visibleImg5) {
		this.visibleImg5 = visibleImg5;
	}

	public boolean isVisibleImg4() {
		return visibleImg4;
	}

	public void setVisibleImg4(boolean visibleImg4) {
		this.visibleImg4 = visibleImg4;
	}

	public Long getIdBigImg() {
		return idBigImg;
	}

	public FileTbl getFileModele() {
		return fileModele;
	}

	public void onRate() {

	}

	public CategoryTbl getCategory() {
		return category;
	}

	public FormatTabl getFormat() {
		return format;
	}

	public Integer getProvider() {
		return provider;
	}

	public void setProvider(Integer provider) {
		this.provider = provider;
	}

	public boolean isFree() {
		return free;
	}

	public void setFree(boolean free) {
		this.free = free;
	}

	public boolean isEnableAdd() {

		if (UserSession.getInstance().getUser() != null) {
			if (!UserSession.getInstance().getUser().isAuthorization()) {
				isEnableAdd = true;
			} else {
				isEnableAdd = false;
			}
		} else {
			isEnableAdd = true;
		}

		return isEnableAdd;
	}

	public void setEnableAdd(boolean isEnableAdd) {
		this.isEnableAdd = isEnableAdd;
	}

}
