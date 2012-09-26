import java.awt.List;
import java.util.ArrayList;

public class aa {
	
	
	
	public static void changeOneChar(char[] ch){
		for (int i = 0; i < ch.length; i++) {
			if (ch[i] == '8') {
				ch[i] = 'e';
			}
			if (ch[i] == ';') {
				ch[i] = 't';
			}
			if (ch[i] == '4') {
				ch[i] = 'h';
			}
			if (ch[i] == '‡') {
				ch[i] = 'o';
			}
		}
		System.out.println(ch);
	}
	public static void changeThreeChar(char[] ch){
		for (int i = 0; i < ch.length-2; i++) {
			if (ch[i] == ';'&&ch[i+1] == '4'&&ch[i+2] == '8') {
				ch[i] = 't';
				ch[i+1] = 'h';
				ch[i+2] = 'e';				
			}			
		}
		System.out.println(ch);
	}

	public static void main(String[] args) {
		String str = "53‡‡†305))6*;4826)4‡.)806*;48†8¶60))85;;]8*;:‡*8†83("
				+ "88)5*†;46(;88*96*?;8)*‡(;485);5*†2:*‡(;4956*2(5*-"
				+ "4)8¶8*;4069285);)6†8)4‡‡;1(‡9;48081;8:8‡1;48†85;4)"
				+ "485†528806*81(‡9;48;(88;4(‡?34;48)4‡;161;:188;‡?;";

		String key = "012345689‡†()*;?-`:";
		char[] keyList = key.toCharArray();
		int[] keyListCnt = new int[keyList.length];

		char ch[] = new char[str.length()];
		ch = str.toCharArray();

//		oneChar(ch, keyList, keyListCnt);
//		twoChar(ch);
//		threeChar(ch);
		
		changeThreeChar(ch);
		changeOneChar(ch);
		
	}
//--------------------------------------------------
//--------------------------------------------------
	public static void oneChar(char[] ch, char[] keyList, int[] keyListCnt) {
		for (int i = 0; i < ch.length; i++) {
			
			for (int j = 0; j < keyList.length; j++) {				
				if (ch[i] == keyList[j]) {
					keyListCnt[j] += 1;
				}
			}
			
		}
		
		
		for (int i = 0; i < keyList.length; i++) {
			System.err.println(keyList[i]+"\t"+keyListCnt[i]);
		}
	}

//--------------------------------------------------
//--------------------------------------------------
	public static void twoChar(char[] ch) {
		char keySet[] = new char[2];

		for (int i = 0; i < ch.length - 1; i++) {

			keySet[0] = ch[i];
			keySet[1] = ch[i + 1];

			twoChareCnt(keySet, ch);
		}
	}

	public static void twoChareCnt(char[] key, char[] ch) {
		char[] two = key;

		int cnt = 0;
		for (int i = 0; i < ch.length - 1; i++) {
			if (ch[i] == two[0] && ch[i + 1] == two[1]) {
				cnt++;
			}
		}
		System.out.print(key);
		System.out.println("\t" + cnt);
	}
	
	//--------------------------------------------------------	
	//--------------------------------------------------------	
	public static void threeChar(char[] ch) {
		char keySet[] = new char[3];
		
		for (int i = 0; i < ch.length - 2; i++) {
			
			keySet[0] = ch[i];
			keySet[1] = ch[i + 1];
			keySet[2] = ch[i + 2];
			
			threeChareCnt(keySet, ch);
		}
	}	
	public static void threeChareCnt(char[] key, char[] ch) {
		char[] three = key;
		
		int cnt = 0;
		for (int i = 0; i < ch.length - 2; i++) {
			if (ch[i] == three[0] && ch[i + 1] == three[1] && ch[i + 2] == three[2] ) {
				cnt++;
			}
		}
		System.out.print(key);
		System.out.println("\t" + cnt);
	}
	
	
	

}
