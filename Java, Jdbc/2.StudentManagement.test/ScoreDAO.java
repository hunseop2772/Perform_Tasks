import java.sql.*;
import java.util.Scanner;

public class ScoreDAO {
    public void insert() throws ClassNotFoundException, SQLException {
        //학생을 등록한다
        // 메소드를 호출한 곳에서 책임전가 -> 메인 트라이 캐치
        Scanner sc = new Scanner(System.in);
        System.out.println("🧶학번(- 제외한 숫자만)을 입력하세요 : ");
        int st_id = sc.nextInt(); // 학번이기 때문에 숫자 정수로 바꿔 해보았습니다.
        System.out.println("🧶이름을 입력하세요 : ");
        String st_name = sc.next();
        System.out.println("🧶연락처를 입력하세요 : ");
        String st_hp = sc.next();
        System.out.println("🧶이메일 입력하세요 : ");
        String st_email = sc.next();
        System.out.println("🧶주소를 입력하세요 : ");
        String st_address = sc.next();



        Connection conn = Dbconn.getConnection();
        // db 호출 명령어
        StringBuilder sql = new StringBuilder();
        // 자신이 가지고 있는 문자열값의 추가, 변경, 삭제 가 가능하게
        sql.append("insert into t_student(st_id,st_name,st_hp,st_email,st_address)")
                .append("values(?,?,?,?,?)");
        // 인서트 문을 사용하여 학생 테이블에 데이터를 입력할 수 있도록 설정
        PreparedStatement pstmt = conn.prepareStatement(sql.toString());
        //한번 사용한 sql이 저장되기 때문에
        // 반복해서 사용할 경우 성능이 좋기 때문에 사용.
        pstmt.setInt(1, st_id);
        pstmt.setString(2, st_name);
        pstmt.setString(3, st_hp);
        pstmt.setString(4, st_email);
        pstmt.setString(5, st_address);
        int result = pstmt.executeUpdate();
        if (result >= 1) System.out.println("학생정보 등록 성공! ");
        else System.out.println("학생정보 등록 실패! ");

    }




    public void list() throws ClassNotFoundException, SQLException{
        // 출력
        Connection conn = Dbconn.getConnection();
        StringBuilder sql = new StringBuilder();

        sql.append("select  row_number() over(order by sc_avg desc, st_id desc) as st_rank" +
                " ,st_id, st_name,st_hp,st_email,st_address, sc_java,sc_python,sc_c,sc_avg,sc_sum,st_rdate   \n" +
                " from t_student left join t_score on t_student.st_id = t_score.sc_id order by t_score.sc_avg desc, t_student.st_id desc");
        // 제가 한 내용은 레프트 조인을 사용하여 학생정보 및 성적을 출력하게 하였으며 출력결과 order by룰 사용하여 t_score에 있는
        // 평균값을 기준으로 내림차순하여 출력하게 하였습니다, 순위의 경우에는 랜크 함수인 row_number인 중복 순위를 없에기 위해 사용하여 평균과 학번을 기준으로
        // 석차를 등록하였으며 랭크 또한 평균 내림차순과 동일 점수 일 경우 학번 내림차순을 이용하여 매겼습니다.

        // 셀렉트 문에 기존 count(st_no) as tot 사용하였는데 하나로 묶여 학생정보가 1나로 나왔습니다. 이 문제를 해결하기 위해
        // 그룹바이나 따로 선언 및 테이블 내에도 선언은 해보았지만 풀지 못하였습니다.



        PreparedStatement pstmt = conn.prepareStatement(sql.toString());
        ResultSet rs = pstmt.executeQuery();




        while (rs.next()) {
            int st_rank = rs.getInt("st_rank");
//       int st_tot = rs.getInt("st_tot");
            int st_id = rs.getInt("st_id");
            String st_name = rs.getString("st_name");
            String st_hp = rs.getString("st_hp");
            String st_email = rs.getString("st_email");
            String st_address = rs.getString("st_address");
            int sc_java = rs.getInt("sc_java");
            int sc_python = rs.getInt("sc_python");
            int sc_c = rs.getInt("sc_c");
            int sc_avg = rs.getInt("sc_avg");
            int sc_sum = rs.getInt("sc_sum");
            String st_rdate = rs.getString("st_rdate");
//            int st_no = rs.getInt("st_no");
////            System.out.println(st_no);
//            System.out.println(st_tot);
            System.out.println("등록된 인원: "+st_rank+"명 입니다 "+"😁석차 : "+ st_rank+  "위  😀학번 : "+st_id + ", 이름 : " + st_name + ", 연락처 : "+st_hp + ", 이메일 : "
                    + st_email +" 주소 : " + st_address + " 자바 : " + sc_java +
                    ", 파이썬 : " + sc_python +  ", C언어 : "+sc_c+", 😮평균 : "+sc_avg+", 합계: "+sc_sum+", 등록: "+st_rdate+"\n");
            // 석차, 학번, 평균은 가시성을 높이기 위해 이모티콘을 사용
        }
    }


    public void edit() throws ClassNotFoundException, SQLException {
        //편집 및 수정문

        Scanner sc = new Scanner(System.in);
        System.out.println("수정할 학번을 입력하세요");
        int st_id = sc.nextInt();
        System.out.println("변경 연락처 입력하세요");
        String st_hp = sc.next();
        System.out.println("변경 이메일 입력하세요");
        String st_email = sc.next();
        System.out.println("변경 주소 입력하세요");
        String st_address = sc.next();

        Connection conn = Dbconn.getConnection();
        StringBuilder sql = new StringBuilder();
        sql.append("update t_student set st_hp =?, st_email =?, st_address =? where st_id = ?");
    // 업데이트 문 사용하여 조건 st_id가 있으면 휴대폰, 이메일, 주소를 내가 원하는 것으로 수정하는 문장
        PreparedStatement pstmt = conn.prepareStatement(sql.toString());

        pstmt.setString(1, st_hp);
        pstmt.setString(2, st_email);
        pstmt.setString(3, st_address);
        pstmt.setInt(4, st_id);
        int result = pstmt.executeUpdate();// INSERT / DELETE / UPDATE 관련 구문에서는 반영된 레코드의 건수를 반환
        if (result >= 1) System.out.println("학생 변경 성공"); // 결과가 있으면 성공
        else System.out.println("해당 학생이 없습니다."); // 없으면 실패
    }

    public void delete() throws ClassNotFoundException, SQLException {
        // 삭제할 단어를 입력하세요
        Scanner sc = new Scanner(System.in);
        System.out.println("삭제할 학번을 입력하세요");
        int st_id = sc.nextInt();
        boolean isFind = false; // 불린타입으로 확인
        Connection conn = Dbconn.getConnection();
        StringBuilder sql = new StringBuilder();
        sql.append("delete  from t_student where st_id = ? ");
        // 딜리트 문 사용하여 조건에 st_id와 일치하는게 있으면 해당 부분을 삭제한다.
        PreparedStatement pstmt = conn.prepareStatement(sql.toString());
        pstmt.setInt(1,st_id);
        int result = pstmt.executeUpdate();
        if (result >= 1) System.out.println("삭제 성공");
        else System.out.println("삭제 실패");

    }

    public void search()throws  ClassNotFoundException, SQLException{
        //검색
        Scanner sc = new Scanner(System.in);
        System.out.println("🧶검색할 학생아이디를 입력하세요 : ");
        int st_id = sc.nextInt();

        boolean isFind = false;
        Connection conn = Dbconn.getConnection();
        StringBuilder sql = new StringBuilder();
        sql.append("select st_id, st_name,st_hp,st_email,st_address, sc_java,sc_python,sc_c,sc_avg,sc_sum,st_rdate   \n" +
                " from t_student left join t_score on t_student.st_id = t_score.sc_id where t_student.st_id = ? ");
        // 레프트 조인을 사용하여 학생정보와 성적 정보를 출력하기 위해 사용, 마지막 조건절에 t_student에 있는 아이디가 있을 경우
        // 성적과 정보를 출력하기 위해 아이디가 존재하는지 확인하는 조건을 사용
        PreparedStatement pstmt = conn.prepareStatement(sql.toString());
        pstmt.setInt(1,st_id);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()){ // 있어야 돈다 rs.next()
            isFind = true; // 찾는게 있는지
             st_id = rs.getInt("st_id");
            String st_name = rs.getString("st_name");
            String st_hp = rs.getString("st_hp");
            String st_email = rs.getString("st_email");
            String st_address = rs.getString("st_address");
            String st_rdate = rs.getString("st_rdate");
            int sc_java = rs.getInt("sc_java");
            int sc_python = rs.getInt("sc_python");
            int sc_c = rs.getInt("sc_c");
            int sc_avg = rs.getInt("sc_avg");
            int sc_sum = rs.getInt("sc_sum");

            System.out.println(" 학번 : "+st_id + ", 이름 : " + st_name + ", 연락처 : "+st_hp + ", 이메일 : "
                    + st_email +" 주소 : " + st_address + " 자바 : " + sc_java +
                    ", 파이썬 : " + sc_python +  ", C언어 : "+sc_c+", 평균 : "+sc_avg+", 합계: "+sc_sum+", 등록: "+st_rdate);
        }
        if(!isFind) System.out.println("찾는 학생이 없습니다.😢😢😢");
    }//찾는 아이디가 없으면 없는 것을 표현

    public void score() throws ClassNotFoundException, SQLException{
        // 점수를 입력받는 부분
        Scanner sc = new Scanner(System.in);
        System.out.println("성적을 등록할 학번을 입력하세요 : ");
        int sc_id = sc.nextInt();
        System.out.println("자바 점수를 입력하세요");
        int sc_java = sc.nextInt();
        System.out.println("파이썬 점수를 입력하세요");
        int sc_python = sc.nextInt();
        System.out.println("C언어 점수를 입력하세요");
        int sc_c = sc.nextInt();

        int sc_sum = sc_java +sc_python+sc_c;
        int sc_avg = (sc_java +sc_python+sc_c)/3;
        // sql 테이블에 미리 입력 후 계산은 따로 자바에서 할 수 있도록 선언

        Connection conn = Dbconn.getConnection();
        StringBuilder sql = new StringBuilder();
        sql.append("insert into t_score(sc_id,sc_java,sc_python,sc_c,sc_sum,sc_avg)")
                .append("values(?,?,?,?,?,?)"); // 인서트 문은 위 학생정보 등록과 동일하다
        PreparedStatement pstmt = conn.prepareStatement(sql.toString());

        pstmt.setInt(1, sc_id);
        pstmt.setInt(2, sc_java);
        pstmt.setInt(3, sc_python);
        pstmt.setInt(4, sc_c);
        pstmt.setInt(5, sc_sum);
        pstmt.setInt(6, sc_avg);
        int result = pstmt.executeUpdate();
        if (result >= 1) System.out.println("점수등록 성공! ");
        else System.out.println("점수등록 실패! ");

    }


    public void scoreEdit() throws ClassNotFoundException, SQLException {

        Scanner sc = new Scanner(System.in);
        System.out.println("수정할 학번을 입력하세요");
        int sc_id = sc.nextInt();
        System.out.println("변경 자바 점수를 입력하세요");
        int sc_java = sc.nextInt();
        System.out.println("변경 파이썬 점수를 입력하세요");
        int sc_python = sc.nextInt();
        System.out.println("변경 C언어 점수를 입력하세요");
        int sc_c = sc.nextInt();

        int sc_sum = sc_java +sc_python+sc_c;
        int sc_avg = (sc_java +sc_python+sc_c)/3;

        Connection conn = Dbconn.getConnection();
        StringBuilder sql = new StringBuilder();
        sql.append("update t_score set sc_java =?, sc_python =?, sc_c=? , sc_sum =?, sc_avg=? where sc_id =?");
        // 점수를 수정하는 경우 합과 평균을 다시 입력해주지 않으면 변경되지 않으며 정보 출력간에 바뀌지 않아 내림차순이 안되게 되기 때문에
        // 업데이트간 평균과 합을 다시 바꿔줘야 한다.
        // 위의 학생정보 수정과 동일하게 업데이트 문을 사용하였습니다.
        PreparedStatement pstmt = conn.prepareStatement(sql.toString());

        pstmt.setInt(1, sc_java);
        pstmt.setInt(2, sc_python);
        pstmt.setInt(3, sc_c);
        pstmt.setInt(4, sc_sum);
        pstmt.setInt(5, sc_avg);
        pstmt.setInt(6, sc_id);
        int result = pstmt.executeUpdate();
        if (result >= 1) System.out.println("점수 변경 성공");
        else System.out.println("해당 학생이 없습니다 혹시 점수나 학생정보를 등록하셨나요??.");
    }

    public void scoreDelete() throws ClassNotFoundException, SQLException {
        // 삭제할 단어를 입력하세요
        Scanner sc = new Scanner(System.in);
        System.out.println("삭제할 학번(점수) 입력하세요");
        int sc_id = sc.nextInt();
        boolean isFind = false;
        Connection conn = Dbconn.getConnection();
        StringBuilder sql = new StringBuilder();
        sql.append("delete  from t_score where sc_id = ?");
        // 위의 학생정보 삭제 부분과 동일하게 딜리트 문을 사용하였습니다.
        PreparedStatement pstmt = conn.prepareStatement(sql.toString());
        pstmt.setInt(1,sc_id);
        int result = pstmt.executeUpdate();
        if (result >= 1) System.out.println("점수삭제 성공");
        else System.out.println("점수삭제 실패");

    }



}