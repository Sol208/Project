package com.aroundThirty.model;


import javax.swing.*;

public class MissingCardDto {
    private int no;
    private ImageIcon defaultImg;


    public MissingCardDto(ImageIcon defaultImg, int no) {
        this.defaultImg = defaultImg;
        this.no = no;
    }

    public ImageIcon getDefaultImg() {
        return defaultImg;
    }

    public void setDefaultImg(ImageIcon defaultImg) {
        this.defaultImg = defaultImg;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }
}
