import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.Scanner;

public class ScoreMain {
    public static void main(String[] args) {
    // 가시성을 높이기 위해 메인페이지에서는 스위치 문을 활용하였습니다.
        Scanner sc = new Scanner(System.in);
        ScoreDAO scoreDAO = new ScoreDAO();
        // 기존과 다른점은 프라이엄리 키를 스트링 형식에서 학번이기 떄문에 인트형으로 바꾸었습니다.
        while(true){
            System.out.println("\n          📚📚📚📚📚 학생 기록부 📚📚📚📚📚");
            System.out.println("                원하는 메뉴를 선택하세요");
            System.out.println("    1. 등록 2. 등록된 정보 출력 3. 정보 수정 4. 삭제 5 검색 \n" +
                    "       6. 점수 등록 7. 점수 수정 8. 점수 삭제 9.종료");
            int input  = sc.nextInt();
            if(input == 9){
                System.out.println("프로그램을 종료합니다.");
                break;
            }
            try {
                switch (input) {
                    case 1:
                        scoreDAO.insert();
                        break;
                    case 2:
                        scoreDAO.list();
                        break;
                    case 3:
                        scoreDAO.edit();
                        break;
                    case 4:
                        scoreDAO.delete();
                        break;
                    case 5:
                        scoreDAO.search();
                        break;
                    case 6:
                        scoreDAO.score();
                        break;
                    case 7:
                        scoreDAO.scoreEdit();
                        break;
                    case 8:
                        scoreDAO.scoreDelete();
                        break;
                }
            }catch (ClassNotFoundException e){
                //Class.forName에 해당하는 예외처리를 해줘야한다
                e.printStackTrace();
            }catch (SQLException e){
                //SQL Server에서 경고 또는 오류를 반환할 때 throw되는 예외이다.
                //연결이 안되었을때는(getConnection 빨간줄 생성)
                e.printStackTrace();

            }
        }
    }
}
