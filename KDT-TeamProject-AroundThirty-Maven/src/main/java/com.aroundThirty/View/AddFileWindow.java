package com.aroundThirty.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import static com.aroundThirty.Resource.FR.*;

public class AddFileWindow extends JFrame {

    JFileChooser jfc = new JFileChooser();
    //    JLabel jlb = new JLabel(" ");
    JTextField jTextField = new JTextField(30);
    JButton fileSearch = new JButton("찾기");
    JButton fileClose = new JButton("닫기");
    ImageIcon imageIcon;
    JLabel imgLbl = new JLabel();

    JPanel northPanel = new JPanel();
    JPanel centerPanel = new JPanel();
    JPanel southPanel = new JPanel();
    JPanel southPanel_center = new JPanel();

    public AddFileWindow() {
        setLayout(new BorderLayout());

        add(BorderLayout.NORTH, northPanel);
        northPanel.add(jTextField);

        add(BorderLayout.CENTER, centerPanel);
        centerPanel.add(BorderLayout.CENTER, imgLbl);

        add(BorderLayout.SOUTH, southPanel);
        southPanel.add(BorderLayout.CENTER, southPanel_center);
        southPanel_center.add(fileSearch);
        southPanel_center.add(fileClose);
//        add(jlb);
        setSize(600, 400);
        setVisible(true);


        fileSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == fileSearch) {
                    int ret = jfc.showOpenDialog(fileSearch);
//            if() 취소버튼을 눌렀을 경우 쇼 다열로그 창 뜨기
                    if (ret != JFileChooser.APPROVE_OPTION) { // 사용자가  창을 강제로 닫았거나 취소 버튼을 누른 경우
                        JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다", title,
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    } else if (ret == jfc.APPROVE_OPTION) {
                        File file = jfc.getSelectedFile(); // 선택된 파일 가져오기
                        jTextField.setText(file.getPath());
                        String filePath = "Saved_Image";
                        fileSave(file, filePath, file.getName());
                        addImgPath = (filePath + "/" + file.getName());
                        imageIcon = new ImageIcon(addImgPath);

                        imgLbl.setIcon(imageSetSize(imageIcon, 300, 300));
                        //   jlb.setText("저장 경로 : " + jfc.getSelectedFile().toString());
                    }
                }

            }
        });

        fileClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        Dimension frameSize = getSize();
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width) / 2,
                (windowSize.height - frameSize.height) / 2); //화면 중앙에 띄우기
    }

    private void fileSave(File file, String path, String name) {

        try {
            File file_01 = new File(path); // 디렉토리 정보
            if (!file_01.exists()) {
                file_01.mkdir();
            }
            // 파일 복사
            String filePath = path + "/" + name;

            // 파일 읽기
            FileInputStream fis = new FileInputStream(file);

            // 파일 쓰기
            FileOutputStream fos = new FileOutputStream(filePath);


            int i = 0;
            byte[] buffer = new byte[1024];

            while ((i = fis.read(buffer, 0, 1024)) != -1) { // -1 이라면 End of File
                fos.write(buffer, 0, i);
            }
            fis.close();
            fos.close();

        } catch (Exception ex) {
        }

    }

}