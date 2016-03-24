package hobotun.user;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.imageio.ImageIO;
import javax.servlet.http.Part;

import hobotun.core.Misc;
import hobotun.core.UserSession;
import hobotun.db.DBUtil;
import hobotun.db.Image.ImageDao;
import hobotun.db.Image.tbl.ImageTbl;
import hobotun.db.user.UserDao;
import hobotun.db.user.table.UserTbl;
import hobotun.user.modelParams.ModelePartParam;

@ManagedBean(name = "editPic")
@ViewScoped
public class EditPic implements Serializable {

	private static final long serialVersionUID = -7147632459564507473L;
	private UserTbl userTbl = UserSession.getInstance().getUser().getUserTbl();
	private ImageTbl imageTbl = new ImageTbl();

	private ModelePartParam img = new ModelePartParam();

	public void onSaveImg() {
		
		try {
			
			BufferedImage imgTmp = ImageIO.read(this.img.getParam().getInputStream());

			BufferedImage scaled = new BufferedImage(80, 80, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = scaled.createGraphics();

			Image scaled1 = imgTmp.getScaledInstance(80, 80, Image.SCALE_SMOOTH);

			g.drawImage(scaled1, 0, 0, null);
			g.dispose();

			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
			g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

			ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
			ImageIO.write(scaled, "JPEG", byteArray);

			imageTbl.setImage(byteArray.toByteArray());

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void onSave() {
		
		ImageDao imageDao = DBUtil.getInstance().getBean("imageDao", ImageDao.class);
		imageDao.Insert(imageTbl);
		
		userTbl.setIdImage(imageTbl.getIdImage());
		
		UserDao userDao = DBUtil.getInstance().getBean("userDao", UserDao.class);
		userDao.UpdateUserById(userTbl.getAllParam());
		
		Misc.redirect("/pages/user/user.jsf?userPageId=1");
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

	public ModelePartParam getImg() {
		return img;
	}
	public void setImg(ModelePartParam img) {
		this.img = img;
	}

	private static final String ID_MSG_FOR_WRONG_PASS = "info-form:pass";
	private static final String MSG_ERROR_WRONG_PASS = "Старый пароль не совпадает";
}
