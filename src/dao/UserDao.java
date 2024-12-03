package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import model.User;

import java.sql.PreparedStatement;

public class UserDao {
		public User login(Connection con,User user) throws Exception
		{
			User resultUser=null;
			String sql="select*from user where  userName=? and password=? and power=?";
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getPssword());
			pstmt.setString(3, user.getPower());
			ResultSet rs=pstmt.executeQuery();
		
		if(rs.next())
		{
			resultUser=new User();
			resultUser.setId(rs.getInt("id"));
			resultUser.setUserName(rs.getString("userName"));
			resultUser.setPssword(rs.getString("password"));
			resultUser.setPower(rs.getString("power"));
		}
		return resultUser;
		}
		public int addUser(Connection con,User user)throws Exception
		{
			String sql="insert into user values(null,?,?,?)";
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getPssword());
			pstmt.setString(3, "员工");
			return pstmt.executeUpdate();
		}
}
