package _test;

public class queueTest {
	
	
	private static int maxQueue;
	private static int queue;
	private static int running;
	private static int n;
	
	
	
	
	public queueTest() {
		// TODO Auto-generated constructor stub
	 
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
