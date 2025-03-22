package ipc_problem;

import java.util.concurrent.Semaphore;

public class CigaretteSmoker2209621 {
	
	//Creating the semaphores
	private final Semaphore[] smokersItems = {new Semaphore (0),new Semaphore (0), new Semaphore (0)};//The smokers 
	private final Semaphore agent = new Semaphore (1); // when the ingredients are placed
	private int[] counts = {2,2,2}; // allowed tosmoke 2 times 
	private String [] agentItems= { "Matches & Tobacco", "Matches & Paper", "Paper and Tobacco"}; //the things the agent is handing out
	private volatile boolean finSmoking = false; 
	
	public static void main(String[] args) {
		CigaretteSmoker2209621 work = new CigaretteSmoker2209621();
		work.startSmoking();
	}
	private void startSmoking() {
		Thread smoker1= new Thread(new Smoker(0, "Paper", "Matches & Tobacco", this));
		Thread smoker2= new Thread(new Smoker(1, "Tobacco", "Matches & Paper", this));
		Thread smoker3= new Thread(new Smoker(2, "Matches", "Paper & Tobacco", this));
		Thread agent= new Thread(new Agent(this));

			smoker1.start();
			smoker2.start();
			smoker3.start();
			agent.start();
	}
	public String [] getAgentItems() {
		return agentItems;
	}
	public void setAgentItems(String [] agentItems) {
		this.agentItems = agentItems;
	}
	public boolean isFinSmoking() {
		return finSmoking;
	}
	public void setFinSmoking(boolean finSmoking) {
		this.finSmoking = finSmoking;
	}
	public Semaphore[] getSmokersItems() {
		return smokersItems;
	}
	public Semaphore getAgent() {
		return agent;
	}
	public int[] getCounts() {
		return counts;
	}
	public void setCounts(int[] counts) {
		this.counts = counts;
	}

}
