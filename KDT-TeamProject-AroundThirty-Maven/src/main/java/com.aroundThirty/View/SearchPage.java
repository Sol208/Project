package com.aroundThirty.View;

import com.aroundThirty.model.XmlDao;
import com.aroundThirty.model.XmlDto;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.aroundThirty.Resource.BR.*;
import static com.aroundThirty.Resource.FR.*;
import static com.aroundThirty.Resource.SearchData.totalArr;

public class SearchPage extends JPanel {
    private static final long serialVersionUID = 1L;
    public static ArrayList<JPanel> paneList = new ArrayList<>();
    public static ArrayList<JButton> searchBtnList = new ArrayList<>();
    public static ArrayList<JLabel> mouseOverLabelList = new ArrayList<>();
    public static ArrayList<JLabel> lblList = new ArrayList<>();
    SearchPagingBtn searchPagingBtn = new SearchPagingBtn();
    JPanel centerPanel = new JPanel(new GridLayout(SIZE_ROW, SIZE_COL));    // SIZE_ROW, SIZE_COL로 행열 지정
    JScrollPane jScrollPane = new JScrollPane(centerPanel);
    static ImageIcon thumbnailIcon;
    static String defaultImgPath = "src/main/java/com.aroundThirty/imgFiles/그림1.png";
    static ImageIcon defaultImg = new ImageIcon(defaultImgPath);
    static ImageIcon imgIcon;

    static {
        setDataListPanel(0, 12 + SIZE_ITEM);
    }

    public SearchPage() {
        searchPagingBtn.setBackground(pastelYellow);
        add(BorderLayout.CENTER, jScrollPane);
        jScrollPane.setPreferredSize(new Dimension(820, 650));
        jScrollPane.setBorder(null);
        jScrollPane.setBackground(pastelYellow);
        centerPanel.setBackground(pastelYellow);

        for (int i = 0; i < paneList.size(); i++) {
            centerPanel.add(paneList.get(i));   // panel에 index를 줘서 변수를 주듯 이름을 매김
        }
        add(BorderLayout.SOUTH, searchPagingBtn);
        setBackground(pastelYellow);
    }

    public static void setDataListPanel(int mpStartIndex, int endIndex) { // 버튼과 라벨을 넣어준다.

        for (int i = 0, dataIdx = mpStartIndex; i < SIZE_ITEM; i++, dataIdx++) {
            JPanel newPane = new JPanel(null);
            Image thumbnailImgIcon = null;
            try {
                URL url = new URL(xmlDtoListAll.get(dataIdx).getFileName());
                thumbnailImgIcon = ImageIO.read(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            JPanel mouseOverPan = new JPanel(new GridBagLayout());
            JLabel mouseOverLabel = new JLabel(xmlDtoListAll.get(dataIdx).getProcessState());
            mouseOverLabelList.add(mouseOverLabel);
            mouseOverLabel.setForeground(Color.WHITE);
            mouseOverLabel.setBackground(grayTransparency);
            mouseOverPan.add(mouseOverLabel);
            mouseOverPan.setBackground(grayTransparency);
            mouseOverPan.setPreferredSize(new Dimension(150, 120));
            mouseOverPan.setVisible(false);
            thumbnailIcon = new ImageIcon(thumbnailImgIcon);

            searchBtnList.add(new JButton(imageSetSize(thumbnailIcon, 150, 120)));
            searchBtnList.get(i).add(mouseOverPan);
            lblList.add(new JLabel("[" + (xmlCardDtoList.get(dataIdx).getNo() + 1) + "] " + "발견일 : " + xmlDtoListAll.get(dataIdx).getHappenDt()));
            searchBtnList.get(i).setBounds(60, 0, 150, 120);   // 위치는 따로 지정 해주지 않고 크기만 지정 해줌
            lblList.get(i).setBounds(60, 120, 150, 20);  // 위치는 따로 지정 해주지 않고 크기만 지정 해줌
            newPane.add(searchBtnList.get(i));
            newPane.add(lblList.get(i));
            newPane.setBackground(pastelYellow);
            paneList.add(newPane);

            int finali = i;
            searchBtnList.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() instanceof JButton) { // e.getsource로 받아온 객체가 JButton의 상속을 받으면 true 반환
                        // instanceof : 객체타입을 확인하는 연산자로 형변환 가능 여부를 확인하며 true, false 로 반환 주로 상속관계에서 부모객체인지 자식객체인지 확인하는데 사용
                        JButton btn = (JButton) e.getSource();   // e.getsource로 받아온 객체의 속성을 btn에 담는다.
                        int postedPageNum = (pageNum * 12) + totalArr.get(finali).getNo();
                        xmlDto = XmlDao.xmlDtoSelectOne(new XmlDto(postedPageNum));
                        searchRightPanel.happenDtDetailLabel.setText(xmlDto.getHappenDt());
                        searchRightPanel.happenPlaceDetailLabel.setText(xmlDto.getHappenPlace());
                        searchRightPanel.happenKindDetailLabel.setText(xmlDto.getKindCd());
                        searchRightPanel.ageDetailLabel.setText(xmlDto.getAge());
                        searchRightPanel.weightDetailLabel.setText(xmlDto.getWeight());
                        searchRightPanel.phone_NumDetailLabel.setText(xmlDto.getPhone_Num());
                        searchRightPanel.specialMarkDetailLabel.setText(xmlDto.getSpecialMark());
                        searchRightPanel.processDetailLabel.setText(xmlDto.getProcessState());
                        Image imageDetail = null;
                        try {
                            URL url = new URL(xmlDto.getThumbnail_Img());
                            imageDetail = ImageIO.read(url);
                        } catch (IOException ea) {
                            ea.printStackTrace();
                        }
                        imgIcon = new ImageIcon(imageDetail); // 이미지를 담음
                        searchRightPanel.imgLabel.setPreferredSize(new Dimension(480, 360));
                        searchRightPanel.imgLabel.setIcon(imgIcon);
                        if (click) {
                            searchRightPanel.setVisible(true);
//                            click = false;
                        }
                        btn.removeActionListener(null);
                    }
                }
            });

            int finalDataIdx = dataIdx;
            searchBtnList.get(i).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    if (totalArr.size() > finalDataIdx) {
                        mouseOverPan.setVisible(true);
                        searchBtnList.get(finalDataIdx).revalidate();
                        searchBtnList.get(finalDataIdx).repaint();
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    mouseOverPan.setVisible(false);
                    searchBtnList.get(finalDataIdx).revalidate();
                    searchBtnList.get(finalDataIdx).repaint();
                }
            });
        }
    }

    // 최근 입력 게시물이 먼저 조회 되도록 수정 해야함
    // 증감식을 -- 로 바꿔야함
    public static void setDataListPage(List<XmlDto> xmlDtoListAll, int mpStartIndex, int endIndex) {  // 버튼과 라벨에 데이터를 넣어준다.
        for (int i = 0, dataIdx = mpStartIndex; i < SIZE_ITEM; i++, dataIdx++) {

            Image thumbnailImgIcon = null;
            try {
                URL url = null;
                if (totalArr.size() > dataIdx) {
                    url = new URL(totalArr.get(dataIdx).getFileName());
                    thumbnailImgIcon = ImageIO.read(url);
                    thumbnailIcon = new ImageIcon(thumbnailImgIcon);
                } else if (totalArr.size() <= dataIdx) {

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (totalArr.size() > dataIdx) {
                postedPageNum = totalArr.get(dataIdx).no;
                mouseOverLabelList.get(i).setText(totalArr.get(dataIdx).getProcessState());
                searchBtnList.get(i).setIcon(imageSetSize(thumbnailIcon, 150, 120));
                lblList.get(i).setText("[" + (xmlCardDtoList.get(dataIdx).getNo() + 1) + "] " + "발견일 : " + totalArr.get(dataIdx).getHappenDt());
            } else if (totalArr.size() <= dataIdx) {
                searchBtnList.get(i).setIcon(imageSetSize(defaultImg, 150, 120));
                lblList.get(i).setText("");
            }
        }
    }
}