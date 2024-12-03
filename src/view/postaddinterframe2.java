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

import dao.PostDao;
import model.Post;
import util.StringUtil;
import util.dbutil;

@SuppressWarnings("serial")
public class postaddinterframe2 extends JInternalFrame {
    private JLabel lblPostName; // 修改名称，更准确表意岗位名称
    private JTextField postNameTxt;
    private JLabel lblPostDescription; // 修改名称，更准确表意岗位描述
    private JTextArea postDescriptionTxt;
    private dbutil dbutill = new dbutil();
    private PostDao postDao = new PostDao();

    // 启动应用程序，在事件分发线程中创建并显示岗位添加内部窗口
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                postaddinterframe2 frame = new postaddinterframe2();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // 创建岗位添加内部窗口的构造函数
    public postaddinterframe2() {
        setIconifiable(true);
        setClosable(true);
        setTitle("岗位添加");
        setBounds(100, 100, 626, 548);

        lblPostName = new JLabel("岗位名称");
        lblPostName.setFont(new Font("宋体", Font.PLAIN, 18));

        lblPostDescription = new JLabel("岗位描述");
        lblPostDescription.setFont(new Font("宋体", Font.PLAIN, 18));

        postNameTxt = new JTextField();
        postNameTxt.setColumns(10);

        postDescriptionTxt = new JTextArea();
        postDescriptionTxt.setFont(new Font("宋体", Font.PLAIN, 18));

        JButton btnAdd = new JButton("添加");
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateInput()) {
                    Post post = buildPostObject();
                    int result = addPostToDatabase(post);
                    handleAddResult(result);
                }
            }
        });

        JButton btnReset = new JButton("重置");
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetInputFields();
            }
        });

        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                      .addGroup(groupLayout.createSequentialGroup()
                              .addGap(115)
                              .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                      .addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
                                      .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                              .addComponent(lblPostName, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
                                              .addComponent(lblPostDescription, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)))
                              .addGap(28)
                              .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                      .addComponent(postNameTxt, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
                                      .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                              .addComponent(btnReset, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
                                              .addComponent(postDescriptionTxt, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)))
                              .addContainerGap(183, Short.MAX_VALUE))
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                      .addGroup(groupLayout.createSequentialGroup()
                              .addGap(144)
                              .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                      .addComponent(postNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                      .addComponent(lblPostName))
                              .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                      .addGroup(groupLayout.createSequentialGroup()
                                              .addGap(52)
                                              .addComponent(lblPostDescription))
                                      .addGroup(groupLayout.createSequentialGroup()
                                              .addGap(42)
                                              .addComponent(postDescriptionTxt, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)))
                              .addPreferredGap(ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                              .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                      .addComponent(btnAdd)
                                      .addComponent(btnReset))
                              .addGap(86))
        );
        getContentPane().setLayout(groupLayout);
    }

    // 验证输入是否合法
    private boolean validateInput() {
        String postName = postNameTxt.getText().trim();
        String postDescription = postDescriptionTxt.getText().trim();
        if (StringUtil.isEmpty(postName)) {
            JOptionPane.showMessageDialog(null, "岗位名称不能为空");
            return false;
        }
        if (StringUtil.isEmpty(postDescription)) {
            JOptionPane.showMessageDialog(null, "岗位描述不能为空");
            return false;
        }
        // 这里可以添加更多的验证逻辑，比如检查岗位名称长度是否符合数据库要求等
        return true;
    }

    // 构建Post对象
    private Post buildPostObject() {
        String postName = postNameTxt.getText().trim();
        String postDescription = postDescriptionTxt.getText().trim();
        try {
            return new Post(postName, postDescription);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "构建岗位对象出现错误：" + ex.getMessage());
            return null;
        }
    }

    // 将岗位信息添加到数据库并返回影响行数
    private int addPostToDatabase(Post post) {
        Connection con = null;
        try {
            con = dbutill.getCon();
            return postDao.add(con, post);
        } catch (Exception ex) {
            // 根据不同的数据库操作异常情况，可以给出更细化的提示信息
            JOptionPane.showMessageDialog(null, "数据库添加岗位操作出现错误：" + ex.getMessage());
            return -1;
        } finally {
            try {
                dbutill.closeCon(con);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 根据添加结果进行相应提示和操作
    private void handleAddResult(int result) {
        if (result > 0) {
            JOptionPane.showMessageDialog(null, "添加成功");
            resetInputFields();
        } else {
            JOptionPane.showMessageDialog(null, "添加失败");
        }
    }

    // 重置输入框内容
    private void resetInputFields() {
        postNameTxt.setText("");
        postDescriptionTxt.setText("");
    }
}