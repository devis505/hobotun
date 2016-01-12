package hobotun.servlets;

import hobotun.db.DBUtil;
import hobotun.db.Image.ImageDao;
import hobotun.db.Image.tbl.ImageTbl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetImage extends HttpServlet {

    private static final long serialVersionUID = -4442024319043291765L;
    private ImageDao imageDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

	if (req.getParameter("idImg") != null) {
	    if (!req.getParameter("idImg").isEmpty()) {
		imageDao = DBUtil.getInstance().getBean("imageDao", ImageDao.class);

		List<ImageTbl> images = imageDao.getImageById(Integer.valueOf(req.getParameter("idImg")));

		byte[] image = null;

		if (images.size() == 1) {
		    image = images.get(0).getImage();
		}

		if (image != null) {
		    response.setContentType("image/png");
		    response.setContentLength(image.length);

		    ServletOutputStream out = response.getOutputStream();
		    out.write(image);
		} else {
		    response.getWriter().write("");
		}
	    }
	} else {
	    response.getWriter().write("");
	}

    }
}
