package com.aroundThirty.model;

public class MissingDto {
    public String missing_Date = null; // 발견 날짜 및 시간
    public String missing_Place = null; // 발견 장소
    public String kind_Missing = null; // 제보 동물에 대한 품종
    public String phone_Num = null; // 전화 번호
    public String detail = null; // 게시글 본문
    public String post_Create_Date = null; // 게시글 작성 날짜 및 시간
    public String post_Modify_Date = null; // 게시글 수정 날짜 및 시간
    public String thumbnail_Img = null; // 동물 이미지
    public String user_ID = null; // 계정 정보
    public int no = 0; // Primary Key

    // Change Insert
    public MissingDto(String missing_Date, String missing_Place, String kind_Missing, String phone_Num, String detail, String post_Create_Date, String post_Modify_Date, String thumbnail_Img, String user_ID) {
        this.missing_Date = missing_Date;
        this.missing_Place = missing_Place;
        this.kind_Missing = kind_Missing;
        this.phone_Num = phone_Num;
        this.detail = detail;
        this.post_Create_Date = post_Create_Date;
        this.post_Modify_Date = post_Modify_Date;
        this.thumbnail_Img = thumbnail_Img;
        this.user_ID = user_ID;
    }

    // insert
    public MissingDto(String missing_Date, String missing_Place, String kind_Missing, String phone_Num, String detail, String post_Create_Date, String thumbnail_Img, String user_ID) {
        this.missing_Date = missing_Date;
        this.missing_Place = missing_Place;
        this.kind_Missing = kind_Missing;
        this.phone_Num = phone_Num;
        this.detail = detail;
        this.post_Create_Date = post_Create_Date;
        this.thumbnail_Img = thumbnail_Img;
        this.user_ID = user_ID;
    }

    // show
    public MissingDto(String missing_Date, String missing_Place, String kind_Missing, String phone_Num, String detail, String post_Create_Date, String post_Modify_Date, String thumbnail_Img, String user_ID, int no) {
        this.missing_Date = missing_Date;
        this.missing_Place = missing_Place;
        this.kind_Missing = kind_Missing;
        this.phone_Num = phone_Num;
        this.detail = detail;
        this.post_Create_Date = post_Create_Date;
        this.post_Modify_Date = post_Modify_Date;
        this.thumbnail_Img = thumbnail_Img;
        this.user_ID = user_ID;
        this.no = no;
    }

    // modify
    public MissingDto(String missing_Date, String missing_Place, String kind_Missing, String phone_Num, String detail, String post_Modify_Date, String thumbnail_Img, int no) {
        this.missing_Date = missing_Date;
        this.missing_Place = missing_Place;
        this.kind_Missing = kind_Missing;
        this.phone_Num = phone_Num;
        this.detail = detail;
        this.post_Modify_Date = post_Modify_Date;
        this.thumbnail_Img = thumbnail_Img;
        this.no = no;
    }

    // delete
    public MissingDto(int no) {
        this.no = no;
    }

    public String getMissing_Date() {
        return missing_Date;
    }

    public void setMissing_Date(String missing_Date) {
        this.missing_Date = missing_Date;
    }

    public String getMissing_Place() {
        return missing_Place;
    }

    public void setMissing_Place(String missing_Place) {
        this.missing_Place = missing_Place;
    }

    public String getKind_Missing() {
        return kind_Missing;
    }

    public void setKind_Missing(String kind_Missing) {
        this.kind_Missing = kind_Missing;
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
        user_ID = user_ID;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return missing_Date + " " + missing_Place + " " + kind_Missing + " " + phone_Num + detail + " " + post_Create_Date + " " + post_Modify_Date + " " + thumbnail_Img + " " + user_ID + " " + no;
    }
}
