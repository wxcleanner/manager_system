package model;

public class Staff {

	private int id;
	private String staff_name;
	private String staff_id;
	private String sex;
	private String nation;
	private String native_place;
	private String politics_status;
	private String idNum;
	private String address;
	private String phoneNum;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStaff_name() {
		return staff_name;
	}
	public void setStaff_name(String staff_name) {
		this.staff_name = staff_name;
	}
	public String getStaff_id() {
		return staff_id;
	}
	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getNative_place() {
		return native_place;
	}
	public void setNative_place(String native_place) {
		this.native_place = native_place;
	}
	public String getPolitics_status() {
		return politics_status;
	}
	public void setPolitics_status(String politics_status) {
		this.politics_status = politics_status;
	}
	public String getIdNum() {
		return idNum;
	}
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Staff(String staff_name, String staff_id, String sex, String nation, String native_place,
			String politics_status, String idNum, String address, String phoneNum) {
		super();
		this.staff_name = staff_name;
		this.staff_id = staff_id;
		this.sex = sex;
		this.nation = nation;
		this.native_place = native_place;
		this.politics_status = politics_status;
		this.idNum = idNum;
		this.address = address;
		this.phoneNum = phoneNum;
	}
	public Staff() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	
}
