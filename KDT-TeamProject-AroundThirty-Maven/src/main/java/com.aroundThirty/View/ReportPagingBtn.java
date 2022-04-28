package com.aroundThirty.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static com.aroundThirty.Resource.FR.*;
import static com.aroundThirty.Resource.BR.*;


public class ReportPagingBtn extends JPanel {
    private static final long serialVersionUID = 1L;
    public static ArrayList<JButton> btnList = new ArrayList<JButton>();
    int size = 0;

    static {
        for (int i = 1; i <= SIZE_ITEM; i++) {
            btnList.add(new JButton("" + i));
        }
    }


    public ReportPagingBtn() {
        if (reportListAll.size() % 12 == 0) {   // 리스트의 사이즈와 게시물의 최대갯수의 나머지가 0일때
            size = reportListAll.size() / 12;   // 리스트 사이즈를 12로 나눈값만큼 버튼의 사이즈가 생성된다.
        } else if (reportListAll.size() % 12 != 0) {    // 리스트의 사이즈와 게시물의 최대갯수의 나머지가 0이 아닐때
            size = (reportListAll.size() / 12) + 1; // 리스트 사이즈를 12로 나눈 값에 +1을하여 버튼의 사이즈가 생성된다.
        }
        for (int i = 0; i < size; i++) {
            add(btnList.get(i));
            btnList.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (e.getSource() instanceof JButton) { // e.getsource로 받아온 객체가 JButton의 상속을 받으면 true 반환
                        // instanceof : 객체타입을 확인하는 연산자로 형변환 가능 여부를 확인하며 true, false 로 반환 주로 상속관계에서 부모객체인지 자식객체인지 확인하는데 사용
                        JButton btn = (JButton) e.getSource();   // e.getsource로 받아온 객체의 속성을 btn에 담는다.
                        report_PageNum = (Integer.parseInt(btn.getText()) - 1); // 하단부 버튼의 텍스트(숫자)를 Idx와 맞추기 위해 -1을 한뒤 가져온다.
                        report_StartIndex = SIZE_ITEM * report_PageNum;   // 페이지에 따라 해당페이지 첫번째 데이터 Idx를 확인한다.
                        report_Page.setReportDataListPage(report_StartIndex, report_StartIndex + 12); // ReportPage 클래스의 setDataListPage 메소드 사용
                        btn.removeActionListener(null);
                    }
                }
            });
        }
    }
}
