package com.aroundThirty.model;

import com.aroundThirty.JdbcUtil;

import java.sql.*;

public class UserDao {
    public static final String SQL_USER_INSERT = "INSERT INTO User_Info(User_ID, User_PW, User_Name, User_Gender, User_Num, User_Email, User_Authority) VALUES (?,?,?,?,?,?,?)";
    public static final String SQL_USER_UPDATE = "UPDATE User_Info SET User_PW=?, User_Name=?, User_Gender=?, User_Num=?, User_Email=? WHERE NO=?";
    public static final String SQL_USER_DELETE = "DELETE FROM User_Info WHERE NO=?";
    public static final String SQL_USER_SELECT_BY_ID = "SELECT * FROM User_Info WHERE USER_ID=?";

    public static Statement stmt = null;
    public static PreparedStatement pstmt = null;
    public static ResultSet rs = null;
    public static Connection conn = null;

    // C
    public static void userInput(UserDto userDto) {
        try {
            conn = JdbcUtil.getConnection();    // JdbcUtil에 있는 getConnection메소드를 활용해 db와 연결하고 그걸 connection 변수인 conn에 담는다.
            pstmt = conn.prepareStatement(SQL_USER_INSERT);   // preparesStatement메소드를 활용하여 쿼리문을 읽어온다.
            pstmt.setString(1, userDto.getUser_ID());
            pstmt.setString(2, userDto.getUser_PW());    // 쿼리문에 순서에 맞게 Index를 선언하고  dto에 저장된 값을 가져와 입력해준다.
            pstmt.setString(3, userDto.getUser_Name());
            pstmt.setString(4, userDto.getUser_Gender());
            pstmt.setString(5, userDto.getUser_Num());
            pstmt.setString(6, userDto.getUser_Email());
            pstmt.setInt(7, userDto.getUser_Authority());
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
    public static void userDelete(UserDto userDto) {
        conn = JdbcUtil.getConnection();
        try {
            pstmt = conn.prepareStatement(SQL_USER_DELETE);
            pstmt.setInt(1, userDto.getNo());
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
    public static void userModify(UserDto userDto) {
        conn = JdbcUtil.getConnection(); //DB와 연결하기 위해 JdbcUtil의 getConncection 메소드 소환
        try {
            pstmt = conn.prepareStatement(SQL_USER_UPDATE); // prepareStatement에 쿼리를 입력하여 DB를 불러와 Statement pstmt에 담는 구문
            pstmt.setString(1, userDto.getUser_PW());
            pstmt.setString(2, userDto.getUser_Name());
            pstmt.setString(3, userDto.getUser_Gender());
            pstmt.setString(4, userDto.getUser_Num());
            pstmt.setString(5, userDto.getUser_Email());
            pstmt.setInt(6, userDto.getNo());

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

    // R
    public static UserDto userSelectById(UserDto userDto) { //id
        conn = JdbcUtil.getConnection();
        UserDto apdto = null;
        try {
            pstmt = conn.prepareStatement(SQL_USER_SELECT_BY_ID);
            pstmt.setString(1, userDto.getUser_ID());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String user_ID = rs.getString(2);
                String user_PW = rs.getString(3);
                String user_Name = rs.getString(4);
                String user_Num = rs.getString(6);
                String user_Email = rs.getString(7);
                int user_Authority = rs.getInt(8);
                apdto = new UserDto(user_ID, user_PW, user_Name, user_Num, user_Email, user_Authority);
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
}
