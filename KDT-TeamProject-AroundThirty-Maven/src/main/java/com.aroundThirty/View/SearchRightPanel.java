package com.aroundThirty.View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

import static com.aroundThirty.Resource.BR.xmlDto;
import static com.aroundThirty.Resource.FR.imageSetSize;
import static com.aroundThirty.Resource.FR.pastelGreen;

public class SearchRightPanel extends JPanel {
    JPanel northPan;
    JPanel southPan;

    Image imageDetail;
    ImageIcon imageDetailIcon;
    JLabel imgLabel;

    JLabel happenDtLabel;
    JLabel happenDtDetailLabel;
    JPanel happenDtPan;

    JLabel happenPlaceLabel;
    JLabel happenPlaceDetailLabel;
    JPanel happenPlacePan;

    JLabel happenKindLabel;
    JLabel happenKindDetailLabel;
    JPanel happenKindPan;

    JLabel phone_NumLabel;
    JLabel phone_NumDetailLabel;
    JPanel phone_NumPan;

    JLabel specialMarkLabel;
    JLabel specialMarkDetailLabel;
    JPanel specialMarkPan;

    JLabel processLabel;
    JLabel processDetailLabel;
    JPanel processPan;

    JLabel ageLabel;
    JLabel ageDetailLabel;
    JPanel agePan;

    JLabel weightLabel;
    JLabel weightDetailLabel;
    JPanel weightPan;

    SearchRightPanel() {
        imageDetail = null;
        try {
            URL url = new URL(xmlDto.getThumbnail_Img());
            imageDetail = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        imageDetailIcon = new ImageIcon(imageDetail);
        imgLabel = new JLabel(imageSetSize(imageDetailIcon, 450, 350));

        northPan = new JPanel();
        northPan.add(imgLabel);
        northPan.setBackground(pastelGreen);

        southPan = new JPanel(new GridLayout(8, 1));

        happenDtLabel = new JLabel("발견 일자 : ");
        happenDtLabel.setPreferredSize(new Dimension(120, 35));
        happenDtDetailLabel = new JLabel(xmlDto.getHappenDt());
        happenDtDetailLabel.setPreferredSize(new Dimension(350, 35));
        happenDtPan = new JPanel();
        happenDtPan.add(happenDtLabel);
        happenDtPan.add(happenDtDetailLabel);
        happenDtPan.setBackground(pastelGreen);

        happenPlaceLabel = new JLabel("보호 장소 : ");
        happenPlaceLabel.setPreferredSize(new Dimension(120, 35));
        happenPlaceDetailLabel = new JLabel(xmlDto.getHappenPlace());
        happenPlaceDetailLabel.setPreferredSize(new Dimension(350, 35));
        happenPlacePan = new JPanel();
        happenPlacePan.add(happenPlaceLabel);
        happenPlacePan.add(happenPlaceDetailLabel);
        happenPlacePan.setBackground(pastelGreen);

        happenKindLabel = new JLabel("품종 : ");
        happenKindLabel.setPreferredSize(new Dimension(120, 35));
        happenKindDetailLabel = new JLabel(xmlDto.getKindCd());
        happenKindDetailLabel.setPreferredSize(new Dimension(350, 35));
        happenKindPan = new JPanel();
        happenKindPan.add(happenKindLabel);
        happenKindPan.add(happenKindDetailLabel);
        happenKindPan.setBackground(pastelGreen);

        phone_NumLabel = new JLabel("임시보호자 전화번호 : ");
        phone_NumLabel.setPreferredSize(new Dimension(120, 35));
        phone_NumDetailLabel = new JLabel(xmlDto.getPhone_Num());
        phone_NumDetailLabel.setPreferredSize(new Dimension(350, 35));
        phone_NumPan = new JPanel();
        phone_NumPan.add(phone_NumLabel);
        phone_NumPan.add(phone_NumDetailLabel);
        phone_NumPan.setBackground(pastelGreen);

        specialMarkLabel = new JLabel("특이 사항 : ");
        specialMarkLabel.setPreferredSize(new Dimension(120, 35));
        specialMarkDetailLabel = new JLabel(xmlDto.getSpecialMark());
        specialMarkDetailLabel.setPreferredSize(new Dimension(350, 35));
        specialMarkPan = new JPanel();
        specialMarkPan.add(specialMarkLabel);
        specialMarkPan.add(specialMarkDetailLabel);
        specialMarkPan.setBackground(pastelGreen);

        processLabel = new JLabel("현재 상황 : ");
        processLabel.setPreferredSize(new Dimension(120, 35));
        processDetailLabel = new JLabel(xmlDto.getProcessState());
        processDetailLabel.setPreferredSize(new Dimension(350, 35));
        processPan = new JPanel();
        processPan.add(processLabel);
        processPan.add(processDetailLabel);
        processPan.setBackground(pastelGreen);

        ageLabel = new JLabel("추정 나이 : ");
        ageLabel.setPreferredSize(new Dimension(120, 35));
        ageDetailLabel = new JLabel(xmlDto.getAge());
        ageDetailLabel.setPreferredSize(new Dimension(350, 35));
        agePan = new JPanel();
        agePan.add(ageLabel);
        agePan.add(ageDetailLabel);
        agePan.setBackground(pastelGreen);

        weightLabel = new JLabel("몸무게 : ");
        weightLabel.setPreferredSize(new Dimension(120, 35));
        weightDetailLabel = new JLabel(xmlDto.getWeight());
        weightDetailLabel.setPreferredSize(new Dimension(350, 35));
        weightPan = new JPanel();
        weightPan.add(weightLabel);
        weightPan.add(weightDetailLabel);
        weightPan.setBackground(pastelGreen);

        southPan.add(happenDtPan);
        southPan.add(happenPlacePan);
        southPan.add(happenKindPan);
        southPan.add(agePan);
        southPan.add(weightPan);
        southPan.add(phone_NumPan);
        southPan.add(specialMarkPan);
        southPan.add(processPan);

        add(BorderLayout.CENTER, northPan);
        add(BorderLayout.SOUTH, southPan);
        setPreferredSize(new Dimension(550, 0));
        setBackground(pastelGreen);
    }
}
