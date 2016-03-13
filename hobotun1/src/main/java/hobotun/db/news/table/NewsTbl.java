package hobotun.db.news.table;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewsTbl {
	
	private Long id_news;
	private String vl_news;
	private Integer nn_count_view;
	private String vl_news_short;
	private String vl_topic;
	
	private Integer msg;
	
	private NewsTbl() {
		
	}
	
	public NewsTbl(ResultSet rs) throws SQLException {
		
		id_news = rs.getLong("id_news");
		vl_news = rs.getString("vl_news");
		nn_count_view = rs.getInt("nn_count_view");
		vl_news_short = rs.getString("vl_news_short");
		vl_topic = rs.getString("vl_topic");
		
		msg = rs.getInt("msg");
		
	}
	
	public Long getId_news() {
		return id_news;
	}
	public void setId_news(Long id_news) {
		this.id_news = id_news;
	}
	public String getVl_news() {
		return vl_news;
	}
	public void setVl_news(String vl_news) {
		this.vl_news = vl_news;
	}
	public Integer getNn_count_view() {
		return nn_count_view;
	}
	public void setNn_count_view(Integer nn_count_view) {
		this.nn_count_view = nn_count_view;
	}
	public String getVl_news_short() {
		return vl_news_short;
	}
	public void setVl_news_short(String vl_news_short) {
		this.vl_news_short = vl_news_short;
	}

	public String getVl_topic() {
		return vl_topic;
	}

	public void setVl_topic(String vl_topic) {
		this.vl_topic = vl_topic;
	}

	public Integer getMsg() {
		return msg;
	}

	public void setMsg(Integer msg) {
		this.msg = msg;
	}
	
}
