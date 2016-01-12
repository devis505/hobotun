package hobotun.user;

import hobotun.db.DBUtil;
import hobotun.db.format.FormatDao;
import hobotun.db.format.table.FormatTabl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;

@ManagedBean(name = "format")
@ViewScoped
public class Format {

    private Integer format = 0;
    private List<SelectItem> formates;

    @PostConstruct
    public void init() {

	formates = new ArrayList<SelectItem>();

	FormatDao formatDao = DBUtil.getInstance().getBean("formatDao", FormatDao.class);
	List<FormatTabl> formatTbl = formatDao.getAllFormat();

	for (FormatTabl formatItem : formatTbl) {
	    formates.add(new SelectItem(formatItem.getIdFormat(), formatItem.getNmFormat()));
	}
    }

    public Integer getFormat() {
	return format;
    }

    public void setFormat(Integer format) {
	this.format = format;
    }

    public List<SelectItem> getFormates() {
        return formates;
    }

    public void setFormates(List<SelectItem> formates) {
        this.formates = formates;
    }
    
}
