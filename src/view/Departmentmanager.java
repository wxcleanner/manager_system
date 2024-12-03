package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;

import dao.DepartmentDao;
import model.Department;
import util.StringUtil;
import util.dbutil;

public class Departmentmanager extends JInternalFrame {
	private JTable depart_table;
	 private dbutil dbutill = new dbutil();
	    private DepartmentDao departmentDao = new DepartmentDao();
	    private JTextField textField;
	    private JTextField ideTxt;
	    private JTextField deoart_nameTxt;
	    private JTextArea depart_descTxt;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Departmentmanager frame = new Departmentmanager();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Departmentmanager() {
		addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
			}
			public void ancestorMoved(AncestorEvent event) {
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		setIconifiable(true);
		setClosable(true);
		setTitle("部门维修");
		setBounds(100, 100, 487, 780);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
			}
			public void ancestorMoved(AncestorEvent event) {
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, " \u641C\u7D22\u6761\u4EF6", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JButton btnNewButton = new JButton(" 查询");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				departmentsearch(e);
			}
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, " \u8868\u5355\u64CD\u4F5C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(332, Short.MAX_VALUE)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addGap(42))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(64)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 347, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 335, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 340, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(60, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(42)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
					.addGap(46)
					.addComponent(btnNewButton)
					.addGap(40)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 299, GroupLayout.PREFERRED_SIZE)
					.addGap(37))
		);
		
		JLabel lblNewLabel_1 = new JLabel("编号");
		
		ideTxt = new JTextField();
		ideTxt.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("部门名称");
		
		deoart_nameTxt = new JTextField();
		deoart_nameTxt.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("部门描述");
		
		JButton btnNewButton_1 = new JButton("修改");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				departmentupdateselectionperformed(e);
			}
		});
		
		JButton btnNewButton_2 = new JButton("删除");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pepartmentdeleteactionperformed(e);
			}
		});
		
		depart_descTxt = new JTextArea();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(26)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_panel_1.createSequentialGroup()
								.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(deoart_nameTxt, GroupLayout.PREFERRED_SIZE, 139, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel_1.createSequentialGroup()
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(ideTxt)))
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_panel_1.createSequentialGroup()
								.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel_1.createSequentialGroup()
								.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(depart_descTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(64, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_1)
						.addComponent(ideTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(deoart_nameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(depart_descTxt, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton_2))
					.addContainerGap(23, Short.MAX_VALUE))
		);
		panel_1.setLayout(gl_panel_1);
		
		JLabel lblNewLabel = new JLabel("部门名称");
		
		textField = new JTextField();
		textField.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(18, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		depart_table = new JTable();
		depart_table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				depart_tablemousepressed(e);
			}
		});
		depart_table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u7F16\u53F7", "\u90E8\u95E8\u540D\u79F0", "\u90E8\u95E8\u63CF\u8FF0"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				true, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		depart_table.getColumnModel().getColumn(0).setResizable(false);
		depart_table.getColumnModel().getColumn(1).setResizable(false);
		depart_table.getColumnModel().getColumn(2).setResizable(false);
		scrollPane.setViewportView(depart_table);
		getContentPane().setLayout(groupLayout);
		this.filltable(new Department());

	}

	private void pepartmentdeleteactionperformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		String id=this.ideTxt.getText();
		 if (StringUtil.isEmpty(id)) {
	            JOptionPane.showMessageDialog(null, " 请选择要修改的内容");
	            return ;
	        }
		 int n=JOptionPane.showConfirmDialog(null, "确定要删除该条记录吗");
		if(n==0) {
			Connection con=null;
			try {
				con=dbutill.getCon();
				int num=departmentDao.delete(con, id);
				if(num==1)
				{
					JOptionPane.showMessageDialog(null, "删除成功");
					this.resetnull();
					this.filltable(new Department());
				}
				else {
					JOptionPane.showMessageDialog(null, "删除失败");
				}
			} catch (Exception e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "删除失败");
			}
			finally {
				try {
					dbutill.closeCon(con);
				} catch (Exception e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
		}
	}

	private void departmentupdateselectionperformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		String id=this.ideTxt.getText();
		String depart_name=this.deoart_nameTxt.getText();
		String depart_desc=this.depart_descTxt.getText();
		 if (StringUtil.isEmpty(id)) {
	            JOptionPane.showMessageDialog(null, " 请选择要修改的内容");
	            return ;
	        }
		if (StringUtil.isEmpty(id)) {
            JOptionPane.showMessageDialog(null, "部门名称不能为空！");
            return ;
        }
        if (StringUtil.isEmpty(id)) {
            JOptionPane.showMessageDialog(null, "部门描述不能为空！");
            return ;
        }
		Department department=new Department(Integer.parseInt(id),depart_name,depart_desc);
		Connection con=null;
		try {
			con=dbutill.getCon();
			int num=departmentDao.update(con, department);
			if(num==1)
			{
				JOptionPane.showMessageDialog(null, "修改成功");
				this.resetnull();
				this.filltable(new Department());
			}
			else {
				JOptionPane.showMessageDialog(null, "修改失败");
			}
		} catch (Exception e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		finally {
			try {
				dbutill.closeCon(con);
			} catch (Exception e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
		}
	}
private void resetnull()
{
	this.ideTxt.setText("");
	this.deoart_nameTxt.setText("");
	this.depart_descTxt.setText("");
}
	private void depart_tablemousepressed(MouseEvent e) {
		// TODO 自动生成的方法存根
		int row=this.depart_table.getSelectedRow();
		this.ideTxt.setText((String) this.depart_table.getValueAt(row, 0));
		this.deoart_nameTxt.setText((String) this.depart_table.getValueAt(row, 1));
		this.depart_descTxt.setText((String) this.depart_table.getValueAt(row, 2));
		
		
	}

	private void departmentsearch(ActionEvent e) {
		// TODO 自动生成的方法存根
		String s_department_name=this.textField.getText();
		Department department=new Department();
		department.setDepart_name(s_department_name);
		this.filltable(department);
	}

	private void filltable(Department department)
	{
		DefaultTableModel dtm=(DefaultTableModel)this.depart_table.getModel();
		dtm.setRowCount(0);
		Connection con=null;
		try {
			con=dbutill.getCon();
		   ResultSet rs=departmentDao.list(con,department);
		while(rs.next())
		{
			Vector v=new Vector<>();
			v.add(rs.getString("id"));
			v.add(rs.getString("depart_name"));
			v.add(rs.getString("depart_desc"));
			dtm.addRow(v);
		}
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		finally
		{
			try {
				dbutill.closeCon(con);
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
}
