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


public class AdoptPage extends JPanel {
    private static final long serialVersionUID = 1L;
    public static ArrayList<JPanel> paneList = new ArrayList<>();
    public static ArrayList<JButton> btnList = new ArrayList<>();
    public static ArrayList<JLabel> lblList = new ArrayList<>();
    AdoptPagingBtn adoptPagingBtn = new AdoptPagingBtn();
    JPanel centerPanel = new JPanel(new GridLayout(SIZE_ROW, SIZE_COL));    // SIZE_ROW, SIZE_COL로 행열 지정
    JScrollPane jScrollPane = new JScrollPane(centerPanel);

    static {
        setDataListPanel(0, 12 + SIZE_ITEM);
    }

    public AdoptPage() {
        adoptPagingBtn.setBackground(pastelYellow);
        add(BorderLayout.CENTER, jScrollPane);
        jScrollPane.setPreferredSize(new Dimension(820, 650));
        jScrollPane.setBorder(null);
        jScrollPane.setBackground(pastelYellow);
        centerPanel.setBackground(pastelYellow);
        for (int i = 0; i < paneList.size(); i++) {
            centerPanel.add(paneList.get(i));   // panel에 index를 줘서 변수를 주듯 이름을 매김
        }
        add(BorderLayout.SOUTH, adoptPagingBtn);
        setBackground(pastelYellow);
    }

    public static void setDataListPanel(int startIndex, int endIndex) { // 버튼과 라벨을 넣어준다.
        for (int i = 0, dataIdx = startIndex; i < SIZE_ITEM; i++, dataIdx++) {
            JPanel newPane = new JPanel(null);
            if (adoptListAll.size() > dataIdx) {   // 데이터의 size가 dataIdx보다 큰 경우에만 통과하는 if문
                postedPageNum = adoptListAll.get(i).no;    //
            }
            if (adoptListAll.size() > dataIdx) {
                if (adoptListAll.get(dataIdx).getThumbnail_Img() == null){
                    btnList.add(new JButton(imageSetSize(defaultImg, 150, 120))); // carddatalist클래스의 이미지를 끌고와서 버튼에 넣어줌 근데 12번째 이미지 부터 넣어줌?
                    lblList.add(new JLabel("[" + postedPageNum + "] " + "작성일 : " + adoptListAll.get(dataIdx).post_Create_Date));  // carddatalist클래스의 이미지를 끌고와서 버튼에 넣어줌 근데 12번째 이미지 부터 넣어줌?
                }else{
                    btnList.add(new JButton(imageSetSize(adoptCardDtoList.get(dataIdx).getDefaultImg(), 150, 120))); // carddatalist클래스의 이미지를 끌고와서 버튼에 넣어줌 근데 12번째 이미지 부터 넣어줌?
                    lblList.add(new JLabel("[" + postedPageNum + "] " + "작성일 : " + adoptListAll.get(dataIdx).post_Create_Date));  // carddatalist클래스의 이미지를 끌고와서 버튼에 넣어줌 근데 12번째 이미지 부터 넣어줌?
                }
            } else if (adoptListAll.size() <= dataIdx) {
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
                        selectBtnNum = adoptCardDtoList.get(finali).getNo();   // 현재 페이지에서 선택한 게시물의 번호를 가져옴(12개 중 선택한 버튼의 idx)
                        adopt_Posted_ListIdx = (adopt_PageNum * 12) + adoptCardDtoList.get(finali).getNo();    // ArrayList에서 데이터를 받아올 수 있도록 선택한 게시물의 인덱스를 만들어 줌
                        int choose_PostedPage_Num = adoptListAll.get(adopt_Posted_ListIdx).no;   // 선택한 게시물에 대한 no를 담는 변수
                        adoptDto = AdoptDao.adoptSelectOne(new AdoptDto(choose_PostedPage_Num));
                        adopt_Right_Panel.adoptPlaceVal.setText(adoptDto.adopt_Place);
                        adopt_Right_Panel.adoptPlaceTxt.setText(adoptDto.adopt_Place);
                        adopt_Right_Panel.adoptKindVal.setText(adoptDto.kind_Adopt);
                        adopt_Right_Panel.adoptKindTxt.setText(adoptDto.kind_Adopt);
                        adopt_Right_Panel.adoptNumVal.setText(adoptDto.phone_Num);
                        adopt_Right_Panel.adoptNumTxt.setText(adoptDto.phone_Num);
                        adopt_Right_Panel.postDtVal.setText(adoptDto.post_Create_Date);
                        adopt_Right_Panel.postDtTxt.setText(adoptDto.post_Create_Date);
                        adopt_Right_Panel.modifyDtVal.setText(adoptDto.post_Modify_Date);
                        adopt_Right_Panel.modifyDtTxt.setText(adoptDto.post_Modify_Date);
                        adopt_Right_Panel.adoptDetail.setText(adoptDto.detail);
                        adopt_Right_Panel.adoptDetailTxt.setText(adoptDto.detail);
                        adopt_Right_Panel.imgPath = adoptDto.thumbnail_Img;
                        if (adopt_Right_Panel.imgPath == null){
                            adopt_Right_Panel.imgLabel.setIcon(imageSetSize(defaultImg,250,250));
                        }else{
                            ImageIcon imgIcon = new ImageIcon(adopt_Right_Panel.imgPath); // 이미지를 담음
                            adopt_Right_Panel.imgLabel.setIcon(imageSetSize(imgIcon, 250, 250));
                        }
                        if (click) {
                            adopt_Right_Panel.setVisible(true);
                        }
                        container.revalidate();
                        container.repaint();
                        btn.removeActionListener(null);
                    }
                }
            });
        }

        adopt_ModifyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (signNum == 1){
                    if (userDto.getUser_ID().equals(adoptDto.getUser_ID())){
                        String act = e.getActionCommand();
                        if (act.equals("수정")) {
                            cardLayout.next(adopt_Right_Top_Panel.switchPanel);
                            cardLayout.next(adopt_Right_Panel.center_North_Top_Panel);
                            cardLayout.next(adopt_Right_Panel.center_Center_Center_Panel_Card);
                            adopt_AddFile.setEnabled(true);
                            adopt_DeleteBtn.setEnabled(false);
                            adopt_WriteBtn.setEnabled(false);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "계정 정보가 일치하지 않습니다.", title, JOptionPane.INFORMATION_MESSAGE);
                    }
                } else if (signNum == 0){
                    JOptionPane.showMessageDialog(null, "로그인이 필요합니다.", title, JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });

        adopt_PostBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String act = e.getActionCommand();
                if (act.equals("완료")) {
                    int result = JOptionPane.showConfirmDialog(null, "게시글을 수정 하시겠습니까?", title, JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.CLOSED_OPTION) {
                        adopt_AddFile.setEnabled(false);
                    } else if (result == JOptionPane.YES_OPTION) {
                        movedBoarderPage = new MovedBoarderPage();
                        Thread thread = new Thread(movedBoarderPage);
                        thread.start();
//                        JOptionPane.showMessageDialog(null, "수정되었습니다.", title, JOptionPane.INFORMATION_MESSAGE);
                        cardLayout.next(adopt_Right_Top_Panel.switchPanel);
                        cardLayout.next(adopt_Right_Panel.center_North_Top_Panel);
                        cardLayout.next(adopt_Right_Panel.center_Center_Center_Panel_Card);

                        String madoptPlace = adopt_Right_Panel.adoptPlaceTxt.getText();
                        String mKind_adopt = adopt_Right_Panel.adoptKindTxt.getText();
                        String madoptNum = adopt_Right_Panel.adoptNumTxt.getText();
                        String mModifyDt = now.toString();
                        String mThumbNail;
                        String mDetail = adopt_Right_Panel.adoptDetailTxt.getText();

                        if (addImgPath == null) {    // 썸네일을 새로 첨부하지 않은 경우에 대한 IF문
                            mThumbNail = adoptDto.getThumbnail_Img();
                        } else {
                            mThumbNail = addImgPath;
                        }

                        adoptDto.setAdopt_Place(madoptPlace);
                        adoptDto.setKind_Adopt(mKind_adopt);
                        adoptDto.setPhone_Num(madoptNum);
                        adoptDto.setPost_Modify_Date(mModifyDt);
                        adoptDto.setThumbnail_Img(mThumbNail);
                        adoptDto.setDetail(mDetail);

                        adopt_Right_Panel.adoptPlaceVal.setText(adoptDto.adopt_Place);
                        adopt_Right_Panel.adoptPlaceTxt.setText(adoptDto.adopt_Place);
                        adopt_Right_Panel.adoptKindVal.setText(adoptDto.kind_Adopt);
                        adopt_Right_Panel.adoptKindTxt.setText(adoptDto.kind_Adopt);
                        adopt_Right_Panel.adoptNumVal.setText(adoptDto.phone_Num);
                        adopt_Right_Panel.adoptNumTxt.setText(adoptDto.phone_Num);
                        adopt_Right_Panel.postDtVal.setText(adoptDto.post_Create_Date);
                        adopt_Right_Panel.postDtTxt.setText(adoptDto.post_Create_Date);
                        adopt_Right_Panel.modifyDtVal.setText(adoptDto.post_Modify_Date);
                        adopt_Right_Panel.modifyDtTxt.setText(adoptDto.post_Modify_Date);
                        adopt_Right_Panel.adoptDetail.setText(adoptDto.detail);
                        adopt_Right_Panel.adoptDetailTxt.setText(adoptDto.detail);
                        adopt_Right_Panel.imgPath = adoptDto.thumbnail_Img;
                        if (adopt_Right_Panel.imgPath == null){
                            adopt_Right_Panel.imgLabel.setIcon(imageSetSize(defaultImg, 250, 250));
                        }else{
                            ImageIcon imgIcon = new ImageIcon(mThumbNail); // 이미지를 담음
                            adopt_Right_Panel.imgLabel.setIcon(imageSetSize(imgIcon, 250, 250));
                        }
                        AdoptDao.adoptModify(new AdoptDto(madoptPlace, mKind_adopt, madoptNum, mDetail, mModifyDt, mThumbNail, adoptDto.getNo()));

                        if (click) {
                            adopt_Right_Panel.setVisible(true);
                        }

                        resetAdoptModifyData(); // 데이터 초기화
                        AdoptPage.setAdoptDataListPage(adopt_StartIndex, adopt_StartIndex + 12);
                        adopt_AddFile.setEnabled(false);
                        adopt_DeleteBtn.setEnabled(true);
                        adopt_WriteBtn.setEnabled(true);

                    } else if (result == JOptionPane.NO_OPTION) {
                        JOptionPane.showMessageDialog(null, "취소되었습니다.", title, JOptionPane.INFORMATION_MESSAGE);
                        cardLayout.next(adopt_Right_Top_Panel.switchPanel);
                        cardLayout.next(adopt_Right_Panel.center_North_Top_Panel);
                        cardLayout.next(adopt_Right_Panel.center_Center_Center_Panel_Card);
                        adopt_AddFile.setEnabled(false);
                        adopt_DeleteBtn.setEnabled(true);
                        adopt_WriteBtn.setEnabled(true);
                    }
                }
            }
        });

        adopt_DeleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (signNum == 1){
                    if (userDto.getUser_ID().equals(adoptDto.getUser_ID())){
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
                            AdoptDao.adoptDelete(new AdoptDto(adoptDto.getNo()));
                            adopt_Right_Panel.setVisible(false);
                            resetAdoptDeleteData(); // 데이터 초기화
                            AdoptPage.setAdoptDataListPage(adopt_StartIndex, adopt_StartIndex + 12);

                            click = true;
                            // 삭제 쿼리 돌려야함
                        } else { //사용자가 No를 선택한 경우
                        }
                    } else{
                        JOptionPane.showMessageDialog(null, "계정 정보가 일치하지 않습니다.", title, JOptionPane.INFORMATION_MESSAGE);
                    }
                } else if (signNum == 0) {
                    JOptionPane.showMessageDialog(null, "로그인이 필요합니다.", title, JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });

        adopt_WriteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (signNum == 1){
                    String act = e.getActionCommand();
                    if (act.equals("새 글 작성")) {
                        cardLayout.next(adopt_Right_Top_Panel.switchPanel2);
                        cardLayout.next(adopt_Right_Panel.center_North_Top_Panel);
                        cardLayout.next(adopt_Right_Panel.center_Center_Center_Panel_Card);

                        adopt_Right_Panel.adoptPlaceTxt.setText("");
                        adopt_Right_Panel.adoptKindTxt.setText("");
                        adopt_Right_Panel.adoptNumTxt.setText("");
                        adopt_Right_Panel.postDtTxt.setText("");
                        adopt_Right_Panel.adoptDetailTxt.setText("");
                        adopt_Right_Panel.imgLabel.setIcon(imageSetSize(defaultImg, 250, 250));

                        adopt_AddFile.setEnabled(true);
                        adopt_DeleteBtn.setEnabled(false);
                        adopt_ModifyBtn.setEnabled(false);
                    }
                } else if (signNum == 0){
                    JOptionPane.showMessageDialog(null, "로그인이 필요합니다.", title, JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        adopt_PostBtn2.addActionListener(new ActionListener() {
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
                        cardLayout.next(adopt_Right_Top_Panel.switchPanel2);
                        cardLayout.next(adopt_Right_Panel.center_North_Top_Panel);
                        cardLayout.next(adopt_Right_Panel.center_Center_Center_Panel_Card);

                        String nAdoptPlace = adopt_Right_Panel.adoptPlaceTxt.getText();
                        String nAdoptKind = adopt_Right_Panel.adoptKindTxt.getText();
                        String nAdoptNum = adopt_Right_Panel.adoptNumTxt.getText();
                        String nAdoptDetail = adopt_Right_Panel.adoptDetailTxt.getText();
                        String nAdoptPost = now.toString();
                        String nID = userDto.getUser_ID();
                        String nImagePath = addImgPath;

                        ImageIcon nImage = new ImageIcon(nImagePath);
                        adopt_Right_Panel.imgLabel.setIcon(imageSetSize(nImage, 250, 250));

                        AdoptDao.adoptInput(new AdoptDto(nAdoptPlace, nAdoptKind, nAdoptNum, nAdoptDetail, nAdoptPost, nImagePath, nID));

                        adopt_AddFile.setEnabled(false);
                        adopt_DeleteBtn.setEnabled(true);
                        adopt_ModifyBtn.setEnabled(true);

                        resetAdoptModifyData(); // 데이터 초기화
                        AdoptPage.setAdoptDataListPage(adopt_StartIndex, adopt_StartIndex + 12);
                    } else {
                        JOptionPane.showMessageDialog(null, "취소되었습니다", title, JOptionPane.ERROR_MESSAGE);
                        cardLayout.next(adopt_Right_Top_Panel.switchPanel2);
                        cardLayout.next(adopt_Right_Panel.center_North_Top_Panel);
                        cardLayout.next(adopt_Right_Panel.center_Center_Center_Panel_Card);

                        adopt_AddFile.setEnabled(false);
                        adopt_DeleteBtn.setEnabled(true);
                        adopt_ModifyBtn.setEnabled(true);
                    }
                }
            }
        });

        adopt_AddFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                add_File_Window = new AddFileWindow();
            }
        });
    }


    public static void setAdoptDataListPage(int adopt_StartIndex, int endIndex) {  // 버튼과 라벨에 데이터를 넣어준다.
        for (int i = 0, dataIdx = adopt_StartIndex; i < SIZE_ITEM; i++, dataIdx++) {
            if (adoptListAll.size() > dataIdx) {
                if (adoptListAll.get(dataIdx).getThumbnail_Img() == null){
                    postedPageNum = adoptListAll.get(dataIdx).no;  // 게시물에 대한 번호를 반복문을 통해 전달함
                    btnList.get(i).setIcon(imageSetSize(defaultImg, 150, 120));
                    lblList.get(i).setText("[" + postedPageNum + "] " + "작성일 : " + adoptListAll.get(dataIdx).post_Create_Date);
                }else{
                    postedPageNum = adoptListAll.get(dataIdx).no;  // 게시물에 대한 번호를 반복문을 통해 전달함
                    btnList.get(i).setIcon(imageSetSize(adoptCardDtoList.get(dataIdx).getDefaultImg(), 150, 120));
                    lblList.get(i).setText("[" + postedPageNum + "] " + "작성일 : " + adoptListAll.get(dataIdx).post_Create_Date);
                }
            } else if (adoptListAll.size() <= dataIdx) {
                btnList.get(i).setIcon(imageSetSize(defaultImg, 150, 120));
                lblList.get(i).setText("");
            }
        }
    }

    public static void resetAdoptModifyData() {
        for (int i = 0; i < SIZE_ITEM; i++) {
            btnList.get(i).setIcon(imageSetSize(defaultImg, 150, 120));
            lblList.get(i).setText("");
        }
        adoptListAll = AdoptDao.adoptSelectAll();
        for (AdoptDto Dto : adoptListAll) {
            adoptDto = Dto;
        }
        int adoptListIdx = adoptCardDtoList.size();
        for (int i = 0; i < adoptListIdx; i++) {
            adoptCardDtoList.remove(0);
        }
        for (int i = 0; i < adoptListAll.size(); i++) {
            ImageIcon thumbnailImg = new ImageIcon(adoptListAll.get(i).thumbnail_Img);
            if (adoptListAll.get(i).thumbnail_Img == null) {
                adoptCardDto = new AdoptCardDto(defaultImg, i);
                adoptCardDtoList.add(adoptCardDto);
            } else {
                adoptCardDto = new AdoptCardDto(thumbnailImg, i);
                adoptCardDtoList.add(adoptCardDto);
            }
        }
    }

    public static void resetAdoptDeleteData() {
        for (int i = 0; i < SIZE_ITEM; i++) {
            btnList.get(i).setIcon(imageSetSize(defaultImg, 150, 120));
            lblList.get(i).setText("");
        }
        adoptListAll = AdoptDao.adoptSelectAll();
        for (AdoptDto Dto : adoptListAll) {
            adoptDto = Dto;
        }
        for (int i = 0; i < adoptListAll.size(); i++) {
            ImageIcon img = new ImageIcon(adoptListAll.get(i).thumbnail_Img);
            if (adoptListAll.get(i).thumbnail_Img == null) {
                adoptCardDto = new AdoptCardDto(defaultImg, i);
                adoptCardDtoList.set(i, adoptCardDto);
            } else {
                adoptCardDto = new AdoptCardDto(img, i);
                adoptCardDtoList.set(i, adoptCardDto);
            }
        }
    }
}
