package hobotun.springDB;

public class Model {
    private String nmModel;
    private Integer idModel;

    public String getNmModel() {
	return nmModel;
    }

    public void setNmModel(String nm_model) {
	this.nmModel = nm_model;
    }

    public Integer getIdModel() {
	return idModel;
    }

    public void setIdModel(Integer id_model) {
	this.idModel = id_model;
    }

    public String toString() {
	return "nmModel = " + nmModel + " idModel = " + idModel;
    }

}
