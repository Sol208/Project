package com.aroundThirty.View;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

import static com.aroundThirty.Resource.BR.adoptDto;
import static com.aroundThirty.Resource.FR.*;

public class AdoptRightPanel extends JPanel {
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
    JPanel center_Center_Center_Panel = new JPanel(new BorderLayout());
    JPanel center_Center_Center_Panel_Card = new JPanel(cardLayout);
    JScrollPane detail_ScrollPane = new JScrollPane(centerPanel);


    JLabel adoptPlace = new JLabel("발견 장소 :");
    JLabel adoptPlace_Card = new JLabel("발견 장소 :");
    JTextArea adoptPlaceVal = new JTextArea(adoptDto.adopt_Place);  // DB 연결 해야함
    JTextArea adoptPlaceTxt = new JTextArea(adoptDto.adopt_Place);  // DB 연결 해야함
    JLabel adoptKind = new JLabel("품종 :");
    JLabel adoptKind_Card = new JLabel("품종 :");
    JLabel adoptKindVal = new JLabel(adoptDto.kind_Adopt);    // DB 연결 해야함
    JTextField adoptKindTxt = new JTextField(adoptDto.kind_Adopt);    // DB 연결 해야함
    JLabel adoptNum = new JLabel("전화번호 :");
    JLabel adoptNum_Card = new JLabel("전화번호 :");
    JLabel adoptNumVal = new JLabel(adoptDto.phone_Num);  // DB 연결 해야함
    JTextField adoptNumTxt = new JTextField(adoptDto.phone_Num);  // DB 연결 해야함
    JLabel postDt = new JLabel("게시일자 :");
    JLabel postDt_Card = new JLabel("게시일자 :");
    JLabel postDtVal = new JLabel(adoptDto.post_Create_Date);  // DB 연결 해야함
    JTextArea postDtTxt = new JTextArea(adoptDto.post_Create_Date);  // DB 연결 해야함
    JLabel modifyDt = new JLabel("수정일자 :");
    JLabel modifyDt_Card = new JLabel("수정일자 :");
    JLabel modifyDtVal = new JLabel(adoptDto.post_Modify_Date);    // DB 연결 해야함
    JTextArea modifyDtTxt = new JTextArea(adoptDto.post_Modify_Date);    // DB 연결 해야함
    JTextArea adoptDetail = new JTextArea(adoptDto.detail); // DB 연결 해야함
    JTextArea adoptDetailTxt = new JTextArea(adoptDto.detail); // DB 연결 해야함

    String imgPath = adoptDto.thumbnail_Img;    // 이미지 주소를 받음
    ImageIcon imgIcon = new ImageIcon(imgPath); // 이미지를 담음
    JLabel imgLabel = new JLabel(imageSetSize(imgIcon, 250, 250));    // 이미지 추가


    public AdoptRightPanel() {
        if (adoptDto.getThumbnail_Img() == null){
            imgLabel = new JLabel(imageSetSize(defaultImg,250,250));
        }
        adopt_Right_Top_Panel = new AdoptRightTopPanel();
        setPreferredSize(new Dimension(550, 0));

        // Frame에 패널 추가
        setLayout(new BorderLayout());
        add(BorderLayout.NORTH, adopt_Right_Top_Panel);
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


        // 패널에 라벨 및 버튼 추가
        center_North_Left_Panel.add(imgLabel);
        center_North_Right_Panel.add(adoptPlace);
        center_North_Right_Panel.add(adoptPlaceVal);
        center_North_Right_Panel.add(adoptKind);
        center_North_Right_Panel.add(adoptKindVal);
        center_North_Right_Panel.add(adoptNum);
        center_North_Right_Panel.add(adoptNumVal);
        center_North_Right_Panel.add(postDt);
        center_North_Right_Panel.add(postDtVal);
        center_North_Right_Panel.add(modifyDt);
        center_North_Right_Panel.add(modifyDtVal);
        center_Center_Center_Panel_Card.add(adoptDetail);


        // 패널에 라벨 및 버튼 추가 card 2
        center_North_Right_Panel_Card.add(adoptPlace_Card);
        center_North_Right_Panel_Card.add(adoptPlaceTxt);
        center_North_Right_Panel_Card.add(adoptKind_Card);
        center_North_Right_Panel_Card.add(adoptKindTxt);
        center_North_Right_Panel_Card.add(adoptNum_Card);
        center_North_Right_Panel_Card.add(adoptNumTxt);
        center_North_Right_Panel_Card.add(postDt_Card);
        center_North_Right_Panel_Card.add(postDtTxt);
        center_North_Right_Panel_Card.add(modifyDt_Card);
        center_North_Right_Panel_Card.add(modifyDtTxt);
        center_Center_Center_Panel_Card.add(adoptDetailTxt);


        // 라벨, 버튼 등 속성
        adoptPlace.setPreferredSize(new Dimension(80,40));
        adoptKind.setPreferredSize(new Dimension(80,40));
        adoptNum.setPreferredSize(new Dimension(80,40));
        postDt.setPreferredSize(new Dimension(80,40));
        modifyDt.setPreferredSize(new Dimension(80,40));
        adoptDetail.setEditable(false);
        adoptPlaceVal.setEditable(false);
        postDtTxt.setEditable(false);
        modifyDtTxt.setEditable(false);
        adoptPlaceVal.setLineWrap(true);
        adoptPlaceTxt.setLineWrap(true);
        adoptDetail.setLineWrap(true);
        adoptPlaceTxt.setPreferredSize(new Dimension(100, 20));
        center_Center_Center_Panel_Card.setPreferredSize(new Dimension(250, 500));
        center_Center_Center_Panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));   // 안쪽 여백 추가
        LineBorder line = new LineBorder(pastelPink, 5, true);   // 이미지 테두리선과 곡선에 대한 값 저장
        imgLabel.setBorder(line);   // 저장된 테두리와 곡선 추가

        // 폰트 설정
        adoptDetail.setFont(new Font("나눔고딕", Font.BOLD, 20));
        adoptDetailTxt.setFont(fontNanum);
        adoptPlace.setFont(fontNanumBold);
        adoptPlace_Card.setFont(fontNanum);
        adoptPlaceVal.setFont(fontNanum);
        adoptPlaceTxt.setFont(fontNanum);
        adoptKind.setFont(fontNanumBold);
        adoptKind_Card.setFont(fontNanum);
        adoptKindVal.setFont(fontNanum);
        adoptKindTxt.setFont(fontNanum);
        adoptNum.setFont(fontNanumBold);
        adoptNum_Card.setFont(fontNanum);
        adoptNumVal.setFont(fontNanum);
        adoptNumTxt.setFont(fontNanum);
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
        adoptDetail.setBackground(pastelPink);
        adoptPlaceVal.setBackground(pastelYellow);
        adoptKindVal.setBackground(pastelYellow);
        adoptNumVal.setBackground(pastelYellow);
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

        detail_ScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);    // 상하 스크롤에 대한 정책을 설정한다. (스크롤바가 항상 보이도록 설정)
        centerPanel.setPreferredSize(new Dimension(300, 800)); // centerPanel의 크기 지정
        detail_ScrollPane.getVerticalScrollBar().setUnitIncrement(15); // 스크롤 속도 지정
        detail_ScrollPane.setViewportView(centerPanel); // 데이터가 화면을 넘어가도 깨지지 않도록 수정 대신 넘어간 데이터가 안보일 수 있음
    }
}
