package API;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import SimulatorMain.ExecuteCommand;

/**
 * <pre>
 * SimulatorMain
 *   |_ client.java
 * </pre>
 *
 * Desc :
 * @Company : KISTI
 * @Author :grkim
 * @Date   :2012. 7. 31. 오후 1:50:00
 * @Version:
 *
 */
public class TestClient {

	
	private static boolean IS_EXIT = true;
	private static Scanner scanner = null;
	private static String command = null;
	
	static API_vcluster API = null;
	
	public TestClient() {
		// TODO Auto-generated constructor stub		

	}	
	
	
	public static void executeCommand(String command, String parameter){
		
		if (command.contains("createvm")) {			       
			System.out.println(API.createNewVirtualMachine(parameter));			
		}
		if (command.contains("delvm")) {
			System.out.println(API.removeVirtualMachine(parameter));
		}
		if (command.contains("job-submit")) {
			System.out.println(API.jobSubmit(parameter));
		}
		if (command.contains("dump")) {
			API.dumpCloudStatus();			
		}
		if (command.contains("disvm")) {
			API.discribeVm(parameter);
		}
		
	}
	
	public static void main(String[] args){
		
		API = new API_vcluster();
		
		String result="";
		scanner =  new Scanner(System.in);
		

		while (IS_EXIT) {
	
			System.out.print("prompt> ");
			command = scanner.nextLine();
			
			if (command.equals("quit") || command.equals("exit")) {				
				IS_EXIT = false;
			} else {
				if (command.contains(" ")) {
					
					String[] tmp = command.split("[ ]");
					executeCommand(tmp[0], tmp[1]);
				}else {
					executeCommand(command, "");
				}
			}
		}		
	}
}
