package _test;

import java.util.UUID;

public class UUIDTest {

	/**
	 * Desc :
	 * UUID (또는 GUID)는 Universally Unique Identifier(또는 Globally Unique Identifier)의
	 *  약자로 프로그래밍에서 유일한 ID값을 얻기위해 종종 사용되어진다. 하나의 UUID는 16 바이트(128비트) 숫자이고,
	 *   canonical 형태는 32개의 16진수 숫자와 이를 5개의 그룹(8-4-4-4-12)으로 나누는 4개의 하이픈(-)을 포함하여 
	 *   총 36개의 문자열로 표현된다. UUID에는 다음과 같은 5가지 버전이 존재한다.

    MAC address based (v1)
    DCE Security based (v2)
    Name based + MD5 hash (v3)
    Random (v4)
    Name based + SHA1 hash (v5)
    
    일반적으로 UUID는 다양한 분산환경 머신에서 유일한 값이 필요할 때 좋은 선택이 될 수 있는데, 다음과 같이 다양한 경우 등에 사용되어 질 수 있다.

    임시파일 이름 생성
    웹싸이트 방문자에 대한 유일한 값(세션값 등) 생성
    트랜잭션 ID 생성
    DB 시퀀스를 대신하는 Primary Key 생성




	 * @Method Name : main
	 * @param args
	 * 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		
		UUID uuid1 = new UUID(0xaaa,0xffff);		
		System.out.println(uuid1.toString());
		
		
		UUID uuid2 = UUID.fromString("3051a8d7-aea7-1801-e0bf-bc539dd60cf3");
		System.out.println(uuid2.toString());
		
		UUID uuid3 = UUID.randomUUID();
		System.out.println(uuid3.toString());
		
		
		
	}

}
