//age, dependentents, returns

package com.functions;

public class RiskScorePersonal {
	private int risk_age;
	private int risk_dependents;
	private int risk_returns;
	private double risk_personal;
	 
	public int getRisk_age() {
		return risk_age;
	}


	public int getRisk_dependents() {
		return risk_dependents;
	}


	public int getRisk_returns() {
		return risk_returns;
	}

	
	public double getRisk_personal() {
		return risk_personal;
	}


	public void calculatePersonalRiskScore(int age, int fin_dependents, int returns ) {
	
		if(age<20) risk_age =3;
		else if(age <25) risk_age =4;
		else if(age <30) risk_age =5;
		else if(age<35) risk_age =6;
		else if(age<40) risk_age =7;
		else if(age<50) risk_age = 8;
		else if(age<65) risk_age =9;
		else risk_age =10;
		
		
		risk_dependents =10;
		for(int i=0;i<10;i++) {
			if(i==fin_dependents) {
				risk_dependents = i;
				break;
			}
		}
		
		if(returns>=2&& returns<=6) risk_returns = 10;
		else if (returns<=9) risk_returns = 7;
		else if (returns<11) risk_returns = 4;
		else risk_returns = 1;

			//Ask doubt to Harish
		risk_personal = (double) (risk_age+ risk_dependents+risk_returns)/3;
	}
	
	
	
}
