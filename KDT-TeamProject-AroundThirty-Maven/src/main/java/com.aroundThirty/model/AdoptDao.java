package com.aroundThirty.model;

import com.aroundThirty.JdbcUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdoptDao {
    public static final String SQL_ADOPT_SELECT = "SELECT * FROM ADOPT";
    public static final String SQL_ADOPT_INSERT = "INSERT INTO ADOPT(adopt_Place, kind_Adopt, phone_Num, detail, post_Create_Date, thumbnail_Img, User_ID) VALUES (?,?,?,?,?,?,?)";
    public static final String SQL_ADOPT_CHANGE_INSERT = "INSERT INTO ADOPT(adopt_Place, kind_Adopt, phone_Num, detail, post_Create_Date, post_Modify_Date, thumbnail_Img, User_ID) VALUES (?,?,?,?,?,?,?,?)";
    public static final String SQL_ADOPT_UPDATE = "UPDATE ADOPT SET adopt_Place=?, kind_Adopt=?, phone_Num=?, detail=?, post_Modify_Date=?, thumbnail_Img=? WHERE NO=?";
    public static final String SQL_ADOPT_DELETE = "DELETE FROM ADOPT WHERE NO=?";
    public static final String SQL_ADOPT_SELECT_ONE = "SELECT * FROM ADOPT WHERE NO=?";


    public static Statement stmt = null;
    public static PreparedStatement pstmt = null;
    public static ResultSet rs = null;
    public static Connection conn = null;

    public static List<AdoptDto> adoptSelectAll() {
        List<AdoptDto> list = new ArrayList<>();
        conn = JdbcUtil.getConnection();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL_ADOPT_SELECT);
            while (rs.next()) {
                int no = rs.getInt(1);
                String adopt_Place = rs.getString(2);
                String kind_Adopt = rs.getString(3);
                String phone_Num = rs.getString(4);
                String detail = rs.getString(5);
                String post_Create_Date = rs.getString(6);
                String post_Modify_Date = rs.getString(7);
                String thumbnail_Img = rs.getString(8);
                String User_ID = rs.getString(9);

                list.add(new AdoptDto(adopt_Place, kind_Adopt, phone_Num, detail,
                        post_Create_Date, post_Modify_Date, thumbnail_Img, User_ID, no));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                JdbcUtil.close(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static AdoptDto adoptSelectOne(AdoptDto adoptDto) { // one
        conn = JdbcUtil.getConnection();
        AdoptDto apdto = null;
        try {
            pstmt = conn.prepareStatement(SQL_ADOPT_SELECT_ONE);
            pstmt.setInt(1, adoptDto.getNo());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int no = rs.getInt(1);
                String adopt_Place = rs.getString(2);
                String kind_Adopt = rs.getString(3);
                String phone_Num = rs.getString(4);
                String detail = rs.getString(5);
                String post_Create_Date = rs.getString(6);
                String post_Modify_Date = rs.getString(7);
                String thumbnail_Img = rs.getString(8);
                String User_ID = rs.getString(9);
                apdto = new AdoptDto(adopt_Place, kind_Adopt, phone_Num, detail, post_Create_Date, post_Modify_Date, thumbnail_Img, User_ID, no);
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
        return apdto;
    }


    public static void adoptInput(AdoptDto adoptDto) {
        try {
            conn = JdbcUtil.getConnection();
            pstmt = conn.prepareStatement(SQL_ADOPT_INSERT);
            pstmt.setString(1, adoptDto.getAdopt_Place());
            pstmt.setString(2, adoptDto.getKind_Adopt());
            pstmt.setString(3, adoptDto.getPhone_Num());
            pstmt.setString(4, adoptDto.getDetail());
            pstmt.setString(5, adoptDto.getPost_Create_Date());
            pstmt.setString(6, adoptDto.getThumbnail_Img());
            pstmt.setString(7, adoptDto.getUser_ID());
            int cnt = pstmt.executeUpdate();
            if (cnt == 0) {
                System.out.println(">>> 입력 실패!");
                conn.rollback();
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception....");
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                JdbcUtil.close(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void adoptChangeInput(AdoptDto adoptDto) {
        try {
            conn = JdbcUtil.getConnection();
            pstmt = conn.prepareStatement(SQL_ADOPT_CHANGE_INSERT);
            pstmt.setString(1, adoptDto.getAdopt_Place());
            pstmt.setString(2, adoptDto.getKind_Adopt());
            pstmt.setString(3, adoptDto.getPhone_Num());
            pstmt.setString(4, adoptDto.getDetail());
            pstmt.setString(5, adoptDto.getPost_Create_Date());
            pstmt.setString(6, adoptDto.getPost_Modify_Date());
            pstmt.setString(7, adoptDto.getThumbnail_Img());
            pstmt.setString(8, adoptDto.getUser_ID());
            int cnt = pstmt.executeUpdate();
            if (cnt == 0) {
                System.out.println(">>> 입력 실패!");
                conn.rollback();
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception....");
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                JdbcUtil.close(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void adoptModify(AdoptDto adoptDto) {
        conn = JdbcUtil.getConnection();
        try {
            pstmt = conn.prepareStatement(SQL_ADOPT_UPDATE);
            pstmt.setString(1, adoptDto.getAdopt_Place());
            pstmt.setString(2, adoptDto.getKind_Adopt());
            pstmt.setString(3, adoptDto.getPhone_Num());
            pstmt.setString(4, adoptDto.getDetail());
            pstmt.setString(5, adoptDto.getPost_Modify_Date());
            pstmt.setString(6, adoptDto.getThumbnail_Img());
            pstmt.setInt(7, adoptDto.getNo());
            int cnt = pstmt.executeUpdate();
            if (cnt == 0) {
                System.out.println("업데이트 실패");
            }
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void adoptDelete(AdoptDto adoptDto) {
        conn = JdbcUtil.getConnection();
        try {
            pstmt = conn.prepareStatement(SQL_ADOPT_DELETE);
            pstmt.setInt(1, adoptDto.getNo());
            int cnt = pstmt.executeUpdate();
            if (cnt == 0) {
                System.out.println(">>> 삭제 실패!");
            }
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
                JdbcUtil.close(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}