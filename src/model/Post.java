package model;

public class Post {
private String post_name;
private String post_desc;
private int id;
public String getPost_name() {
	return post_name;
}
public void setPost_name(String post_name) {
	this.post_name = post_name;
}
public String getPost_desc() {
	return post_desc;
}
public void setPost_desc(String post_desc) {
	this.post_desc = post_desc;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public Post(String post_name, String post_desc) {
	super();
	this.post_name = post_name;
	this.post_desc = post_desc;
}
public Post() {
	super();
	// TODO 自动生成的构造函数存根
}
@Override
public String toString() {
	return post_name;
}

}
