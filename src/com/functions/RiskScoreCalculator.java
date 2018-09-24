package com.functions;

/*import java.util.ArrayList;
import java.util.List;
*/

public class RiskScoreCalculator {

	private double [] ques_score = new double[10];
	private double risk_score;
	
	
	
	public double[] getQues_score() {
		return ques_score;
	}


	public double getRisk_score() {
		return risk_score;
	}


	public void calculateRiskScore (int [] points, int [] weights) {
		
		double w_sum = 0;
		
		for(int i=0;i<10;i++) {
			ques_score[i]= points[i]*weights[i];
			w_sum = w_sum+ques_score[i];
		}
		risk_score = w_sum/10;
		
	}
	
	
}
