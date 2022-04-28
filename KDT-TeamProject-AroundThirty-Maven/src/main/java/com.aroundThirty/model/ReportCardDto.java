package com.aroundThirty.model;

import javax.swing.*;

public class ReportCardDto {
    private int no;
    private ImageIcon defaultImg;


    public ReportCardDto(ImageIcon defaultImg, int no) {
        this.defaultImg = defaultImg;
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public ImageIcon getDefaultImg() {
        return defaultImg;
    }

    public void setDefaultImg(ImageIcon defaultImg) {
        this.defaultImg = defaultImg;
    }
}
