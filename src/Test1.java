

import org.apache.commons.math.stat.correlation.Covariance;
import org.apache.commons.math.stat.descriptive.moment.StandardDeviation;

import com.functions.AllocateAssets;
import com.functions.Normalizer;
import com.functions.OptimumPoint;
import com.functions.RiskCalc;
import com.functions.RiskScorePersonal;
import com.functions.RiskScorePortfolio;
import com.functions.RiskScoreQuestions;
import com.functions.SurplusCalculator;
import com.impl.PortfolioPointImpl;
import com.pojo.PortfolioPoint;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class Test1 {
	
    public static void main(String[] args)throws IOException {
    	  
//        Test1 t = new Test1();
//        double[] equityReturnsArray = t.getDataArray("DowJonesEquity.csv");
//        double[] bondReturnsArray = t.getDataArray("USBonds.csv");
//        //double[] goldReturnsArray = t.getDataArray("USGold.csv");
//        double equityValues[] = t.calculateUSReturnAndVolatility("week", equityReturnsArray);
//        double bondValues[] = t.calculateUSReturnAndVolatility("week", bondReturnsArray);
//       
//        // double goldValues[] = t.calculateUSReturnAndVolatility("week", goldReturnsArray);
//       // System.out.println(goldValues[0]+" "+goldValues[1]);
//        
//        DataPoint1 opt = t.generateFrontier(8, equityValues[1], bondValues[0], bondValues[1], 16, 40, t.getCovariance(equityReturnsArray, bondReturnsArray), -0.353, -0.34, 2.0 );
//        
//        System.out.println(equityValues[0]+" "+equityValues[1]);
//        System.out.println("Hello "+ t.getCovariance(equityReturnsArray, bondReturnsArray));
//        
//        
//        RiskScoreQuestions r = new RiskScoreQuestions();
//		RiskScorePortfolio risk = new RiskScorePortfolio();
//		RiskScorePersonal risk2 = new RiskScorePersonal();

//		r.calculateRiskQuestions(points, weights );				
//		
//		risk.calculatePortfolioRiskScore(20, 8000, 7200, 50000 , 3000);	
//		risk2.calculatePersonalRiskScore(20, 1);
//		
//		System.out.println("Risk Score Portfolio is "+risk.getRisk_portfolio());
//		System.out.println("Risk Score Questions is "+ r.getRisk_questions());
//		System.out.println("Risk Score Personal is "+ risk2.getRisk_personal());
//		


		int [] points = new int[10];
		int [] weights = new int[10];
		
		for(int i=0;i<7;i++) {
			if(i==0)points[i]=10;
			else if(i==1) points[i]=3;
			else if(i==2) points[i]=2;
			else if(i==3) points[i]=7;
			else if(i==4) points[i]=1;
			else if(i==5) points[i]=5;
			else if(i==6) points[i]=5 ;
			
			weights[i]=1;
		}

        DecimalFormat df = new DecimalFormat("#.00");
		
		PortfolioPointImpl impl = new PortfolioPointImpl();
		PortfolioPoint opt = new PortfolioPoint();		
        RiskCalc calc = new RiskCalc();
        
        
        opt = OptimumPoint.findPoint();
        double risk_aversion = calc.calculateRisk(20, 1, 8000, 7200, 50000, 3000, points, weights);
		
		
        double riskyAssetWeight = impl.calculateRiskyAssetWeight(opt, risk_aversion);
        double riskFreeAssetWeight = 1- riskyAssetWeight;

        System.out.println("invest in risk free t-bills: "+df.format(riskFreeAssetWeight*100)+"%");
        System.out.println("invest in bonds: "+df.format(riskyAssetWeight*opt.getBondWeight()*100)+"%");
        System.out.println("invest in equity: "+df.format(riskyAssetWeight*opt.getEquityWeight()*100)+"%");
        System.out.println("invest in commodity: "+df.format(riskyAssetWeight*opt.getGoldWeight()*100)+"%");
    
//    
//       PortfolioPoint opt1 = new  PortfolioPoint(opt.getEquityWeight(), opt.getBondWeight(), opt.getGoldWeight(), opt.getPortfolioExpectedReturn(), opt.getPortfolioVolatility(), opt.getSharpeRatio());
//       System.out.println(opt1.getBondWeight());
       
        
       int years = 7;
        double [] values = new double [4];
    
        double total = 0;
        
        
        
        for(int i=0;i<years;i++) {
        	 
        	values = AllocateAssets.cashFlow(SurplusCalculator.surplusWithTime(i, 50000, 45000), opt, 68000, riskyAssetWeight );
        	System.out.println(SurplusCalculator.surplusWithTime(i, 50000, 45000)+"\n");
        	for(int j=0;j<4;j++) {
        		
        		if(j==0)System.out.println("Bonds Now is: "+values[0]);
        		else if(j==1)System.out.println("Equities now is "+values[1]);
        		else if(j==2)System.out.println("Comodities now is "+values[2]);
        		else if(j==3)System.out.println("TBills now is "+values[3]);      		
        	}
        	
        	
        	
        }
    
        for(double js:values) {
    		total = total+js;
    	}
    	
    	System.out.println(" \nTotal Now is "+total);
        
       // System.out.println("Total Money at the end is "+total);
        	

    
    }
//
//    public double[] calculateUSReturnAndVolatility(String bucketSize, double[] returns)throws IOException
//    {
//        int annualizationFactor = 1;
//        if(bucketSize.equalsIgnoreCase("week"))
//        {
//            annualizationFactor = 52;
//        }
//        else if(bucketSize.equalsIgnoreCase("day"))
//        {
//            annualizationFactor = 252;
//        }
//        else if(bucketSize.equalsIgnoreCase("month"))
//        {
//            annualizationFactor = 12;
//        }
//        double expectedReturn = 0;
//        for(int i=0;i<returns.length;i++)
//        {
//            expectedReturn += returns[i];
//        }
//        expectedReturn = expectedReturn*annualizationFactor*100.0/returns.length;
//        StandardDeviation sd = new StandardDeviation();
//        double volatility = sd.evaluate(returns)*100.0*Math.sqrt(annualizationFactor);
//        double ans[] = {expectedReturn, volatility};
//        return ans;
//    }
//
//    public double[] getDataArray(String fileName)throws IOException
//    {
//        BufferedReader br = new BufferedReader(new FileReader(fileName));
//        String line = "";
//        List<Double> prices = new ArrayList<>();
//        int row = 0;
//        while((line=br.readLine())!=null)
//        {
//            if(row==0)
//            {
//                row++;
//                continue;
//            }
//            String[] data = line.split(",");
//            prices.add(Double.parseDouble(data[5]));
//        }
//        List<Double> returns = new ArrayList<>();
//        for(int i=0;i<prices.size()-1;i++)
//        {
//            double ret = (prices.get(i+1)-prices.get(i))/prices.get(i);
//            returns.add(ret);
//        }
//        double[] listToArray = new double[returns.size()];
//        for(int i=0;i<returns.size();i++)
//            listToArray[i] = returns.get(i);
//        return listToArray;
//    }
//
//    public double getCovariance(double[] returns1, double[] returns2)
//    {
//        double covariance = new Covariance().covariance(returns1, returns2);
//        return covariance*Math.pow(10,4);
//    }
//
//    public DataPoint1 generateFrontier(double equityExpectedReturn, double equityVolatility, double bondExpectedReturn, double bondVolatility, double goldExpectedReturn, double goldVolatility, double equityBondcovariance, double equityGoldCovariance, double bondGoldCovariance, double riskFreeRate)
//    {
//        DataPoint1 opt = new DataPoint1();
//        for(double i=0;i<=1;i+=0.001)
//        {
//            double equityWeight = i;
//            for(double j=0;j<=(1-i);j+=0.001)
//            {
//                double bondWeight = j;
//                double goldWeight = 1.0 - i - j;
//                double portfolioReturn = equityWeight*equityExpectedReturn + bondWeight*bondExpectedReturn + goldWeight*goldExpectedReturn;
//                double portfolioVariance = Math.pow((equityVolatility*equityWeight), 2) + Math.pow((bondVolatility*bondWeight), 2) + Math.pow((goldVolatility*goldWeight), 2) + 2*bondWeight*equityWeight*equityBondcovariance + 2*equityWeight*goldWeight*equityGoldCovariance + 2*bondWeight*goldWeight*bondGoldCovariance;
//                double sharpeRatio = (portfolioReturn - riskFreeRate)/Math.sqrt(portfolioVariance);
//                if(opt.getSharpeRatio()<=sharpeRatio)
//                {
//                    opt = new DataPoint1(equityWeight, bondWeight, goldWeight, portfolioReturn, Math.sqrt(portfolioVariance), sharpeRatio);
//                }
//            }
//        }
//        return opt;
//    }
//
//
//    public double calculateRiskyAssetWeight(DataPoint1 point, double riskAversionCoefficient)
//    {
//        double riskyAssetWeight = (point.getSharpeRatio())/((point.getPortfolioVolatility()/100)*riskAversionCoefficient);
//        return riskyAssetWeight;
//    }
//    




}


