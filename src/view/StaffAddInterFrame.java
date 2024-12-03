package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import model.Department;
import model.Post;
import model.Staff;
import util.StringUtil;
import util.dbutil;
import dao.DepartmentDao;
import dao.PostDao;
import dao.StaffDao;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StaffAddInterFrame extends JInternalFrame {
	private JTextField staff_nameTxt;
	private JTextField staff_idTxt;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField nationTxt;
	private JTextField nativeTxt;
	private JTextField politicsTxt;
	private JTextField idNumTxt;
	private JTextField phoneNumTxt;
	private JTextField addressTxt;

	
	private dbutil dbUtil = new dbutil();
	private DepartmentDao departmentdao = new DepartmentDao();
	private PostDao postdao = new PostDao();
	private StaffDao Staffdao = new StaffDao();
	
	
	private JRadioButton manjrb;
	private JRadioButton femalejrb;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StaffAddInterFrame frame = new StaffAddInterFrame();
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
	public StaffAddInterFrame() {
		setClosable(true);
		setIconifiable(true);
		getContentPane().setFont(new Font("宋体", Font.PLAIN, 20));
		setTitle("基本信息录入");
		setBounds(100, 100, 551, 404);
		
		JLabel lblNewLabel = new JLabel("姓名");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		
		staff_nameTxt = new JTextField();
		staff_nameTxt.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("工号");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 20));
		
		staff_idTxt = new JTextField();
		staff_idTxt.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("性别");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 20));
		
		manjrb = new JRadioButton("男");
		manjrb.setSelected(true);
		buttonGroup.add(manjrb);
		manjrb.setFont(new Font("宋体", Font.PLAIN, 20));
		
		 femalejrb = new JRadioButton("女");
		buttonGroup.add(femalejrb);
		femalejrb.setFont(new Font("宋体", Font.PLAIN, 20));
		
		JLabel lblNewLabel_3 = new JLabel("民族");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 20));
		
		nationTxt = new JTextField();
		nationTxt.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("政治面貌");
		lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 20));
		
		nativeTxt = new JTextField();
		nativeTxt.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("籍贯");
		lblNewLabel_5.setFont(new Font("宋体", Font.PLAIN, 20));
		
		politicsTxt = new JTextField();
		politicsTxt.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("身份证号");
		lblNewLabel_8.setFont(new Font("宋体", Font.PLAIN, 20));
		
		idNumTxt = new JTextField();
		idNumTxt.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("电话号码");
		lblNewLabel_9.setFont(new Font("宋体", Font.PLAIN, 20));
		
		phoneNumTxt = new JTextField();
		phoneNumTxt.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("地址");
		lblNewLabel_10.setFont(new Font("宋体", Font.PLAIN, 20));
		
		addressTxt = new JTextField();
		addressTxt.setColumns(10);
		
		JButton btnNewButton = new JButton("添加");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				staffAddActionPerformed(e);
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 25));
		
		JButton btnNewButton_1 = new JButton("重置");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValuse();
			}
		});
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 25));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel)
										.addComponent(lblNewLabel_2))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(manjrb)
											.addGap(18)
											.addComponent(femalejrb))
										.addComponent(staff_nameTxt, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_4)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(nativeTxt, 94, 94, 94)))
							.addGap(84)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel_1)
										.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(nationTxt, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
										.addComponent(staff_idTxt, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel_5)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(politicsTxt, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)))
							.addGap(101))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel_9)
								.addComponent(lblNewLabel_8))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(addressTxt, Alignment.LEADING)
								.addComponent(idNumTxt, Alignment.LEADING)
								.addComponent(phoneNumTxt, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
								.addComponent(btnNewButton_1))
							.addContainerGap())))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(32)
					.addComponent(lblNewLabel_10)
					.addContainerGap(536, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(310, Short.MAX_VALUE)
					.addComponent(btnNewButton)
					.addGap(215))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(staff_idTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel)
							.addComponent(staff_nameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(11)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_2)
								.addComponent(manjrb)
								.addComponent(femalejrb)))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel_3))
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(nationTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(politicsTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_5)
						.addComponent(lblNewLabel_4)
						.addComponent(nativeTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_8)
						.addComponent(idNumTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_9)
						.addComponent(phoneNumTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_10)
						.addComponent(addressTxt, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addContainerGap(91, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);


	}
	/**
	 * 基本信息添加事件
	 * @param e
	 */
	private void staffAddActionPerformed(ActionEvent e) {
		String staff_name = this.staff_nameTxt.getText();
		String staff_id = this.staff_idTxt.getText();
		String nation = this.nationTxt.getText();
		String native_place = this.nativeTxt.getText();
		String politics_status = this.politicsTxt.getText();
		String idNum = this.idNumTxt.getText();
		String address = this.addressTxt.getText();
		String phoneNum = this.phoneNumTxt.getText();
		
		if (StringUtil.isEmpty(staff_name)) {
            JOptionPane.showMessageDialog(null, "员工姓名不能为空");
            return ;
        }
        if (StringUtil.isEmpty(staff_id)) {
            JOptionPane.showMessageDialog(null, "员工工号不能为空");
            return ;
        }
        if (StringUtil.isEmpty(nation)) {
            JOptionPane.showMessageDialog(null, "民族不能为空");
            return ;
        }
        if (StringUtil.isEmpty(native_place)) {
            JOptionPane.showMessageDialog(null, "籍贯不能为空");
            return ;
        }
        if (StringUtil.isEmpty(politics_status)) {
            JOptionPane.showMessageDialog(null, "政治面貌不能为空");
            return ;
        }
        if (StringUtil.isEmpty(idNum)) {
            JOptionPane.showMessageDialog(null, "身份证号不能为空");
            return ;
        }
        if (StringUtil.isEmpty(address)) {
            JOptionPane.showMessageDialog(null, "地址不能为空");
            return ;
        }
        if (StringUtil.isEmpty(phoneNum)) {
            JOptionPane.showMessageDialog(null, "电话号码不能为空");
            return ;
        }
           
		String sex = "";
		if(this.manjrb.isSelected()) {
			sex = "男";
		}else if(this.femalejrb.isSelected()) {
			sex = "女";
		}
		
		Staff staff = new Staff( staff_name,  staff_id,  sex,  nation,  native_place,
				 politics_status,  idNum,  address,  phoneNum);
		Connection con = null;
			try {
				con = dbutil.getCon();
				int addnum = Staffdao.add(con,staff);
				if(addnum==1) {
					JOptionPane.showMessageDialog(null, "员工基本信息添加成功！");
					resetValuse();
				}else {
					JOptionPane.showMessageDialog(null, "员工基本信息添加失败！");
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "员工基本信息添加失败！");
			}finally {
				try {
					con = dbutil.getCon();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
	}

/**
 * 重置表单
 */
	private void resetValuse() {
		this.staff_nameTxt.setText("");
		this.staff_idTxt.setText("");
		this.manjrb.setSelected(true);
		this.nationTxt.setText("");
		this.nativeTxt.setText("");
		this.politicsTxt.setText("");
		this.idNumTxt.setText("");
		this.phoneNumTxt.setText("");
		this.addressTxt.setText("");
}
	
	
	
}
