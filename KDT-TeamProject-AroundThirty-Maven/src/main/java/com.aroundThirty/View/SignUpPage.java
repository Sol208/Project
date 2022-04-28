package com.aroundThirty.View;

import javax.swing.*;
import java.lang.String;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;
import com.aroundThirty.model.UserDao;
import com.aroundThirty.model.UserDto;
import static com.aroundThirty.Resource.FR.*;
import static com.aroundThirty.Resource.BR.*;

public class SignUpPage extends JFrame {

    JPanel contentPane;
    JPanel lblJoin;
    JButton joinCompleteBtn;
    JButton cancelBtn;
    JPanel id;
    JButton lblidcfBtn;
    JPanel password;
    JPanel name;
    JPanel phone;
    JPanel email;



    public SignUpPage() {

        setSize(480, 640);
        setVisible(true);

        setLocationRelativeTo(null);

        contentPane = new JPanel() {

            public void paint(Graphics g) {

                g.drawImage(signbgImg.getImage(), 0, 0, null);
                setOpaque(false);
                super.paint(g);
            }
        };
        add(contentPane);

        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel lblJoinPan = new JPanel();
        JLabel signlbl = new JLabel("회원가입");
        lblJoinPan.add(signlbl);
        Font f1 = new Font("돋움체", Font.BOLD, 40);
        signlbl.setFont(f1);
        lblJoinPan.setBounds(109, 41, 201, 50);
        lblJoinPan.setOpaque(false);
        contentPane.add(lblJoinPan);

        JPanel labelPan = new JPanel();
        labelPan.add(new JLabel("ID"));
        labelPan.setBounds(45, 116, 90, 20);
        contentPane.add(labelPan);
        JTextField id = new JTextField(20);
        id.setBounds(125, 113, 180, 35);
        contentPane.add(id);
        labelPan.setOpaque(false);

        JPanel lblPWPan = new JPanel();
        lblPWPan.add(new JLabel("PW"));
        lblPWPan.setBounds(45, 163, 90, 20);
        contentPane.add(lblPWPan);
        JPasswordField pw = new JPasswordField(20);
        pw.setBounds(125, 156, 180, 35);
        contentPane.add(pw);
        lblPWPan.setOpaque(false);

        JPanel lblNamePan = new JPanel();
        lblNamePan.add(new JLabel("NAME"));
        lblNamePan.setBounds(45, 210, 90, 20);
        contentPane.add(lblNamePan);
        JTextField name = new JTextField(10);
        name.setBounds(125, 203, 180, 35);
        contentPane.add(name);
        lblNamePan.setOpaque(false);

        JPanel lblPhonePan = new JPanel();
        lblPhonePan.add(new JLabel("PHONE"));
        lblPhonePan.setBounds(45, 257, 90, 20);
        contentPane.add(lblPhonePan);
        JTextField phone = new JTextField(15);
        phone.setBounds(125, 250, 180, 35);
        contentPane.add(phone);
        lblPhonePan.setOpaque(false);

        JPanel lblEmailPan = new JPanel();
        lblEmailPan.add(new JLabel("E-mail"));
        lblEmailPan.setBounds(45, 304, 90, 20);
        contentPane.add(lblEmailPan);
        JTextField email = new JTextField(20);
        email.setBounds(125, 297, 180, 35);
        contentPane.add(email);
        lblEmailPan.setOpaque(false);

        lblidcfBtn = new JButton("ID 확인");
        lblidcfBtn.setBounds(345, 113, 80, 20);
        contentPane.add(lblidcfBtn);
        lblidcfBtn.setBackground(Color.ORANGE);

        joinCompleteBtn = new JButton("회원가입완료");
        joinCompleteBtn.setBounds(124, 363, 110, 29);
        contentPane.add(joinCompleteBtn);
        joinCompleteBtn.setBackground(Color.ORANGE);

        cancelBtn = new JButton("취소");
        cancelBtn.setBounds(235, 363, 65, 29);
        contentPane.add(cancelBtn);
        cancelBtn.setBackground(Color.ORANGE);


        setVisible(true);

        joinCompleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String label = id.getText();
                String lblPW = new String(pw.getPassword());
                String lblName = name.getText();
                String lblPhone = phone.getText();
                String lblEmail = email.getText();

                if (id.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "아이디를 입력해 주세요.", title, JOptionPane.WARNING_MESSAGE);
                    id.grabFocus();
                    return;

                } else if (pw.getPassword() == null) {
                    JOptionPane.showMessageDialog(null, "비밀번호를 입력해 주세요.", title, JOptionPane.WARNING_MESSAGE);
                    pw.grabFocus();
                    return;

                } else if (name.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "이름을 입력해 주세요.", title, JOptionPane.WARNING_MESSAGE);
                    name.grabFocus();
                    return;

                } else if (phone.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "휴대폰 을 입력해 주세요.", title, JOptionPane.WARNING_MESSAGE);
                    phone.grabFocus();
                    return;

                } else if (email.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "e-mail 을 입력해 주세요.", title, JOptionPane.WARNING_MESSAGE);
                    email.grabFocus();
                    return;
                }
                if (signNum == 1) {

                    String nId = id.getText();
                    String nPw = String.valueOf(pw.getPassword());
                    String nName = name.getText();
                    String nPhone = phone.getText();
                    String nEmail = email.getText();
                    int nAuth = 1;

                    userDto = new UserDto(nId, nPw, nName, nPhone, nEmail, nAuth);
                    UserDao.userInput(userDto);

                    JOptionPane.showMessageDialog(null, "회원가입이 완료 되었습니다!", title, JOptionPane.INFORMATION_MESSAGE);

                    dispose();
                    signNum = 0;
                }else {
                    JOptionPane.showMessageDialog(null,"아이디 중복 확인을 해주세요!",title,JOptionPane.WARNING_MESSAGE);
                }
            }
        });



        lblidcfBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserDao dao = new UserDao();
                try {
                    if (dao.userSelectById(new UserDto(id.getText())) != null) {
                        JOptionPane.showMessageDialog(null, "사용 할 수 없는 ID 입니다!", title, JOptionPane.WARNING_MESSAGE);
                        id.setText("");

                        signNum = 0;
                        id.revalidate();
                        id.repaint();

                    } else {
                        JOptionPane.showMessageDialog(null, "사용 가능 한 ID 입니다!", title, JOptionPane.INFORMATION_MESSAGE);
                        signNum = 1;

                    }
                } catch (HeadlessException e1) {
                    e1.printStackTrace();
                }
            }
        });

        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                dispose();
            }
        });


        Dimension frameSize = getSize();
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();

        setLocation((windowSize.width - frameSize.width) / 2,
                (windowSize.height - frameSize.height) / 2); //화면 중앙에 띄우기


        setResizable(false);
        setVisible(true);
    }
}

