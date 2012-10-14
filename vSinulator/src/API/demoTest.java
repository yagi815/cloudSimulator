package API;

import java.util.Iterator;
import java.util.Random;

import SimulatorMain.Simulator;

/**
 * <pre>
 * API
 *   |_ demoTest.java
 *
 * </pre>
 *
 * Desc :
 * @Company : KISTI
 * @Author :grkim
 * @Date   :2012. 9. 26. 오전 11:49:50
 * @Version:
 *
 */
public class demoTest {
			
	private static API_android api_android= null;
	private static API_vcluster api_vcluster= null;
	private static tiny_vCluster  vCluster = null;
	
	
	
	public demoTest() {
		// TODO Auto-generated constructor stub
//		api_android = new API_android();	
		api_vcluster = new API_vcluster();	
		vCluster = new tiny_vCluster();
	}		
	//------------------------------------------------------------------
	public static void demoStart(){
		/*
		 * Max Queue 1000 개
		 * 총 가능 vm 수는 12*20 = 240개
		 * MaxHost = 20
		 * Max VM each = 12
		 *  
		 * job 서브미션은 5초에 40~50개 사이 
		 * 
		 * 시뮬레이터에서는 기본 가상머신 몇개 동작중
		 * 상태모니터링 해서 모자르면 vm생성~
		 * 상태모니터링해서 남으면 호스트머신 삭제
		 * Job 은 5~10초정도 수행하고 없어짐 
		 * 
		 * 
		 * 추가로 fail모듈 생성해서 vm 죽이고, 새로운 멋힌에 Job 이동시켜 수행한다. 
		 */
		
		
		
		
		
		
		
		final Random random = new Random();
		final int randomNumber = 0;
		int totalJobs=0;	
		
		
		
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					api_vcluster.showCloud();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		}).start();
		
		
		vCluster.reductionResorce(); // 리소스 정리 60초 마다 검사해서 정리 한다.
		
		
		for (int i = 0; i < 3; i++) {
			vCluster.job_submit(50*i); // 10초에 한번씩
			System.out.println(50*i+"개 job is submitted.");
			try {Thread.sleep(5000);} catch (InterruptedException e) {e.printStackTrace();}
		}
					
	}
	
	//------------------------------------------------------------------	
	public static void main(String[] args) {
		
//		new Simulator();
		
		
		
		demoTest demo = new demoTest();
		
		
		demo.demoStart();
		
	}
}

