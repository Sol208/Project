package com.aroundThirty.View;

import com.aroundThirty.model.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static com.aroundThirty.Resource.BR.*;
import static com.aroundThirty.Resource.FR.*;
import static com.aroundThirty.Resource.FR.lblList;
import static com.aroundThirty.View.MainView.container;


public class TemporaryPage extends JPanel {
    private static final long serialVersionUID = 1L;
    public static ArrayList<JPanel> paneList = new ArrayList<>();
    public static ArrayList<JButton> btnList = new ArrayList<>();
    public static ArrayList<JLabel> lblList = new ArrayList<>();
    TemporaryPagingBtn temporaryPagingBtn = new TemporaryPagingBtn();
    JPanel centerPanel = new JPanel(new GridLayout(SIZE_ROW, SIZE_COL));    // SIZE_ROW, SIZE_COL로 행열 지정
    JScrollPane jScrollPane = new JScrollPane(centerPanel);

    static {
        setDataListPanel(0, 12 + SIZE_ITEM);
    }

    public TemporaryPage() {
        temporaryPagingBtn.setBackground(pastelYellow);
        add(BorderLayout.CENTER, jScrollPane);
        jScrollPane.setPreferredSize(new Dimension(820, 650));
        jScrollPane.setBorder(null);
        jScrollPane.setBackground(pastelYellow);
        centerPanel.setBackground(pastelYellow);
        for (int i = 0; i < paneList.size(); i++) {
            centerPanel.add(paneList.get(i));   // panel에 index를 줘서 변수를 주듯 이름을 매김
        }
        add(BorderLayout.SOUTH, temporaryPagingBtn);
        setBackground(pastelYellow);
    }

    public static void setDataListPanel(int startIndex, int endIndex) { // 버튼과 라벨을 넣어준다.
        for (int i = 0, dataIdx = startIndex; i < SIZE_ITEM; i++, dataIdx++) {
            JPanel newPane = new JPanel(null);
            if (temporaryListAll.size() > dataIdx) {   // 데이터의 size가 dataIdx보다 큰 경우에만 통과하는 if문
                postedPageNum = temporaryListAll.get(i).no;    //
            }
            if (temporaryListAll.size() > dataIdx) {
                if (temporaryListAll.get(dataIdx).getThumbnail_Img() == null){
                    btnList.add(new JButton(imageSetSize(defaultImg, 150, 120))); // carddatalist클래스의 이미지를 끌고와서 버튼에 넣어줌 근데 12번째 이미지 부터 넣어줌?
                    lblList.add(new JLabel("[" + postedPageNum + "] " + "작성일 : " + temporaryListAll.get(dataIdx).post_Create_Date));  // carddatalist클래스의 이미지를 끌고와서 버튼에 넣어줌 근데 12번째 이미지 부터 넣어줌?
                }else{
                    btnList.add(new JButton(imageSetSize(temporaryCardDtoList.get(dataIdx).getDefaultImg(), 150, 120))); // carddatalist클래스의 이미지를 끌고와서 버튼에 넣어줌 근데 12번째 이미지 부터 넣어줌?
                    lblList.add(new JLabel("[" + postedPageNum + "] " + "작성일 : " + temporaryListAll.get(dataIdx).post_Create_Date));  // carddatalist클래스의 이미지를 끌고와서 버튼에 넣어줌 근데 12번째 이미지 부터 넣어줌?
                }
            } else if (temporaryListAll.size() <= dataIdx) {
                btnList.add(new JButton(imageSetSize(defaultImg, 150, 150)));
                lblList.add(new JLabel(""));
            }
            btnList.get(i).setBounds(60, 0, 150, 120);   // 위치는 따로 지정 해주지 않고 크기만 지정 해줌
            lblList.get(i).setBounds(60, 120, 150, 20);  // 위치는 따로 지정 해주지 않고 크기만 지정 해줌
            newPane.add(btnList.get(i));
            newPane.add(lblList.get(i));
            newPane.setBackground(pastelYellow);
            paneList.add(newPane);
            int finali = i;
            btnList.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() instanceof JButton) { // e.getsource로 받아온 객체가 JButton의 상속을 받으면 true 반환
                        // instanceof : 객체타입을 확인하는 연산자로 형변환 가능 여부를 확인하며 true, false 로 반환 주로 상속관계에서 부모객체인지 자식객체인지 확인하는데 사용
                        JButton btn = (JButton) e.getSource();   // e.getsource로 받아온 객체의 속성을 btn에 담는다.
                        selectBtnNum = temporaryCardDtoList.get(finali).getNo();   // 현재 페이지에서 선택한 게시물의 번호를 가져옴(12개 중 선택한 버튼의 idx)
                        temporary_Posted_ListIdx = (temporary_PageNum * 12) + temporaryCardDtoList.get(finali).getNo();    // ArrayList에서 데이터를 받아올 수 있도록 선택한 게시물의 인덱스를 만들어 줌
                        int choose_PostedPage_Num = temporaryListAll.get(temporary_Posted_ListIdx).no;   // 선택한 게시물에 대한 no를 담는 변수
                        temporaryDto = TemporaryDao.temporarySelectOne(new TemporaryDto(choose_PostedPage_Num));
                        temporary_Right_Panel.temporaryDtVal.setText(temporaryDto.tmp_Date);
                        temporary_Right_Panel.temporaryDtTxt.setText(temporaryDto.tmp_Date);
                        temporary_Right_Panel.temporaryPlaceVal.setText(temporaryDto.tmp_Place);
                        temporary_Right_Panel.temporaryPlaceTxt.setText(temporaryDto.tmp_Place);
                        temporary_Right_Panel.temporaryKindVal.setText(temporaryDto.kind_Tmp);
                        temporary_Right_Panel.temporaryKindTxt.setText(temporaryDto.kind_Tmp);
                        temporary_Right_Panel.temporaryNumVal.setText(temporaryDto.phone_Num);
                        temporary_Right_Panel.temporaryNumTxt.setText(temporaryDto.phone_Num);
                        temporary_Right_Panel.postDtVal.setText(temporaryDto.post_Create_Date);
                        temporary_Right_Panel.postDtTxt.setText(temporaryDto.post_Create_Date);
                        temporary_Right_Panel.modifyDtVal.setText(temporaryDto.post_Modify_Date);
                        temporary_Right_Panel.modifyDtTxt.setText(temporaryDto.post_Modify_Date);
                        temporary_Right_Panel.temporaryDetail.setText(temporaryDto.detail);
                        temporary_Right_Panel.temporaryDetailTxt.setText(temporaryDto.detail);
                        temporary_Right_Panel.imgPath = temporaryDto.thumbnail_Img;
                        if (temporary_Right_Panel.imgPath == null){
                            temporary_Right_Panel.imgLabel.setIcon(imageSetSize(defaultImg,250,250));
                        }else{
                            ImageIcon imgIcon = new ImageIcon(temporary_Right_Panel.imgPath); // 이미지를 담음
                            temporary_Right_Panel.imgLabel.setIcon(imageSetSize(imgIcon,250,250));
                        }
                        if (click) {
                            temporary_Right_Panel.setVisible(true);
                        }
                        btn.removeActionListener(null);
                    }
                }
            });
        }

        temporary_ModifyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (signNum == 1){
                   if (userDto.getUser_ID().equals(temporaryDto.getUserID())){
                       String act = e.getActionCommand();
                       if (act.equals("수정")) {
                           cardLayout.next(temporary_Right_Top_Panel.switchPanel);
                           cardLayout.next(temporary_Right_Panel.center_North_Top_Panel);
                           cardLayout.next(temporary_Right_Panel.center_Center_Center_Panel_Card);
                           temporary_AddFile.setEnabled(true);
                           temporary_BoaderCombo.setEnabled(true);
                           temporary_DeleteBtn.setEnabled(false);
                           temporary_WriteBtn.setEnabled(false);
                       }
                   } else {
                       JOptionPane.showMessageDialog(null, "계정 정보가 일치하지 않습니다.", title, JOptionPane.INFORMATION_MESSAGE);
                   }
                } else if (signNum == 0){
                    JOptionPane.showMessageDialog(null, "로그인이 필요합니다.", title, JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        temporary_PostBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String act = e.getActionCommand();
                if (act.equals("완료")) {
                    int result = JOptionPane.showConfirmDialog(null, "게시글을 수정 하시겠습니까?", title, JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.CLOSED_OPTION) {
                        temporary_AddFile.setEnabled(false);
                    } else if (result == JOptionPane.YES_OPTION) {
                        movedBoarderPage = new MovedBoarderPage();
                        Thread thread = new Thread(movedBoarderPage);
                        thread.start();
//                        JOptionPane.showMessageDialog(null, "수정되었습니다.", title, JOptionPane.INFORMATION_MESSAGE);
                        cardLayout.next(temporary_Right_Top_Panel.switchPanel);
                        cardLayout.next(temporary_Right_Panel.center_North_Top_Panel);
                        cardLayout.next(temporary_Right_Panel.center_Center_Center_Panel_Card);

                        int boaderIdx = temporary_BoaderCombo.getSelectedIndex() + 1;

                        if (tabPaneIdx == 1) {

                            String mtemporaryDt = temporary_Right_Panel.temporaryDtTxt.getText();
                            String mtemporaryPlace = temporary_Right_Panel.temporaryPlaceTxt.getText();
                            String mKind_temporary = temporary_Right_Panel.temporaryKindTxt.getText();
                            String mtemporaryNum = temporary_Right_Panel.temporaryNumTxt.getText();
                            String mModifyDt = now.toString();
                            String mThumbNail;
                            String mDetail = temporary_Right_Panel.temporaryDetailTxt.getText();

                            if (addImgPath == null) {    // 썸네일을 새로 첨부하지 않은 경우에 대한 IF문
                                mThumbNail = temporaryDto.getThumbnail_Img();
                            } else {
                                mThumbNail = addImgPath;
                            }

                            temporaryDto.setTmp_Date(mtemporaryDt);
                            temporaryDto.setTmp_Place(mtemporaryPlace);
                            temporaryDto.setKind_Tmp(mKind_temporary);
                            temporaryDto.setPhone_Num(mtemporaryNum);
                            temporaryDto.setPost_Modify_Date(mModifyDt);
                            temporaryDto.setThumbnail_Img(mThumbNail);
                            temporaryDto.setDetail(mDetail);

                            temporary_Right_Panel.postDtVal.setText(temporaryDto.tmp_Date);
                            temporary_Right_Panel.postDtTxt.setText(temporaryDto.tmp_Date);
                            temporary_Right_Panel.temporaryPlaceVal.setText(temporaryDto.tmp_Place);
                            temporary_Right_Panel.temporaryPlaceTxt.setText(temporaryDto.tmp_Place);
                            temporary_Right_Panel.temporaryKindVal.setText(temporaryDto.kind_Tmp);
                            temporary_Right_Panel.temporaryKindTxt.setText(temporaryDto.kind_Tmp);
                            temporary_Right_Panel.temporaryNumVal.setText(temporaryDto.phone_Num);
                            temporary_Right_Panel.temporaryNumTxt.setText(temporaryDto.phone_Num);
                            temporary_Right_Panel.postDtVal.setText(temporaryDto.post_Create_Date);
                            temporary_Right_Panel.postDtTxt.setText(temporaryDto.post_Create_Date);
                            temporary_Right_Panel.modifyDtVal.setText(temporaryDto.post_Modify_Date);
                            temporary_Right_Panel.modifyDtTxt.setText(temporaryDto.post_Modify_Date);
                            temporary_Right_Panel.temporaryDetail.setText(temporaryDto.detail);
                            temporary_Right_Panel.temporaryDetailTxt.setText(temporaryDto.detail);
                            temporary_Right_Panel.imgPath = temporaryDto.thumbnail_Img;
                            if (temporary_Right_Panel.imgPath == null){
                                temporary_Right_Panel.imgLabel.setIcon(imageSetSize(defaultImg,250,250));
                            }else{
                                ImageIcon imgIcon = new ImageIcon(mThumbNail); // 이미지를 담음
                                temporary_Right_Panel.imgLabel.setIcon(imageSetSize(imgIcon, 250, 250));
                            }
                            TemporaryDao.temporaryModity(new TemporaryDto(mtemporaryDt, mtemporaryPlace, mKind_temporary, mtemporaryNum, mDetail, mModifyDt, mThumbNail, temporaryDto.getNo()));
                        } else {
                            temporary_ChooseBoader(boaderIdx);
                        }

                        if (click) {
                            temporary_Right_Panel.setVisible(true);
                        }

                        resetTemporaryModifyData(); // 데이터 초기화
                        TemporaryPage.setTemporaryDataListPage(temporary_StartIndex, temporary_StartIndex + 12);
                        temporary_AddFile.setEnabled(false);
                        temporary_BoaderCombo.setEnabled(false);
                        temporary_DeleteBtn.setEnabled(true);
                        temporary_WriteBtn.setEnabled(true);

                    } else if (result == JOptionPane.NO_OPTION) {
                        JOptionPane.showMessageDialog(null, "취소되었습니다.", title, JOptionPane.INFORMATION_MESSAGE);
                        cardLayout.next(temporary_Right_Top_Panel.switchPanel);
                        cardLayout.next(temporary_Right_Panel.center_North_Top_Panel);
                        cardLayout.next(temporary_Right_Panel.center_Center_Center_Panel_Card);
                        temporary_AddFile.setEnabled(false);
                        temporary_BoaderCombo.setEnabled(false);
                        temporary_DeleteBtn.setEnabled(true);
                        temporary_WriteBtn.setEnabled(true);
                    }
                }
            }
        });

        temporary_DeleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (signNum == 1){
                    if (userDto. getUser_ID().equals(temporaryDto.getUserID())){
                        String defaultImgPath = "src/com/aroundThirty/imgFiles/그림1.png";
                        ImageIcon defaultImg = new ImageIcon(defaultImgPath);
                        int result = JOptionPane.showConfirmDialog(null, "게시글을 삭제 하시겠습니까?", title, JOptionPane.YES_NO_OPTION);
                        // 게시글 삭제 여부를 사용자에게 묻는 이벤트
                        if (result == JOptionPane.CLOSED_OPTION) {    // 사용자가 Yes 와 No 둘다 선택하지 않고 창을 끄는 경우
                        } else if (result == JOptionPane.YES_OPTION) { // 사용자가 게시글 삭제를 한 경우
                            // 로딩 창 구현
                            movedBoarderPage = new MovedBoarderPage();
                            Thread thread = new Thread(movedBoarderPage);
                            thread.start();
//                            JOptionPane.showMessageDialog(null, "게시글이 삭제되었습니다.", title, JOptionPane.PLAIN_MESSAGE);
                            TemporaryDao.temporaryDelete(new TemporaryDto(temporaryDto.getNo()));
                            temporary_Right_Panel.setVisible(false);
                            resetTemporaryDeleteData(); // 데이터 초기화
                            TemporaryPage.setTemporaryDataListPage(temporary_StartIndex, temporary_StartIndex + 12);

                            click = true;
                            // 삭제 쿼리 돌려야함
                        } else { //사용자가 No를 선택한 경우
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "계정 정보가 일치하지 않습니다.", title, JOptionPane.INFORMATION_MESSAGE);
                    }
                } else if (signNum == 0){
                    JOptionPane.showMessageDialog(null, "로그인이 필요합니다.", title, JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        temporary_WriteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (signNum == 1){
                    String act = e.getActionCommand();
                    if (act.equals("새 글 작성")) {
                        cardLayout.next(temporary_Right_Top_Panel.switchPanel2);
                        cardLayout.next(temporary_Right_Panel.center_North_Top_Panel);
                        cardLayout.next(temporary_Right_Panel.center_Center_Center_Panel_Card);

                        temporary_Right_Panel.temporaryDtTxt.setText("");
                        temporary_Right_Panel.temporaryPlaceTxt.setText("");
                        temporary_Right_Panel.temporaryKindTxt.setText("");
                        temporary_Right_Panel.temporaryNumTxt.setText("");
                        temporary_Right_Panel.postDtTxt.setText("");
                        temporary_Right_Panel.temporaryDetailTxt.setText("");
                        temporary_Right_Panel.imgLabel.setIcon(imageSetSize(defaultImg, 250, 250));

                        temporary_AddFile.setEnabled(true);
                        temporary_ModifyBtn.setEnabled(false);
                        temporary_BoaderCombo.setEnabled(false);
                        temporary_DeleteBtn.setEnabled(false);
                    }
                } else if (signNum == 0){
                    JOptionPane.showMessageDialog(null, "로그인이 필요합니다.", title, JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        temporary_PostBtn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String act = e.getActionCommand();
                if (act.equals("완료")) {
                    int confirm = JOptionPane.showConfirmDialog(null, "저장하시겠습니까?", title, JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        // 로딩 창 구현
                        movedBoarderPage = new MovedBoarderPage();
                        Thread thread = new Thread(movedBoarderPage);
                        thread.start();
//                        JOptionPane.showMessageDialog(null, "저장되었습니다.", title, JOptionPane.INFORMATION_MESSAGE);
                        cardLayout.next(temporary_Right_Top_Panel.switchPanel2);
                        cardLayout.next(temporary_Right_Panel.center_North_Top_Panel);
                        cardLayout.next(temporary_Right_Panel.center_Center_Center_Panel_Card);

                        String nTemporaryDt = temporary_Right_Panel.temporaryDtTxt.getText();
                        String nTemporaryPlace = temporary_Right_Panel.temporaryPlaceTxt.getText();
                        String nTemporaryKind = temporary_Right_Panel.temporaryKindTxt.getText();
                        String nTemporaryNum = temporary_Right_Panel.temporaryNumTxt.getText();
                        String nTemporaryDetail = temporary_Right_Panel.temporaryDetailTxt.getText();
                        String nTemporaryPost = now.toString();
                        String nID = userDto.getUser_ID();
                        String nImagePath = addImgPath;

                        ImageIcon nImage = new ImageIcon(nImagePath);
                        temporary_Right_Panel.imgLabel.setIcon(imageSetSize(nImage, 250, 250));

                        TemporaryDao.temporaryInput(new TemporaryDto(nTemporaryDt, nTemporaryPlace, nTemporaryKind, nTemporaryNum, nTemporaryDetail, nTemporaryPost, nImagePath, nID));

                        temporary_AddFile.setEnabled(false);
                        temporary_DeleteBtn.setEnabled(true);
                        temporary_ModifyBtn.setEnabled(true);

                        resetTemporaryModifyData(); // 데이터 초기화
                        TemporaryPage.setTemporaryDataListPage(temporary_StartIndex, temporary_StartIndex + 12);
                    } else {
                        JOptionPane.showMessageDialog(null, "취소되었습니다", title, JOptionPane.ERROR_MESSAGE);
                        cardLayout.next(temporary_Right_Top_Panel.switchPanel2);
                        cardLayout.next(temporary_Right_Panel.center_North_Top_Panel);
                        cardLayout.next(temporary_Right_Panel.center_Center_Center_Panel_Card);

                        temporary_AddFile.setEnabled(false);
                        temporary_DeleteBtn.setEnabled(true);
                        temporary_ModifyBtn.setEnabled(true);
                    }
                }
            }
        });

        temporary_AddFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                add_File_Window = new AddFileWindow();
            }
        });
    }


    public static void setTemporaryDataListPage(int temporary_StartIndex, int endIndex) {  // 버튼과 라벨에 데이터를 넣어준다.
        for (int i = 0, dataIdx = temporary_StartIndex; i < SIZE_ITEM; i++, dataIdx++) {
            if (temporaryListAll.size() > dataIdx) {
                if (temporaryListAll.get(dataIdx).getThumbnail_Img() == null){
                    postedPageNum = temporaryListAll.get(dataIdx).no;  // 게시물에 대한 번호를 반복문을 통해 전달함
                    btnList.get(i).setIcon(imageSetSize(defaultImg, 150, 120));
                    lblList.get(i).setText("[" + postedPageNum + "] " + "작성일 : " + temporaryListAll.get(dataIdx).post_Create_Date);
                }else{
                    postedPageNum = temporaryListAll.get(dataIdx).no;  // 게시물에 대한 번호를 반복문을 통해 전달함
                    btnList.get(i).setIcon(imageSetSize(temporaryCardDtoList.get(dataIdx).getDefaultImg(), 150, 120));
                    lblList.get(i).setText("[" + postedPageNum + "] " + "작성일 : " + temporaryListAll.get(dataIdx).post_Create_Date);
                }
            } else if (temporaryListAll.size() <= dataIdx) {
                btnList.get(i).setIcon(imageSetSize(defaultImg, 150, 120));
                lblList.get(i).setText("");
            }
        }
    }

    public static void resetTemporaryModifyData() {
        for (int i = 0; i < SIZE_ITEM; i++) {
            btnList.get(i).setIcon(imageSetSize(defaultImg, 150, 120));
            lblList.get(i).setText("");
        }
        temporaryListAll = TemporaryDao.temporarySelectAll();
        for (TemporaryDto Dto : temporaryListAll) {
            temporaryDto = Dto;
        }
        int temporaryListIdx = temporaryCardDtoList.size();
        for (int i = 0; i < temporaryListIdx; i++) {
            temporaryCardDtoList.remove(0);
        }
        for (int i = 0; i < temporaryListAll.size(); i++) {
            ImageIcon thumbnailImg = new ImageIcon(temporaryListAll.get(i).thumbnail_Img);
            if (temporaryListAll.get(i).thumbnail_Img == null) {
                temporaryCardDto = new TemporaryCardDto(defaultImg, i);
                temporaryCardDtoList.add(temporaryCardDto);
            } else {
                temporaryCardDto = new TemporaryCardDto(thumbnailImg, i);
                temporaryCardDtoList.add(temporaryCardDto);
            }
        }
    }

    public static void resetTemporaryDeleteData() {
        for (int i = 0; i < SIZE_ITEM; i++) {
            btnList.get(i).setIcon(imageSetSize(defaultImg, 150, 120));
            lblList.get(i).setText("");
        }
        temporaryListAll = TemporaryDao.temporarySelectAll();
        for (TemporaryDto Dto : temporaryListAll) {
            temporaryDto = Dto;
        }
        for (int i = 0; i < temporaryListAll.size(); i++) {
            ImageIcon img = new ImageIcon(temporaryListAll.get(i).thumbnail_Img);
            if (temporaryListAll.get(i).thumbnail_Img == null) {
                temporaryCardDto = new TemporaryCardDto(defaultImg, i);
                temporaryCardDtoList.set(i, temporaryCardDto);
            } else {
                temporaryCardDto = new TemporaryCardDto(img, i);
                temporaryCardDtoList.set(i, temporaryCardDto);
            }
        }
    }

    // Boader콤보박스에서 선택한 데이터를 가져온다.
    // 받아온 데이터와 게시판 정보를 비교한다.
    // 비교하여 일치하는 게시판에 쿼리문을 사용하여 DB에 넣어준다.
    // 기존 게시판에 있던 게시물은 쿼리문을 사용해 삭제한다.
    public static void temporary_ChooseBoader(int num) {
        String mTemporaryDt = temporary_Right_Panel.temporaryDtTxt.getText();
        String mTemporaryPlace = temporary_Right_Panel.temporaryPlaceTxt.getText();
        String mKind_Temporary = temporary_Right_Panel.temporaryKindTxt.getText();
        String mTemporaryNum = temporary_Right_Panel.temporaryNumTxt.getText();
        String mCreateDt = temporary_Right_Panel.postDtTxt.getText();
        String mModifyDt = now.toString();
        String mThumbNail;
        String mDetail = temporary_Right_Panel.temporaryDetailTxt.getText();
        String mUser_ID = temporaryDto.user_ID;


        if (addImgPath == null) {    // 썸네일을 새로 첨부하지 않은 경우에 대한 IF문
            mThumbNail = temporaryDto.getThumbnail_Img();
        } else {
            mThumbNail = addImgPath;
        }


        switch (num) {
            case 2:
                reportDto = new ReportDto(mTemporaryDt, mTemporaryPlace, mKind_Temporary, mTemporaryNum, mDetail, mCreateDt, mModifyDt, mThumbNail, mUser_ID);
                TemporaryDao.temporaryDelete(new TemporaryDto(temporaryDto.getNo()));
                ReportDao.reportChangeInput(reportDto);
                report_Page.resetReportModifyData();
                report_Page.setReportDataListPage(temporary_StartIndex, temporary_StartIndex + 12);
                break;
            case 3:
                missingDto = new MissingDto(mTemporaryDt, mTemporaryPlace, mKind_Temporary, mTemporaryNum, mDetail, mCreateDt, mModifyDt, mThumbNail, mUser_ID);
                TemporaryDao.temporaryDelete(new TemporaryDto(temporaryDto.getNo()));
                MissingDao.missingChangeInput(missingDto);
                missing_Page.resetMissingModifyData();
                missing_Page.setMissingDataListPage(missing_StartIndex, missing_StartIndex + 12);
                break;
        }
    }
}