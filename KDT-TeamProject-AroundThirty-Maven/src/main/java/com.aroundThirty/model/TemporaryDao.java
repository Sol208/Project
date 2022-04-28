package com.aroundThirty.model;

import com.aroundThirty.JdbcUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class TemporaryDao { // select 쪽은 우선 보류(담당: 김영준)
    public static final String SQL_TEMPORARY_SELECT = "SELECT * FROM TEMPORARY";
    public static final String SQL_TEMPORARY_INSERT = "INSERT INTO TEMPORARY(tmp_Date, tmp_Place, kind_Tmp, phone_Num, detail, post_Create_Date, thumbnail_Img, user_ID) VALUES (?,?,?,?,?,?,?,?)";
    public static final String SQL_TEMPORARY_CHANGE_INSERT = "INSERT INTO TEMPORARY(tmp_Date, tmp_Place, kind_Tmp, phone_Num, detail, post_Create_Date, post_Modify_Date, thumbnail_Img, user_ID) VALUES (?,?,?,?,?,?,?,?,?)";
    public static final String SQL_TEMPORARY_UPDATE = "UPDATE TEMPORARY tmp_Date=?, tmp_Place=?, kind_Tmp=?, phone_Num=?, detail=?, thumbnail_Img=?, post_Modify_Date=? WHERE NO=?";
    public static final String SQL_TEMPORARY_DELETE = "DELETE FROM TEMPORARY WHERE NO=?";
    public static final String SQL_TEMPORARY_SELECT_ONE = "SELECT * FROM TEMPORARY WHERE NO=?";

    public static Statement stmt = null;
    public static PreparedStatement pstmt = null;
    public static ResultSet rs = null;
    public static Connection conn = null;


    // R
    public static List<TemporaryDto> temporarySelectAll() {
        List<TemporaryDto> list = new ArrayList<TemporaryDto>();
        conn = JdbcUtil.getConnection();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL_TEMPORARY_SELECT);
            while (rs.next()) {
                int no = rs.getInt(1);
                String tmp_Date = rs.getString(2);
                String tmp_Place = rs.getString(3);
                String kind_Tmp = rs.getString(4);
                String phone_Num = rs.getString(5);
                String detail = rs.getString(6);
                String post_Create_Date = rs.getString(7);
                String thumbnail_Img = rs.getString(8);
                String post_Modify_Date = rs.getString(9);
                String User_ID = rs.getString(10);
                // 위 while문 안에 있는 변수들을 순서대로 아래에 대입 -> list에 넣어주기
                list.add(new TemporaryDto(tmp_Date, tmp_Place, kind_Tmp, phone_Num, detail, post_Create_Date, post_Modify_Date, thumbnail_Img, User_ID, no));
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

    // R
    public static TemporaryDto temporarySelectOne(TemporaryDto tem) { // one
        conn = JdbcUtil.getConnection();
        TemporaryDto tmpdto = null;
        try {
            pstmt = conn.prepareStatement(SQL_TEMPORARY_SELECT_ONE);
            pstmt.setInt(1, tem.getNo());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int no = rs.getInt(1);
                String temporary_Date = rs.getString(2);
                String temporary_Place = rs.getString(3);
                String kind_Temporary = rs.getString(4);
                String phone_Num = rs.getString(5);
                String detail = rs.getString(6);
                String post_Create_Date = rs.getString(7);
                String post_Modify_Date = rs.getString(9);
                String thumbnail_Img = rs.getString(8);
                String User_ID = rs.getString(10);
                tmpdto = new TemporaryDto(temporary_Date, temporary_Place, kind_Temporary, phone_Num, detail, post_Create_Date, post_Modify_Date, thumbnail_Img, User_ID, no);
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
        return tmpdto;
    }

    // C
    public static void temporaryInput(TemporaryDto tmpdto) {
        try {
            conn = JdbcUtil.getConnection();    // JdbcUtil에 있는 getConnection메소드를 활용해 db와 연결하고 그걸 connection 변수인 conn에 담는다.
            pstmt = conn.prepareStatement(SQL_TEMPORARY_INSERT);    // preparesStatement메소드를 활용하여 쿼리문을 읽어온다.
            pstmt.setString(1, tmpdto.getTmp_Date());    // 쿼리문에 순서에 맞게 Index를 선언하고  dto에 저장된 값을 가져와 입력해준다.
            pstmt.setString(2, tmpdto.getTmp_Place());
            pstmt.setString(3, tmpdto.getKind_Tmp());
            pstmt.setString(4, tmpdto.getPhone_Num());
            pstmt.setString(5, tmpdto.getDetail());
            pstmt.setString(6, tmpdto.getPost_Create_Date());
            pstmt.setString(7, tmpdto.getThumbnail_Img());
            pstmt.setString(8, tmpdto.getUserID());
            int cnt = pstmt.executeUpdate();                        // insert가 성공할때 마다 카운트하여 cnt변수에 담아준다.
            if (cnt == 0) {                                         // cnt가 0인 경우 쿼리문이 정상적으로 돌지 않았다는것이기에 "입력실패" 라는 텍스트를 띄워준다.
                System.out.println(">>> 입력 실패!");
                conn.rollback();
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception....");                // DB에 접근이 실패하는 경우 출력될 문장
            try {
                conn.rollback();                                    // DB접근이 실패하면 커넥션을 다시 초기화(?)하여 정상적인 상태를 유지한다.
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();                   // preparedStatement 객체를 닫아준다.
                JdbcUtil.close(conn);                               // 커넥션을 닫아준다.
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void temporaryChangeInput(TemporaryDto tmpdto) {
        try {
            conn = JdbcUtil.getConnection();    // JdbcUtil에 있는 getConnection메소드를 활용해 db와 연결하고 그걸 connection 변수인 conn에 담는다.
            pstmt = conn.prepareStatement(SQL_TEMPORARY_CHANGE_INSERT);    // preparesStatement메소드를 활용하여 쿼리문을 읽어온다.
            pstmt.setString(1, tmpdto.getTmp_Date());    // 쿼리문에 순서에 맞게 Index를 선언하고  dto에 저장된 값을 가져와 입력해준다.
            pstmt.setString(2, tmpdto.getTmp_Place());
            pstmt.setString(3, tmpdto.getKind_Tmp());
            pstmt.setString(4, tmpdto.getPhone_Num());
            pstmt.setString(5, tmpdto.getDetail());
            pstmt.setString(6, tmpdto.getPost_Create_Date());
            pstmt.setString(7, tmpdto.getPost_Modify_Date());
            pstmt.setString(8, tmpdto.getThumbnail_Img());
            pstmt.setString(9, tmpdto.getUserID());
            int cnt = pstmt.executeUpdate();                        // insert가 성공할때 마다 카운트하여 cnt변수에 담아준다.
            if (cnt == 0) {                                         // cnt가 0인 경우 쿼리문이 정상적으로 돌지 않았다는것이기에 "입력실패" 라는 텍스트를 띄워준다.
                System.out.println(">>> 입력 실패!");
                conn.rollback();
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception....");                // DB에 접근이 실패하는 경우 출력될 문장
            try {
                conn.rollback();                                    // DB접근이 실패하면 커넥션을 다시 초기화(?)하여 정상적인 상태를 유지한다.
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();                   // preparedStatement 객체를 닫아준다.
                JdbcUtil.close(conn);                               // 커넥션을 닫아준다.
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // D
    public static void temporaryDelete(TemporaryDto tmpdto) {
        conn = JdbcUtil.getConnection();
        try {
            pstmt = conn.prepareStatement(SQL_TEMPORARY_DELETE);
            pstmt.setInt(1, tmpdto.getNo());
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

    // U
    public static void temporaryModity(TemporaryDto tmpdto) {
        conn = JdbcUtil.getConnection(); //DB와 연결하기 위해 JdbcUtil의 getConncection 메소드 소환
        try {
            pstmt = conn.prepareStatement(SQL_TEMPORARY_UPDATE); // prepareStatement에 쿼리를 입력하여 DB를 불러와 Statement pstmt에 담는 구문
            pstmt.setString(1, tmpdto.getTmp_Date()); // 제보, 실종등의 날짜와 시간 치환
            pstmt.setString(2, tmpdto.getTmp_Place());  // 장소 치환
            pstmt.setString(3, tmpdto.getKind_Tmp());   // 품종 치환
            pstmt.setString(4, tmpdto.getPhone_Num());   //전화번호 치환
            pstmt.setString(5, tmpdto.getDetail()); // 본문내용 치환
            pstmt.setString(6, tmpdto.getThumbnail_Img()); // 본문내용 치환
            pstmt.setString(7, tmpdto.getPost_Modify_Date());   // 게시글 수정 일자 및 시간 치환
            pstmt.setInt(8, tmpdto.getNo()); // 업데이트할 데이터의 기준값

            int cnt = pstmt.executeUpdate(); // 업데이트를 성공 했는지 확인하기 위해 결과값을 인트형으로 받는다.
            if (cnt == 0) { // 결과값이 0인 경우에는 업데이트가 되지않은것이기 떄문에 "업데이트 실패"라는 텍스트를 띄워준다.
                System.out.println("업데이트 실패");
            }
        } catch (SQLException e) {
            try {
                conn.rollback(); // DB를 제대로 불러오지 못하는경우(쿼리문이 다른경우?)커넥션 메소드를 초기화 한다.
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
}