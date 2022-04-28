package com.aroundThirty.View;

import javax.swing.*;
import java.awt.*;

import static com.aroundThirty.Resource.FR.*;

public class LoginPage extends JFrame {

    JPanel centerPan = new JPanel(new BorderLayout());
    JPanel findPan;
    JPanel idPwPan;

    public LoginPage() {
        findPan = new JPanel(new BorderLayout());
        idPwPan = new JPanel();
        setSize(480, 640);
        add(BorderLayout.SOUTH, findPan);
        findPan.add(BorderLayout.EAST, idPwPan);
        findPan.setOpaque(false);

        JPanel backg = new JPanel() {
            public void paint(Graphics g) {
                g.drawImage(bgImg.getImage(), 0, 0, null);
                setOpaque(false);
                super.paint(g);
            }
        };

        JButton findIdPwBTN = new JButton("ID/PW 찾기");
        findIdPwBTN.setFont(fontAppGothic);
        findIdPwBTN.setForeground(Color.GRAY);
        idPwPan.add(findIdPwBTN);
        idPwPan.setOpaque(false);
        findIdPwBTN.setBorderPainted(false);

        JPanel rowPan01 = new JPanel(); // ID, PW 라벨과 텍스트필드를 그룹으로 묶어서 관리!
        rowPan01.add(new JLabel("I  D"));
        rowPan01.add(idTxtFld);
        rowPan01.setOpaque(false);
        JPanel rowPan02 = new JPanel();
        rowPan02.add(new JLabel("PW"));
        rowPan02.add(pwTxtFld);
        rowPan02.setOpaque(false);

        JPanel gridPan = new JPanel(new GridLayout(4, 1));
        gridPan.add(rowPan01);
        gridPan.add(rowPan02);
        gridPan.add(loginPopup);
        gridPan.add(signUp);
        gridPan.setOpaque(false);

        centerPan.add(gridPan, BorderLayout.CENTER);
        centerPan.setOpaque(false);
        loginPopup.setFont(fontCourier);
        loginPopup.setBorderPainted(false);
        backg.add(centerPan);
        add(backg);

        Dimension frameSize = getSize();
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((windowSize.width - frameSize.width) / 2,
                (windowSize.height - frameSize.height) / 2); //화면 중앙에 띄우기
        setResizable(false);
        setVisible(true);
    }

}