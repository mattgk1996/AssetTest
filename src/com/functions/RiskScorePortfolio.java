package com.functions;

public class RiskScorePortfolio {
	
	
	
	private double risky_percent;
	private double risk_portfolio;
	
	
	public double getRisky_percent() {
		return risky_percent;
	}

	public double getRisk_portfolio() {
		return risk_portfolio;
	}

	public void calculatePortfolioRiskScore(int age, double tbills, double bonds, double equity, double gold) {
		
		double val= 100-age;
		double total = tbills+bonds+equity+gold;
		risky_percent = (equity /total)*100;
		
		val=val/10;
		double scaler = val;
		System.out.println("Scaler Done" + scaler);
		for(int i=3;i<13;i++) {
			scaler=i*val;
			if(risky_percent<scaler) {
				scaler = (i-1)*val;
				risk_portfolio = i+(risky_percent-scaler)/(scaler);
				break;
			}
			
		}
		System.out.println("Out of for");
		risk_portfolio=risk_portfolio-2;
		
		
		
	
	}
	
	//public void calculatePortfolioRiskScore() {
		
		//if(country_code==1) {
	//	double[] allocated_percentage = {0,7,18,33,47.38,58.93,67.89,74.65,79.73,83.51};
		
		//for(int i=0;i<allocated_percentage.length;i++) {
			//if(non_risky_percent>allocated_percentage[i]) {
				//risk_portfolio=(i+1)+((non_risky_percent-allocated_percentage[i])/(allocated_percentage[i+1]-allocated_percentage[i]));
			
	//	}
		  
		
		
		
		
	}


