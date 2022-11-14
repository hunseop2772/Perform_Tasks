import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;

public class ChatServer {

    private HashMap<String, OutputStream> clients;
    // <유저네임, 데이터>
    // 키는 아이디를 저장하고 내보네는 스트림을 아웃풋으로 하는 해시맵을 클라이언트라고한다.
    // 키와 벨류로 있는것이 헤시멥
    // 필드만 만들어 놓움

    public static void main(String[] args) {
        new ChatServer().start();
        // 메인이 실행되자 마자 생성자 호출하고 쳇서버, 스타트 실행
    }

    public ChatServer() {
        clients = new HashMap<>();
    }

    public void start() {
        ServerSocket serverSocket = null;
        //서버 프로그램을 개발할 때 쓰이는 클래스
        Socket socket = null;
        //client에서 서버로 접속하거나 Server에서 accept 하는데 필요한 클래스

        try {
            serverSocket = new ServerSocket(3579);
            //클라이언트가 아이피 주소와 포트번호까지 써줘야 이 문장으로 들어올 수 있다.
            System.out.println("서버가 시작되었습니다");

            while (true) {
                socket = serverSocket.accept();
                // 서버가 사용자가 들어올 때까지 대기하고 있는 장소로
                // 들어오면 일반 소켓을 만들어지게 된다. 사용자의 소켓, 포트 등 정보를 알 수 있다.
                System.out.println("IP : " + socket.getInetAddress() +
                        ", PORT : " + socket.getPort() + "에서 접속했습니다.");
                ServerReceiver thread = new ServerReceiver(socket);
                thread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // 모든 접속한 사람들에게 동일한 내용을 보네기 위한 메소드를 만들어보자

    public void sendToAll(String msg) {
        // 클라이언트가 해시맵, 키셋은 유저네임
        // 키셋()은 메소드를 사용하여 set객체를 반환받은 후 이터레이터 사용
        Iterator<String> it = clients.keySet().iterator();//탐색 / 이터레이터 해시멥을 탐색하는
        // 전체 출력을 하는 경우 반복문을 사용하지 않고 Iterator을 사용하여 출력한다.
        while (it.hasNext()) { //자료가 있다면(접속된 사용자 만큼 반복)
            try {
                DataOutputStream out = (DataOutputStream) clients.get(it.next());
                // it.next() 는 유저네임
                //clients.get(it.next()는 아웃풋 스트림 -> 데이터 아웃풋 스트림으로
                // 들어가게 하기 위해 (DataOutputStream)사용하여 변환하게 한다.
                out.writeUTF(msg); // 받은걸 출력
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class ServerReceiver extends Thread {
        private Socket socket; // 참조변수
        private DataInputStream in;
        private DataOutputStream out;
        public ServerReceiver(Socket socket) {// 소켓을 메개변수로 넣고 서로 연관시키기
            this.socket = socket;
            try {
                in = new DataInputStream(socket.getInputStream());
                // 소켓에 데한 인풋 스트림을 만들어 입력가능하게
                out = new DataOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override // 스레드를 만들었기 때문에 만들어야 한다.
        public void run() {
            String userName = "";
            try {
                userName = in.readUTF(); //이름 읽어오기
                // 상대가 입력하지 않으면 계속 대기한다(스캐너 역할)
                sendToAll("😁" + userName + "님이 들어오셨습니다"); // 모든 클라이언트에 전송
                clients.put(userName, out);
                // 서버가 있고 클라이언트가 있으면 접속을 하게되면 유저네임을 집어넣고 접속한 사람에게 아웃풋 스트림을 전송하면
                // 메시지를 보넬 통로를 만든것이다. 이는 해시맵을 만든것으로 통로가 서로 달라 각 쌍으로 키와 벨류로 저장한 것이다.
                //아이디 별로 아웃풋 스트림을 해시멥에 담아준것이다.
                System.out.println("현재 접속자 : " + clients.size()); //수는 해시맵의 사이즈로 확인 가능하다.

                while (in != null) {
                    String msg = in.readUTF();
                    if (msg.contains("quit")) break; // 종료
                    sendToAll(msg);//아니면 모든 사용자에게 메시지를 전송한다.
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                sendToAll("😢" + userName + " 님이 나가셨습니다");
                clients.remove(userName);
                System.out.println("IP : " + socket.getInetAddress() +
                        ", PORT : " + socket.getPort() + "에서 접속종료.");
                System.out.println("현재 접속자 : " + clients.size());
            }
        }
    }
}
