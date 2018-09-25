package com.functions;

public class RiskScorePortfolio {
	
	private double non_risky_percent;
	private double risk_portfolio;
	
	
	public double getNon_risky_percent() {
		return non_risky_percent;
	}

	public double getRisk_portfolio() {
		return risk_portfolio;
	}

	public void portfolioPercentCalc(double tbills, double bonds, double equity, double gold) {
		
		double total = tbills+bonds+equity+gold;
		non_risky_percent = ( tbills /total)*100;				
	}
	
	public void calculatePortfolioRiskScore(int country_code) {
		
		if(country_code==1) {
		double[] allocated_percentage = {0,7,18,33,47.38,58.93,67.89,74.65,79.73,83.51};
		
		for(int i=0;i<allocated_percentage.length;i++) {
			if(non_risky_percent>allocated_percentage[i]) {
				risk_portfolio=(i+1)+((non_risky_percent-allocated_percentage[i])/(allocated_percentage[i+1]-allocated_percentage[i]));
			}
		}  
		}
		
		
		
	}

}
