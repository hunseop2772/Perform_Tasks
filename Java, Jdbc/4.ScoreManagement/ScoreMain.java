import java.sql.SQLException;
import java.util.Scanner;

public class ScoreMain {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ScoreDAO scoreDAO = new ScoreDAO();

        while(true) {
            System.out.println("        πππππ λ©λ΄ πππππ");
            System.out.println("μνλ λ©λ΄λ₯Ό μ ννμΈμ");
            System.out.println("1. λ±λ‘ 2. λ¦¬μ€νΈ 3. μμ  4. μ­μ  5 μ±μ λ±λ‘ 6. μ±μ λ³΄κΈ° 7. μ’λ£");
            int input = sc.nextInt();
            if (input == 7) {
                System.out.println("νλ‘κ·Έλ¨μ μ’λ£ν©λλ€.");
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
