package com.functions;

import com.pojo.PortfolioPoint;

public class AllocateAssets {

	public static double[] cashFlow(double surplus, PortfolioPoint currentP, double holdings_total, double riskyAssetWeight) {

		
		double[] allocations = new double[4]; 
		double[] randomReturn = FutureReturns.getFutureReturns(8, 12, 2.5, 3.5, 18, 40); 
		
		/*PortfolioPointImpl impl = new PortfolioPointImpl();
		
		double r=impl.calculateRiskyAssetWeight(currentP, 17.45874012);*/
		
		holdings_total= holdings_total+surplus;		

		for(int i=0;i<4;i++) {
			
			if(i == 0)holdings_total = holdings_total*(1+0.01*randomReturn[i]*currentP.getBondWeight()*riskyAssetWeight);
			else if(i==1)holdings_total = holdings_total*(1+0.01*randomReturn[i]*currentP.getEquityWeight()*riskyAssetWeight);
			else if(i==2)holdings_total = holdings_total*(1+0.01*randomReturn[i]*currentP.getGoldWeight()*riskyAssetWeight);
			else if(i==3)holdings_total = holdings_total*(1+0.01*randomReturn[i]*(1-riskyAssetWeight));
			
		}
 
/*
		System.out.println("Total Holdings now is:"+ holdings_total);*/
		
		for(int i=0;i<4;i++) {
			if(i == 0)allocations[i] = holdings_total*currentP.getBondWeight()*riskyAssetWeight;
			else if(i==1)allocations[i] = holdings_total*currentP.getEquityWeight()*riskyAssetWeight;
			else if(i==2)allocations[i] = holdings_total*currentP.getGoldWeight()*riskyAssetWeight;
			else if(i==3)allocations[i] = holdings_total*(1-riskyAssetWeight);
			
		}
		
		return allocations;
	}

}
