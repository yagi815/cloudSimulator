package messageQueueTest;

import java.io.IOException;
import java.util.Random;



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
public class simulator extends Thread {

	/**
	 * Desc :
	 * 
	 * @Method Name : main
	 * @param args
	 * 
	 */
	private final int NUMBERCLOUDMACHNE = 100;

	private String sCloudName;
	private static int nNumRunning;
	private static int nNumIdel;
	private static int nNumUnhealthy;
	private static int nNumAvailable;
	private static int nNumQueue;

	public simulator() {
		// TODO Auto-generated constructor stub
		initDat();
	}

	public void initDat() {
		Random rNum = new Random();

		nNumRunning = rNum.nextInt(30);
		nNumIdel = rNum.nextInt(30);
		nNumUnhealthy = rNum.nextInt(30);
		nNumAvailable = rNum.nextInt(30);
		nNumQueue = rNum.nextInt(30);
		System.out.println("All data were reseted..");
	}

	@Override
	public void run() {
		while (true) {

			// TODO Auto-generated method stub
			try {
				Thread.sleep(3000);
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			initDat();

		}
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		Thread t = new simulator();
		t.start();
		
		
	
//		MessageQueue queue = MessageQueueService.getMessageQueue("test");
//
//		MessageQueue  que ;
//		
//		Message mm = null;
		
		
		// 1. 데이터 정리
		// 2. 간단한 자료구조
		// 3. 자료구조 데이터를 변경하는 쓰레드 생성( policy 가지고 있음)

		// 4. 메인 줄기엣 IPC while 로 대기

	
		while (true) {
			
			
			
			
			
			
		}

		
		
		
	}

	private void parseCommand() {

	}

	private void executeCommand() {

	}

}
