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

import static com.aroundThirty.Resource.BR.*;
import static com.aroundThirty.Resource.FR.*;


public class CenterPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    public static ArrayList<JPanel> paneList = new ArrayList<>();
    public static ArrayList<JButton> btnList = new ArrayList<>();
    public static ArrayList<JLabel> mouseOverLabelList = new ArrayList<>();
    public static ArrayList<JLabel> lblList = new ArrayList<>();
    MainPagingBtn mainPagingBtn = new MainPagingBtn();
    JPanel centerPanel = new JPanel(new GridLayout(SIZE_ROW, SIZE_COL));    // SIZE_ROW, SIZE_COL로 행열 지정
    JScrollPane jScrollPane = new JScrollPane(centerPanel);
    static ImageIcon thumbnailIcon;
    static String defaultImgPath = "src/main/java/com.aroundThirty/imgFiles/그림1.png";
    static ImageIcon defaultImg = new ImageIcon(defaultImgPath);
    static ImageIcon imgIcon;

    static {
        setDataListPanel(0, 12 + SIZE_ITEM);
    }

    public CenterPanel() {
        mainPagingBtn.setBackground(pastelYellow);
        add(BorderLayout.CENTER, jScrollPane);
        jScrollPane.setPreferredSize(new Dimension(820, 650));
        jScrollPane.setBorder(null);
        jScrollPane.setBackground(pastelYellow);
        centerPanel.setBackground(pastelYellow);

        for (int i = 0; i < paneList.size(); i++) {
            centerPanel.add(paneList.get(i));   // panel에 index를 줘서 변수를 주듯 이름을 매김
        }
        add(BorderLayout.SOUTH, mainPagingBtn);
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

            btnList.add(new JButton(imageSetSize(thumbnailIcon, 150, 120)));
            btnList.get(i).add(mouseOverPan);
            lblList.add(new JLabel("[" + (xmlCardDtoList.get(dataIdx).getNo() + 1) + "] " + "발견일 : " + xmlDtoListAll.get(dataIdx).getHappenDt()));
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
                        int postedPageNum = (pageNum * 12) + xmlCardDtoList.get(finali).getNo() + 1;
                        xmlDto = XmlDao.xmlDtoSelectOne(new XmlDto(postedPageNum));
                        main_Right_Panel.happenDtDetailLabel.setText(xmlDto.getHappenDt());
                        main_Right_Panel.happenPlaceDetailLabel.setText(xmlDto.getHappenPlace());
                        main_Right_Panel.happenKindDetailLabel.setText(xmlDto.getKindCd());
                        main_Right_Panel.phone_NumDetailLabel.setText(xmlDto.getPhone_Num());
                        main_Right_Panel.specialMarkDetailLabel.setText(xmlDto.getSpecialMark());
                        main_Right_Panel.processDetailLabel.setText(xmlDto.getProcessState());
                        Image imageDetail = null;
                        try {
                            URL url = new URL(xmlDto.getThumbnail_Img());
                            imageDetail = ImageIO.read(url);
                        } catch (IOException ea) {
                            ea.printStackTrace();
                        }
                        imgIcon = new ImageIcon(imageDetail); // 이미지를 담음
                        main_Right_Panel.imgLabel.setPreferredSize(new Dimension(480, 360));
                        main_Right_Panel.imgLabel.setIcon(imgIcon);
                        if (click) {
                            main_Right_Panel.setVisible(true);
//                            click = false;
                        }
                        btn.removeActionListener(null);
                    }
                }
            });
            int finalDataIdx = dataIdx;
            btnList.get(i).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    mouseOverPan.setVisible(true);
                    btnList.get(finalDataIdx).revalidate();
                    btnList.get(finalDataIdx).repaint();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    mouseOverPan.setVisible(false);
                    btnList.get(finalDataIdx).revalidate();
                    btnList.get(finalDataIdx).repaint();
                }
            });
        }
    }

    // 최근 입력 게시물이 먼저 조회 되도록 수정 해야함
    // 증감식을 -- 로 바꿔야함
    public static void setDataListPage(int mpStartIndex, int endIndex) {  // 버튼과 라벨에 데이터를 넣어준다.
        for (int i = 0, dataIdx = mpStartIndex; i < SIZE_ITEM; i++, dataIdx++) {

            Image thumbnailImgIcon = null;
            try {
                URL url = null;
                if (xmlDtoListAll.size() > dataIdx) {
                    url = new URL(xmlDtoListAll.get(dataIdx).getFileName());
                    thumbnailImgIcon = ImageIO.read(url);
                    thumbnailIcon = new ImageIcon(thumbnailImgIcon);
                } else if (xmlDtoListAll.size() <= dataIdx) {

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (xmlDtoListAll.size() > dataIdx) {
                postedPageNum = xmlDtoListAll.get(dataIdx).no;
                mouseOverLabelList.get(i).setText(xmlDtoListAll.get(dataIdx).getProcessState());
                btnList.get(i).setIcon(imageSetSize(thumbnailIcon, 150, 120));
                lblList.get(i).setText("[" + (xmlCardDtoList.get(dataIdx).getNo() + 1) + "] " + "발견일 : " + xmlDtoListAll.get(dataIdx).getHappenDt());
            } else if (xmlDtoListAll.size() <= dataIdx) {
                btnList.get(i).setIcon(imageSetSize(defaultImg, 150, 120));
                lblList.get(i).setText("");
            }
        }
    }
}