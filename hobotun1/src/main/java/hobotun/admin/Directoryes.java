package hobotun.admin;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.TreeNode;

import hobotun.core.ParamsForQuery;
import hobotun.db.DBUtil;
import hobotun.db.category.table.Category2Tbl;
import hobotun.db.category.table.CategoryTbl;
import hobotun.db.format.FormatDao;
import hobotun.db.format.table.FormatTabl;
import hobotun.model.ModelCategory;

@ManagedBean(name = "directoryes")
@ViewScoped
public class Directoryes {

	private TreeNode root;
	private DocumentCategory selectedCategoryTree;

	private TreeNode selectedNode;

	@ManagedProperty("#{categoryTree}")
	private CategoryTree categoryTree;

	private List<FormatTabl> formats;
	private FormatDao formatDao;
	private String nmCategory;
	private String nmFormat;

	@ManagedProperty("#{modelCategory}")
	private ModelCategory modelCategory;

	private Category2Tbl category = new Category2Tbl();
	private CategoryTbl subCategory = new CategoryTbl();

	public Category2Tbl getCategory() {
		return category;
	}

	public void setCategory(Category2Tbl category) {
		this.category = category;
	}

	public void setModelCategory(ModelCategory modelCategory) {
		this.modelCategory = modelCategory;
	}

	public void onSaveCategory() {
		modelCategory.addCategory(category);
	}

	public void updateDialog() {
		try {
			if (selectedNode != null) {
				selectedCategoryTree = (DocumentCategory) selectedNode.getData();
			}
		} catch (Exception e) {
			selectedCategoryTree.setName("");
		}
	}

	public void onSaveSubCategory() {
		subCategory.setId_category_p(selectedCategoryTree.getId());
		modelCategory.addSubCategory(subCategory);
	}

	@PostConstruct
	public void init() {
		root = categoryTree.createDocuments();
		formatDao = DBUtil.getInstance().getBean("formatDao", FormatDao.class);

		fill();
	}

	public Directoryes() {

	}

	public String getNmCategory() {
		return nmCategory;
	}

	public void setNmCategory(String nmCategory) {
		this.nmCategory = nmCategory;
	}

	public CategoryTree getCategoryTree() {
		return categoryTree;
	}

	public void setCategoryTree(CategoryTree categoryTree) {
		this.categoryTree = categoryTree;
	}

	private void fill() {
		setFormats(formatDao.getAllFormat());
	}

	public void onSaveFormat() {
		ParamsForQuery inParam = new ParamsForQuery();
		inParam.setParam("nmFormat", nmFormat);

		formatDao.insertFormat(inParam.getAllParam());

		fill();
	}

	public List<FormatTabl> getFormats() {
		return formats;
	}

	public void setFormats(List<FormatTabl> formats) {
		this.formats = formats;
	}

	public String getNmFormat() {
		return nmFormat;
	}

	public void setNmFormat(String nmFormat) {
		this.nmFormat = nmFormat;
	}

	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

	public DocumentCategory getSelectedCategoryTree() {
		return selectedCategoryTree;
	}

	public void setSelectedCategoryTree(DocumentCategory selectedCategoryTree) {
		this.selectedCategoryTree = selectedCategoryTree;
	}

	public TreeNode getSelectedNode() {
		return selectedNode;
	}

	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}

	public CategoryTbl getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(CategoryTbl subCategory) {
		this.subCategory = subCategory;
	}

}
