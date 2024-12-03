package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import model.Department;
public class DepartmentDao {
	public int add(Connection con, Department department) throws Exception {
	    String sql = "insert into department values(null,?,?)";
	        PreparedStatement pstmt = con.prepareStatement(sql);
	        pstmt.setString(1, department.getDepart_name());
	        pstmt.setString(2, department.getDepart_desc());
	        pstmt.setInt(3, department.getId()); // 假设Department类中有id属性用于标识部门
	        return pstmt.executeUpdate();
	}
}
