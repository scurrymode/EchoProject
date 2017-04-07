/*
 * 자바를 이용하여 서버측 프로그래램을 작성한다.
 * */

package echo.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
	//대화를 나누기전에 접속을 알려주기 위한 객체!! 즉 아직 대화는 못나눈다!
	//서버는 클라이언트가 찾아오길 기다리므로, 클라이언트와 약속한 포트번호만 보유하면 된다.(생성자에 넣어~)
	//포트번호 0~1023까지는 이미 시스템이 점유하고 있다.
	ServerSocket server;
	int port = 8888;
	Socket socket;
	
	public MyServer() {
		try {
			
			server = new ServerSocket(port);
			System.out.println("서버생성");
			//클라이언트의 접속을 기다린다. 접속이 있을때까지 무한대기 즉 지연에 빠진다. 마치 스트림의 read()계열과 같다!
			//데이터가 있을때만 작동하고 무한대기하지
			while(true){
				socket=server.accept();
				System.out.println("접속자 발견");
				
				//소켓을 이용하여 데이터를 받고자 하는 경우엔 입력 스트림을, 데이터를 보내고자 하는 경우에는 출력 스트림을 사용
				InputStream is=socket.getInputStream();
				InputStreamReader reader=new InputStreamReader(is);
				
				int data;
				while(true){
					data = reader.read();
					if(data==-1)break;
					System.out.print((char)data);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new MyServer();
		

	}

}
