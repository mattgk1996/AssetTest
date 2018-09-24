import com.functions.Normalizer;
import com.functions.RiskScoreCalculator;

public class TestFunctions {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Normalizer a= new Normalizer();
		RiskScoreCalculator r = new RiskScoreCalculator();
		int [] points = new int[10];
		int [] weights = new int[10];
		
		for(int i=0;i<10;i++) {
			points[i]=i;
			weights[i]=1;
		}
		
		r.calculateRiskScore(points, weights);		
		a.calculateRiskAversion(r.getRisk_score());
		
		System.out.println("The Risk Score is "+ a.getRisk_aversion());
	}

}
