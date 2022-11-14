import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ScoreDAO {
    public void insert() throws ClassNotFoundException, SQLException {// 메소드를 호출한 곳에서 책임전가 -> 메인 트라이 캐치
        Scanner sc = new Scanner(System.in);
        System.out.println("🧶학번을 입력하세요 : ");
        String st_id = sc.next();
        System.out.println("🧶이름을 입력하세요 : ");
        String st_name = sc.next();
        System.out.println("🧶연락처를 입력하세요 : ");
        String st_hp = sc.next();
        System.out.println("🧶성별을 입력하세요 : ");
        String st_gender = sc.next();
        System.out.println("🧶주소를 입력하세요 : ");
        String st_address = sc.next();


        Connection conn = Dbconn.getConnection();
        StringBuilder sql = new StringBuilder();
        sql.append("insert into tb_student(st_id,st_name,st_hp,st_gender,st_address)")
                .append("values(?,?,?,?,?)");
        PreparedStatement pstmt = conn.prepareStatement(sql.toString());
        pstmt.setString(1, st_id);
        pstmt.setString(2, st_name);
        pstmt.setString(3, st_hp);
        pstmt.setString(4, st_gender);
        pstmt.setString(5, st_address);
        int result = pstmt.executeUpdate();
        if (result >= 1) System.out.println("단어등록 성공! ");
        else System.out.println("단어등록 실패! ");

    }


    public void list() throws ClassNotFoundException, SQLException {
        Connection conn = Dbconn.getConnection();
        StringBuilder sql = new StringBuilder();
        sql.append("select st_id, st_name, st_hp, st_gender, st_address,st_rdate from tb_student order by st_id");
        PreparedStatement pstmt = conn.prepareStatement(sql.toString());
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            String st_id = rs.getString("st_id");
            String st_name = rs.getString("st_name");
            String st_hp = rs.getString("st_hp");
            String st_gender = rs.getString("st_gender");
            String st_address = rs.getString("st_address");
            String st_rdate = rs.getString("st_rdate");
            System.out.println(st_id + ": " + st_name +  ": " + st_hp +
                    ": " + st_gender +  ": "+st_address+": "+st_rdate);
        }
    }


    public void edit() throws ClassNotFoundException, SQLException {

        Scanner sc = new Scanner(System.in);
        System.out.println("수정할 학번을 입력하세요");
        String st_id = sc.next();
        System.out.println("변경 연락처 입력하세요");
        String st_hp = sc.next();
        System.out.println("변경 주소 입력하세요");
        String st_address = sc.next();

        Connection conn = Dbconn.getConnection();
        StringBuilder sql = new StringBuilder();
        sql.append("update tb_student set st_hp =?, st_address =? where st_id =?");

        PreparedStatement pstmt = conn.prepareStatement(sql.toString());

        pstmt.setString(1, st_hp);
        pstmt.setString(2, st_address);
        pstmt.setString(3, st_id);
        int result = pstmt.executeUpdate();
        if (result >= 1) System.out.println("학생 변경 성공");
        else System.out.println("해당 학생이 없습니다.");
    }

    public void delete() throws ClassNotFoundException, SQLException {
        // 삭제할 단어를 입력하세요
        Scanner sc = new Scanner(System.in);
        System.out.println("삭제할 학번을 입력하세요");
        String st_id = sc.next();
        boolean isFind = false;
        Connection conn = Dbconn.getConnection();
        StringBuilder sql = new StringBuilder();
        sql.append("delete  from tb_student where st_id = ?");
        PreparedStatement pstmt = conn.prepareStatement(sql.toString());
        pstmt.setString(1,st_id);
        int result = pstmt.executeUpdate();
        if (result >= 1) System.out.println("단어삭제 성공");
        else System.out.println("단어삭제 실패");

    }
    public void score() throws ClassNotFoundException, SQLException{
        Scanner sc = new Scanner(System.in);
        System.out.println("성적을 등록할 학번을 입력하세요 : ");
        String sc_id = sc.next();
        System.out.println("국어점수를 입력하세요");
        int sc_kor = sc.nextInt();
        System.out.println("수학점수를 입력하세요");
        int sc_math = sc.nextInt();
        System.out.println("영어점수를 입력하세요");
        int sc_eng = sc.nextInt();

        int sc_sum = sc_kor +sc_eng+sc_math;
        int sc_avg = (sc_kor +sc_eng+sc_math)/3;

        Connection conn = Dbconn.getConnection();
        StringBuilder sql = new StringBuilder();
        sql.append("insert into tb_score(sc_id,sc_kor,sc_math,sc_eng,sc_sum,sc_avg)")
                .append("values(?,?,?,?,?,?)");
        PreparedStatement pstmt = conn.prepareStatement(sql.toString());

        pstmt.setString(1, sc_id);
        pstmt.setInt(2, sc_kor);
        pstmt.setInt(3, sc_math);
        pstmt.setInt(4, sc_eng);
        pstmt.setInt(5, sc_sum);
        pstmt.setInt(6, sc_avg);
        int result = pstmt.executeUpdate();
        if (result >= 1) System.out.println("점수등록 성공! ");
        else System.out.println("점수등록 실패! ");

    }

    public void scoreList() throws ClassNotFoundException, SQLException{
        Connection conn = Dbconn.getConnection();
        StringBuilder sql = new StringBuilder();
        System.out.println("성적보기");
        sql.append("select st_id, st_name, sc_kor,sc_math,sc_eng,sc_avg,sc_sum  \n" +
                " from tb_student left join tb_score on tb_student.st_id = tb_score.sc_id;");
        PreparedStatement pstmt = conn.prepareStatement(sql.toString());
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            String st_id = rs.getString("st_id");
            String st_name = rs.getString("st_name");
            int sc_kor = rs.getInt("sc_kor");
            int sc_math = rs.getInt("sc_math");
            int sc_eng = rs.getInt("sc_eng");
            int sc_avg = rs.getInt("sc_avg");
            int sc_sum = rs.getInt("sc_sum");
            System.out.println(" 아이디 : "+st_id + " 이름 : " + st_name +  " 국어 : " + sc_kor +
                    "수학 : " + sc_math +  "영어 : "+sc_eng+" 평균 : "+sc_avg+" 합계: "+sc_sum);
        }
    }

}
