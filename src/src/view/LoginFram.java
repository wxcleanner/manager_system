package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class LoginFram extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3518738865467346092L;
	private JPanel contentPane;
    // 修改为表意更明确的变量名
    private JPasswordField passwordField;
    private JTextField userNameField;
    private JComboBox<String> powerComboBox;
    private dbutil dbUtil = new dbutil();
    private UserDao userDao = new UserDao();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginFram frame = new LoginFram();
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
    public LoginFram() {
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

        powerComboBox = new JComboBox<>();
        powerComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"员工", "管理员"}));
        powerComboBox.setBounds(391, 292, 119, 23);
        contentPane.add(powerComboBox);

        JButton btnLogin = new JButton("登录");
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginActionPerformed(e);
            }
        });
        btnLogin.setBounds(370, 403, 97, 23);
        contentPane.add(btnLogin);

        userNameField = new JTextField();
        userNameField.setBounds(390, 156, 139, 21);
        contentPane.add(userNameField);
        userNameField.setColumns(10);

        JLabel lblPower = new JLabel("权限");
        lblPower.setFont(new Font("新宋体", Font.PLAIN, 18));
        lblPower.setBounds(322, 285, 58, 35);
        contentPane.add(lblPower);
        
        JButton btnNewButton = new JButton("注册");
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		new RegFrame().setVisible(true);
        	}
        });
        btnNewButton.setBounds(632, 451, 97, 23);
        contentPane.add(btnNewButton);
    }

    private void loginActionPerformed(ActionEvent e) {
        String userName = userNameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        // 统一验证提示格式，更友好规范
        if (StringUtil.isEmpty(userName)) {
            JOptionPane.showMessageDialog(null, "请输入用户名");
            return;
        }
        if (StringUtil.isEmpty(password)) {
            JOptionPane.showMessageDialog(null, "请输入密码");
            return;
        }

        String power = null;
        String juris = (String) powerComboBox.getSelectedItem();
        User user = new User(userName, password, power);
        Connection con = null;
        // 修正权限设置逻辑的语法错误（去掉多余分号）
        if (juris.equals("管理员")) {
            user.setPower("管理员");
            try {
                con = dbUtil.getCon();
                User currentUser = userDao.login(con, user);
                if (currentUser!= null) {
                    dispose();
                    new AdminMainFrame().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "用户名或者密码错误，请重新输入");
                }
            } catch (Exception ex) {
                // 优化异常提示信息，更准确反映问题
                ex.printStackTrace();
            } finally {
                try {
                    dbUtil.closeCon(con);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        } else if (juris.equals("员工")) {
            user.setPower("员工");
            try {
                con = dbUtil.getCon();
                User currentUser = userDao.login(con, user);
                if (currentUser!= null) {
                    dispose();
                    new UserMainFrame().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "用户名或者密码错误，请重新输入");
                }
            } catch (Exception ex) {
                // 优化异常提示信息，更准确反映问题
                ex.printStackTrace();
            } finally {
                try {
                    dbUtil.closeCon(con);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

       
    
    }
}