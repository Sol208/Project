package com.aroundThirty.model;

import javax.swing.*;

public class ReportDto {
    public String report_Date = null; // 발견 날짜 및 시간
    public String report_Place = null; // 발견 장소
    public String kind_Report = null; // 제보 동물에 대한 품종
    public String phone_Num = null; // 전화 번호
    public String detail = null; // 게시글 본문
    public String post_Create_Date = null; // 게시글 작성 날짜 및 시간
    public String post_Modify_Date = null; // 게시글 수정 날짜 및 시간
    public String thumbnail_Img = null; // 동물 이미지
    public ImageIcon thumbnail_ImgPath = null;
    public String user_ID = null; // 계정 정보
    public int no = 0; // Primary Key

    // show
    public ReportDto(String report_Date, String report_Place, String kind_Report, String phone_Num, String detail, String post_Create_Date, String post_Modify_Date, String thumbnail_Img, String user_ID, int no) {
        this.report_Date = report_Date;
        this.report_Place = report_Place;
        this.kind_Report = kind_Report;
        this.phone_Num = phone_Num;
        this.detail = detail;
        this.post_Create_Date = post_Create_Date;
        this.post_Modify_Date = post_Modify_Date;
        this.thumbnail_Img = thumbnail_Img;
        this.user_ID = user_ID;
        this.no = no;
    }

    // input
    public ReportDto(String report_Date, String report_Place, String kind_Report, String phone_Num, String detail, String post_Create_Date, String thumbnail_Img, String user_ID) {
        this.report_Date = report_Date;
        this.report_Place = report_Place;
        this.kind_Report = kind_Report;
        this.phone_Num = phone_Num;
        this.detail = detail;
        this.post_Create_Date = post_Create_Date;
        this.thumbnail_Img = thumbnail_Img;
        this.user_ID = user_ID;
    }

    // Change Insert
    public ReportDto(String report_Date, String report_Place, String kind_Report, String phone_Num, String detail, String post_Create_Date, String post_Modify_Date, String thumbnail_Img, String user_ID) {
        this.report_Date = report_Date;
        this.report_Place = report_Place;
        this.kind_Report = kind_Report;
        this.phone_Num = phone_Num;
        this.detail = detail;
        this.post_Create_Date = post_Create_Date;
        this.post_Modify_Date = post_Modify_Date;
        this.thumbnail_Img = thumbnail_Img;
        this.user_ID = user_ID;
    }

    // Modify
    public ReportDto(String report_Date, String report_Place, String kind_Report, String phone_Num, String detail, String post_Modify_Date, String thumbnail_Img, int no) {
        this.report_Date = report_Date;
        this.report_Place = report_Place;
        this.kind_Report = kind_Report;
        this.phone_Num = phone_Num;
        this.detail = detail;
        this.post_Modify_Date = post_Modify_Date;
        this.thumbnail_Img = thumbnail_Img;
        this.no = no;
    }

    //delete & ShowOne
    public ReportDto(int no) {
        this.no = no;
    }

    public ImageIcon getthumbnail_ImgPath() {
        return thumbnail_ImgPath;
    }

    public void setthumbnail_ImgPath(ImageIcon thumbnail_ImgPath) {
        this.thumbnail_ImgPath = thumbnail_ImgPath;
    }

    public String getReport_Date() {
        return report_Date;
    }

    public void setReport_Date(String report_Date) {
        this.report_Date = report_Date;
    }

    public String getReport_Place() {
        return report_Place;
    }

    public void setReport_Place(String report_Place) {
        this.report_Place = report_Place;
    }

    public String getKind_Report() {
        return kind_Report;
    }

    public void setKind_Report(String kind_Report) {
        this.kind_Report = kind_Report;
    }

    public String getPhone_Num() {
        return phone_Num;
    }

    public void setPhone_Num(String phone_Num) {
        this.phone_Num = phone_Num;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPost_Create_Date() {
        return post_Create_Date;
    }

    public void setPost_Create_Date(String post_Create_Date) {
        this.post_Create_Date = post_Create_Date;
    }

    public String getPost_Modify_Date() {
        return post_Modify_Date;
    }

    public void setPost_Modify_Date(String post_Modify_Date) {
        this.post_Modify_Date = post_Modify_Date;
    }

    public String getThumbnail_Img() {
        return thumbnail_Img;
    }

    public void setThumbnail_Img(String thumbnail_Img) {
        this.thumbnail_Img = thumbnail_Img;
    }

    public String getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(String user_ID) {
        this.user_ID = user_ID;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return report_Date + " " + report_Place + " " + kind_Report + " " + phone_Num + " " + detail + " " + post_Create_Date + " " + post_Modify_Date + " " + thumbnail_Img + " " + user_ID + " " + no;
    }
}

