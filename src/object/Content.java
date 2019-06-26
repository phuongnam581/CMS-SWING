package object;

import java.util.Date;

public class Content {
	int id;
	String title;
	String brief;
	String content;
	Date createdDate;
	Date updateTime;
	int sort;
	int authorId;
	public Content(int id, String title, String brief, String content, Date createdDate, Date updateTime, int sort,
			int authorId) {
		super();
		this.id = id;
		this.title = title;
		this.brief = brief;
		this.content = content;
		this.createdDate = createdDate;
		this.updateTime = updateTime;
		this.sort = sort;
		this.authorId = authorId;
	}
	public Content() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public int getSort() {
		return sort;
	}
	public void setSort(int sort) {
		this.sort = sort;
	}
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	public static void main(String[] args) {
		Date d = new Date();
		Content c  = new Content(10, "a", "b","c",d,d,9,11);
		System.out.println(c.createdDate);
	}
}
