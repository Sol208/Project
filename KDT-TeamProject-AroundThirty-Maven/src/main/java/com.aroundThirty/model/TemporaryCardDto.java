package com.aroundThirty.model;

import javax.swing.*;

public class TemporaryCardDto {
    private int no;
    private ImageIcon defaultImg;

    public TemporaryCardDto(ImageIcon defaultImg, int no) {
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
