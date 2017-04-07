/*
 * �ڹٸ� �̿��Ͽ� ������ ���α׷����� �ۼ��Ѵ�.
 * */

package echo.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
	//��ȭ�� ���������� ������ �˷��ֱ� ���� ��ü!! �� ���� ��ȭ�� ��������!
	//������ Ŭ���̾�Ʈ�� ã�ƿ��� ��ٸ��Ƿ�, Ŭ���̾�Ʈ�� ����� ��Ʈ��ȣ�� �����ϸ� �ȴ�.(�����ڿ� �־�~)
	//��Ʈ��ȣ 0~1023������ �̹� �ý����� �����ϰ� �ִ�.
	ServerSocket server;
	int port = 8888;
	Socket socket;
	
	public MyServer() {
		try {
			
			server = new ServerSocket(port);
			System.out.println("��������");
			//Ŭ���̾�Ʈ�� ������ ��ٸ���. ������ ���������� ���Ѵ�� �� ������ ������. ��ġ ��Ʈ���� read()�迭�� ����!
			//�����Ͱ� �������� �۵��ϰ� ���Ѵ������
			while(true){
				socket=server.accept();
				System.out.println("������ �߰�");
				
				//������ �̿��Ͽ� �����͸� �ް��� �ϴ� ��쿣 �Է� ��Ʈ����, �����͸� �������� �ϴ� ��쿡�� ��� ��Ʈ���� ���
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
