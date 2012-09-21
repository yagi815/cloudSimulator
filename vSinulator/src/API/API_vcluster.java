package API;

import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.AbstractDocument.BranchElement;

/**
 * <pre>
 * API
 *   |_ API_for_vcluster.java
 * 
 * </pre>
 * 
 * Desc : Vcluster 가 사용 할 vsimulator 접속하는 API이다. 가상 클라우드인 시뮬레이터에서 필요한 모든 데이터를
 * 요청하고 받아온다.
 * 
 * @Company : KISTI
 * @Author :grkim
 * @Date :2012. 8. 10. 오후 2:43:57
 * @Version: 1.0
 * 
 */

public class API_vcluster {

	private static final int PORT = 7878;
	private static final String IP_ADDR = "127.0.0.1";
	// private static final String IP_ADDR = "150.183.234.168";
	private static InetAddress inetAddress = null;
	private static Socket socket = null;

	private static PrintWriter printWriter = null;
	private static BufferedReader bufferedReader = null;

	private static String tmp = "";

	public API_vcluster() {
		// TODO Auto-generated constructor stub

	}

	private static Object requestToSimulator(String command) {
		Object result = null;
		try {
			socket = new Socket(IP_ADDR, PORT);

			// 명령 서버로 날림
			printWriter = new PrintWriter(new OutputStreamWriter(
					socket.getOutputStream()));
			printWriter.println(command);
			printWriter.flush();

			// 서버로부터 결과 받아서 출력
			// bufferedReader = new BufferedReader(new
			// InputStreamReader(socket.getInputStream()));
			// while ( (tmp=bufferedReader.readLine()) !=null ) {
			// result += tmp+"\n";
			// }
			// bufferedReader.close();

			ObjectInputStream ois = new ObjectInputStream(
					socket.getInputStream());
			result = ois.readObject();

			ois.close();
			printWriter.close();
			socket.close();

		} catch (Exception e) {
			// TODO: handle EXception
			e.printStackTrace();
			System.out.println(e.toString());
		}
		return result;
		// return true;
	}

	// ******************************************************************
	// 0X: HOSTMACHINE
	// ******************************************************************

	/**
	 * Desc : 동작하고 있는 호스트 리스트를 가져온다
	 * 
	 * @Method Name : getRunningHostList
	 * @return 동작하는 호스트 리스트 반환 br <h2>EX) "host01, host02... " <br>
	 *         String으로 캐스팅해서 사용 (String)list.get(i)
	 */
	public List getRunningHostList() {
		List runningHostList = new ArrayList();
		runningHostList = (List) requestToSimulator("00:nothing");
		return runningHostList;
	}

	/**
	 * Desc : 호스트 전원을 켠다
	 * 
	 * @Method Name : turnOnHostMachine
	 * @param hostMachine
	 *            호스트머신 이름 EX) "host04"
	 * @return 성공하면 "1", 실패하면 "-1"
	 * 
	 */
	public String turnOnHostMachine(String hostMachine) {
		return (String) requestToSimulator("01:" + hostMachine);
	}

	/**
	 * Desc : 호스트 전원을 끈다.
	 * 
	 * @Method Name : turnOffHostMachine
	 * @param hostMachine
	 *            호스트머신 이름 EX) "host04"
	 * @return 성공하면 "1", 실패하면 "-1"
	 * 
	 */
	public String turnOffHostMachine(String hostMachine) {
		return (String) requestToSimulator("02:" + hostMachine);
	}

	/**
	 * Desc : 사용가능한 호스트 리스트
	 * 
	 * @Method Name : getAvailableHostList
	 * @return 사용가능한 호스트 리스트 반환 <br>
	 *         "host07, host08... " <br>
	 *         스트링으로 캐스팅해서 사용 (String)list.get(i)
	 * 
	 */
	public List getAvailableHostList() {
		List availableHostList = new ArrayList();
		availableHostList = (List) requestToSimulator("03:-");
		return availableHostList;
	}

	/**
	 * Desc : 현재 접속한 클라우드 이름을 가져온다.
	 * 
	 * @Method Name : getCloudName
	 * @return 현재 클라우드 이름 반환 <br>
	 *         EX) "CloudSinulator"
	 * 
	 */
	public String getCloudName() {
		return (String) requestToSimulator("04:-");
	}

	/**
	 * Desc : 모든 호스트의 리스트
	 * 
	 * @Method Name : getHostList
	 * @return 모든 호스트의 리스트 <br>
	 *         스트링으로 캐스팅해서 사용 (String)list.get(i)
	 */
	public String getHostList() {
		return (String) requestToSimulator("05:-");
	}

	// ******************************************************************
	// 2X: VIRTUAL MACHINE
	// ******************************************************************

	/**
	 * Desc : 새로운 가상머신을 생성한다.
	 * 
	 * @Method Name : createNewVirtualMachine
	 * @param virtualMachine
	 *            생성할 가상머신이름 EX) "host03-vm03" <br>
	 *            parameter가 "-" 이면, 앞에서 순차적으로 생성됨 *
	 * @return 성공하면 "1", 실패하면 "-1"
	 * 
	 */
	public String createNewVirtualMachine(String virtualMachine) {
		return (String) requestToSimulator("20:" + virtualMachine);
	}

	/**
	 * Desc : 삭제할 가상머신을 생성한다.
	 * 
	 * @Method Name : removeVirtualMachine
	 * @param virtualMachine
	 *            삭제할 가상머신이름 EX) "host03-vm03"
	 * @return 성공하면 "1", 실패하면 "-1"
	 * 
	 */
	public String removeVirtualMachine(String virtualMachine) {
		return (String) requestToSimulator("21:" + virtualMachine);
	}

	/**
	 * Desc : 동작하는 가상머신을 새로운 가상머신으로 이동
	 * 
	 * @Method Name : migrationVirtualMachine
	 * @param srcVirtualMachine
	 *            src 가상머신이름 EX) "host01-vm01"
	 * @param desVirtualMachine
	 *            desc 가상머신이름 EX) "host02-vm04"
	 * @return 성공하면 "1", 실패하면 "-1"
	 * 
	 */
	public String migrationVirtualMachine(String srcVirtualMachine,
			String desVirtualMachine) {
		return (String) requestToSimulator("22:" + srcVirtualMachine + ","
				+ desVirtualMachine);
	}

	/**
	 * Desc : 클라우드 내에서 동작중인 가상머신의 리스트를 얻어온다. 호스트-vm정보 의 포멧으로 형식 *
	 * 
	 * @Method Name : getRunningVmList
	 * @return 동작중인 가상머신 리스트를 반환 <br>
	 *         EX)"host01-vm01,host02-vm05,host07-vm06" <br>
	 *         스트링으로 캐스팅해서 사용 (String)list.get(i)
	 * 
	 */
	public List getRunningVmList(String hostName) {
		List runningVmList = new ArrayList();
		runningVmList = (List) requestToSimulator("23:" + hostName);
		return runningVmList;
	}

	/**
	 * Desc : Available 상태의 가상머신의 리스트를 얻어온다.
	 * 
	 * @Method Name : getAvailableVmList
	 * @return Available 상태의 가상머신 리스트를 반환 <br>
	 *         EX) "host07-vm10,host07-vm11,host07-vm12" * <br>
	 *         스트링으로 캐스팅해서 사용 (String)list.get(i)
	 */
	public List getAvailableVmList() {
		List availableVmList = new ArrayList();
		availableVmList = (List) requestToSimulator("24:nothing");
		return availableVmList;
	}

	/**
	 * Desc : FAIL 상태의 가상머신의 리스트를 얻어 온다. *
	 * 
	 * @Method Name : getFailVmList
	 * @return FAIL 상태의 가상머신 리스트를 반환 <br>
	 *         EX) "host07-vm06" * <br>
	 *         스트링으로 캐스팅해서 사용 (String)list.get(i)
	 */
	public List getFailVmList() {
		List failVmList = new ArrayList();
		failVmList = (List) requestToSimulator("25:nothing");
		return failVmList;
	}

	/**
	 * Desc : BUSY 상태의 가상머신의 리스트를 얻어온다. *
	 * 
	 * @Method Name : getBusyVmList
	 * @return BUSY 상태의 가상머신 리스트를 반환 <br>
	 *         EX) "host07-vm06" <br>
	 *         스트링으로 캐스팅해서 사용 (String)list.get(i)
	 */
	public List getBusyVmList() {
		List failVmList = new ArrayList();
		failVmList = (List) requestToSimulator("26:nothing");
		return failVmList;

	}

	/**
	 * Desc : IDLE 상태의 가상머신의 리스트를 얻어온다.
	 * 
	 * @Method Name : getIdleVmList
	 * @return IDLE 상태의 가상머신 리스트를 반환 <br>
	 *         EX) "host07-vm05" <br>
	 *         스트링으로 캐스팅해서 사용 (String)list.get(i)
	 */
	public List getIdleVmList() {
		List idleVmList = new ArrayList();
		idleVmList = (List) requestToSimulator("27:nothing");
		return idleVmList;
	}

	/**
	 * Desc : unhealthy 상태의 가상머신의 리스트를 얻어온다.
	 * 
	 * @Method Name : getUnhealthyVmList
	 * @return unhealthy 상태의 가상머신 리스트를 반환 <br>
	 *         EX) "host07-vm01" <br>
	 *         스트링으로 캐스팅해서 사용 (String)list.get(i)
	 */
	public List getUnhealthyVmList() {
		List unHealthyVmList = new ArrayList();
		unHealthyVmList = (List) requestToSimulator("28:nothing");
		return unHealthyVmList;
	}

	/**
	 * Desc : 클라우드 내의 가용한 모든 호스트의 리스트를 얻어온다. *
	 * 
	 * @Method Name : getTotalVmList
	 * @return 가용한 모든 호스트 리스트 들을 반환 <br>
	 *         EX) "host01-vm01,host02-vm02..." <br>
	 *         스트링으로 캐스팅해서 사용 (String)list.get(i)
	 */
	public List getTotalVmList() {
		List totalVmList = new ArrayList();
		totalVmList = (List) requestToSimulator("29:nothing");
		return totalVmList;
	}

	/**
	 * Desc : Job을 실행중인(busy) 머신 수를 반환
	 * 
	 * @Method Name : getRunningJobs
	 * @return Job을 처리중인 머신수 EX) 80
	 * 
	 */
	public String getRunningJobs() {
		return (String) requestToSimulator("30:nothing");
	}

	/**
	 * Desc : 최대로 생성가능한 가상머신의 개수를 반환
	 * 
	 * @Method Name : getTotalVMs
	 * @return 최대 생성가능한 가상머신 수 EX) 196
	 * 
	 */
	public String getTotalVMs() {
		return (String) requestToSimulator("31:nothing");
	}

	/**
	 * Desc : 가상머신의 상태를 반환
	 * 
	 * @Method Name : getVmStatus
	 * @param virtualMachine
	 *            가성머신 이름 EX) "host01-vm02"
	 * @return 다음중 하나 반환
	 *         "pending","prolog","running","shudown","eliplog","stop","null"
	 */
	public String getVmStatus(String virtualMachine) {
		return (String) requestToSimulator("32:" + virtualMachine);
	}

	/**
	 * Desc : Job이 수행중인지 확인
	 * 
	 * @Method Name : getVMBusy
	 * @param virtualMachine
	 *            가상머신 이름 EX) "host01-vm03"
	 * @return 상태반환 "busy" or "idle"
	 * 
	 */
	public String getVMBusy(String virtualMachine) {
		return (String) requestToSimulator("33:" + virtualMachine);
	}

	// ******************************************************************
	// 4X: VIRTUAL SPEC
	// ******************************************************************

	/**
	 * Desc : 가상머신이 런닝한 시간을 가져온다.
	 * 
	 * @Method Name : getVMActiveTime
	 * @param virtualMachine
	 * @return [시간:분:초 ] 스트링 형식으로 반환 <br>
	 *         EX) "00:10:13"
	 * 
	 */
	// public String getVMActiveTime(String virtualMachine){
	// String activeTime = "00:10:13";
	// return activeTime;
	// }
	/**
	 * 
	 * Desc : 특정 가상머신의 cpu 스펙을 가져온다.
	 * 
	 * @Method Name : getVMCpuInfo
	 * @param virtualMachine
	 *            cpu 정보를 가져올 가상 머신 이름을 String 형식으로 입력
	 * @return cpu 스펙을 스트링으로 반환 <br>
	 *         EX) "Intel(R) Xeon(R) CPU           E5640  @ 2.67GHz"
	 * 
	 */
	public String getVMCpuInfo(String virtualMachine) {
		return (String) requestToSimulator("40:" + virtualMachine);
	}

	/**
	 * Desc : 특정 가상머신의 mem 정보를 가져온다.
	 * 
	 * @Method Name : getVMMemInfo
	 * @param virtualMachine
	 *            메모리 정보를 가져올 가상 머신 이름을 String 형식으로 입력
	 * @return 메모리 정보를 스트링으로 반환 <br>
	 *         EX) "8Gbyte"
	 * 
	 */
	public String getVMMemInfo(String virtualMachine) {
		return (String) requestToSimulator("41:" + virtualMachine);
	}

	/**
	 * Desc : 특정 가상머신의 디스크 용량을 가져온다.
	 * 
	 * @Method Name : getVMDiskInfo
	 * @param virtualMachine
	 *            디스크 정보를 가져올 가상 머신 이름을 String 형식으로 입력
	 * @return 디스크 용량을 스트링으로 반환 <br>
	 *         EX) "100Gbyte"
	 * 
	 */
	public String getVMDiskInfo(String virtualMachine) {
		return (String) requestToSimulator("42:" + virtualMachine);
	}

	/**
	 * Desc : 특정 가상머신의 OS 정보를 가져온다.
	 * 
	 * @Method Name : getVMOSInfo
	 * @param virtualMachine
	 *            OS 정보를 가져올 가상 머신 이름을 String 형식으로 입력
	 * @return OS정보를 스트링으로 반환 <br>
	 *         EX) "Scientific Linux SLF release 5.7 (Lederman)"
	 * 
	 */
	public String getVMOSInfo(String virtualMachine) {
		return (String) requestToSimulator("43:" + virtualMachine);
	}

	/**
	 * Desc : 특정 가상머신의 OSBIT 정보를 가져온다.
	 * 
	 * @Method Name : getVMOSBitInfo
	 * @param virtualMachine
	 *            OS BIT 정보를 가져올 가상 머신 이름을 String 형식으로 입력
	 * @return OSBIT정보를 스트링으로 반환 <br>
	 *         EX) "Scientific Linux SLF release 5.7 (Lederman)"
	 * 
	 */
	public String getVMOSBitInfo(String virtualMachine) {
		return (String) requestToSimulator("44:" + virtualMachine);
	}

	/**
	 * Desc : 특정 가상머신의 Kernel 정보를 가져온다.
	 * 
	 * @Method Name : getVMKernelInfo
	 * @param virtualMachine
	 *            커널 정보를 가져올 가상 머신 이름을 String 형식으로 입력
	 * @return Kernel정보를 스트링 형식으로 반환 <br>
	 *         EX) "2.6.18-308.11.1.el5"
	 * 
	 */
	public String getVMKernelInfo(String virtualMachine) {
		return (String) requestToSimulator("45:" + virtualMachine);
	}

	/**
	 * Desc : 생성된 가상머신의 고유번호(ID)를 가져온다.
	 * 
	 * @Method Name : getVMUUID
	 * @param virtualMachine
	 *            UUID를 가져올 머신을 이름을 String 형식으로 입력
	 * @return VM 고유 번호 스트링 형식으로 반환
	 * 
	 */
	public String getVMUUID(String virtualMachine) {
		return (String) requestToSimulator("46:" + virtualMachine);
	}

	// ******************************************************************
	// MANIPULATION 8X:
	// ******************************************************************
	/**
	 * Desc : Job 수행
	 * 
	 * @Method Name : jobSubmit
	 * @param jobName
	 *            수행 Job 이름
	 * @return 성공하면 "1", 실패 "-1"
	 * 
	 */
	public String jobSubmit(String jobName) {
		return (String) requestToSimulator("80:" + jobName);
	}

	// ******************************************************************
	// FOR TEST
	// ******************************************************************

	/**
	 * Desc : Test 용 클라우드 출력 함수 * EX> prompt> dump <br>
	 * ====================================================================<br>
	 * HostName: host01<br>
	 * TotalVMs: 12 runningVMs: 3<br>
	 * --------------------------------------------------------------<br>
	 * vm ID: 2fa54c42-05c4-4fa4-a70a-87d6f1c69421 vm Name: master vm Status:
	 * running<br>
	 * vm ID: 10de73c4-46ae-4cde-af8a-3d3efbbd20a5 vm Name: vm001 vm Status:
	 * running<br>
	 * vm ID: 0b056dcf-f286-4d9c-ac63-9a3c2c553e63 vm Name: vm002 vm Status:
	 * running<br>
	 * <br>
	 * ===================================================================<br>
	 * 
	 * @Method Name : dumpCloudStatus
	 * @return 클라우드 상태를 출력 한다.
	 * 
	 */
	public void showHost(String hostName) {
		System.out.println("hostStatus....");
		String dumpCloudStatsus = "";

		List runningVmList = getRunningVmList(hostName);

		dumpCloudStatsus += "\n===================================================================\n";
		dumpCloudStatsus += "\n Running VMs: \n";
		//host 별로 런닝 vm 개수 필요
		//host 별로 idel 개수 필요
		//host 별로 busy 개수 필요
		
		
		dumpCloudStatsus += "--------------running Vm list --------------\n";
		for (int i = 0; i < runningVmList.size(); i++) {
			dumpCloudStatsus += (String) runningVmList.get(i) + "\n";
		}

		System.out.println(dumpCloudStatsus);

		// return dumpCloudStatsus;
	}

	public void showVM(String virtualMachine) {
		String dumpCloudStatus = "";
		dumpCloudStatus += "[ ID=" + getVMUUID(virtualMachine) + "\n"
				+ " Status=" + getVmStatus(virtualMachine) + "\n" + " Cpu="
				+ getVMCpuInfo(virtualMachine) + "\n" + " OS="
				+ getVMOSInfo(virtualMachine) + "\n" + " Disk="
				+ getVMDiskInfo(virtualMachine) + "\n" + " Busy="
				+ getVMBusy(virtualMachine) + " ]";
		System.out.println(dumpCloudStatus);
	}
,
	public void showCloud() {
		System.out.println("dumpCloudStatus....");
		String dumpCloudStatsus = "";

		dumpCloudStatsus += "====================================================================\n"
				+ "total VMs :"
				+ getTotalVMs()
				+ "\n running Vms :"
				+ getRunningVmList("-").size()
				+ "\ttotal Running Job :"
				+ getRunningJobs()
				+ "\ttotal idle VMs :"
				+ getIdleVmList().size() + "\n";
		dumpCloudStatsus += "\n===================================================================\n";

		System.out.println(dumpCloudStatsus);
	}

}
