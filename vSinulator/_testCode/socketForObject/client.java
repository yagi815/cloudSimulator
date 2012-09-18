package socketForObject;

import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;



/**
 * <pre>
 * API
 *   |_ API_for_vcluster.java
 *
 * </pre>
 *
 * Desc : Vcluster 가 사용 할  vsimulator 접속하는 API이다. 가상 클라우드인 시뮬레이터에서 
 * 필요한 모든 데이터를 요청하고 받아온다.  
 * @Company : KISTI
 * @Author :grkim
 * @Date   :2012. 8. 10. 오후 2:43:57
 * @Version: 1.0
 *
 */

public class client {
	
	
	private static final int PORT = 7878;
	private static final String IP_ADDR = "127.0.0.1";
	private static InetAddress inetAddress = null;
	private static Socket socket = null;
	
	private static PrintWriter printWriter = null;
	private static BufferedReader bufferedReader = null;

	private static String tmp = "";
	private static Object result =null;
	
	
	public client() {
		// TODO Auto-generated constructor stub
	}
	
	private static Object	requestToSimulator(String command){
//		String result=null;
		try {
			socket = new Socket(IP_ADDR,PORT);
			
			// 명령 서버로 날림
			printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			printWriter.println(command);
			printWriter.flush();
			
			//서버로부터 결과 받아서 출력
//			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));			
//			while (  (tmp=bufferedReader.readLine()) !=null   ) {
//				result += tmp+"\n";	
//			}			
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());                        
            //object 에 저장                          
            result = ois.readObject();

			
			
			
			
			ois.close();
			printWriter.close();			
			socket.close();
			
		} catch (Exception e) {
			// TODO: handle EXception
			e.printStackTrace();
			System.out.println(e.toString());
		}		
		return result;				
//		return true;
	}
	
	public static void main(String[] args) {
		
		
		
		result = requestToSimulator("0:host02");

		 List list = (ArrayList)result;
		 System.out.println("size :"+list.size());
		 for (int i = 0; i < list.size(); i++) {
			System.out.println((String)list.get(i));
		}
		
		 
//		 result = requestToSimulator("1:host02");
//		 int n = (int)result;
//		 String nn = (String)result;
//		 
//		 
//		 result = requestToSimulator("2:host02");
//		 boolean is  = (boolean)result;
//		 
//		 
//		 
//		 String[] strArray = (String[])result;
//		 
		 
		 
		 
		 
	}
}

