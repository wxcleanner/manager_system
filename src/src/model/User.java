package model;


public class User {
	private int id;
		private String userName;
		private String pssword;
		private String power;
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getPssword() {
			return pssword;
		}
		public void setPssword(String pssword) {
			this.pssword = pssword;
		}
		public String getPower() {
			return power;
		}
		public void setPower(String power) {
			this.power = power;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public User()
		{
			super();
		}
		public User(String userName, String pssword, String power) {
			super();
			this.userName = userName;
			this.pssword = pssword;
			this.power = power;
		}
		public User(String userName, String pssword) {
			super();
			this.userName = userName;
			this.pssword = pssword;
		}
		
}
