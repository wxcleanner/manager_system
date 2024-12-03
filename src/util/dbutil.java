package util;
import java.sql.Connection;
import java.sql.DriverManager;
public class dbutil {
 private static String dbUrl="jdbc:mysql://localhost:3306/infosystem";//数据库地址；
 private static String dbUsername="root";
 private static String dbPassword="123456";
 private static String jdbcName="com.mysql.jdbc.Driver";

 public static Connection getCon()throws Exception
 {
	 Class.forName(jdbcName);
	 Connection con=DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
	 return con;
 }
 public static  void closeCon(Connection con) throws Exception
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
