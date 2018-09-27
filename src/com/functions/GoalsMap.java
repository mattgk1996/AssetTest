package com.functions;

import com.pojo.PortfolioPoint;

public class GoalsMap {

	private boolean[] achieved = new boolean[4];
	
	public boolean[] GoalCheck(int[] years,  int[]goals, double riskyAssetWeight, PortfolioPoint opt,double port_total,double income,double expense,CashFlow [] flow  ) {
		
		double [] assets = new double[4]; 				
		int number_years =years[years.length-1];

		for(int i=0;i<goals.length;i++) {
		
			double total =0;
			int year;			
			if(i==0) year = 1;
			else { 
				year = years[i-1]+1;
			}
			
			
			while(year<=years[i]) {
	        	assets = AllocateAssets.cashFlow(SurplusCalculator.surplusWithTime(year, income, expense), opt, port_total, riskyAssetWeight );
	        	CashFlow current = new CashFlow(assets[0], assets[1], assets[2], assets[3], year);
	        	flow[year-1] = current;	        		        	
	        	total = assets[0]+assets[1]+assets[2]+assets[3];
	        	year++;	        	
	        }
	

	    
	        System.out.println("Total at year "+ years[i]+ ": "+total);
	        System.out.println("Goal at year "+years[i]+": "+goals[i]);
	       
	        if(total>goals[i]) {
	        System.out.println("Total "+total);	
	        System.out.println("Goal Success");	
	        port_total = total - goals[i];
	        System.out.println("After Achieving Goal "+ port_total);
	        achieved[i] = true;
	        
	        assets = AllocateAssets.cashFlow(0, opt, port_total, riskyAssetWeight);
	        
	        }
	        
	        else {
	        	achieved[i] = false;
	        	System.out.println("Not Success");
	        }
		
		}

		
		
		
		return achieved;
		
		
	}
}
