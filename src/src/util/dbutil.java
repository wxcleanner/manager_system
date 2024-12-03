package util;
import java.sql.Connection;
import java.sql.DriverManager;
public class dbutil {
 private String dbUrl="jdbc:mysql://localhost:3306/infosystem";//数据库地址；
 private String dbUsername="root";
 private String dbPassword="123456";
 private String jdbcName="com.mysql.jdbc.Driver";

 public Connection getCon()throws Exception
 {
	 Class.forName(jdbcName);
	 Connection con=DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
	 return con;
 }
 public void closeCon(Connection con) throws Exception
 {
	 if(con!=null)
	 {
		 con.close();
	 }
 }
 	public static void main(String[] args)
 	{
 		dbutil dbutil=new dbutil();
 		try
 		{
 			dbutil.getCon();
 			System.out.println("数据库连接成功");
 		}catch (Exception e) {
			// TODO: handle exception
 			e.printStackTrace();
 			System.out.println("数据库连接失败");
		}
 	}
}
