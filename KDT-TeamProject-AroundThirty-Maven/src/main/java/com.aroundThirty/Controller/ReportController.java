package com.aroundThirty.Controller;

import com.aroundThirty.model.ReportCardDto;
import com.aroundThirty.model.ReportDao;
import com.aroundThirty.model.ReportDto;

import javax.swing.*;


import static com.aroundThirty.Resource.BR.*;
import static com.aroundThirty.Resource.FR.*;


public class ReportController {
    String defaultImgPath = "src/com/aroundThirty/imgFiles/그림1.png";
    ImageIcon defaultImg = new ImageIcon(defaultImgPath);


    public ReportController() {
        showAll();

    }

    public void showAll(){
        reportListAll = ReportDao.reportSelectAll(); // 전체 게시글에 대한 데이터 리스트 생성
        if (!reportListAll.isEmpty()){
            reportDto = reportListAll.get(reportListAll.size() - 1);
        }

        // 게시글 버튼에 들어갈 이미지와 게시글 번호에 대한 리스트를 만듦
        for (int i = 0; i < reportListAll.size(); i++) {
            ImageIcon img = new ImageIcon(reportListAll.get(i).thumbnail_Img);
            if (reportListAll.get(i).thumbnail_Img == null) {
                reportCardDto = new ReportCardDto(defaultImg, i);
                reportCardDtoList.add(reportCardDto);
            } else {
                reportCardDto = new ReportCardDto(img, i);
                reportCardDtoList.add(reportCardDto);
            }
        }
    }
}
