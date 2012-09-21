package API;

import java.awt.List;
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
		
		/*
		 * cloudinfo
		 * 클라우드 정보를 출력 
		 * Total host:       
		 * Total vms:	runningVms:		idleVms:	    
		 * 
		 * list vms 
		 * 시뮬레이터에 올라가잇는 모든 가상머신 리스트
		 * 
		 * list runningvms
		 * 전원이 들어가서 동작중인 가상머신 리스트 
		 * 
		 * list busyvms
		 * job을 수행중인 가상머신 리스트
		 * 
		 *  
		 * list hosts
		 * 시뮬레이터에 있는 물리적인 호스트 들의 리스트
		 * 
		 * list failvms
		 * fail vm 리스트 
		 * 
		 * list availvms
		 * available vm 리스트 
		 * 
		 * list idlevms 
		 * idle vm 리스트
		 *  
		 * 
		 * 
		 * showvm 'vm-name'
		 * showhost 
		 * dump 형식으로 출력
		 * showhost 'hostname'
		 * dump 형식으로 출력하되 한 호스트만 
		 * 
		 * starthost 'hostname'
		 * 호스트 전원 켜기
		 * 
		 * stophost 'hostname'
		 * 호스트 전원끄기 
		 * 
		 * startvm 'vm-name'
		 * vm 추가 생성
		 * 
		 * stopvm 'vm-name'
		 * vm 전원 끄기 / 삭제 
		 *		 * 
		 * job-submit 'job-name'
		 * job 수행  
		 * 
		 * 
		 * 
		 * */
		
		
		
		if (command.contains("list")) {
			
			if ( parameter.equals("vms") ) {
			}
			if(parameter.equals("runningvms")){
				System.out.println(API.getRunningVmList("-"));				
			}
			if (parameter.equals("busyvms")) {
				System.out.println(API.getBusyVmList());				
			}
			if (parameter.equals("hosts")) {				
				System.out.println(API.getHostList());				
			}
			if (parameter.equals("hostavail")) {				
				System.out.println(API.getAvailableHostList());				
			}			
			if (parameter.equals("failvms")) {				
				System.out.println(API.getFailVmList());				
			}
			if (parameter.equals("idlevms")) {				
				System.out.println(API.getIdleVmList());				
			}
			if (parameter.equals("availvms")) {				
				System.out.println(API.getAvailableVmList());				
			}			
		}		
		
		if (command.contains("createvm")) {		
			System.out.println(API.createNewVirtualMachine(parameter));			
		}
		if (command.contains("delvm")) {
			System.out.println(API.removeVirtualMachine(parameter));
		}
		
			
		if (command.equals("showvm")) {
			API.showVM(parameter);
		}
		if (command.equals("showhost")) {
			API.showHost(parameter);
		}
		if (command.equals("showcloud")) {
			API.showCloud();
		}
	
	
		if (command.contains("starthost")) {
			System.out.println(API.turnOnHostMachine(parameter));
		}
		if (command.contains("stophost")) {
			System.out.println(API.turnOffHostMachine(parameter));			
		}
//		if (command.contains("starvm")) {
//			
//		}
//		if (command.contains("stopvm")) {
//			
//		}
		
		
		
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
//				if (command.contains(" ")) {
					
					String[] tmp = command.split("[ ]");
					if (tmp.length > 1) {
						
						executeCommand(tmp[0], tmp[1]);
					}
					if (tmp.length == 1) {
						executeCommand(tmp[0], "-");
					}
//				}else {
//					executeCommand(command, "");
//				}
			}
		}		
	}
}
