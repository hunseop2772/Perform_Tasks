import java.sql.SQLException;
import java.util.Scanner;

public class ScoreMain {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ScoreDAO scoreDAO = new ScoreDAO();

        while(true) {
            System.out.println("        📚📚📚📚📚 메뉴 📚📚📚📚📚");
            System.out.println("원하는 메뉴를 선택하세요");
            System.out.println("1. 등록 2. 리스트 3. 수정 4. 삭제 5 성적등록 6. 성적보기 7. 종료");
            int input = sc.nextInt();
            if (input == 7) {
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
                        scoreDAO.score();
                        break;
                    case 6:
                        scoreDAO.scoreList();
                        break;

                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
