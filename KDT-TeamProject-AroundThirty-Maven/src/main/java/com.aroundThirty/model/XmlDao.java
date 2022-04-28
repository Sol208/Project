package com.aroundThirty.model;

import com.aroundThirty.JdbcUtil;
import com.aroundThirty.Resource.FR.*;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.aroundThirty.Resource.FR.*;

public class XmlDao {
    public static final String SQL_XML_SELECT = "SELECT * FROM XML";
    public static final String SQL_XML_SELECT_ONE = "SELECT * FROM XML WHERE NO=?";


    public static Statement stmt = null;
    public static PreparedStatement pstmt = null;
    public static ResultSet rs = null;
    public static Connection conn = null;

    public static List<XmlDto> xmlSelectAll() {
        List<XmlDto> list = new ArrayList<>();
        conn = JdbcUtil.getConnection();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL_XML_SELECT);
            while (rs.next()) {
                String age = rs.getString(1);
                String phone_Num = rs.getString(4);
                String colorCd = rs.getString(6);
                String filename = rs.getString(8);
                String happenDt = rs.getString(9);
                String happenPlace = rs.getString(10);
                String kindCd = rs.getString(11);
                String neuterYn = rs.getString(12);
                String orgNm = rs.getString(17);
                String thumbnail_Img = rs.getString(18);
                String processState = rs.getString(19);
                String sexCd = rs.getString(20);
                String specialMark = rs.getString(21);
                String weight = rs.getString(22);
                int no = rs.getInt(24);
                list.add(new XmlDto(age, phone_Num, colorCd, filename, happenDt, happenPlace, kindCd, neuterYn,
                        orgNm, thumbnail_Img, processState, sexCd, specialMark, weight, no));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static XmlDto xmlDtoSelectOne(XmlDto xmlDto) { // one
        conn = JdbcUtil.getConnection();
        XmlDto xmdto = null;
        try {
            pstmt = conn.prepareStatement(SQL_XML_SELECT_ONE);
            pstmt.setInt(1, xmlDto.no);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String age = rs.getString(1);
                String phone_Num = rs.getString(4);
                String colorCd = rs.getString(6);
                String fileName = rs.getString(8);
                String happenDt = rs.getString(9);
                String happenPlace = rs.getString(10);
                String kindCd = rs.getString(11);
                String neuterYn = rs.getString(12);
                String orgNm = rs.getString(17);
                String thumbnail_Img = rs.getString(18);
                String processState = rs.getString(19);
                String sexCd = rs.getString(20);
                String specialMark = rs.getString(21);
                String weight = rs.getString(22);
                int no = rs.getInt(24);
                xmdto = new XmlDto(age, phone_Num, colorCd, fileName, happenDt, happenPlace, kindCd, neuterYn, orgNm, thumbnail_Img, processState, sexCd, specialMark, weight, no);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (stmt != null)
                    stmt.close();
                JdbcUtil.close(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return xmdto;
    }
}