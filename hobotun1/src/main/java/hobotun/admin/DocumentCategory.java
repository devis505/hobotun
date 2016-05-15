package hobotun.admin;

import java.io.Serializable;

import hobotun.db.category.table.CategoryAll;

public class DocumentCategory implements Serializable{

	private String name;
	private Integer id;
	private Integer id_parent;
	
	private CategoryAll category;

	public DocumentCategory(String name, Integer id, Integer id_parent, CategoryAll category) {
		super();
		this.name = name;
		this.id = id;
		this.id_parent = id_parent;
		this.category = category; 
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId_parent() {
		return id_parent;
	}

	public void setId_parent(Integer id_parent) {
		this.id_parent = id_parent;
	}
	
	public void onDelete() {
		category.onDelete();
	}

	private static final long serialVersionUID = -8873347261984001606L;


}
