package com.aroundThirty.View;

import com.aroundThirty.Controller.MainController;
import com.aroundThirty.Resource.SearchData;
import com.aroundThirty.model.*;
import com.aroundThirty.myframe.MyJFrame;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;

import static com.aroundThirty.Resource.FR.*;
import static com.aroundThirty.Resource.BR.*;
import static com.aroundThirty.model.ReportDao.*;

public class MainView extends MyJFrame {
    public static Container container;

    public MainView() {
        setSize(1440, 900);
        setTitle(title);
        setBackground(pastelYellow);
        setResizable(false);
        displayLayer();

    }

    protected void displayLayer() {
        left_Panel = new LeftPanel();
        report_Right_Panel = new ReportRightPanel();
        missing_Right_Panel = new MissingRightPanel();
        temporary_Right_Panel = new TemporaryRightPanel();
        adopt_Right_Panel = new AdoptRightPanel();
        main_Right_Panel = new MainRightPanel();
        bottom_Panel = new BottomPanel();
        searchRightPanel = new SearchRightPanel();
        mainController = new MainController();
        container = getContentPane();
        switchPan = main_Right_Panel;

        container.add(BorderLayout.SOUTH, bottom_Panel);
        container.add(BorderLayout.WEST, left_Panel);
        container.add(BorderLayout.EAST, main_Right_Panel);
        tabbed_Pane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JTabbedPane tabbed_Pane = (JTabbedPane) e.getSource();
                tabPaneIdx = tabbed_Pane.getSelectedIndex();
                switch (tabPaneIdx) {
                    case 0:
                        tabbed_Pane.remove(searchPage);
//                        MainView.container.add(BorderLayout.EAST, main_Right_Panel);
                        container.remove(switchPan);
                        container.add(BorderLayout.EAST, main_Right_Panel);
                        switchPan = main_Right_Panel;
                        revalidate();
                        repaint();
                        break;
                    case 1:
                        tabbed_Pane.remove(searchPage);
//                        MainView.container.add(BorderLayout.EAST, report_Right_Panel);
                        container.remove(switchPan);
                        container.add(BorderLayout.EAST, report_Right_Panel);
                        switchPan = report_Right_Panel;
                        revalidate();
                        repaint();
                        break;
                    case 2:
                        tabbed_Pane.remove(searchPage);
                        container.remove(switchPan);
                        container.add(BorderLayout.EAST, missing_Right_Panel);
                        switchPan = missing_Right_Panel;
                        revalidate();
                        repaint();
                        break;
                    case 3:
                        tabbed_Pane.remove(searchPage);
                        container.remove(switchPan);
                        container.add(BorderLayout.EAST, temporary_Right_Panel);
                        switchPan = temporary_Right_Panel;
                        revalidate();
                        repaint();
                        break;
                    case 4:
                        tabbed_Pane.remove(searchPage);
                        container.remove(switchPan);
                        container.add(BorderLayout.EAST, adopt_Right_Panel);
                        switchPan = adopt_Right_Panel;
                        revalidate();
                        repaint();
                        break;
                    case 5:
                        container.remove(switchPan);
                        container.add(BorderLayout.EAST, searchRightPanel);
                        switchPan = searchRightPanel;
                        revalidate();
                        repaint();
                        break;
                }
            }
        });
        container.setBackground(pastelYellow);
//        newPost.setEnabled(false);
        setBackground(pastelYellow);

        createMenu();

        Dimension frameSize = getSize();
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width) / 2,
                (windowSize.height - frameSize.height) / 2); //화면 중앙에 띄우기
    }

    @Override
    protected void actionEvent() {
        loginMain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginPage = new LoginPage();
            }
        });
        loginPopup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idTxtFld.getText().trim();
                String pw = String.valueOf(pwTxtFld.getPassword());

                userDto = UserDao.userSelectById(new UserDto(id));
                if (userDto == null) {
                    JOptionPane.showMessageDialog(null, "입력하신 회원정보는 존재하지 않습니다.", title, JOptionPane.ERROR_MESSAGE);
                    idTxtFld.setText("");
                    pwTxtFld.setText("");
                } else {
                    if (id.equals(userDto.getUser_ID()) && pw.equals(userDto.getUser_PW())) {
                        int confirm = JOptionPane.showConfirmDialog(null, "로그인 할까요?", title, JOptionPane.YES_NO_OPTION);
                        if (confirm == JOptionPane.YES_OPTION) {
                            JOptionPane.showMessageDialog(null, "로그인 되었어요 :)", title, JOptionPane.INFORMATION_MESSAGE);
                            loginPage.dispose();
                            bottom_Panel.groupPanRight.remove(loginMain);
                            bottom_Panel.groupPanRight.add(logoutMain, 0);
                            bottom_Panel.revalidate();
                            bottom_Panel.repaint();
                            signNum = 1;
                        } else {
                            JOptionPane.showMessageDialog(null, "취소되었어요", title, JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else if (id.length() == 0 || pw.length() == 0) {
                        JOptionPane.showMessageDialog(null, "ID와 비밀번호를 입력 해주세요.", title, JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "ID 또는 비밀번호가 맞지 않아요 ㅠㅠ", title, JOptionPane.ERROR_MESSAGE);
                        idTxtFld.setText("");
                        pwTxtFld.setText("");
                    }
                }
            }
        });

        searchBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    tabbed_Pane.remove(searchPage);
                    search_Data = new SearchData();
                    tabbed_Pane.addTab("검색", searchPage);
                    left_Panel.revalidate();
                    left_Panel.repaint();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        signUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signUpPage = new SignUpPage();
            }
        });

        logoutMain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "로그아웃 하시나요?", title, JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, "로그아웃 되었어요.", title, JOptionPane.INFORMATION_MESSAGE);
                    bottom_Panel.groupPanRight.remove(logoutMain);
                    bottom_Panel.groupPanRight.add(loginMain, 0);
                    bottom_Panel.revalidate();
                    bottom_Panel.repaint();
                    signNum = 0;
                } else {
                    JOptionPane.showMessageDialog(null, "취소되었어요.", title, JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        dogBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainController.Play(dogSoundPath);
            }
        });

        catBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainController.Play(catSoundPath);
            }
        });
    }
}