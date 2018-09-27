package com.functions;

import com.pojo.PortfolioPoint;

public class GoalsMap {

	private boolean[] achieved;
	
	public boolean[] GoalCheck(int[] years,  int[]goals, double riskyAssetWeight, PortfolioPoint opt ) {
		
		double [] assets = new double[4]; 
		
		
		
		for(int i=0;i<goals.length;i++) {
		
			System.out.println("\n");
			double total =0;
			int year;
			
			if(i==0) year = 1;
			else { 
				year = years[i-1]+1;
			
			//System.out.println(years[i]-years[i-1]);
			}
			while(year<=years[i]) {
        	 
				System.out.println("Year is "+year);
	        	assets = AllocateAssets.cashFlow(SurplusCalculator.surplusWithTime(year, 50000, 45000), opt, 68000, riskyAssetWeight );
	        	//System.out.println(SurplusCalculator.surplusWithTime(year, 50000, 45000)+"\n");	        		        	
	        	year++;
	        	
	        }
	    

			//System.out.println("Year is "+years[i]);
	        for(int j=0;j<assets.length;j++) {
	    		total = total+assets[j];
	    		
        		if(j==0)System.out.println("Bonds "+years[i]+""+assets[0]);
        		else if(j==1)System.out.println("Equities "+assets[1]);
        		else if(j==2)System.out.println("Comodities "+assets[2]);
        		else if(j==3)System.out.println("TBills "+assets[3]);     
	    		
	    	}
	        
	        System.out.println("Total at year "+ years[i]+ ": "+total);
		}
		
	    
		
		
		
		return achieved;
		
		
	}
}
