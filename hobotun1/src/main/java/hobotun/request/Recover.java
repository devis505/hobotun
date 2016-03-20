package hobotun.request;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import hobotun.core.Misc;
import hobotun.db.DBUtil;
import hobotun.db.user.UserDao;
import hobotun.db.user.table.ForgetPassTbl;
import hobotun.db.user.table.UserTbl;
import hobotun.util.SendEmail;

@ManagedBean(name = "recover")
@ViewScoped
public class Recover implements Serializable {

	private static final long serialVersionUID = -6596635184360113684L;
	private UserDao userDao = DBUtil.getInstance().getBean("userDao", UserDao.class);

	private String msg;
	
	public Recover() {
		Map<String, Object> params = new HashMap<>();
		
		String id_user = Misc.getRequestParam(FacesContext.getCurrentInstance(), "i");
		
		params.put("id_user", id_user);
		params.put("vl_hash", Misc.getRequestParam(FacesContext.getCurrentInstance(), "c"));
		params.put("dt_create", new Date());
		
		List<ForgetPassTbl> forgetPass = userDao.findForgetPass(params);
		List<UserTbl> userTbl = userDao.getUserById(new Long(id_user));
		
		if (!forgetPass.isEmpty()) {
			msg = "�� ���� ����� <b>" + userTbl.get(0).getMail() + "</b> ��������� ����� ������!";
			
			String newPass = Misc.getUnicValue(8);
			String mail = userTbl.get(0).getMail();
			String body = String.format(msgToMail, mail, newPass);
			
			Map<String, Object> userParams = userTbl.get(0).getAllParam();
			userParams.put("password", Misc.md5Custom(newPass));
			
			userDao.UpdateUserById(userParams);
			
			SendEmail.getInstance().SendMail(mail, "�������������� ������", body);
			
		} else {
			msg = "������ �� �������������� ������ �� ������";
		}
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	private final static String msgToMail = 
			"<p>��� ��� �� ������ ������ � ��������� ��� ��������������, ���������" +
			"�������, �� �������� ����� �����, ������������ (!) ��� �����," +
			"���������, ������������������� ������.</p>" +
			"<p>���������� ������ ����������, ����������� ��� ������� � ��� �� ����" +
			"�������� ��������� �������:</p><br/>" + 
			"<b>�����:</b> %s<br/>" +
			"<b>������:</b> %s<br/><br/>" +
			"<p>����� ���������� ������� �� <a href=\"https://www.hobotun.com\">https://www.hobotun.com</a></p>";
	
}
