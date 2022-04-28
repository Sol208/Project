package com.aroundThirty.model;

import javax.swing.*;

public class XmlCardDto {
    private int no;
    private String title;
    private String image;
    private ImageIcon defaultImg;



    public XmlCardDto(int no, String title, ImageIcon defaultImg) {
        this.no = no;
        this.title = title;
        this.defaultImg = defaultImg;
    }

    public XmlCardDto(ImageIcon defaultImg, int no) {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return no + title + image;
    }
}
