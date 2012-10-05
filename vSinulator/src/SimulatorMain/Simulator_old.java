package SimulatorMain;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


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
public class Simulator_old implements Runnable {
	
	private static final int PORT = 7878;

	
	private ServerSocket serverSocket = null;
	private Socket socket = null;
	private BufferedReader bufferedReader = null;	
	private String receiveString = null;

	private PrintWriter printdWriter = null;	
	private Object resultObj = null;
	

	
	private static ExecuteCommand executeCommand= null;
	private static SimManager simManager = null;
	
	
	
	
	
	
	
	public Simulator_old() {
		// TODO Auto-generated constructor stub		
		executeCommand = new ExecuteCommand();
		
		
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
				
				

				System.out.println("receiveString > "+receiveString);
				resultObj = executeCommand.execute(receiveString);
				


				
				
				
				 oos = new ObjectOutputStream(socket.getOutputStream());
                 oos.writeObject(resultObj);
                 oos.flush();    
                 
				
				receiveString = "";
				resultObj = null;
				bufferedReader.close();
//				printdWriter.close();
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
		new Simulator_old();		
//		simManager.initSimulator();		
//		Simulator simulator = new Simulator();
		
	}	
}