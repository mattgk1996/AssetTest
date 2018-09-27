package com.functions;

public class RiskCalc  {

	
	public double calculateRisk(int age, int fin_dependents, double tbills, double bonds, double equity, double gold,
			int[] points, int[] weights) {
		// TODO Auto-generated method stub
		
        
        RiskScoreQuestions risk_q = new RiskScoreQuestions();
		RiskScorePortfolio risk_portfolio = new RiskScorePortfolio();
		RiskScorePersonal risk_personal = new RiskScorePersonal();		
		Normalizer normalize = new Normalizer();
		
		
		risk_q.calculateRiskQuestions(points, weights );				
		risk_portfolio.calculatePortfolioRiskScore(age, tbills, bonds, equity , gold);	
		risk_personal.calculatePersonalRiskScore(age, fin_dependents);
		
		
		
		double final_risk = 3*risk_personal.getRisk_personal()+2*risk_portfolio.getRisk_portfolio()+ risk_q.getRisk_questions();
		final_risk=final_risk/6;
		
		normalize.calculateRiskAversion(final_risk);
		System.out.println("Final Risk is "+ final_risk);
		System.out.println("Risk Aversion is "+ normalize.getRisk_aversion());
		return normalize.getRisk_aversion();
	}

}
