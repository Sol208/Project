package com.aroundThirty.View;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

import static com.aroundThirty.Resource.BR.missingDto;
import static com.aroundThirty.Resource.FR.*;

public class MissingRightPanel extends JPanel {
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


    JLabel missingDt = new JLabel("제보일자 :");
    JLabel missingDt_Card = new JLabel("제보일자 :");
    JLabel missingDtVal = new JLabel(missingDto.missing_Date);    // DB 연결 해야함
    JTextField missingDtTxt = new JTextField(missingDto.missing_Date);    // DB 연결 해야함
    JLabel missingPlace = new JLabel("실종 장소 :");
    JLabel missingPlace_Card = new JLabel("실종 장소 :");
    JTextArea missingPlaceVal = new JTextArea(missingDto.missing_Place);  // DB 연결 해야함
    JTextArea missingPlaceTxt = new JTextArea(missingDto.missing_Place);  // DB 연결 해야함
    JLabel missingKind = new JLabel("품종 :");
    JLabel missingKind_Card = new JLabel("품종 :");
    JLabel missingKindVal = new JLabel(missingDto.kind_Missing);    // DB 연결 해야함
    JTextField missingKindTxt = new JTextField(missingDto.kind_Missing);    // DB 연결 해야함
    JLabel missingNum = new JLabel("전화번호 :");
    JLabel missingNum_Card = new JLabel("전화번호 :");
    JLabel missingNumVal = new JLabel(missingDto.phone_Num);  // DB 연결 해야함
    JTextField missingNumTxt = new JTextField(missingDto.phone_Num);  // DB 연결 해야함
    JLabel postDt = new JLabel("게시일자 :");
    JLabel postDt_Card = new JLabel("게시일자 :");
    JLabel postDtVal = new JLabel(missingDto.post_Create_Date);  // DB 연결 해야함
    JTextArea postDtTxt = new JTextArea(missingDto.post_Create_Date);  // DB 연결 해야함
    JLabel modifyDt = new JLabel("수정일자 :");
    JLabel modifyDt_Card = new JLabel("수정일자 :");
    JLabel modifyDtVal = new JLabel(missingDto.post_Modify_Date);    // DB 연결 해야함
    JTextArea modifyDtTxt = new JTextArea(missingDto.post_Modify_Date);    // DB 연결 해야함
    JTextArea missingDetail = new JTextArea(missingDto.detail); // DB 연결 해야함
    JTextArea missingDetailTxt = new JTextArea(missingDto.detail); // DB 연결 해야함

    String imgPath = missingDto.thumbnail_Img;    // 이미지 주소를 받음
    ImageIcon imgIcon = new ImageIcon(imgPath); // 이미지를 담음
    JLabel imgLabel = new JLabel(imageSetSize(imgIcon, 250, 250));    // 이미지 추가


    public MissingRightPanel() {
        if (missingDto.getThumbnail_Img() == null){
            imgLabel = new JLabel(imageSetSize(defaultImg,250,250));
        }
        missing_Right_Top_Panel = new MissingRightTopPanel();
        setPreferredSize(new Dimension(550, 0));

        // Frame에 패널 추가
        setLayout(new BorderLayout());
        add(BorderLayout.NORTH, missing_Right_Top_Panel);
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
        center_North_Right_Panel.add(missingDt);
        center_North_Right_Panel.add(missingDtVal);
        center_North_Right_Panel.add(missingPlace);
        center_North_Right_Panel.add(missingPlaceVal);
        center_North_Right_Panel.add(missingKind);
        center_North_Right_Panel.add(missingKindVal);
        center_North_Right_Panel.add(missingNum);
        center_North_Right_Panel.add(missingNumVal);
        center_North_Right_Panel.add(postDt);
        center_North_Right_Panel.add(postDtVal);
        center_North_Right_Panel.add(modifyDt);
        center_North_Right_Panel.add(modifyDtVal);
        center_Center_Center_Panel_Card.add(missingDetail);
        center_Center_North_West_Panel.add(missing_BoaderCombo);


        // 패널에 라벨 및 버튼 추가 card 2
        center_North_Right_Panel_Card.add(missingDt_Card);
        center_North_Right_Panel_Card.add(missingDtTxt);
        center_North_Right_Panel_Card.add(missingPlace_Card);
        center_North_Right_Panel_Card.add(missingPlaceTxt);
        center_North_Right_Panel_Card.add(missingKind_Card);
        center_North_Right_Panel_Card.add(missingKindTxt);
        center_North_Right_Panel_Card.add(missingNum_Card);
        center_North_Right_Panel_Card.add(missingNumTxt);
        center_North_Right_Panel_Card.add(postDt_Card);
        center_North_Right_Panel_Card.add(postDtTxt);
        center_North_Right_Panel_Card.add(modifyDt_Card);
        center_North_Right_Panel_Card.add(modifyDtTxt);
        center_Center_Center_Panel_Card.add(missingDetailTxt);


        // 라벨, 버튼 등 속성
        missingDt.setPreferredSize(new Dimension(80,40));
        missingPlace.setPreferredSize(new Dimension(80,40));
        missingKind.setPreferredSize(new Dimension(80,40));
        missingNum.setPreferredSize(new Dimension(80,40));
        postDt.setPreferredSize(new Dimension(80,40));
        modifyDt.setPreferredSize(new Dimension(80,40));
        missingDetail.setEditable(false);
        missingPlaceVal.setEditable(false);
        postDtTxt.setEditable(false);
        modifyDtTxt.setEditable(false);
        missingPlaceVal.setLineWrap(true);
        missingPlaceTxt.setLineWrap(true);
        missingDetail.setLineWrap(true);
        missing_BoaderCombo.setEnabled(false);
        missingPlaceTxt.setPreferredSize(new Dimension(100, 20));
        center_Center_Center_Panel_Card.setPreferredSize(new Dimension(250, 500));
        center_Center_Center_Panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));   // 안쪽 여백 추가
        LineBorder line = new LineBorder(pastelPink, 5, true);   // 이미지 테두리선과 곡선에 대한 값 저장
        imgLabel.setBorder(line);   // 저장된 테두리와 곡선 추가

        // 폰트 설정
        missingDetail.setFont(new Font("나눔고딕", Font.BOLD, 20));
        missingDetailTxt.setFont(fontNanum);
        missingDt.setFont(fontNanumBold);
        missingDt_Card.setFont(fontNanum);
        missingDtVal.setFont(fontNanum);
        missingDtTxt.setFont(fontNanum);
        missingPlace.setFont(fontNanumBold);
        missingPlace_Card.setFont(fontNanum);
        missingPlaceVal.setFont(fontNanum);
        missingPlaceTxt.setFont(fontNanum);
        missingKind.setFont(fontNanumBold);
        missingKind_Card.setFont(fontNanum);
        missingKindVal.setFont(fontNanum);
        missingKindTxt.setFont(fontNanum);
        missingNum.setFont(fontNanumBold);
        missingNum_Card.setFont(fontNanum);
        missingNumVal.setFont(fontNanum);
        missingNumTxt.setFont(fontNanum);
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
        missingDetail.setBackground(pastelPink);
        missingDtVal.setBackground(pastelYellow);
        missingPlaceVal.setBackground(pastelYellow);
        missingKindVal.setBackground(pastelYellow);
        missingNumVal.setBackground(pastelYellow);
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
