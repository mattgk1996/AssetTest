

import org.apache.commons.math.stat.correlation.Covariance;
import org.apache.commons.math.stat.descriptive.moment.StandardDeviation;

import com.functions.Normalizer;
import com.functions.RiskScorePersonal;
import com.functions.RiskScorePortfolio;
import com.functions.RiskScoreQuestions;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
/**
 * Created by jenilshah on 13/09/2018.
 */
public class Test1 {
    public static void main(String[] args)throws IOException {
        Test1 t = new Test1();
        double[] equityReturnsArray = t.getDataArray("DowJonesEquity.csv");
        double[] bondReturnsArray = t.getDataArray("USBonds.csv");
        double[] goldReturnsArray = t.getDataArray("USGold.csv");
        double equityValues[] = t.calculateUSReturnAndVolatility("week", equityReturnsArray);
        double bondValues[] = t.calculateUSReturnAndVolatility("week", bondReturnsArray);
        double goldValues[] = t.calculateUSReturnAndVolatility("week", goldReturnsArray);
        DecimalFormat df = new DecimalFormat("#.00");
        DataPoint1 opt = t.generateFrontier(equityValues[0], equityValues[1], bondValues[0], bondValues[1], goldValues[0], goldValues[1], t.getCovariance(equityReturnsArray, bondReturnsArray), t.getCovariance(equityReturnsArray, goldReturnsArray), t.getCovariance(bondReturnsArray, goldReturnsArray), 2.0 );
        RiskScoreQuestions r = new RiskScoreQuestions();
		RiskScorePortfolio risk = new RiskScorePortfolio();
		RiskScorePersonal risk2 = new RiskScorePersonal();
		
		
		
		int [] points = new int[10];
		int [] weights = new int[10];
		
		for(int i=0;i<10;i++) {
			points[i]=i;
			weights[i]=1;
		}
		
		r.calculateRiskQuestions(points, weights );			
		
		risk.calculatePortfolioRiskScore(25, 20, 20, 100, 10);
		
		
		risk2.calculatePersonalRiskScore(25, 3,8);
		
		System.out.println("Risk Score Portfolio is "+risk.getRisk_portfolio());
		System.out.println("Risk Score Questions is "+ r.getRisk_questions());
		System.out.println("Risk Score Personal is "+ risk2.getRisk_personal());
		
		Normalizer normalize = new Normalizer();
		
		double final_risk = 3*risk2.getRisk_personal()+2*risk.getRisk_portfolio()+ r.getRisk_questions();
		final_risk=final_risk/6;
		normalize.calculateRiskAversion(final_risk);
		System.out.println("Final Risk is "+ final_risk);
		System.out.println("Risk Aversion is "+ normalize.getRisk_aversion());
        
        double riskyAssetWeight = t.calculateRiskyAssetWeight(opt, normalize.getRisk_aversion());
        double riskFreeAssetWeight = 1- riskyAssetWeight;
        System.out.println("invest in risk free t-bills: "+df.format(riskFreeAssetWeight*100)+"%");
        System.out.println("invest in bonds: "+df.format(riskyAssetWeight*opt.getBondWeight()*100)+"%");
        System.out.println("invest in equity: "+df.format(riskyAssetWeight*opt.getEquityWeight()*100)+"%");
        System.out.println("invest in gold: "+df.format(riskyAssetWeight*opt.getGoldWeight()*100)+"%");
    }

    public double[] calculateUSReturnAndVolatility(String bucketSize, double[] returns)throws IOException
    {
        int annualizationFactor = 1;
        if(bucketSize.equalsIgnoreCase("week"))
        {
            annualizationFactor = 52;
        }
        else if(bucketSize.equalsIgnoreCase("day"))
        {
            annualizationFactor = 252;
        }
        else if(bucketSize.equalsIgnoreCase("month"))
        {
            annualizationFactor = 12;
        }
        double expectedReturn = 0;
        for(int i=0;i<returns.length;i++)
        {
            expectedReturn += returns[i];
        }
        expectedReturn = expectedReturn*annualizationFactor*100.0/returns.length;
        StandardDeviation sd = new StandardDeviation();
        double volatility = sd.evaluate(returns)*100.0*Math.sqrt(annualizationFactor);
        double ans[] = {expectedReturn, volatility};
        return ans;
    }

    public double[] getDataArray(String fileName)throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line = "";
        List<Double> prices = new ArrayList<>();
        int row = 0;
        while((line=br.readLine())!=null)
        {
            if(row==0)
            {
                row++;
                continue;
            }
            String[] data = line.split(",");
            prices.add(Double.parseDouble(data[5]));
        }
        List<Double> returns = new ArrayList<>();
        for(int i=0;i<prices.size()-1;i++)
        {
            double ret = (prices.get(i+1)-prices.get(i))/prices.get(i);
            returns.add(ret);
        }
        double[] listToArray = new double[returns.size()];
        for(int i=0;i<returns.size();i++)
            listToArray[i] = returns.get(i);
        return listToArray;
    }

    public double getCovariance(double[] returns1, double[] returns2)
    {
        double covariance = new Covariance().covariance(returns1, returns2);
        return covariance*Math.pow(10,4);
    }

    public DataPoint1 generateFrontier(double equityExpectedReturn, double equityVolatility, double bondExpectedReturn, double bondVolatility, double goldExpectedReturn, double goldVolatility, double equityBondcovariance, double equityGoldCovariance, double bondGoldCovariance, double riskFreeRate)
    {
        DataPoint1 opt = new DataPoint1();
        for(double i=0;i<=1;i+=0.001)
        {
            double equityWeight = i;
            for(double j=0;j<=(1-i);j+=0.001)
            {
                double bondWeight = j;
                double goldWeight = 1.0 - i - j;
                double portfolioReturn = equityWeight*equityExpectedReturn + bondWeight*bondExpectedReturn + goldWeight*goldExpectedReturn;
                double portfolioVariance = Math.pow((equityVolatility*equityWeight), 2) + Math.pow((bondVolatility*bondWeight), 2) + Math.pow((goldVolatility*goldWeight), 2) + 2*bondWeight*equityWeight*equityBondcovariance + 2*equityWeight*goldWeight*equityGoldCovariance + 2*bondWeight*goldWeight*bondGoldCovariance;
                double sharpeRatio = (portfolioReturn - riskFreeRate)/Math.sqrt(portfolioVariance);
                if(opt.getSharpeRatio()<=sharpeRatio)
                {
                    opt = new DataPoint1(equityWeight, bondWeight, goldWeight, portfolioReturn, Math.sqrt(portfolioVariance), sharpeRatio);
                }
            }
        }
        return opt;
    }


    public double calculateRiskyAssetWeight(DataPoint1 point, double riskAversionCoefficient)
    {
        double riskyAssetWeight = (point.getSharpeRatio())/((point.getPortfolioVolatility()/100)*riskAversionCoefficient);
        return riskyAssetWeight;
    }
}
