package ipc_problem;

 class Smoker implements Runnable{
	 private int number;
	 private String neededIngr;
	 private String ingredients;
	 private CigaretteSmoker2209621 runn;
	 
	 public Smoker(int number,String ingredients,String neededIngr,CigaretteSmoker2209621 runn) {
		 this.number=number;
		 this.ingredients=ingredients;
		 this.neededIngr=neededIngr;
		 this.runn=runn;
	 }

	@Override
	public void run() {
	    while (!runn.isFinSmoking()) {
	        try {
	            runn.getSmokersItems()[number].acquire();
		            synchronized (runn) {  
	                if (runn.getCounts()[number] == 0) continue;

	                System.out.println("--> Smoker who has " + ingredients + " is making a cigarette.");
	                runn.getCounts()[number]--;
	            }

	            Thread.sleep(2000);
	            System.out.println("Smoker who had " + ingredients + " alone, has finished smoking.");

	            if (runn.getCounts()[0] == 0 && runn.getCounts()[1] == 0 && runn.getCounts()[2] == 0) {
	                runn.setFinSmoking(true);  
	            }
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
	}

}
