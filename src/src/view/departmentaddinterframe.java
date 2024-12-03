package view;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection; 
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import model.Department;
import util.StringUtil;
import util.dbutil;
import dao.DepartmentDao;
public class departmentaddinterframe extends JInternalFrame {
    private JLabel lblNewLabel_1;
    private JTextField depart_nameTxt;
    private JTextArea depart_descTxt;
    private dbutil dbutill = new dbutil();
    private DepartmentDao departmentDao = new DepartmentDao();

    /**
     * 启动应用程序，在事件分发线程中创建并显示部门添加内部窗口
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    departmentaddinterframe frame = new departmentaddinterframe();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 创建部门添加内部窗口的构造函数
     */
    public departmentaddinterframe() {
        setIconifiable(true);
        setClosable(true);
        setTitle("部门添加");
        setBounds(100, 100, 626, 548);

        JLabel lblNewLabel = new JLabel("部门名称");
        lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 18));

        lblNewLabel_1 = new JLabel("部门描述");
        lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 18));

        depart_nameTxt = new JTextField();
        depart_nameTxt.setColumns(10);

        depart_descTxt = new JTextArea();
        depart_descTxt.setFont(new Font("宋体", Font.PLAIN, 18));

        JButton btnNewButton = new JButton("添加");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               resetAddActionPerformed();
            }
        });

        JButton btnNewButton_1 = new JButton("重置");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                departmentAddActionPerformed();
            }
        });

        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(115)
        			.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
        				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
        					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)))
        			.addGap(28)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addComponent(depart_nameTxt, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
        				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
        					.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
        					.addComponent(depart_descTxt, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap(183, Short.MAX_VALUE))
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(144)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(depart_nameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblNewLabel))
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(52)
        					.addComponent(lblNewLabel_1))
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(42)
        					.addComponent(depart_descTxt, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)))
        			.addPreferredGap(ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
        			.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnNewButton)
        				.addComponent(btnNewButton_1))
        			.addGap(86))
        );
        getContentPane().setLayout(groupLayout);

    }


private void resetAddActionPerformed() {
		// TODO 自动生成的方法存根
	 resetNull();
	}


	private void departmentAddActionPerformed() {
        String depart_name = this.depart_nameTxt.getText();
        String depart_desc = this.depart_descTxt.getText();
        if (StringUtil.isEmpty(depart_name)) {
            JOptionPane.showMessageDialog(null, "部门名称不能为空");
            return;
        }
        if (StringUtil.isEmpty(depart_desc)) {
            JOptionPane.showMessageDialog(null, "部门描述不能为空");
            return;
        }
        Department department = new Department(depart_name, depart_desc);
         Connection con=null;
        try {
             con = dbutill.getCon();
            int addnum = departmentDao.add(con,department);
            if (addnum == 1) {
                JOptionPane.showMessageDialog(null, "添加成功");
                resetNull();
            } else {
                JOptionPane.showMessageDialog(null, "添加失败");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "数据库操作出现错误：" + e.getMessage());
        } finally {
           try {
			dbutill.closeCon(con);
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
        }
    }

    private Connection getConnection() throws Exception {
        return dbutill.getCon();
    }

    private void closeConnection() {
        try {
            dbutill.closeCon(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void resetNull() {
        this.depart_nameTxt.setText("");
        this.depart_descTxt.setText("");
    }
}