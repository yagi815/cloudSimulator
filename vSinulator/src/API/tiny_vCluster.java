package API;

public class tiny_vCluster {
	
	
	private static int maxQueue=1000;;
	private static int queue=0;
	private static int running;
	private static int cnt=0;
	
	
	private API_vcluster API;
	private Queue jobQueue;
	
	
	
	public tiny_vCluster() {
		// TODO Auto-generated constructor stub
		API	 = new API_vcluster();
		jobQueue = new Queue(maxQueue);
	}
	
	
	
	

	private int getMaxQueue(){
		return this.maxQueue;		
	}
	private int getQueue(){
		return this.queue;
	}	
	
	private void job_submit(int num){
		
		int runningJobs = getRunninJobs();
		int availSlots = getAvailableSlots();
		int requestJobs = num;
//		int remainJobs = ;

		//1.  큐에 Job 모두 넣기 
//		for (int i = 0; i < num; i++) {			
//			API.jobSubmit(String.format("job%3d", i));			
//		}
		for (int i = 0; i < num; i++) {
			if (!jobQueue.isFull()) {
				System.out.println("큐가 가득 찼습니다. "+i+"개 job이 큐에 들어갔습니다. ");
				break;
			}
			jobQueue.enQueue(String.format("%d.job", cnt++));			
		}		
		
		//2. avail list 대로 찾아서  job 수행하기 모두 수행 
		
		while (!jobQueue.isEmpty()) {
						
			if (getAvailableSlots() > 0) {
				API.jobSubmit(jobQueue.deQueue());				
			}else { //가능한 슬롯이 없으면 vm 을 생성 한다. 
				if (getRunningVms() < getTotalAvailableVMs()) {
					//vm 하나씩 생성 
					createVM();
				}
			}
			
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
	
	
	
	public static void main(String[] args) {
		Queue q = new Queue(5);
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
		
	
}

class Queue {
	private int size=0;
	private int front;
	private int rear;
	
	private String queue[] = null;
	private int cnt=0;
	
	public Queue() {
		// TODO Auto-generated constructor stub		
	}
	public Queue(int size){
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
