package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Department;
import util.StringUtil;
public class DepartmentDao {
	public int add(Connection con, Department department) throws Exception {
	    String sql = "insert into department values(null,?,?)";
	        PreparedStatement pstmt = con.prepareStatement(sql);
	        //pstmt.setInt(1, department.getId());
	        pstmt.setString(1, department.getDepart_name());
	        pstmt.setString(2, department.getDepart_desc());
	        // 假设Department类中有id属性用于标识部门
	        return pstmt.executeUpdate();
	}
	public ResultSet list(Connection con, Department department) throws Exception{
	    StringBuffer strb = new StringBuffer("select * from department");
	    if (StringUtil.isNotEmpty(department.getDepart_name())) {
	        
	        strb.append(" and depart_name like '%"+department.getDepart_name()+"%'");
	    }
	    PreparedStatement pstmt = con.prepareStatement(strb.toString().replace("and", "where"));
	    return pstmt.executeQuery();
	}
	public int delete(Connection con,String id)throws Exception
	{
		String sql="delete from department where id =?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,id);
		return pstmt.executeUpdate();
	}
	
	public int update(Connection con,Department department) throws Exception
	{
		String sql="update department set depart_name=?,depart_desc=?where id =?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, department.getDepart_name());
		pstmt.setString(2, department.getDepart_desc());
		pstmt.setInt(3, department.getId());
		return pstmt.executeUpdate();
	}
	}
