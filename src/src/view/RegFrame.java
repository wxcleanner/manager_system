package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.channels.NonWritableChannelException;
import java.sql.Connection;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dao.UserDao;
import model.User;
import util.StringUtil;
import util.dbutil;

public class RegFrame extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3518738865467346092L;
	private JPanel contentPane;
    // 修改为表意更明确的变量名
    private JPasswordField passwordField;
    private JTextField userNameField;
    private dbutil dbUtil = new dbutil();
    private UserDao userDao = new UserDao();
    private JPasswordField passwordField_1;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RegFrame frame = new RegFrame();
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
    public RegFrame() {
        setTitle("员工信息管理系统1.0");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1366, 768);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(192, 192, 192));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        // 修改标签变量名为更清晰的名称
        JLabel lblUserName = new JLabel("账号");
        lblUserName.setFont(new Font("宋体", Font.PLAIN, 18));
        lblUserName.setBounds(322, 129, 58, 73);
        contentPane.add(lblUserName);

        JLabel lblPassword = new JLabel("密码");
        lblPassword.setFont(new Font("宋体", Font.PLAIN, 18));
        lblPassword.setBounds(320, 212, 58, 54);
        contentPane.add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setBounds(391, 230, 138, 21);
        contentPane.add(passwordField);

        JButton btnLogin = new JButton("登录");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               regActionPerformed(e);
            }
        });
        btnLogin.setBounds(381, 368, 97, 23);
        contentPane.add(btnLogin);

        userNameField = new JTextField();
        userNameField.setBounds(390, 156, 139, 21);
        contentPane.add(userNameField);
        userNameField.setColumns(10);
        
        JLabel lblNewLabel = new JLabel("注册账号");
        lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 18));
        lblNewLabel.setBounds(391, 85, 276, 37);
        contentPane.add(lblNewLabel);
        
        JLabel lblPassword_1 = new JLabel("确认密码");
        lblPassword_1.setFont(new Font("宋体", Font.PLAIN, 12));
        lblPassword_1.setBounds(322, 267, 58, 54);
        contentPane.add(lblPassword_1);
        
        passwordField_1 = new JPasswordField();
        passwordField_1.setBounds(391, 284, 138, 21);
        contentPane.add(passwordField_1);
    }
//注册时间处理
	private void regActionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		String userName=this.userNameField.getText();
		String password=new String(this.passwordField.getPassword());
		String re_password=new String(this.passwordField_1.getPassword());
		 if (StringUtil.isEmpty(userName)) {
	            JOptionPane.showMessageDialog(null, "请输入用户名");
	            return;
	        }
		 if (StringUtil.isEmpty(password)) {
	            JOptionPane.showMessageDialog(null, "请输入密码");
	            return;
	        }
		 if (StringUtil.isEmpty(re_password)) {
	            JOptionPane.showMessageDialog(null, "请输入确认密码");
	            return;
	        }
		 if(password.equals(re_password))
		 {
			 Connection con=null;
			 User user=new User(userName,password);
			 try {
				con=dbUtil.getCon();
				int addnum=userDao.addUser(con, user);
				if(addnum==1)
				{
					JOptionPane.showMessageDialog(null, "注册成功");
				}
				else {
					JOptionPane.showMessageDialog(null, "注册失败");
				}
			} catch (Exception e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
			 finally {
				 try {
					dbUtil.closeCon(con);
				} catch (Exception e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
		 }
		 else
		 {
			 JOptionPane.showMessageDialog(null, "用户两次密码不一致");
			 return;
		 }
	}
}