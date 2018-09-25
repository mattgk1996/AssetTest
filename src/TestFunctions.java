import com.functions.Normalizer;
import com.functions.RiskScorePersonal;
import com.functions.RiskScoreQuestions;
import com.functions.RiskScorePortfolio;

public class TestFunctions {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Normalizer a= new Normalizer();
		
		RiskScoreQuestions r = new RiskScoreQuestions();
		RiskScorePortfolio risk = new RiskScorePortfolio();
		RiskScorePersonal risk2 = new RiskScorePersonal();
		
		
		
		int [] points = new int[10];
		int [] weights = new int[10];
		
		for(int i=0;i<10;i++) {
			points[i]=i;
			weights[i]=1;
		}
		
		r.calculateRiskQuestions(points, weights);			
		
		risk.portfolioPercentCalc(50, 20, 20, 10);
		risk.calculatePortfolioRiskScore(1);
		
		risk2.calculatePersonalRiskScore(25, 3,12);
		
		System.out.println("Risk Score Portfolio is "+risk.getRisk_portfolio());
		System.out.println("Risk Score Questions is "+ r.getRisk_questions());
		System.out.println("Risk Score Personal is "+ risk2.getRisk_personal());
		
		Normalizer normalize = new Normalizer();
		
		double final_risk = 3*risk2.getRisk_personal()+2*risk.getRisk_portfolio()+ r.getRisk_questions();
		final_risk=final_risk/6;
		normalize.calculateRiskAversion(final_risk);
		System.out.println("Final Risk is "+ final_risk);
		System.out.println("Risk Aversion is "+ normalize.getRisk_aversion());
	}

}
