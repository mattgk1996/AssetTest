package com.functions;

public class TotalReturns {
	
	
	public static double getTotalReturns(CashFlow [] flow, int year, double[] goals,int[] years, boolean[] check, double income, double expense) {
		
		double current = flow[year].getPortfolio_total();
		double previous =flow[year-1].getPortfolio_total();
		double inv_amt = SurplusCalculator.surplusWithTime(year, income, expense);
		previous = previous+inv_amt;
		int i;

		
		for ( i = 0;i<4;i++) {
		if((year==years[i])&&(check[i])) {
			previous = previous - goals[i];
		}
		}
			
		return ((current-previous)*100/previous);
		
	}
	

}
