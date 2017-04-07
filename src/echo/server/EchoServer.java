/*
 * �������α׷��̶�?
 * Ŭ���̾�Ʈ�� �޼����� �״�� �ٽ� �����ϴ� ����� ����!! ä�� ���� 1�ܰ�
 * */

package echo.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	ServerSocket server;
	int port = 7777;
	
	public EchoServer() {
		try {
			server = new ServerSocket(port);
			System.out.println("��������!!");
			
			//server.accept()�� �����ڰ� ���������� ���� ���
			Socket socket=server.accept();
			InetAddress ia=socket.getInetAddress();
			String ip=ia.getHostAddress();
			System.out.println(ip+"������ �߰�!!");
			
			//Ŭ���̾�Ʈ�� �����͸� �ޱ� ���� �Է� ��Ʈ�� ���
			
			BufferedReader buffr=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedWriter buffw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			//Ŭ���̾�Ʈ�� �� ���!
			
			//��ȭ�� ��� ����������, ����ΰ� �Ʒ��� while ���ȿ� ���������Ƿ�, ���̻� �߰� �����ڿ� ���� ���� ����� �Ұ��ϴ�!
			while(true){
				String msg=buffr.readLine();
				System.out.println("Ŭ���̾�Ʈ�� ���� ��:"+msg);
				
				buffw.write(msg+"\n");//���ٺ�����
				buffw.flush();//���� �о��
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new EchoServer();
		

	}

}
