package com.aroundThirty.View;

import javax.swing.*;
import java.awt.*;

import static com.aroundThirty.Resource.FR.*;

public class SourcePage extends JFrame {
    JPanel centerPan;
    JPanel personOnePan;
    JPanel personTwoPan;
    JPanel personThreePan;
    JPanel personFourPan;
    JPanel personFivePan;
    JPanel subjectPan01;
    JPanel subjectPan02;
    JPanel emptyPan;
    JPanel personSixPan;

    JLabel personOneLabel;
    JLabel personTwoLabel;
    JLabel personThreeLabel;
    JLabel personFourLabel;
    JLabel personFiveLabel;
    JLabel subjectLabel;
    JLabel thankstoLabel;
    JLabel personSixLabel;


    public SourcePage() {
        setSize(480, 360);
        setResizable(false);

        subjectLabel = new JLabel("출 처");
        subjectLabel.setFont(fontNanumBold);
        personOneLabel = new JLabel("https://www.data.go.kr/");
        personOneLabel.setFont(fontNanum);
        personTwoLabel = new JLabel("https://www.animal.go.kr/front/index.do");
        personTwoLabel.setFont(fontNanum);
        personThreeLabel = new JLabel("----------");
        personThreeLabel.setFont(fontNanum);
        personFourLabel = new JLabel("----------");
        personFourLabel.setFont(fontNanum);
        personFiveLabel = new JLabel("----------");
        personFiveLabel.setFont(fontNanum);
        thankstoLabel = new JLabel("'서른즈음에' 깃허브");
        thankstoLabel.setFont(fontNanumBold);
        personSixLabel = new JLabel("https://github.com/Dev-SHK/KDT-TeamProject-AroundThirty");
        personSixLabel.setFont(fontNanum);

        subjectPan01 = new JPanel();
        subjectPan01.add(subjectLabel);
        subjectPan01.setBackground(pastelGreen);

        personOnePan = new JPanel();
        personOnePan.add(personOneLabel);
        personOnePan.setBackground(pastelGreen);

        personTwoPan = new JPanel();
        personTwoPan.add(personTwoLabel);
        personTwoPan.setBackground(pastelGreen);

        personThreePan = new JPanel();
        personThreePan.add(personThreeLabel);
        personThreePan.setBackground(pastelGreen);

        personFourPan = new JPanel();
        personFourPan.add(personFourLabel);
        personFourPan.setBackground(pastelGreen);

        personFivePan = new JPanel();
        personFivePan.add(personFiveLabel);
        personFivePan.setBackground(pastelGreen);

        emptyPan = new JPanel();
        emptyPan.setBackground(pastelGreen);

        subjectPan02 = new JPanel();
        subjectPan02.add(thankstoLabel);
        subjectPan02.setBackground(pastelGreen);

        personSixPan = new JPanel();
        personSixPan.add(personSixLabel);
        personSixPan.setBackground(pastelGreen);

        JPanel gridPan = new JPanel(new GridLayout(9, 1));
        gridPan.add(subjectPan01);
        gridPan.add(personOnePan);
        gridPan.add(personTwoPan);
        gridPan.add(personThreePan);
        gridPan.add(personFourPan);
        gridPan.add(personFivePan);
        gridPan.add(emptyPan);
        gridPan.add(subjectPan02);
        gridPan.add(personSixPan);
        gridPan.setBackground(pastelGreen);

        centerPan = new JPanel();
        centerPan.add(gridPan);
        centerPan.setBackground(pastelGreen);

        setBackground(pastelGreen);
        add(BorderLayout.CENTER, centerPan);
        setVisible(true);

        Dimension frameSize = getSize();
        Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();

        setLocation((windowSize.width - frameSize.width) / 2,
                (windowSize.height - frameSize.height) / 2); //화면 중앙에 띄우기
    }

}
