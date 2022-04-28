package com.aroundThirty.myframe;

import com.aroundThirty.View.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.aroundThirty.Resource.FR.*;

public abstract class MyJFrame extends JFrame {
    public MyJFrame() {
        this("My JFrame", 200, 200);
    }

    public MyJFrame(String title, int w, int h) {
        // 닫기 버튼을 눌렀을 때 강제 종료 이벤트 처리
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // 타이틀 지정
        setTitle("첫번째 스윙 실습");
        // 크기 설정
        setSize(w, h);

        displayLayer();
        actionEvent();
    }

    public void createMenu() {
        JMenuBar mb = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem saveMenu = new JMenuItem("Save");
        JMenuItem importMenu = new JMenuItem("Import");
        JMenuItem exportMenu = new JMenuItem("Export");
        JMenuItem openMenu = new JMenuItem("Open");
        JMenuItem settingMenu = new JMenuItem("Setting");
        JMenuItem exitMenu = new JMenuItem("Close");

        JMenu editMenu = new JMenu("Edit");
        JMenuItem editDetailMenu = new JMenuItem("Edit");
        JMenu viewMenu = new JMenu("View");
        JMenuItem viewDetailMenu = new JMenuItem("View");
        JMenu toolMenu = new JMenu("Tool");
        JMenuItem vgg16 = new JMenuItem("vgg16");
        JMenuItem cnn = new JMenuItem("cnn");

        JMenu helpMenu = new JMenu("Help");
        JMenuItem helpDetailMenu = new JMenuItem("Help");
        JMenuItem aboutMenu = new JMenuItem("About");
        JMenuItem sourceMenu = new JMenuItem("Source");
        aboutMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                introduce_Page = new IntroducePage();
            }
        });
        sourceMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                source_Page = new SourcePage();
            }
        });
        exitMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "종료할까요?", title, JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    closePage = new ClosePage();
                    Thread thread = new Thread(closePage);
                    thread.start();
                } else {
                    JOptionPane.showMessageDialog(null, "취소되었어요.", title, JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        saveMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "준비 중이에요 ㅠㅠ", title, JOptionPane.PLAIN_MESSAGE);
            }
        });

        openMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "준비 중이에요 ㅠㅠ", title, JOptionPane.PLAIN_MESSAGE);
            }
        });

        importMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "준비 중이에요 ㅠㅠ", title, JOptionPane.PLAIN_MESSAGE);
            }
        });

        exportMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "준비 중이에요 ㅠㅠ", title, JOptionPane.PLAIN_MESSAGE);
            }
        });

        settingMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "준비 중이에요 ㅠㅠ", title, JOptionPane.PLAIN_MESSAGE);
            }
        });

        editDetailMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "준비 중이에요 ㅠㅠ", title, JOptionPane.PLAIN_MESSAGE);
            }
        });

        viewDetailMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CircleGraphView();
            }
        });

        vgg16.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FirstWebView();
            }
        });

        cnn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SecondWebView();
            }
        });

        helpDetailMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "준비 중이에요 ㅠㅠ", title, JOptionPane.PLAIN_MESSAGE);
            }
        });

        fileMenu.add(saveMenu);
        fileMenu.add(openMenu);
        fileMenu.addSeparator();
        fileMenu.add(importMenu);
        fileMenu.add(exportMenu);
        fileMenu.addSeparator();
        fileMenu.add(settingMenu);
        fileMenu.addSeparator();
        fileMenu.add(exitMenu);

        helpMenu.add(helpDetailMenu);
        helpMenu.addSeparator();
        helpMenu.add(sourceMenu);
        helpMenu.addSeparator();
        helpMenu.add(aboutMenu);

        editMenu.add(editDetailMenu);
        viewMenu.add(viewDetailMenu);
        toolMenu.add(vgg16);
        toolMenu.add(cnn);


        mb.add(fileMenu);
        mb.add(editMenu);
        mb.add(viewMenu);
        mb.add(toolMenu);
        mb.add(helpMenu);

        this.setJMenuBar(mb);
        mb.setBorderPainted(false);
        mb.setBackground(pastelGreen);
    }

    protected abstract void displayLayer();

    protected abstract void actionEvent();
}