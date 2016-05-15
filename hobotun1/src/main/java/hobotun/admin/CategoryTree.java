package hobotun.admin;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import hobotun.db.DBUtil;
import hobotun.db.category.CategoryDao;
import hobotun.db.category.table.Category2Tbl;
import hobotun.db.category.table.CategoryTbl;

@ManagedBean(name = "categoryTree")
@ApplicationScoped
public class CategoryTree {

	public TreeNode createDocuments() {
		TreeNode root = new DefaultTreeNode(new DocumentCategory("ROOT", null, null, null), null);

		CategoryDao categoryDao = DBUtil.getInstance().getBean("categoryDao", CategoryDao.class);

		for (Category2Tbl category2Tbl : categoryDao.getAllCategory2()) {
			TreeNode category2 = new DefaultTreeNode(new DocumentCategory(category2Tbl.getNm_category_p(),
					category2Tbl.getId_category_p(), null, category2Tbl), root);

			for (CategoryTbl categoryTbl : categoryDao.selectCategoryByIdParent(category2Tbl.getId_category_p())) {
				TreeNode category = new DefaultTreeNode(new DocumentCategory(categoryTbl.getNmCategory(),
						categoryTbl.getId_category_p(), null, categoryTbl), category2);
			}
			
		}

		return root;
	}

}
