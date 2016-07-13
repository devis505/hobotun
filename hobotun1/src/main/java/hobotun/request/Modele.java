package hobotun.request;

import java.io.ByteArrayInputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

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
import hobotun.db.model.tbl.ModeleMsgTbl;
import hobotun.db.rating.RatingDao;
import hobotun.db.rating.table.RatingModeleTbl;
import hobotun.db.user.UserDao;
import hobotun.db.user.table.UserTbl;
import hobotun.db.userModel.UserModelDao;
import hobotun.db.userModel.table.UserModelTbl;
import hobotun.sod.Sender;
import hobotun.sod.request.JsonRequestParams;
import hobotun.sod.request.RequestParam;
import hobotun.sod.responce.JsonResponceParams;

@Component
@ManagedBean(name = "modele")
@ViewScoped
public class Modele implements Serializable {

    private String modeleId;

    private String url;// =
		       // "https://money.yandex.ru/quickpay/confirm.xml?receiver=4100163656886&formcomment=&short-dest=&label=1&quickpay-form=donate&targets=1&sum=1&comment=test&need-fio=false&need-email=false&need-phone=false&need-address=false&paymentType=PC";

    private ModelTbl modele;
    private UserModelTbl userModel;
    private UserTbl user;
    private FileTbl fileModele;
    private CategoryTbl category;
    private FormatTabl format;

    private boolean isAllredyBay = false;

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
    private boolean ratingReadOnly = false;

    private Integer allRatingModel = 0;

    private Long id_user_session = null;

    private RatingDao ratingDao;
    private ModelDao modeleDao;

    private List<ModeleMsgTbl> modeleMsg;

    private String text;

    private static final long serialVersionUID = -1726709996903225394L;

    private void loadUser(Long id_user) {
	UserDao userDao = DBUtil.getInstance().getBean("userDao", UserDao.class);
	user = userDao.getUserById(id_user).get(0);
    }

    public Modele() {

	modeleId = Misc.getRequestParam(FacesContext.getCurrentInstance(), "id");

	modeleDao = DBUtil.getInstance().getBean("modelDao", ModelDao.class);
	modele = modeleDao.selectModelById(new Long(modeleId)).get(0);
	modeleMsg = modeleDao.selectModeleMsgByIdModele(new Long(modeleId));

	allRatingModel = modele.getRating();

	UserModelDao userModelDao = DBUtil.getInstance().getBean("userModelDao", UserModelDao.class);
	userModel = userModelDao.findUserModelByIdModel(new Long(modeleId)).get(0);

	loadUser(userModel.getIdUser());

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

	    ratingDao = DBUtil.getInstance().getBean("ratingDao", RatingDao.class);

	    id_user_session = UserSession.getInstance().getUser().getUserTbl().getId_user();

	    List<RatingModeleTbl> ratingTbl = ratingDao.selectUserModelRatingForModel(id_user_session,
		    new Long(modeleId));
	    if (!ratingTbl.isEmpty()) {
		rating = ratingTbl.get(0).getVl_rating();
		ratingReadOnly = true;
	    }

	    if (!userModelDao.checkUserModel(id_user_session, new Long(modeleId)).isEmpty()) {
		isAllredyBay = true;
		free = true;
	    }

	    if (!userModelDao.checkUserModelOwner(id_user_session, new Long(modeleId)).isEmpty()) {
		isAllredyBay = true;
		free = true;
	    }
	} else {
	    ratingReadOnly = true;
	}

	idBigImg = modele.getIdImg1();
    }

    public void onSaveText() {

	ModeleMsgTbl modeleMsgTbl = new ModeleMsgTbl();

	modeleMsgTbl.setDt_msg(new Date());
	modeleMsgTbl.setId_modele(new Long(modeleId));
	modeleMsgTbl.setId_user(UserSession.getInstance().getUser().getUserTbl().getId_user());
	modeleMsgTbl.setVl_msg(text);

	modeleDao.insertModeleMsg(modeleMsgTbl);

	Misc.redirect("/pages/modele/modele.jsf?id=" + modeleId);
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
	allRatingModel += rating;

	ratingReadOnly = true;

	RatingModeleTbl ratinTbl = new RatingModeleTbl();
	ratinTbl.setId_model(new Long(modeleId));
	ratinTbl.setId_user(id_user_session);
	ratinTbl.setVl_rating(rating);

	ratingDao.InsertModeleRating(ratinTbl);

	loadUser(userModel.getIdUser());
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

    public void send() throws RestClientException, UnsupportedEncodingException {
	JsonRequestParams request = new JsonRequestParams();

	List<RequestParam> params = new ArrayList<>();
	params.add(new RequestParam("id_user", id_user_session));
	params.add(new RequestParam("sm_pay", modele.getPrice_calc()));
	params.add(new RequestParam("kd_state", 1));
	params.add(new RequestParam("id_modele", userModel.getIdModel()));

	request.setKey_holder("id_pay");
	request.setOwner("dsHobotun");
	request.setQueryName("qHbInsertPay");
	request.setParams(params);

	JsonResponceParams responce = new JsonResponceParams();

	Sender sender = new Sender(request);
	sender.send();
	responce = sender.getResponce();

	Double id = (Double) responce.getResult().get(0).get("key_holder");

	if (provider == 1) {
	    url = "https://money.yandex.ru/quickpay/confirm.xml?receiver=4100163656886&formcomment=&short-dest=&label="
		    + id + "&quickpay-form=donate&targets=Транзакция " + id + "&sum=" + modele.getPrice_calc()
		    + "&comment=Оплата модели " + modele.getNmModel()
		    + "&need-fio=false&need-email=false&need-phone=false&need-address=false&paymentType=PC";
	} else {
	    url = "https://money.yandex.ru/quickpay/confirm.xml?receiver=4100163656886&formcomment=&short-dest=&label="
		    + id + "&quickpay-form=donate&targets=Транзакция " + id + "&sum=" + modele.getPrice_calc()
		    + "&comment=Оплата модели " + modele.getNmModel()
		    + "&need-fio=false&need-email=false&need-phone=false&need-address=false&paymentType=AC";
	}

	Misc.redirectOut(url);
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

    public boolean isRatingReadOnly() {
	return ratingReadOnly;
    }

    public void setRatingReadOnly(boolean ratingReadOnly) {
	this.ratingReadOnly = ratingReadOnly;
    }

    public Integer getAllRatingModel() {
	return allRatingModel;
    }

    public void setAllRatingModel(Integer allRatingModel) {
	this.allRatingModel = allRatingModel;
    }

    public List<ModeleMsgTbl> getModeleMsg() {
	return modeleMsg;
    }

    public void setModeleMsg(List<ModeleMsgTbl> modeleMsg) {
	this.modeleMsg = modeleMsg;
    }

    public String getText() {
	return text;
    }

    public void setText(String text) {
	this.text = text;
    }

    public String getUrl() {
	return url;
    }

    public void setUrl(String url) {
	this.url = url;
    }

}
