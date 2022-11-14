import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class VocaDAO {
    public void insert() throws ClassNotFoundException, SQLException {// 메소드를 호출한 곳에서 책임전가 -> 메인 트라이 캐치
        Scanner sc = new Scanner(System.in);
        System.out.println("🧶단어를 입력하세요 : ");
        String eng = sc.next();
        System.out.println("🧶뜻 입력하세요 : ");
        String kor = sc.next();
        System.out.println("🧶레벨을 입력하세요 : ");
        int level = sc.nextInt();

        Connection conn = Dbconn.getConnection();
        StringBuilder sql = new StringBuilder();
        sql.append("insert into tb_word(eng,kor,lev)")
                .append("values(?,?,?)");
        PreparedStatement pstmt = conn.prepareStatement(sql.toString());
        pstmt.setString(1, eng);
        pstmt.setString(2, kor);
        pstmt.setInt(3, level);
        int result = pstmt.executeUpdate();
        if (result >= 1) System.out.println("단어등록 성공! ");
        else System.out.println("단어등록 실패! ");

    }

    public void list() throws ClassNotFoundException, SQLException {
        Connection conn = Dbconn.getConnection();
        StringBuilder sql = new StringBuilder();
        sql.append("select eng, kor, lev, rdate from tb_word order by eng");
        PreparedStatement pstmt = conn.prepareStatement(sql.toString());
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            String eng = rs.getString("eng");
            String kor = rs.getString("kor");
            int lev = rs.getInt("lev");
            String rdate = rs.getString("rdate");
            System.out.println(eng + ": " + kor + "(" + lev + ", " + rdate + ")");
        }
    }

    public void search()throws  ClassNotFoundException, SQLException{
        Scanner sc = new Scanner(System.in);
        System.out.println("🧶검색할 단어를 입력하세요 : ");
        String word = sc.next();

        boolean isFind = false;
        Connection conn = Dbconn.getConnection();
        StringBuilder sql = new StringBuilder();
        sql.append("select eng, kor, lev, rdate from tb_word where eng like ?");
        PreparedStatement pstmt = conn.prepareStatement(sql.toString());
        pstmt.setString(1,"%"+word+"%");
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()){ // 있어야 돈다 rs.next()
            isFind = true;
            String eng = rs.getString("eng");
            String kor = rs.getString("kor");
            int lev = rs.getInt("lev");
            String rdate = rs.getString("rdate");
            System.out.println(eng + ": " + kor + "(" + lev + ", " + rdate + ")");
        }
        if(!isFind) System.out.println("찾는 단어가 없습니다.😢😢😢");
    }
    public void edit() throws ClassNotFoundException, SQLException {
        // 수정할 단어를 입력하세요 : apple
        // 단어의 뜻을 입력하세요 : 사과
        // 레벨을 입력하세요 : 2
        Scanner sc = new Scanner(System.in);
        System.out.println("수정할 단어를 입력하세요");
        String eng = sc.next();
        System.out.println("단어의 뜻을 입력하세요");
        String kor = sc.next();
        System.out.println("레벨을 입력하세요");
        int lev = sc.nextInt();

        Connection conn = Dbconn.getConnection();
        StringBuilder sql = new StringBuilder();
        sql.append("update tb_word set kor =?, lev =? where eng =?");

        PreparedStatement pstmt = conn.prepareStatement(sql.toString());

        pstmt.setString(1, kor);
        pstmt.setInt(2, lev);
        pstmt.setString(3, eng);
        int result = pstmt.executeUpdate();
        if (result >= 1) System.out.println("단어장 성공");
        else System.out.println("찾는 단어가 없습니다.");
    }
    public void delete() throws ClassNotFoundException, SQLException {
        // 삭제할 단어를 입력하세요
        Scanner sc = new Scanner(System.in);
        System.out.println("삭제할 단어를 입력하세요");
        String eng = sc.next();
        boolean isFind = false;
        Connection conn = Dbconn.getConnection();
        StringBuilder sql = new StringBuilder();
        sql.append("delete  from tb_word where eng = ?");
        PreparedStatement pstmt = conn.prepareStatement(sql.toString());
        pstmt.setString(1,eng);
        int result = pstmt.executeUpdate();
        if (result >= 1) System.out.println("단어삭제 성공");
        else System.out.println("단어삭제 실패");

    }

}











