package API;

import java.util.ArrayList;
import java.util.List;


/**
 * <pre>
 * API.android
 *   |_ API_android.java
 * 
 * </pre>
 * 
 * Desc : 추후에 command 모듈에서도 사용. vCluster부분에 탑재 할 API 리스트 이다. vSimulator 를 사용하는
 * 경우는 API_vCluster 를 이용한다.
 * 
 * @Company : KISTI
 * @Author :grkim
 * @Date :2012. 8. 24. 오후 4:03:35
 * @Version:
 * 
 */
public class API_android {

	private API_vcluster API = null;
	

	public API_android() {
		// TODO Auto-generated constructor stub
		API = new API_vcluster();
	}

	// ******************************************************************
	// vClustger API for Androied
	// 00:param1,param2......
	// ******************************************************************

	/**
	 * Desc : Job을 담을 수 있는 max 큐 사이즈
	 * 
	 * @Method Name : getMaxQueue
	 * @return Max 큐 사이즈 반환
	 * 
	 */
	public int getMaxQueue() {
		return 1000;
	}

	/**
	 * Desc : 현재 처리 중인 Job 개수를 반환
	 * 
	 * @Method Name : getRunningJobs
	 * @return 현재 수행하고 있는 Job 수를 반환
	 * 
	 */
	public int getRunningJobs() {
		return 80;
	}

	/**
	 * Desc : 큐에 대기하고있는 Job수를 반환
	 * 
	 * @Method Name : getWatingJobs
	 * @return 큐에 대기중인 Job 수
	 * 
	 */
	public int getWatingJobs() {
		return 90;
	}

	/**
	 * Desc : 연결 가능한 클라우드 리스트 반환
	 * 
	 * @Method Name : getAvaiableCloudList
	 * @return 연결가능한 클라우드 리스트 반환 <br>
	 *         EX) "vSimulator,fermiCloud,amazon,gCloud" <br>
	 *         String으로 캐스팅해서 사용 (String)list.get(i)
	 */
	public List getAvaiableCloudList() {
		List list = new ArrayList();
		list.add("vSimulator");
		list.add("fermiCloud");
		list.add("amazon");
		list.add("gCloud");
		return list;
	}
	/**
	 * Desc : 현재 연결한 클라우드 리스트
	 * 
	 * @Method Name : getAccessCloudList
	 * @return 현재 연결중인 클라우드 사이트들 반환 <br>
	 *         ex) "vSimulator","amazon" <br>
	 *         String으로 캐스팅해서 사용 (String)list.get(i)
	 */
	public List getAccessCloudList() {
		List list = new ArrayList();
		list.add("vSimulator");
		list.add("fermiCloud");
		return list;
	}
	/**
	 * Desc : 현재 동작중인 host리스트
	 * 
	 * @Method Name : getRunningHostList
	 * @return 현재 동작중인 host 리스트 반환 <br>
	 *         EX)
	 *         "vSimulator-host01,vSimulaotr-host02,fermiCloud-host01,fermiCloud-host02"
	 * 	 <br>         String으로 캐스팅해서 사용 (String)list.get(i)
	 */
	public List getRunningHostList(String cloudName) {
		return API.getRunningHostList();
	}
	/**
	 * Desc :현재 동작중인 가상머신 리스트 
	 * 
	 * @Method Name : getCurrentRunningVms
	 * @return 현재 동작중인 가상머신 리스트
	 * <br>         String으로 캐스팅해서 사용 (String)list.get(i)
	 */
	public List getCurrentRunningVmList(String cloudName) {
		return API.getRunningVmList();
	}
	/**
	 * Desc : 현재 사용가능한 (생성은 되었으나 job은 수행하지 않은..) 가상머신 리스트
	 * 
	 * @Method Name : getCurrentIdleVmList
	 * @return job을 수행가능한 가상머신 리스트 반환
	 * <br>         String으로 캐스팅해서 사용 (String)list.get(i)
	 */
	public List getCurrentIdleVmList(String cloudName) {
		return API.getIdleVmList();
	}
	/**
	 * Desc : 현재 launch 가능한 가상머신 리스트 
	 * 
	 * @Method Name : getCurrentAvailableVmList
	 * @return launch 가능한 vm 리스트
	 * <br>         String으로 캐스팅해서 사용 (String)list.get(i)
	 */
	public List getCurrentAvailableVmList(String cloudName) {
		return API.getAvailableVmList();
	}
	/**
	 * Desc : 현태 동작중이나 사용이 불가한 가상머신 리스트
	 * 
	 * @Method Name : getCurrentUnhealthyVms
	 * @return 사용 불가한 가상머시 개수 리스트
	 * 
	 */
	public List getCurrentUnhealthyVmList(String cloudName) {
		return API.getUnhealthyVmList();
	}
	/**
	 * Desc : vm생성시 사용할 OS 이미지 리스트 출력
	 * 
	 * @Method Name : getImageRepositoryList
	 * @return 사용 가능한 OS 이미지 리스트 출력
	 * 
	 */
	public List getImageRepositoryList(String cloudName) { 
		List list = new ArrayList();
		list.add("CDF_OS_Image");
		list.add("Belle_OS_Image");
		list.add("Ligo_OS_Image");
		return list;
	}

}