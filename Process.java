package application;

import java.util.ArrayList;

public class Process implements Comparable<Process>{
	private String PID;
	private ArrayList<Integer> first_CPU_burst;//first cpu burst before excution(to calculate sum cpu burst) 
	private ArrayList<Integer> CPU_burst;// in the exction will decrement 
	private ArrayList<Integer> IO_burst;
	private int arrival_time;// increse arrival time when process go to the wating 
	private int First_arrival_time;// first arrival time before increaing (to calculate average wating time)
	private int completion_time;
	public int count;//to move process in queue
	public int PreEstimated;

	public Process(String PID, ArrayList<Integer> CPU_burst, int arrival_time, ArrayList<Integer> IO_burst) {
		this.PID = PID;
		this.arrival_time = arrival_time;
		this.First_arrival_time = arrival_time;
		setCPU_burst(CPU_burst);
		setIO_burst(IO_burst);
		setFirst_CPU_burst(CPU_burst);
		count = 0;
		PreEstimated = 0;
	}

	public String getPID() {
		return PID;
	}

	public void setPID(String pID) {
		PID = pID;
	}

	public int getArrival_time() {
		return arrival_time;
	}

	public void setArrival_time(int arrival_time) {
		this.arrival_time = arrival_time;
	}

	public int getCompletion_time() {
		return completion_time;
	}

	public void setCompletion_time(int completion_time) {
		this.completion_time = completion_time;
	}

	public ArrayList<Integer> getCPU_burst() {
		return CPU_burst;
	}

	public void setCPU_burst(ArrayList<Integer> cPU_burst) {
		this.CPU_burst = new ArrayList<>();
		for (int i = 0; i < cPU_burst.size(); i++) {
			this.CPU_burst.add(cPU_burst.get(i));
		}
	}

	public ArrayList<Integer> getIO_burst() {
		return IO_burst;
	}

	public void setIO_burst(ArrayList<Integer> iO_burst) {
		this.IO_burst = new ArrayList<>();
		for (int i = 0; i < iO_burst.size(); i++) {
			this.IO_burst.add(iO_burst.get(i));
		}
	}

	//get the sum of the cpu burst for this process
	public int SumCPUBurst() {
		int sum = 0;
		for (int i =0; i<first_CPU_burst.size();i++) {
			sum += first_CPU_burst.get(i);
		}
		return sum;
	}

	//find the waiting time for this process
	public int waitingTime() { 
		return completion_time - First_arrival_time - SumCPUBurst(); 
	}

	@Override
	public String toString() {
		return PID+"\t"+arrival_time+"\t"+CPU_burst+"\t"+IO_burst;
	}

	@Override
	public int compareTo(Process o) {
		if(o.getArrival_time() > getArrival_time())//to store the array list (memory) according to arrival time 
			return -1;
		else if(o.getArrival_time() < getArrival_time())
			return 1;//store first 
		return 0;//equal
	}

	public void decrementCPU(int j) {// to decrement cpu burst 
		getCPU_burst().set(j,getCPU_burst().get(j) - 1);
	}

	public int getFirst_arrival_time() {
		return First_arrival_time;
	}

	public void setFirst_arrival_time(int first_arrival_time) {
		First_arrival_time = first_arrival_time;
	}

	public ArrayList<Integer> getFirst_CPU_burst() {
		return first_CPU_burst;
	}

	public void setFirst_CPU_burst(ArrayList<Integer> first_CPU_burst) {
		this.first_CPU_burst = new ArrayList<>();
		for (int i = 0; i < first_CPU_burst.size(); i++) {
			this.first_CPU_burst.add(first_CPU_burst.get(i));
		}

	}
}
