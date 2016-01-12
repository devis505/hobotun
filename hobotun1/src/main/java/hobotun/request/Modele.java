package hobotun.request;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.RateEvent;

@ManagedBean(name = "modele")
@ViewScoped
public class Modele implements Serializable {

    private String miniPic1Url = "http://b2.3ddd.ru/media/cache/sky_model_preview_small/model_images/0000/0000/0389/389603.562984cdcb9fa.jpeg";
    private String miniPic2Url = "http://b2.3ddd.ru/media/cache/sky_model_preview_small/model_images/0000/0000/0389/389605.562984cdcc075.jpeg";
    private String miniPic3Url = "http://b2.3ddd.ru/media/cache/sky_model_preview_small/model_images/0000/0000/0389/389607.562984cdcc568.jpeg";

    private String bigPic1Url = "http://b2.3ddd.ru/media/cache/sky_gallery_preview_mid_resize_ru/model_images/0000/0000/0389/389603.562984cdcb9fa.jpeg";
    private String bigPic2Url = "http://b2.3ddd.ru/media/cache/sky_gallery_preview_mid_resize_ru/model_images/0000/0000/0389/389605.562984cdcc075.jpeg";
    private String bigPic3Url = "http://b2.3ddd.ru/media/cache/sky_gallery_preview_mid_resize_ru/model_images/0000/0000/0389/389607.562984cdcc568.jpeg";

    private String selectedBigPic = "http://b2.3ddd.ru/media/cache/sky_gallery_preview_mid_resize_ru/model_images/0000/0000/0389/389603.562984cdcb9fa.jpeg";
    private Integer seceted = 1;

    private String miniPic1Classes = "";
    private String miniPic2Classes = "Opac30";
    private String miniPic3Classes = "Opac30";

    private Integer modeleRating = 102;
    private Integer rating; 

    private static final long serialVersionUID = -1726709996903225394L;

    private void refreshComanents() {

	if (seceted.equals(1)) {
	    selectedBigPic = bigPic1Url;
	    miniPic1Classes = "";
	    miniPic2Classes = "Opac30";
	    miniPic3Classes = "Opac30";

	} else if (seceted.equals(2)) {
	    selectedBigPic = bigPic2Url;
	    miniPic2Classes = "";
	    miniPic1Classes = "Opac30";
	    miniPic3Classes = "Opac30";

	} else {
	    selectedBigPic = bigPic3Url;
	    miniPic3Classes = "";
	    miniPic2Classes = "Opac30";
	    miniPic1Classes = "Opac30";

	}

    }

    public void onrate(RateEvent rateEvent) {
	modeleRating += rating;
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

    public String getSelectedBigPic() {
	return selectedBigPic;
    }

    public String getMiniPic1Url() {
	return miniPic1Url;
    }

    public String getMiniPic2Url() {
	return miniPic2Url;
    }

    public String getMiniPic3Url() {
	return miniPic3Url;
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

    public Integer getModeleRating() {
	return modeleRating;
    }

    public Integer getRating() {
	return rating;
    }

    public void setRating(Integer rating) {
	this.rating = rating;
    }

}
