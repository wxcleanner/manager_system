package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import model.Post;

//岗位添加
public class PostDao {
   public int add(Connection con,Post post)throws Exception
   {
	   String sql="Insert into post values(null,?,?)";
	   PreparedStatement pstmt=con.prepareStatement(sql);
	   pstmt.setString(1,post.getPost_name());
	   pstmt.setString(2,post.getPost_desc());
	   return pstmt.executeUpdate();
   }
}
