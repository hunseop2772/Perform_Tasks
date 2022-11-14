import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) {
        System.out.println("대화명을 입력하세요");
        Scanner sc = new Scanner(System.in);
        String userName = sc.next();
        try {
            Socket socket = new Socket("192.168.6.13", 3579);
            ////client에서 서버로 접속하거나 Server에서 accept 하는데 필요한 클래스
            System.out.println("서버와 연결되었습니다");

            Thread sender = new Thread(new ClientSender(socket, userName));
            Thread receiver = new Thread(new ClientReceiver(socket));

            sender.start();
            receiver.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClientSender extends Thread { // 단일로 돌아감
        private Socket socket;
        private DataOutputStream out;
        private String userName;

        public ClientSender(Socket socket, String userName) {
            this.socket = socket;
            try {
                out = new DataOutputStream(socket.getOutputStream());
                this.userName = userName;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new InputStreamReader(System.in));
                // 터미널에서 입력한 데이터를 가저오는 것이다. 인풋스트림 리더를 통해 버퍼에 저장하고 버퍼에서 저장된 라이트
                if (out != null) out.writeUTF(userName);

                while (out != null) {
                    out.writeUTF("[" + userName + "]" + br.readLine());
                }// 메세지가 돌며 버퍼에서 있는것을 읽어 서버쪽으로 전송하는 구문
                // [apple] 안녕하세요를 라인별로 읽어 서버쪽으로 계속 전달

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (br != null) try {
                    br.close();
                } catch (IOException e) {
                }
                // 입출력 예외처리
                if (out != null) try {
                    out.close();
                } catch (IOException e) {
                }
            }
        }
    }

    static class ClientReceiver extends Thread {// 서버의 글을 받악가지고 처리하는 부분
        private Socket socket;
        private DataInputStream in;

        public ClientReceiver(Socket socket) {
            this.socket = socket;
            try {
                in = new DataInputStream(socket.getInputStream());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            while (in != null) {
                try {//읽어온것을 그저 찍어낸다
                    System.out.println(in.readUTF());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
