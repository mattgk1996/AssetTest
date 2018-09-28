package com.functions;

import java.util.ArrayList;
import java.util.List;

import com.pojo.PortfolioPoint;

public class GoalsMap {

	private boolean[] achieved = new boolean[4];
	private double[][] assetReturn = new double[10][];
	

	public double[][] getAssetReturn() {
		return assetReturn;
	}




	public boolean[] GoalCheck(int[] years,  double[]goals, double riskyAssetWeight, PortfolioPoint opt,double port_total,double income,double expense,CashFlow [] flow) {
		
		double [] assets = new double[4]; 

		for(int i=0;i<goals.length;i++) {
			
			System.out.println("\n");
		

			int year;			
			if(i==0) year = 1;
			else { 
				year = years[i-1]+1;
			}
			
			
			while(year<=years[i]) {
				//System.out.println(port_total);
				AllocateAssets assetPortfo = new AllocateAssets();
				
	        	
				assets = assetPortfo.cashFlow(SurplusCalculator.surplusWithTime(year, income, expense), opt, port_total, riskyAssetWeight );
				assetReturn[year-1] = assetPortfo.getRandomReturn();
				
	        	
	        	CashFlow current = new CashFlow(assets[0], assets[1], assets[2], assets[3], year);
	        	flow[year-1] = current;	        		        	
	        	port_total = assets[0]+assets[1]+assets[2]+assets[3];
	        	year++;	        	
	
			
			}
	
 
	    
	        System.out.println("Total at year "+ years[i]+ ": "+port_total);
	        System.out.println("Goal at year "+years[i]+": "+goals[i]);
	       
	        if(port_total>goals[i]) {
	        System.out.println("Goal Success");	
	        port_total = port_total - goals[i];
	        achieved[i] = true;
//	        total = port_total;
	        assets = AllocateAssets.splitUP(port_total, opt);
	        System.out.println("After Achieving Goal "+ port_total);
	        
	        }
	        
	        else {
	        	
	        	achieved[i] = false;
	        	System.out.println("Not Success");
	        }
		
		} 

		
		
		
		return achieved;
		
		
	}
}
