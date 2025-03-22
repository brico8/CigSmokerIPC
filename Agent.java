package ipc_problem;

import java.util.Random;

public class Agent implements Runnable{
private Random random= new Random();
private CigaretteSmoker2209621 runn;

public Agent (CigaretteSmoker2209621 runn) {
	this.runn=runn;
	
}

@Override
public void run() {
    while (!runn.isFinSmoking()) {
        try {
            runn.getAgent().acquire();  // 🔹 Wait until the agent can place ingredients
            
            int smokerSelected;
            do {
                smokerSelected = random.nextInt(3);
            } while (runn.getCounts()[smokerSelected] == 0);
            
            if (runn.isFinSmoking()) {  // 🔹 Exit if all smokers are done
                break;
            }

            System.out.println("Agent placed " + runn.getAgentItems()[smokerSelected]);

            runn.getSmokersItems()[smokerSelected].release();  // 🔹 Signal the correct smoker
            Thread.sleep(1000); 
            runn.getAgent().release();  // 🔹 Allow agent to run again
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
}
