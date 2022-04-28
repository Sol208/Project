package com.aroundThirty.View;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

import static com.aroundThirty.Resource.FR.*;
import static com.aroundThirty.Resource.BR.*;

public class ReportRightPanel extends JPanel {
    JPanel centerPanel = new JPanel(new BorderLayout());
    JPanel southPanel = new JPanel();
    JPanel north_East_Panel = new JPanel();
    JPanel north_East_West_Panel = new JPanel(cardLayout);
    JPanel center_North_Top_Panel = new JPanel(cardLayout);
    JPanel north_East_East_Panel = new JPanel();
    JPanel center_North_Panel = new JPanel();
    JPanel center_Center_Panel = new JPanel(new BorderLayout());
    JPanel center_North_Right_Panel = new JPanel(new GridLayout(6, 2, 0, 10));
    JPanel center_North_Right_Panel_Card = new JPanel(new GridLayout(6, 2, 0, 10));
    JPanel center_North_Left_Panel = new JPanel();
    JPanel center_Center_Right_Panel = new JPanel();
    JPanel center_Center_Left_Panel = new JPanel();
    JPanel center_Center_NorthPanel = new JPanel(new BorderLayout());
    JPanel center_Center_North_West_Panel = new JPanel();
    JPanel center_Center_Center_Panel = new JPanel(new BorderLayout());
    JPanel center_Center_Center_Panel_Card = new JPanel(cardLayout);
    JScrollPane detail_ScrollPane = new JScrollPane(centerPanel);


    JLabel reportDt = new JLabel("제보일자 :");
    JLabel reportDt_Card = new JLabel("제보일자 :");
    JLabel reportDtVal = new JLabel(reportDto.report_Date);    // DB 연결 해야함
    JTextField reportDtTxt = new JTextField(reportDto.report_Date);    // DB 연결 해야함
    JLabel reportPlace = new JLabel("발견 장소 :");
    JLabel reportPlace_Card = new JLabel("발견 장소 :");
    JTextArea reportPlaceVal = new JTextArea(reportDto.report_Place);  // DB 연결 해야함
    JTextArea reportPlaceTxt = new JTextArea(reportDto.report_Place);  // DB 연결 해야함
    JLabel reportKind = new JLabel("품종 :");
    JLabel reportKind_Card = new JLabel("품종 :");
    JLabel reportKindVal = new JLabel(reportDto.kind_Report);    // DB 연결 해야함
    JTextField reportKindTxt = new JTextField(reportDto.kind_Report);    // DB 연결 해야함
    JLabel reportNum = new JLabel("전화번호 :");
    JLabel reportNum_Card = new JLabel("전화번호 :");
    JLabel reportNumVal = new JLabel(reportDto.phone_Num);  // DB 연결 해야함
    JTextField reportNumTxt = new JTextField(reportDto.phone_Num);  // DB 연결 해야함
    JLabel postDt = new JLabel("게시일자 :");
    JLabel postDt_Card = new JLabel("게시일자 :");
    JLabel postDtVal = new JLabel(reportDto.post_Create_Date);  // DB 연결 해야함
    JTextArea postDtTxt = new JTextArea(reportDto.post_Create_Date);  // DB 연결 해야함
    JLabel modifyDt = new JLabel("수정일자 :");
    JLabel modifyDt_Card = new JLabel("수정일자 :");
    JLabel modifyDtVal = new JLabel(reportDto.post_Modify_Date);    // DB 연결 해야함
    JTextArea modifyDtTxt = new JTextArea(reportDto.post_Modify_Date);    // DB 연결 해야함
    JTextArea reportDetail = new JTextArea(reportDto.detail); // DB 연결 해야함
    JTextArea reportDetailTxt = new JTextArea(reportDto.detail); // DB 연결 해야함
    String imgPath = reportDto.thumbnail_Img;    // 이미지 주소를 받음
    ImageIcon imgIcon = new ImageIcon(imgPath); // 이미지를 담음
    JLabel imgLabel = new JLabel(imageSetSize(imgIcon, 250, 250));    // 이미지 추가


    public ReportRightPanel() {
        if (reportDto.getThumbnail_Img() == null){
            imgLabel = new JLabel(imageSetSize(defaultImg, 250, 250));    // 이미지 추가
        }
        report_Right_Top_Panel = new ReportRightTopPanel();
        setPreferredSize(new Dimension(550, 0));

        // Frame에 패널 추가
        setLayout(new BorderLayout());
        add(BorderLayout.NORTH, report_Right_Top_Panel);
        add(BorderLayout.CENTER, centerPanel);
        add(BorderLayout.SOUTH, southPanel);
        add(detail_ScrollPane);

        // 패널 구성
//        northPanel.add(BorderLayout.EAST, north_East_Panel);
        north_East_Panel.add(north_East_West_Panel);
        north_East_Panel.add(north_East_East_Panel);
        centerPanel.add(BorderLayout.NORTH, center_North_Panel);
        centerPanel.add(BorderLayout.CENTER, center_Center_Panel);
        centerPanel.add(BorderLayout.CENTER, center_Center_Panel);
        center_North_Panel.add(BorderLayout.WEST, center_North_Left_Panel);
        center_North_Panel.add(BorderLayout.EAST, center_North_Top_Panel);
        center_North_Top_Panel.add(center_North_Right_Panel);
        center_North_Top_Panel.add(center_North_Right_Panel_Card);
        center_Center_Panel.add(BorderLayout.WEST, center_Center_Left_Panel);
        center_Center_Panel.add(BorderLayout.EAST, center_Center_Right_Panel);
        center_Center_Panel.add(BorderLayout.CENTER, center_Center_Center_Panel);
        center_Center_Center_Panel.add(BorderLayout.CENTER, center_Center_Center_Panel_Card);
        center_Center_Center_Panel.add(BorderLayout.NORTH, center_Center_NorthPanel);
        center_Center_NorthPanel.add(BorderLayout.WEST,center_Center_North_West_Panel);


        // 패널에 라벨 및 버튼 추가
        center_North_Left_Panel.add(imgLabel);
        center_North_Right_Panel.add(reportDt);
        center_North_Right_Panel.add(reportDtVal);
        center_North_Right_Panel.add(reportPlace);
        center_North_Right_Panel.add(reportPlaceVal);
        center_North_Right_Panel.add(reportKind);
        center_North_Right_Panel.add(reportKindVal);
        center_North_Right_Panel.add(reportNum);
        center_North_Right_Panel.add(reportNumVal);
        center_North_Right_Panel.add(postDt);
        center_North_Right_Panel.add(postDtVal);
        center_North_Right_Panel.add(modifyDt);
        center_North_Right_Panel.add(modifyDtVal);
        center_Center_Center_Panel_Card.add(reportDetail);
        center_Center_North_West_Panel.add(report_BoaderCombo);


        // 패널에 라벨 및 버튼 추가 card 2
        center_North_Right_Panel_Card.add(reportDt_Card);
        center_North_Right_Panel_Card.add(reportDtTxt);
        center_North_Right_Panel_Card.add(reportPlace_Card);
        center_North_Right_Panel_Card.add(reportPlaceTxt);
        center_North_Right_Panel_Card.add(reportKind_Card);
        center_North_Right_Panel_Card.add(reportKindTxt);
        center_North_Right_Panel_Card.add(reportNum_Card);
        center_North_Right_Panel_Card.add(reportNumTxt);
        center_North_Right_Panel_Card.add(postDt_Card);
        center_North_Right_Panel_Card.add(postDtTxt);
        center_North_Right_Panel_Card.add(modifyDt_Card);
        center_North_Right_Panel_Card.add(modifyDtTxt);
        center_Center_Center_Panel_Card.add(reportDetailTxt);


        // 라벨, 버튼 등 속성
        reportDt.setPreferredSize(new Dimension(80,40));
        reportPlace.setPreferredSize(new Dimension(80,40));
        reportKind.setPreferredSize(new Dimension(80,40));
        reportNum.setPreferredSize(new Dimension(80,40));
        postDt.setPreferredSize(new Dimension(80,40));
        modifyDt.setPreferredSize(new Dimension(80,40));
        reportDetail.setEditable(false);
        reportPlaceVal.setEditable(false);
        postDtTxt.setEditable(false);
        modifyDtTxt.setEditable(false);
        reportPlaceVal.setLineWrap(true);
        reportPlaceTxt.setLineWrap(true);
        reportDetail.setLineWrap(true);
        report_BoaderCombo.setEnabled(false);
        reportPlaceTxt.setPreferredSize(new Dimension(100, 20));
        center_Center_Center_Panel_Card.setPreferredSize(new Dimension(250, 500));
        center_Center_Center_Panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));   // 안쪽 여백 추가
        LineBorder line = new LineBorder(pastelPink, 5, true);   // 이미지 테두리선과 곡선에 대한 값 저장
        imgLabel.setBorder(line);   // 저장된 테두리와 곡선 추가

        // 폰트 설정
        reportDetail.setFont(new Font("나눔고딕", Font.BOLD, 20));
        reportDetailTxt.setFont(fontNanum);
        reportDt.setFont(fontNanumBold);
        reportDt_Card.setFont(fontNanum);
        reportDtVal.setFont(fontNanum);
        reportDtTxt.setFont(fontNanum);
        reportPlace.setFont(fontNanumBold);
        reportPlace_Card.setFont(fontNanum);
        reportPlaceVal.setFont(fontNanum);
        reportPlaceTxt.setFont(fontNanum);
        reportKind.setFont(fontNanumBold);
        reportKind_Card.setFont(fontNanum);
        reportKindVal.setFont(fontNanum);
        reportKindTxt.setFont(fontNanum);
        reportNum.setFont(fontNanumBold);
        reportNum_Card.setFont(fontNanum);
        reportNumVal.setFont(fontNanum);
        reportNumTxt.setFont(fontNanum);
        postDt.setFont(fontNanumBold);
        postDt_Card.setFont(fontNanum);
        postDtVal.setFont(fontNanum);
        postDtTxt.setFont(fontNanum);
        modifyDt.setFont(fontNanumBold);
        modifyDt_Card.setFont(fontNanum);
        modifyDtVal.setFont(fontNanum);
        modifyDtTxt.setFont(fontNanum);

        // 패널 색상
        center_North_Right_Panel_Card.setBackground(pastelYellow);
        reportDetail.setBackground(pastelPink);
        reportDtVal.setBackground(pastelYellow);
        reportPlaceVal.setBackground(pastelYellow);
        reportKindVal.setBackground(pastelYellow);
        reportNumVal.setBackground(pastelYellow);
        postDtVal.setBackground(pastelYellow);
        modifyDtVal.setBackground(pastelYellow);
        centerPanel.setBackground(pastelYellow);
        southPanel.setBackground(pastelYellow);
        north_East_Panel.setBackground(pastelPink);
        center_Center_Panel.setBackground(pastelYellow);
        center_North_Panel.setBackground(pastelYellow);
        center_North_Right_Panel.setBackground(pastelYellow);
        center_North_Left_Panel.setBackground(pastelYellow);
        center_Center_Center_Panel.setBackground(pastelPink);
        center_Center_Right_Panel.setBackground(pastelPink);
        center_Center_Left_Panel.setBackground(pastelPink);
        center_Center_Center_Panel_Card.setBackground(pastelPink);
        center_Center_NorthPanel.setBackground(pastelPink);
        center_Center_North_West_Panel.setBackground(pastelPink);

        detail_ScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);    // 상하 스크롤에 대한 정책을 설정한다. (스크롤바가 항상 보이도록 설정)
        centerPanel.setPreferredSize(new Dimension(300, 800)); // centerPanel의 크기 지정
        detail_ScrollPane.getVerticalScrollBar().setUnitIncrement(15); // 스크롤 속도 지정
        detail_ScrollPane.setViewportView(centerPanel); // 데이터가 화면을 넘어가도 깨지지 않도록 수정 대신 넘어간 데이터가 안보일 수 있음


    }
}
