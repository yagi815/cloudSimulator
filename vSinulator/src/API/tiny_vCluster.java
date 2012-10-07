package API;

import java.util.List;



public class tiny_vCluster {
	
	
	private static int maxQueue=1000;;
	private static int queue=0;
	private static int running;
	private static int cnt=1;
	
	
	private API_vcluster API;
	private JobQueue jobQueue;
	
	
	
	public tiny_vCluster() {	
		API	 = new API_vcluster();
		jobQueue = new JobQueue(maxQueue);
	}
	private int getMaxQueue(){
		return this.maxQueue;		
	}
	private int getQueue(){
		return this.queue;
	}	
	
	
	
	
	
	public void reductionResorce(){
		//1분마다 검사해서 필요 없는 머신 끈다.
		//vm먼저 끄기,
		//running 중이 vm이 하나도 없는 머신은 호스트 전원끄기
		
		new Thread(new Runnable() {
			
			boolean IS_EXIT=false;
			List runningHost=null;
			
			
			@Override
			public void run() {
				
				while (!IS_EXIT){
					
					try {Thread.sleep(60000);} catch (InterruptedException e) {e.printStackTrace();}
					
					runningHost = API.getRunningHostList();
					
					for (int i = 0; i < runningHost.size(); i++) {
						if(API.getRunningVmList((String)runningHost.get(i)).size() ==0 ){
							API.turnOffHostMachine((String)runningHost.get(i));
						}
					}
					
					
				}

				
			}
		}).start();
		
	}
	
	
	
	public void job_submit(int num){
		
		int runningJobs = getRunninJobs();
		int availSlots = getAvailableSlots();
		int runninVMs = getRunningVms();
		int totalAvailableVMs = getTotalAvailableVMs();
		int idleVMs = getIdleVMs();
		
		
		int remainJob = num;
		int sendCnt = 0;
//		int remainJobs = ;

		//1.  큐에 Job 모두 넣기 
//		for (int i = 0; i < num; i++) {			
//			API.jobSubmit(String.format("job%3d", i));			
//		}
		for (int i = 0; i < num; i++) {
			if (jobQueue.isFull()) {
				System.out.println("큐가 가득 찼습니다. ");
				break;
			}
			jobQueue.enQueue(String.format("%10d.job", cnt++));			
		}		
		
		//2. avail list 대로 찾아서  job 수행하기 모두 수행		
		while (!jobQueue.isEmpty()) {	
			try {Thread.sleep(200);} catch (InterruptedException e) {e.printStackTrace();}
			
			if (idleVMs > remainJob) {
				API.jobSubmit(jobQueue.deQueue());
				remainJob--;
			}else { 
				// 남은 Job이 생성가능한 vm보다 크면
				
 

					// 가능한 슬롯이 10개보다 많으면 VM생성
					if (availSlots > 10) {
						
						for (int i = 0; i < 10; i++) {
							createVM();
						}
					}else {
						// 10개보다 작으면, host를 하나 더 생성한다.
						turnOnHostMachine();
					}

					// 값 다시 읽기 
					availSlots = getAvailableSlots();
					idleVMs = getIdleVMs();					
				
			}			
		}		
	}
	private void turnOnHostMachine(){
		if (API.turnOnHostMachine("-").equals("1")) {
			System.out.println("host turn on");
		}else {
			System.out.println("fail");
		}
	}
	private void createVM(){
		API.createNewVirtualMachine("-");
	}
	private int getTotalAvailableVMs(){
		return Integer.parseInt(API.getTotalAvailableVMs());
	}
	private int getRunninJobs(){
		return API.getBusyVmList("-").size();		
	}	
	private int getAvailVms() {
		return API.getAvailableVmList("-").size();
	}
	private int getAvailableSlots() {
		return API.getAvailableVmList("-").size();
	}
	private int getRunningVms(){
		return API.getRunningVmList("-").size();
	}
	private int getIdleVMs(){
		return API.getIdleVmList("-").size();
	}
	
	
	/*
	public static void main(String[] args) {
		JobQueue q = new JobQueue(5);
		q.enQueue("a");
		q.enQueue("b");
		q.enQueue("c");
		q.enQueue("d");
		q.enQueue("e");
		q.enQueue("f");


		while (!q.isEmpty()) {
			System.out.println(q.deQueue());
			
		}
	}
	*/
	
}

class JobQueue {
	private int size=0;
	private int front;
	private int rear;
	
	private String queue[] = null;
	private int cnt=0;
	
	public JobQueue() {
		// TODO Auto-generated constructor stub		
	}
	public JobQueue(int size){
		this.size = size;	
		queue = new String[size];
		
		this.front = 0;
		this.rear = 0;
	}
	
	
	
	public boolean enQueue(String jobName) {
		boolean is;
		if (!isFull()) {
			queue[front] = jobName;
			front = (front+1)%size;
			return true;
		}else{
			return false;
		}
	}
	
	public String deQueue() {
		String 	str = peek();
		
		if (str != null) {
			rear = (rear +1)%size;		
		}
		return str;		
	}
	
	public String peek() {
		String str=null;
		if (!isEmpty()) {
			str = queue[rear];
		}else {
			System.out.println("큐에 데이터 없음");
			str = null;
		}
		return str;
	}
	
	
	
	
	
	
	
	private boolean incrementSize(int size) {
		int tempSize = this.size+size;
		
		String[] tempQueue = new String[tempSize];
		System.arraycopy(queue, 0, tempQueue, 0, this.size);
		
		this.queue = tempQueue;
		this.size = tempSize;
		
		return true;	
	}
	public boolean isFull() {
		// 입구에서 출구가 앞쪽으로 1칸 차이면 가득 찬 것이다. 
		if ( (front +1)%size == rear ) {
			return true;
		}else{
			return false;			
		}
	}
	public boolean isEmpty() {
		//입구와 출구가 같으면 비어있는것이다. 
		if (front == rear) {
			return true;
		} else {
			return false;
		}
	}
	
	
	
}
