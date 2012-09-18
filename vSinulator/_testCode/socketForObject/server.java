package socketForObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * pipeTest
 *   |_ sumulator.java
 * 
 * </pre>
 * 
 * Desc :
 * 
 * @Company : KISTI
 * @Author :grkim
 * @Date :2012. 7. 27. 오후 1:34:31
 * @Version:
 * 
 */
public class server implements Runnable {
	
	private static final int PORT = 7878;
	private static final String IP_ADDR = "127.0.0.1";
	
	private ServerSocket serverSocket = null;
	private Socket socket = null;
	private BufferedReader bufferedReader = null;	
	private String receiveString = null;

	private PrintWriter printdWriter = null;	
	private Object result = null;
	
//	private Vector vector = null;
//	private List hostList = null;
//	 
	
	
	
	
	
	
	
	
	public server() {
		// TODO Auto-generated constructor stub		
		Thread t = new Thread(this);
		t.start();
	}
	
	

	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		 ObjectOutputStream oos=null;

		while (true) {

			try {
				System.out.println("waiting client...");
				serverSocket = new ServerSocket(PORT);
				socket = serverSocket.accept();
				bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				receiveString = bufferedReader.readLine();
				
				System.out.println("server  받은것 :  "+receiveString);
				result = executeCommand(receiveString);
				

				// 처리 함수로 넘김 결과 받아서 다시 보내줌
//				printdWriter = new ObjectOutpuStream(new OutputStreamWriter(socket.getOutputStream()));
//				printdWriter.print(resultString);
//				printdWriter.flush();
				 oos = new ObjectOutputStream(socket.getOutputStream());
                 oos.writeObject(result);
                 oos.flush();    
				
				
				
				receiveString = "";
				
				bufferedReader.close();
				oos.close();
				socket.close();
				serverSocket.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				System.out.println(e.toString());
			}
		}
	}
	
	
		
	
	
	public static void main(String[] args) {		
		
		server s = new server();

		
	}	
	public Object  executeCommand(String command){
		List list = new ArrayList();
		if (command.substring(0, 1).equals("0")) {
			list.add("host01");
			list.add("host02");
			list.add("host03");			
		}
		
		
		return list;
	}
}