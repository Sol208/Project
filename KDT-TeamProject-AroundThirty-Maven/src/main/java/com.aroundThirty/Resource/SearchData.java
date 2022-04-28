package com.aroundThirty.Resource;

import com.aroundThirty.JdbcUtil;
import com.aroundThirty.View.CenterPanel;
import com.aroundThirty.View.SearchPage;
import com.aroundThirty.model.XmlDao;
import com.aroundThirty.model.XmlDto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.StringTokenizer;

import static com.aroundThirty.Resource.FR.*;
import static com.aroundThirty.Resource.BR.*;
import static com.aroundThirty.model.XmlDto.*;
import static com.aroundThirty.model.XmlDao.*;

public class SearchData {
    String searchKind;
    ArrayList<String> kindArr;

    String searchGender;
    ArrayList<String> genderArr;

    StringTokenizer tokenizerGetGender;
    String divideGenderWord;

    String searchLocation;
    ArrayList<String> locationArr;

    int count;

    public static ArrayList<XmlDto> totalArr = new ArrayList<>();

    public SearchData() throws SQLException {
        // "서울", "부산", "대구", "인천", "광주", "대전", "울산", "세종", "경기", "강원", "충북", "충남", "전북", "전남", "경북", "경남", "제주"
        getLocation = locationCombo.getSelectedItem().toString();
        getLocationDetail = seoulCombo.getSelectedItem().toString();
        getLocationAll = String.format("%s %s", getLocation, getLocationDetail);
        getKind = kindCombo.getSelectedItem().toString();
        getGender = genderCombo.getSelectedItem().toString();
        tokenizerGetGender = new StringTokenizer(getGender, "()");
        while (tokenizerGetGender.hasMoreTokens()) {
            String temp = tokenizerGetGender.nextToken();
            divideGenderWord = tokenizerGetGender.nextToken(); // ComboBox에 있는 Gender를 M 또는 F로 분리
        }
        xmlDtoListAll = XmlDao.xmlSelectAll();

        count = 0;
        totalArr.clear();
        for (int i = 0; i < xmlDtoListAll.size(); i++) {
            if ((xmlDtoListAll.get(i).orgNm).contains(getLocation) && (xmlDtoListAll.get(i).orgNm).contains(getLocationDetail) && (xmlDtoListAll.get(i).sexCd).contains(divideGenderWord) && (xmlDtoListAll.get(i).kindCd).contains(getKind)) {
                totalArr.add(xmlDtoListAll.get(i));
                count++;
            }
        } // for문 끝
        if (count > 0) {
            SearchPage.setDataListPage(totalArr, 0, 12);
            JOptionPane.showMessageDialog(null, String.format("검색 결과 %d개를 찾았습니다.", count), title, JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "검색결과가 없습니다.", title, JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
