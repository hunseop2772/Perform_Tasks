import java.sql.SQLException;
import java.util.Scanner;

public class VocaMain {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        VocaDAO vocaDAO = new VocaDAO();

        while(true){
            System.out.println("        πππππ λ¨μ΄μ₯ πππππ");
            System.out.println("μνλ λ©λ΄λ₯Ό μ ννμΈμ");
            System.out.println("1. λ±λ‘ 2. λ¦¬μ€νΈ 3. κ²μ 4. μμ  5 μ­μ  6. μ’λ₯");
            int input  = sc.nextInt();
            if(input == 6){
                System.out.println("νλ‘κ·Έλ¨μ μ’λ£ν©λλ€.");
                break;
            }
            try {
                switch (input) {
                    case 1:
                        vocaDAO.insert();
                        break;
                    case 2:
                        vocaDAO.list();
                        break;
                    case 3:
                        vocaDAO.search();
                        break;
                    case 4:
                        vocaDAO.edit();
                        break;
                    case 5:
                        vocaDAO.delete();
                        break;
                }
            }catch (ClassNotFoundException e){
                e.printStackTrace();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
