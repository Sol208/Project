package com.aroundThirty.View;

import com.aroundThirty.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static com.aroundThirty.Resource.BR.*;
import static com.aroundThirty.Resource.FR.*;
import static com.aroundThirty.View.MainView.container;


public class MissingPage extends JPanel {
    private static final long serialVersionUID = 1L;
    public static ArrayList<JPanel> paneList = new ArrayList<>();
    public static ArrayList<JButton> btnList = new ArrayList<>();
    public static ArrayList<JLabel> lblList = new ArrayList<>();
    MissingPagingBtn missingPagingBtn = new MissingPagingBtn();
    JPanel centerPanel = new JPanel(new GridLayout(SIZE_ROW, SIZE_COL));    // SIZE_ROW, SIZE_COL로 행열 지정
    JScrollPane jScrollPane = new JScrollPane(centerPanel);

    static {
        setDataListPanel(0, 12 + SIZE_ITEM);
    }

    public MissingPage() {
        missingPagingBtn.setBackground(pastelYellow);
        add(BorderLayout.CENTER, jScrollPane);
        jScrollPane.setPreferredSize(new Dimension(820, 650));
        jScrollPane.setBorder(null);
        jScrollPane.setBackground(pastelYellow);
        centerPanel.setBackground(pastelYellow);
        for (int i = 0; i < paneList.size(); i++) {
            centerPanel.add(paneList.get(i));   // panel에 index를 줘서 변수를 주듯 이름을 매김
        }
        add(BorderLayout.SOUTH, missingPagingBtn);
        setBackground(pastelYellow);
    }

    public static void setDataListPanel(int startIndex, int endIndex) { // 버튼과 라벨을 넣어준다.
        for (int i = 0, dataIdx = startIndex; i < SIZE_ITEM; i++, dataIdx++) {
            JPanel newPane = new JPanel(null);
            if (missingListAll.size() > dataIdx) {   // 데이터의 size가 dataIdx보다 큰 경우에만 통과하는 if문
                postedPageNum = missingListAll.get(i).no;    //
            }
            if (missingListAll.size() > dataIdx) {
                if (missingListAll.get(dataIdx).getThumbnail_Img() == null) {
                    btnList.add(new JButton(imageSetSize(defaultImg, 150, 120))); // carddatalist클래스의 이미지를 끌고와서 버튼에 넣어줌 근데 12번째 이미지 부터 넣어줌?
                    lblList.add(new JLabel("[" + postedPageNum + "] " + "작성일 : " + missingListAll.get(dataIdx).post_Create_Date));  // carddatalist클래스의 이미지를 끌고와서 버튼에 넣어줌 근데 12번째 이미지 부터 넣어줌?
                } else {
                    btnList.add(new JButton(imageSetSize(missingCardDtoList.get(dataIdx).getDefaultImg(), 150, 120))); // carddatalist클래스의 이미지를 끌고와서 버튼에 넣어줌 근데 12번째 이미지 부터 넣어줌?
                    lblList.add(new JLabel("[" + postedPageNum + "] " + "작성일 : " + missingListAll.get(dataIdx).post_Create_Date));  // carddatalist클래스의 이미지를 끌고와서 버튼에 넣어줌 근데 12번째 이미지 부터 넣어줌?
                }
            } else if (missingListAll.size() <= dataIdx) {
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
                        selectBtnNum = missingCardDtoList.get(finali).getNo();   // 현재 페이지에서 선택한 게시물의 번호를 가져옴(12개 중 선택한 버튼의 idx)
                        missing_Posted_ListIdx = (missing_PageNum * 12) + missingCardDtoList.get(finali).getNo();    // ArrayList에서 데이터를 받아올 수 있도록 선택한 게시물의 인덱스를 만들어 줌
                        int choose_PostedPage_Num = missingListAll.get(missing_Posted_ListIdx).no;   // 선택한 게시물에 대한 no를 담는 변수
                        missingDto = MissingDao.missingSelectOne(new MissingDto(choose_PostedPage_Num));
                        missing_Right_Panel.missingDtVal.setText(missingDto.missing_Date);
                        missing_Right_Panel.missingDtTxt.setText(missingDto.missing_Date);
                        missing_Right_Panel.missingPlaceVal.setText(missingDto.missing_Place);
                        missing_Right_Panel.missingPlaceTxt.setText(missingDto.missing_Place);
                        missing_Right_Panel.missingKindVal.setText(missingDto.kind_Missing);
                        missing_Right_Panel.missingKindTxt.setText(missingDto.kind_Missing);
                        missing_Right_Panel.missingNumVal.setText(missingDto.phone_Num);
                        missing_Right_Panel.missingNumTxt.setText(missingDto.phone_Num);
                        missing_Right_Panel.postDtVal.setText(missingDto.post_Create_Date);
                        missing_Right_Panel.postDtTxt.setText(missingDto.post_Create_Date);
                        missing_Right_Panel.modifyDtVal.setText(missingDto.post_Modify_Date);
                        missing_Right_Panel.modifyDtTxt.setText(missingDto.post_Modify_Date);
                        missing_Right_Panel.missingDetail.setText(missingDto.detail);
                        missing_Right_Panel.missingDetailTxt.setText(missingDto.detail);
                        missing_Right_Panel.imgPath = missingDto.thumbnail_Img;
                        if (missing_Right_Panel.imgPath == null) {
                            missing_Right_Panel.imgLabel.setIcon(imageSetSize(defaultImg, 250, 250));
                        } else {
                            ImageIcon imgIcon = new ImageIcon(missing_Right_Panel.imgPath); // 이미지를 담음
                            missing_Right_Panel.imgLabel.setIcon(imageSetSize(imgIcon, 250, 250));
                        }
                        if (click) {
                            missing_Right_Panel.setVisible(true);
                        }
                        btn.removeActionListener(null);
                    }
                }
            });
        }

        missing_ModifyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (signNum == 1) {
                    if (userDto.getUser_ID().equals(missingDto.getUser_ID())) {
                        String act = e.getActionCommand();
                        if (act.equals("수정")) {
                            cardLayout.next(missing_Right_Top_Panel.switchPanel);
                            cardLayout.next(missing_Right_Panel.center_North_Top_Panel);
                            cardLayout.next(missing_Right_Panel.center_Center_Center_Panel_Card);
                            missing_AddFile.setEnabled(true);
                            missing_BoaderCombo.setEnabled(true);
                            missing_DeleteBtn.setEnabled(false);
                            missing_WriteBtn.setEnabled(false);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "계정 정보가 일치하지 않습니다.", title, JOptionPane.INFORMATION_MESSAGE);
                    }
                } else if (signNum == 0) {
                    JOptionPane.showMessageDialog(null, "로그인이 필요합니다.", title, JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        missing_PostBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String act = e.getActionCommand();
                if (act.equals("완료")) {
                    int result = JOptionPane.showConfirmDialog(null, "게시글을 수정 하시겠습니까?", title, JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.CLOSED_OPTION) {
                        missing_AddFile.setEnabled(false);
                    } else if (result == JOptionPane.YES_OPTION) {
                        movedBoarderPage = new MovedBoarderPage();
                        Thread thread = new Thread(movedBoarderPage);
                        thread.start();
//                        JOptionPane.showMessageDialog(null, "수정되었습니다.", title, JOptionPane.INFORMATION_MESSAGE);
                        cardLayout.next(missing_Right_Top_Panel.switchPanel);
                        cardLayout.next(missing_Right_Panel.center_North_Top_Panel);
                        cardLayout.next(missing_Right_Panel.center_Center_Center_Panel_Card);

                        int boaderIdx = missing_BoaderCombo.getSelectedIndex() + 1;

                        if (tabPaneIdx == 1) {
                            String mmissingDt = missing_Right_Panel.missingDtTxt.getText();
                            String mmissingPlace = missing_Right_Panel.missingPlaceTxt.getText();
                            String mKind_missing = missing_Right_Panel.missingKindTxt.getText();
                            String mmissingNum = missing_Right_Panel.missingNumTxt.getText();
                            String mModifyDt = now.toString();
                            String mThumbNail;
                            String mDetail = missing_Right_Panel.missingDetailTxt.getText();

                            if (addImgPath == null) {    // 썸네일을 새로 첨부하지 않은 경우에 대한 IF문
                                mThumbNail = missingDto.getThumbnail_Img();
                            } else {
                                mThumbNail = addImgPath;
                            }

                            missingDto.setMissing_Date(mmissingDt);
                            missingDto.setMissing_Place(mmissingPlace);
                            missingDto.setKind_Missing(mKind_missing);
                            missingDto.setPhone_Num(mmissingNum);
                            missingDto.setPost_Modify_Date(mModifyDt);
                            missingDto.setThumbnail_Img(mThumbNail);
                            missingDto.setDetail(mDetail);

                            missing_Right_Panel.postDtVal.setText(missingDto.missing_Date);
                            missing_Right_Panel.postDtTxt.setText(missingDto.missing_Date);
                            missing_Right_Panel.missingPlaceVal.setText(missingDto.missing_Place);
                            missing_Right_Panel.missingPlaceTxt.setText(missingDto.missing_Place);
                            missing_Right_Panel.missingKindVal.setText(missingDto.kind_Missing);
                            missing_Right_Panel.missingKindTxt.setText(missingDto.kind_Missing);
                            missing_Right_Panel.missingNumVal.setText(missingDto.phone_Num);
                            missing_Right_Panel.missingNumTxt.setText(missingDto.phone_Num);
                            missing_Right_Panel.postDtVal.setText(missingDto.post_Create_Date);
                            missing_Right_Panel.postDtTxt.setText(missingDto.post_Create_Date);
                            missing_Right_Panel.modifyDtVal.setText(missingDto.post_Modify_Date);
                            missing_Right_Panel.modifyDtTxt.setText(missingDto.post_Modify_Date);
                            missing_Right_Panel.missingDetail.setText(missingDto.detail);
                            missing_Right_Panel.missingDetailTxt.setText(missingDto.detail);
                            missing_Right_Panel.imgPath = missingDto.thumbnail_Img;
                            if (missing_Right_Panel.imgPath == null) {
                                missing_Right_Panel.imgLabel.setIcon(imageSetSize(defaultImg, 250, 250));
                            } else {
                                ImageIcon imgIcon = new ImageIcon(mThumbNail); // 이미지를 담음
                                missing_Right_Panel.imgLabel.setIcon(imageSetSize(imgIcon, 250, 250));
                            }

                            MissingDao.missingModify(new MissingDto(mmissingDt, mmissingPlace, mKind_missing, mmissingNum, mDetail, mModifyDt, mThumbNail, missingDto.getNo()));
                        } else {
                            missing_ChooseBoader(boaderIdx);
                        }

                        if (click) {
                            missing_Right_Panel.setVisible(true);
                        }

                        resetMissingModifyData(); // 데이터 초기화
                        MissingPage.setMissingDataListPage(missing_StartIndex, missing_StartIndex + 12);
                        missing_AddFile.setEnabled(false);
                        missing_BoaderCombo.setEnabled(false);
                        missing_DeleteBtn.setEnabled(true);
                        missing_WriteBtn.setEnabled(true);

                    } else if (result == JOptionPane.NO_OPTION) {
                        JOptionPane.showMessageDialog(null, "취소되었습니다.", title, JOptionPane.INFORMATION_MESSAGE);
                        cardLayout.next(missing_Right_Top_Panel.switchPanel);
                        cardLayout.next(missing_Right_Panel.center_North_Top_Panel);
                        cardLayout.next(missing_Right_Panel.center_Center_Center_Panel_Card);
                        missing_AddFile.setEnabled(false);
                        missing_BoaderCombo.setEnabled(false);
                        missing_DeleteBtn.setEnabled(true);
                        missing_WriteBtn.setEnabled(true);
                    }
                }
            }
        });

        missing_DeleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (signNum == 1){
                    if (userDto.getUser_ID().equals(missingDto.getUser_ID())){
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
                            MissingDao.missingDelete(new MissingDto(missingDto.getNo()));
                            missing_Right_Panel.setVisible(false);
                            resetMissingDeleteData(); // 데이터 초기화
                            MissingPage.setMissingDataListPage(missing_StartIndex, missing_StartIndex + 12);

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

        missing_WriteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (signNum == 1) {
                    String act = e.getActionCommand();
                    if (act.equals("새 글 작성")) {
                        cardLayout.next(missing_Right_Top_Panel.switchPanel2);
                        cardLayout.next(missing_Right_Panel.center_North_Top_Panel);
                        cardLayout.next(missing_Right_Panel.center_Center_Center_Panel_Card);

                        missing_Right_Panel.missingDtTxt.setText("");
                        missing_Right_Panel.missingPlaceTxt.setText("");
                        missing_Right_Panel.missingKindTxt.setText("");
                        missing_Right_Panel.missingNumTxt.setText("");
                        missing_Right_Panel.postDtTxt.setText("");
                        missing_Right_Panel.missingDetailTxt.setText("");
                        missing_Right_Panel.imgLabel.setIcon(imageSetSize(defaultImg, 250, 250));

                        missing_AddFile.setEnabled(true);
                        missing_ModifyBtn.setEnabled(false);
                        missing_BoaderCombo.setEnabled(false);
                        missing_DeleteBtn.setEnabled(false);
                    }
                } else if (signNum == 0){
                    JOptionPane.showMessageDialog(null, "로그인이 필요합니다.", title, JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        missing_PostBtn2.addActionListener(new ActionListener() {
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
                        cardLayout.next(missing_Right_Top_Panel.switchPanel2);
                        cardLayout.next(missing_Right_Panel.center_North_Top_Panel);
                        cardLayout.next(missing_Right_Panel.center_Center_Center_Panel_Card);

                        String nMissingDt = missing_Right_Panel.missingDtTxt.getText();
                        String nMissingPlace = missing_Right_Panel.missingPlaceTxt.getText();
                        String nMissingKind = missing_Right_Panel.missingKindTxt.getText();
                        String nMissingNum = missing_Right_Panel.missingNumTxt.getText();
                        String nMissingDetail = missing_Right_Panel.missingDetailTxt.getText();
                        String nMissingPost = now.toString();
                        String nID = userDto.getUser_ID();
                        String nImagePath = addImgPath;

                        ImageIcon nImage = new ImageIcon(nImagePath);
                        missing_Right_Panel.imgLabel.setIcon(imageSetSize(nImage, 250, 250));

                        MissingDao.missingInput(new MissingDto(nMissingDt, nMissingPlace, nMissingKind, nMissingNum, nMissingDetail, nMissingPost, nImagePath, nID));

                        missing_AddFile.setEnabled(false);
                        missing_DeleteBtn.setEnabled(true);
                        missing_ModifyBtn.setEnabled(true);

                        resetMissingModifyData(); // 데이터 초기화
                        MissingPage.setMissingDataListPage(missing_StartIndex, missing_StartIndex + 12);
                    } else {
                        JOptionPane.showMessageDialog(null, "취소되었습니다", title, JOptionPane.ERROR_MESSAGE);
                        cardLayout.next(missing_Right_Top_Panel.switchPanel2);
                        cardLayout.next(missing_Right_Panel.center_North_Top_Panel);
                        cardLayout.next(missing_Right_Panel.center_Center_Center_Panel_Card);

                        missing_AddFile.setEnabled(false);
                        missing_DeleteBtn.setEnabled(true);
                        missing_ModifyBtn.setEnabled(true);
                    }
                }
            }
        });

        missing_AddFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                add_File_Window = new AddFileWindow();
            }
        });

    }


    public static void setMissingDataListPage(int missing_StartIndex, int endIndex) {  // 버튼과 라벨에 데이터를 넣어준다.
        for (int i = 0, dataIdx = missing_StartIndex; i < SIZE_ITEM; i++, dataIdx++) {
            if (missingListAll.size() > dataIdx) {
                if (missingListAll.get(dataIdx).getThumbnail_Img() == null) {
                    postedPageNum = missingListAll.get(dataIdx).no;  // 게시물에 대한 번호를 반복문을 통해 전달함
                    btnList.get(i).setIcon(imageSetSize(defaultImg, 150, 120));
                    lblList.get(i).setText("[" + postedPageNum + "] " + "작성일 : " + missingListAll.get(dataIdx).post_Create_Date);
                }
                postedPageNum = missingListAll.get(dataIdx).no;  // 게시물에 대한 번호를 반복문을 통해 전달함
                btnList.get(i).setIcon(imageSetSize(missingCardDtoList.get(dataIdx).getDefaultImg(), 150, 120));
                lblList.get(i).setText("[" + postedPageNum + "] " + "작성일 : " + missingListAll.get(dataIdx).post_Create_Date);
            } else if (missingListAll.size() <= dataIdx) {
                btnList.get(i).setIcon(imageSetSize(defaultImg, 150, 120));
                lblList.get(i).setText("");
            }
        }
    }

    public static void resetMissingModifyData() {
        for (int i = 0; i < SIZE_ITEM; i++) {
            btnList.get(i).setIcon(imageSetSize(defaultImg, 150, 120));
            lblList.get(i).setText("");
        }
        missingListAll = MissingDao.missingSelectAll();
        for (MissingDto Dto : missingListAll) {
            missingDto = Dto;
        }
        int missingListIdx = missingCardDtoList.size();
        for (int i = 0; i < missingListIdx; i++) {
            missingCardDtoList.remove(0);
        }
        for (int i = 0; i < missingListAll.size(); i++) {
            ImageIcon thumbnailImg = new ImageIcon(missingListAll.get(i).thumbnail_Img);
            if (missingListAll.get(i).thumbnail_Img == null) {
                missingCardDto = new MissingCardDto(defaultImg, i);
                missingCardDtoList.add(missingCardDto);
            } else {
                missingCardDto = new MissingCardDto(thumbnailImg, i);
                missingCardDtoList.add(missingCardDto);
            }
        }
    }

    public static void resetMissingDeleteData() {
        for (int i = 0; i < SIZE_ITEM; i++) {
            btnList.get(i).setIcon(imageSetSize(defaultImg, 150, 120));
            lblList.get(i).setText("");
        }
        missingListAll = MissingDao.missingSelectAll();
        for (MissingDto Dto : missingListAll) {
            missingDto = Dto;
        }
        for (int i = 0; i < missingListAll.size(); i++) {
            ImageIcon img = new ImageIcon(missingListAll.get(i).thumbnail_Img);
            if (missingListAll.get(i).thumbnail_Img == null) {
                missingCardDto = new MissingCardDto(defaultImg, i);
                missingCardDtoList.set(i, missingCardDto);
            } else {
                missingCardDto = new MissingCardDto(img, i);
                missingCardDtoList.set(i, missingCardDto);
            }
        }
    }

    // Boader콤보박스에서 선택한 데이터를 가져온다.
    // 받아온 데이터와 게시판 정보를 비교한다.
    // 비교하여 일치하는 게시판에 쿼리문을 사용하여 DB에 넣어준다.
    // 기존 게시판에 있던 게시물은 쿼리문을 사용해 삭제한다.
    public static void missing_ChooseBoader(int num) {
        String mmissingDt = missing_Right_Panel.missingDtTxt.getText();
        String mmissingPlace = missing_Right_Panel.missingPlaceTxt.getText();
        String mKind_missing = missing_Right_Panel.missingKindTxt.getText();
        String mmissingNum = missing_Right_Panel.missingNumTxt.getText();
        String mCreateDt = missing_Right_Panel.postDtTxt.getText();
        String mModifyDt = now.toString();
        String mThumbNail;
        String mDetail = missing_Right_Panel.missingDetailTxt.getText();
        String mUser_ID = missingDto.user_ID;


        if (addImgPath == null) {    // 썸네일을 새로 첨부하지 않은 경우에 대한 IF문
            mThumbNail = missingDto.getThumbnail_Img();
        } else {
            mThumbNail = addImgPath;
        }

        switch (num) {
            case 2:
                reportDto = new ReportDto(mmissingDt, mmissingPlace, mKind_missing, mmissingNum, mDetail, mCreateDt, mModifyDt, mThumbNail, mUser_ID);
                MissingDao.missingDelete(new MissingDto(missingDto.getNo()));
                ReportDao.reportChangeInput(reportDto);
                report_Page.resetReportModifyData();
                report_Page.setReportDataListPage(temporary_StartIndex, temporary_StartIndex + 12);
                break;
            case 3:
                temporaryDto = new TemporaryDto(mmissingDt, mmissingPlace, mKind_missing, mmissingNum, mDetail, mCreateDt, mModifyDt, mThumbNail, mUser_ID);
                MissingDao.missingDelete(new MissingDto(missingDto.getNo()));
                TemporaryDao.temporaryChangeInput(temporaryDto);
                temporary_Page.resetTemporaryModifyData();
                temporary_Page.setTemporaryDataListPage(temporary_StartIndex, temporary_StartIndex + 12);
                break;
        }
    }

}