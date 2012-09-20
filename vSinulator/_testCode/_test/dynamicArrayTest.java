package _test;

import java.util.Arrays;


public class dynamicArrayTest {
	
	
	static int max = 5;

	/**
	 * Desc :
	 * @Method Name : main
	 * @param args
	 * 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		testClass[] t = new testClass[max];
		
		
		for (int i = 0; i < t.length; i++) {
			t[i] = new testClass();
			t[i].pri();
		}
		System.out.println("--");
			
		
		t = Arrays.copyOf(t, t.length+3				);
		
		for (int i = 0; i < t.length; i++) {
//			t[i] = new testClass();
//			t[i].pri();
			if (t[i] == null) {
				t[i] = new testClass();
				t[i].pri();
			}
		}
		
		
		

	}

}

class testClass{
	public testClass() {
		// TODO Auto-generated constructor stub
	}
	public void pri(){
		
		System.out.println("hello");
	}

}
