package echo.server;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EchoClient extends JFrame{
	JTextArea area;
	JScrollPane scroll;
	JPanel p_south;
	JTextField t_input;
	JButton bt_connect;
	//대화를 나눌 수 있는 소켓! 말걸때는 소켓으로부터 출력스트림 말들을때는 소켓으로부터 입력스트림
	Socket socket;//종이컵
	BufferedReader buffr;
	BufferedWriter buffw;
	
	
	public EchoClient() {
		area = new JTextArea();
		scroll = new JScrollPane(area);
		p_south = new JPanel();
		t_input = new JTextField(15);
		bt_connect = new JButton("접속");
		
		p_south.add(t_input);
		p_south.add(bt_connect);
		
		bt_connect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect();
			}
		});
		
		t_input.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				int key=e.getKeyCode();
				if(key==KeyEvent.VK_ENTER){
					send();
				}
			}
		});
		
		
		add(scroll);
		add(p_south, BorderLayout.SOUTH);
		
		
		setSize(300, 400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	//서버에 말 보내기!!
	public void send(){
		//텍스트상자의 메세지 값을 얻기
		String msg=t_input.getText();
		try {
			
			buffw.write(msg+"\n");	
			//받는애가 버퍼의 끝을 알수없다. 왜냐? 뛰어쓰기가 아니라 보내지는건 그냥 그자체라서
			buffw.flush();
			//버퍼에 남아있을지도 모를 데이터를 대상으로 모두 출력시켜버린다.
			
			String data=buffr.readLine();
			area.append(data+"\n");
			t_input.setText("");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	//서버에 접속하는 메서드
	public void connect(){
		try {
			socket = new Socket("localhost", 7777);
			
			//접속이 완료되었으니, 스트림 얻어놓자! 왜? 대화나누려고!
			buffr = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void main(String[] args) {
		new EchoClient();
	}
}
