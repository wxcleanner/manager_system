package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import model.Staff;

public class StaffDao {
/**
 *基本信息录入
 */
	public int add(Connection con,Staff staff)throws Exception {
			String sql = "insert into staff_information values(null,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1,staff.getStaff_name());
			pstmt.setString(2,staff.getStaff_id());
			pstmt.setString(3,staff.getSex());
			pstmt.setString(4,staff.getNation());
			pstmt.setString(5,staff.getNative_place());
			pstmt.setString(6,staff.getPolitics_status());
			pstmt.setString(7,staff.getIdNum());
			pstmt.setString(8,staff.getAddress());
			pstmt.setString(9,staff.getPhoneNum());
			return pstmt.executeUpdate();
					
	}
}
