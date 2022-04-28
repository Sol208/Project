package com.aroundThirty.View;

import com.aroundThirty.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static com.aroundThirty.Resource.FR.*;
import static com.aroundThirty.Resource.BR.*;


public class ReportPage extends JPanel {
    private static final long serialVersionUID = 1L;
    public static ArrayList<JPanel> paneList = new ArrayList<>();
    ReportPagingBtn reportPagingBtn = new ReportPagingBtn();
    JPanel centerPanel = new JPanel(new GridLayout(SIZE_ROW, SIZE_COL));    // SIZE_ROW, SIZE_COL로 행열 지정
    JScrollPane jScrollPane = new JScrollPane(centerPanel);
    static JPanel newPane;

    static {
        setDataListPanel(0, 12 + SIZE_ITEM);
    }

    public ReportPage() {
        reportPagingBtn.setBackground(pastelYellow);
        add(BorderLayout.CENTER, jScrollPane);
        jScrollPane.setPreferredSize(new Dimension(820, 650));
        jScrollPane.setBorder(null);
        jScrollPane.setBackground(pastelYellow);
        centerPanel.setBackground(pastelYellow);
        for (int i = 0; i < paneList.size(); i++) {
            centerPanel.add(paneList.get(i));   // panel에 index를 줘서 변수를 주듯 이름을 매김
        }
        add(BorderLayout.SOUTH, reportPagingBtn);
        setBackground(pastelYellow);
    }

    public static void setDataListPanel(int report_StartIndex, int endIndex) { // 버튼과 라벨을 넣어준다.
        for (int i = 0, dataIdx = report_StartIndex; i < SIZE_ITEM; i++, dataIdx++) {  // startIdx(페이지 별 첫번째 게시물의 no)를 SIZE_ITEM(12번) 반복한다.
            newPane = new JPanel(null); // 버튼과 라벨을 붙일 패널 생성
            if (reportListAll.size() > dataIdx) {   // 데이터의 size가 dataIdx보다 큰 경우에만 통과하는 if문
                postedPageNum = reportListAll.get(i).no;    //
            }
            if (reportListAll.size() > dataIdx) {
                if (reportListAll.get(dataIdx).getThumbnail_Img() == null) {
                    btnList.add(new JButton(imageSetSize(defaultImg, 150, 120)));
                    lblList.add(new JLabel("[" + postedPageNum + "] " + "작성일 : " + reportListAll.get(dataIdx).post_Create_Date));
                } else {
                    btnList.add(new JButton(imageSetSize(reportCardDtoList.get(dataIdx).getDefaultImg(), 150, 120)));
                    lblList.add(new JLabel("[" + postedPageNum + "] " + "작성일 : " + reportListAll.get(dataIdx).post_Create_Date));
                }
            } else if (reportListAll.size() <= dataIdx) {
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
                        selectBtnNum = reportCardDtoList.get(finali).getNo();   // 현재 페이지에서 선택한 게시물의 번호를 가져옴(12개 중 선택한 버튼의 idx)
                        report_Posted_ListIdx = (report_PageNum * 12) + reportCardDtoList.get(finali).getNo();    // ArrayList에서 데이터를 받아올 수 있도록 선택한 게시물의 인덱스를 만들어 줌
                        int choose_PostedPage_Num = reportListAll.get(report_Posted_ListIdx).no;   // 선택한 게시물에 대한 no를 담는 변수
                        reportDto = ReportDao.reportSelectOne(new ReportDto(choose_PostedPage_Num));
                        report_Right_Panel.reportDtVal.setText(reportDto.report_Date);
                        report_Right_Panel.reportDtTxt.setText(reportDto.report_Date);
                        report_Right_Panel.reportPlaceVal.setText(reportDto.report_Place);
                        report_Right_Panel.reportPlaceTxt.setText(reportDto.report_Place);
                        report_Right_Panel.reportKindVal.setText(reportDto.kind_Report);
                        report_Right_Panel.reportKindTxt.setText(reportDto.kind_Report);
                        report_Right_Panel.reportNumVal.setText(reportDto.phone_Num);
                        report_Right_Panel.reportNumTxt.setText(reportDto.phone_Num);
                        report_Right_Panel.postDtVal.setText(reportDto.post_Create_Date);
                        report_Right_Panel.postDtTxt.setText(reportDto.post_Create_Date);
                        report_Right_Panel.modifyDtVal.setText(reportDto.post_Modify_Date);
                        report_Right_Panel.modifyDtTxt.setText(reportDto.post_Modify_Date);
                        report_Right_Panel.reportDetail.setText(reportDto.detail);
                        report_Right_Panel.reportDetailTxt.setText(reportDto.detail);
                        report_Right_Panel.imgPath = reportDto.thumbnail_Img;
                        if (report_Right_Panel.imgPath == null) {
                            report_Right_Panel.imgLabel.setIcon(imageSetSize(defaultImg, 250, 250));
                        } else {
                            ImageIcon imgIcon = new ImageIcon(report_Right_Panel.imgPath); // 이미지를 담음
                            report_Right_Panel.imgLabel.setIcon(imageSetSize(imgIcon, 250, 250));
                        }
                        if (click) {
                            report_Right_Panel.setVisible(true);
                        }
                        btn.removeActionListener(null);
                    }
                }
            });
        }
        report_ModifyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (signNum == 1) {
                    if (userDto.getUser_ID().equals(reportDto.getUser_ID())) {
                        String act = e.getActionCommand();
                        if (act.equals("수정")) {
                            cardLayout.next(report_Right_Top_Panel.switchPanel);
                            cardLayout.next(report_Right_Panel.center_North_Top_Panel);
                            cardLayout.next(report_Right_Panel.center_Center_Center_Panel_Card);
                            report_AddFile.setEnabled(true);
                            report_BoaderCombo.setEnabled(true);
                            report_DeleteBtn.setEnabled(false);
                            report_WriteBtn.setEnabled(false);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "계정 정보가 일치하지 않습니다.", title, JOptionPane.INFORMATION_MESSAGE);
                    }
                } else if (signNum == 0) {
                    JOptionPane.showMessageDialog(null, "로그인이 필요합니다.", title, JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        report_PostBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String act = e.getActionCommand();
                if (act.equals("완료")) {
                    int result = JOptionPane.showConfirmDialog(null, "게시글을 수정 하시겠습니까?", title, JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.CLOSED_OPTION) {
                        report_AddFile.setEnabled(false);
                    } else if (result == JOptionPane.YES_OPTION) {
                        movedBoarderPage = new MovedBoarderPage();
                        Thread thread = new Thread(movedBoarderPage);
                        thread.start();
//                        JOptionPane.showMessageDialog(null, "수정되었습니다.", title, JOptionPane.INFORMATION_MESSAGE);
                        cardLayout.next(report_Right_Top_Panel.switchPanel);
                        cardLayout.next(report_Right_Panel.center_North_Top_Panel);
                        cardLayout.next(report_Right_Panel.center_Center_Center_Panel_Card);

                        // BoaderCombo에서 선택한 게시판의 번호를 받아온다.
                        // (BoaderCombo는 특정 게시판의 게시글을 현재 게시판 외의 게시판으로 옮기기 위해 만든 콤보박스)
                        int boaderIdx = report_BoaderCombo.getSelectedIndex() + 1;
                        // 사용자가 현재 보고 있는 게시판의 인덱스와, 콤보박스에서 선택한 게시판 인덱스의 일치 여부 확인
                        if (tabPaneIdx == boaderIdx) {
                            String mReportDt = report_Right_Panel.reportDtTxt.getText();
                            String mReportPlace = report_Right_Panel.reportPlaceTxt.getText();
                            String mKind_Report = report_Right_Panel.reportKindTxt.getText();
                            String mReportNum = report_Right_Panel.reportNumTxt.getText();
                            String mModifyDt = now.toString();
                            String mThumbNail;
                            String mDetail = report_Right_Panel.reportDetailTxt.getText();

                            if (addImgPath == null) {    // 썸네일을 새로 첨부하지 않은 경우에 대한 IF문
                                mThumbNail = reportDto.getThumbnail_Img();
                            } else {
                                mThumbNail = addImgPath;
                            }

                            reportDto.setReport_Date(mReportDt);
                            reportDto.setReport_Place(mReportPlace);
                            reportDto.setKind_Report(mKind_Report);
                            reportDto.setPhone_Num(mReportNum);
                            reportDto.setPost_Modify_Date(mModifyDt);
                            reportDto.setThumbnail_Img(mThumbNail);
                            reportDto.setDetail(mDetail);

                            report_Right_Panel.postDtVal.setText(reportDto.report_Date);
                            report_Right_Panel.postDtTxt.setText(reportDto.report_Date);
                            report_Right_Panel.reportPlaceVal.setText(reportDto.report_Place);
                            report_Right_Panel.reportPlaceTxt.setText(reportDto.report_Place);
                            report_Right_Panel.reportKindVal.setText(reportDto.kind_Report);
                            report_Right_Panel.reportKindTxt.setText(reportDto.kind_Report);
                            report_Right_Panel.reportNumVal.setText(reportDto.phone_Num);
                            report_Right_Panel.reportNumTxt.setText(reportDto.phone_Num);
                            report_Right_Panel.postDtVal.setText(reportDto.post_Create_Date);
                            report_Right_Panel.postDtTxt.setText(reportDto.post_Create_Date);
                            report_Right_Panel.modifyDtVal.setText(reportDto.post_Modify_Date);
                            report_Right_Panel.modifyDtTxt.setText(reportDto.post_Modify_Date);
                            report_Right_Panel.reportDetail.setText(reportDto.detail);
                            report_Right_Panel.reportDetailTxt.setText(reportDto.detail);
                            report_Right_Panel.imgPath = reportDto.thumbnail_Img;
                            if (report_Right_Panel.imgPath == null) {
                                report_Right_Panel.imgLabel.setIcon(imageSetSize(defaultImg, 250, 250));
                            } else {
                                ImageIcon imgIcon = new ImageIcon(mThumbNail); // 이미지를 담음
                                report_Right_Panel.imgLabel.setIcon(imageSetSize(imgIcon, 250, 250));
                            }
                            ReportDao.reportModify(new ReportDto(mReportDt, mReportPlace, mKind_Report, mReportNum, mDetail, "2022-02-14", mThumbNail, reportDto.getNo()));
                        } else {
                            chooseBoader(boaderIdx);
                        }

                        if (click) {
                            report_Right_Panel.setVisible(true);
                        }

                        resetReportModifyData(); // 데이터 초기화
                        ReportPage.setReportDataListPage(report_StartIndex, report_StartIndex + 12);
                        report_AddFile.setEnabled(false);
                        report_BoaderCombo.setEnabled(false);
                        report_DeleteBtn.setEnabled(true);
                        report_WriteBtn.setEnabled(true);

                    } else if (result == JOptionPane.NO_OPTION) {
                        JOptionPane.showMessageDialog(null, "취소되었습니다.", title, JOptionPane.INFORMATION_MESSAGE);
                        cardLayout.next(report_Right_Top_Panel.switchPanel);
                        cardLayout.next(report_Right_Panel.center_North_Top_Panel);
                        cardLayout.next(report_Right_Panel.center_Center_Center_Panel_Card);
                        report_AddFile.setEnabled(false);
                        report_BoaderCombo.setEnabled(false);
                        report_DeleteBtn.setEnabled(true);
                        report_WriteBtn.setEnabled(true);
                    }
                }
            }
        });

        report_DeleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (signNum == 1) {
                    if (userDto.getUser_ID().equals(reportDto.getUser_ID())) {
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
                            ReportDao.reportDelete(new ReportDto(reportDto.getNo()));
                            report_Right_Panel.setVisible(false);
                            resetDeleteData(); // 데이터 초기화
                            ReportPage.setReportDataListPage(report_StartIndex, report_StartIndex + 12);

                            click = true;
                            // 삭제 쿼리 돌려야함
                        } else { //사용자가 No를 선택한 경우
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "계정 정보가 일치하지 않습니다.", title, JOptionPane.INFORMATION_MESSAGE);
                    }
                } else if (signNum == 0) {
                    JOptionPane.showMessageDialog(null, "로그인이 필요합니다.", title, JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        report_WriteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (signNum == 1) {
                    String act = e.getActionCommand();
                    if (act.equals("새 글 작성")) {
                        cardLayout.next(report_Right_Top_Panel.switchPanel2);
                        cardLayout.next(report_Right_Panel.center_North_Top_Panel);
                        cardLayout.next(report_Right_Panel.center_Center_Center_Panel_Card);

                        report_Right_Panel.reportDtTxt.setText("");
                        report_Right_Panel.reportPlaceTxt.setText("");
                        report_Right_Panel.reportKindTxt.setText("");
                        report_Right_Panel.reportNumTxt.setText("");
                        report_Right_Panel.postDtTxt.setText("");
                        report_Right_Panel.reportDetailTxt.setText("");
                        report_Right_Panel.imgLabel.setIcon(imageSetSize(defaultImg, 250, 250));

                        report_AddFile.setEnabled(true);
                        report_ModifyBtn.setEnabled(false);
                        report_BoaderCombo.setEnabled(false);
                        report_DeleteBtn.setEnabled(false);
                    }
                } else if (signNum == 0) {
                    JOptionPane.showMessageDialog(null, "로그인이 필요합니다.", title, JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        report_PostBtn2.addActionListener(new ActionListener() {
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
                        cardLayout.next(report_Right_Top_Panel.switchPanel2);
                        cardLayout.next(report_Right_Panel.center_North_Top_Panel);
                        cardLayout.next(report_Right_Panel.center_Center_Center_Panel_Card);

                        String nReportDt = report_Right_Panel.reportDtTxt.getText();
                        String nReportPlace = report_Right_Panel.reportPlaceTxt.getText();
                        String nReportKind = report_Right_Panel.reportKindTxt.getText();
                        String nReportNum = report_Right_Panel.reportNumTxt.getText();
                        String nReportDetail = report_Right_Panel.reportDetailTxt.getText();
                        String nReportPost = now.toString();
                        String nID = userDto.getUser_ID();
                        String nImagePath = addImgPath;

                        ImageIcon nImage = new ImageIcon(nImagePath);
                        report_Right_Panel.imgLabel.setIcon(imageSetSize(nImage, 250, 250));

                        ReportDao.reportInput(new ReportDto(nReportDt, nReportPlace, nReportKind, nReportNum, nReportDetail, nReportPost, nImagePath, nID));

                        report_AddFile.setEnabled(false);
                        report_DeleteBtn.setEnabled(true);
                        report_ModifyBtn.setEnabled(true);

                        resetReportModifyData(); // 데이터 초기화
                        ReportPage.setReportDataListPage(report_StartIndex, report_StartIndex + 12);
                    } else {
                        JOptionPane.showMessageDialog(null, "취소되었습니다", title, JOptionPane.ERROR_MESSAGE);
                        cardLayout.next(report_Right_Top_Panel.switchPanel2);
                        cardLayout.next(report_Right_Panel.center_North_Top_Panel);
                        cardLayout.next(report_Right_Panel.center_Center_Center_Panel_Card);

                        report_AddFile.setEnabled(false);
                        report_DeleteBtn.setEnabled(true);
                        report_ModifyBtn.setEnabled(true);
                    }
                }
            }
        });

        report_AddFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                add_File_Window = new AddFileWindow();
            }
        });

    }

    // 최근 입력 게시물이 먼저 조회 되도록 수정 해야함
    // 증감식을 -- 로 바꿔야함

    // 어레이리스트로 이미지와를 받아오는 리스트를 만들고 별개로
    // 넘버만 저장하는 리스트를 만들어서 삭제 및 수정시 리스트의 인덱스를 뽑아온다.

    public static void setReportDataListPage(int report_StartIndex, int endIndex) {  // 버튼과 라벨에 데이터를 넣어준다.
        for (int i = 0, dataIdx = report_StartIndex; i < SIZE_ITEM; i++, dataIdx++) {
            if (reportListAll.size() > dataIdx) {
                if (reportListAll.get(dataIdx).getThumbnail_Img() == null) {
                    postedPageNum = reportListAll.get(dataIdx).no;  // 게시물에 대한 번호를 반복문을 통해 전달함
                    btnList.get(i).setIcon(imageSetSize(defaultImg, 150, 120));
                    lblList.get(i).setText("[" + postedPageNum + "] " + "작성일 : " + reportListAll.get(dataIdx).post_Create_Date);
                } else {
                    postedPageNum = reportListAll.get(dataIdx).no;  // 게시물에 대한 번호를 반복문을 통해 전달함
                    btnList.get(i).setIcon(imageSetSize(reportCardDtoList.get(dataIdx).getDefaultImg(), 150, 120));
                    lblList.get(i).setText("[" + postedPageNum + "] " + "작성일 : " + reportListAll.get(dataIdx).post_Create_Date);
                }
            } else if (reportListAll.size() <= dataIdx) {
                btnList.get(i).setIcon(imageSetSize(defaultImg, 150, 120));
                lblList.get(i).setText("");
            }
        }
    }

    public static void resetReportModifyData() {
        for (int i = 0; i < SIZE_ITEM; i++) {
            btnList.get(i).setIcon(imageSetSize(defaultImg, 150, 120));
            lblList.get(i).setText("");
        }
        reportListAll = ReportDao.reportSelectAll();
        for (ReportDto Dto : reportListAll) {
            reportDto = Dto;
        }
        int reportListIdx = reportCardDtoList.size();
        for (int i = 0; i < reportListIdx; i++) {
            reportCardDtoList.remove(0);
        }
        for (int i = 0; i < reportListAll.size(); i++) {
            ImageIcon thumbnailImg = new ImageIcon(reportListAll.get(i).thumbnail_Img);
            if (reportListAll.get(i).thumbnail_Img == null) {
                reportCardDto = new ReportCardDto(defaultImg, i);
                reportCardDtoList.add(reportCardDto);
            } else {
                reportCardDto = new ReportCardDto(thumbnailImg, i);
                reportCardDtoList.add(reportCardDto);
            }
        }
    }

    public static void resetDeleteData() {
        for (int i = 0; i < SIZE_ITEM; i++) {
            btnList.get(i).setIcon(imageSetSize(defaultImg, 150, 120));
            lblList.get(i).setText("");
        }
        reportListAll = ReportDao.reportSelectAll();
        for (ReportDto Dto : reportListAll) {
            reportDto = Dto;
        }
        for (int i = 0; i < reportListAll.size(); i++) {
            ImageIcon img = new ImageIcon(reportListAll.get(i).thumbnail_Img);
            if (reportListAll.get(i).thumbnail_Img == null) {
                reportCardDto = new ReportCardDto(defaultImg, i);
                reportCardDtoList.set(i, reportCardDto);
            } else {
                reportCardDto = new ReportCardDto(img, i);
                reportCardDtoList.set(i, reportCardDto);
            }
        }
    }

    // Boader콤보박스에서 선택한 데이터를 가져온다.
    // 받아온 데이터와 게시판 정보를 비교한다.
    // 비교하여 일치하는 게시판에 쿼리문을 사용하여 DB에 넣어준다.
    // 기존 게시판에 있던 게시물은 쿼리문을 사용해 삭제한다.
    public static void chooseBoader(int num) {
        String mReportDt = report_Right_Panel.reportDtTxt.getText();
        String mReportPlace = report_Right_Panel.reportPlaceTxt.getText();
        String mKind_Report = report_Right_Panel.reportKindTxt.getText();
        String mReportNum = report_Right_Panel.reportNumTxt.getText();
        String mCreateDt = report_Right_Panel.postDtTxt.getText();
        String mModifyDt = now.toString();
        String mThumbNail;
        String mDetail = report_Right_Panel.reportDetailTxt.getText();
        String mUser_ID = reportDto.user_ID;


        if (addImgPath == null) {    // 썸네일을 새로 첨부하지 않은 경우에 대한 IF문
            mThumbNail = reportDto.getThumbnail_Img();
        } else {
            mThumbNail = addImgPath;
        }


        switch (num) {
            case 2:
                missingDto = new MissingDto(mReportDt, mReportPlace, mKind_Report, mReportNum, mDetail, mCreateDt, mModifyDt, mThumbNail, mUser_ID);
                ReportDao.reportDelete(new ReportDto(reportDto.getNo()));
                MissingDao.missingChangeInput(missingDto);
                missing_Page.resetMissingModifyData();
                missing_Page.setMissingDataListPage(missing_StartIndex, missing_StartIndex + 12);
                break;
            case 3:
                temporaryDto = new TemporaryDto(mReportDt, mReportPlace, mKind_Report, mReportNum, mDetail, mCreateDt, mModifyDt, mThumbNail, mUser_ID);
                ReportDao.reportDelete(new ReportDto(reportDto.getNo()));
                TemporaryDao.temporaryChangeInput(temporaryDto);
                temporary_Page.resetTemporaryModifyData();
                temporary_Page.setTemporaryDataListPage(temporary_StartIndex, temporary_StartIndex + 12);
                break;
        }
    }
}