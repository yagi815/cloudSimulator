package messageQueueTest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;

public class client {

	/**
	 * Desc :
	 * @Method Name : main
	 * @param args
	 * 
	 */
	private static boolean IS_EXIT = true;

	
	private static Scanner read ;
	private static String command; 
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		// simulator 에 대이터를 요청 하면 받아서 출력 hell
		// for 에 사용자 cmd 받아서 처리 .
		System.out.println("---------------------------------");
		
		read = new Scanner(System.in);
		
		while(IS_EXIT){
						
			System.out.println("prompt>");
			command = read.next();
			
			if (command.equals("quit")) {
				IS_EXIT = false;
			} else {

				
				
				
				
			}
			
		}
		
	}
	private void dispUsage(){
		System.out.println("quit: exit the program.");
	}

}
