import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.functions.AllocateAssets;
import com.functions.CashFlow;
import com.functions.GoalsMap;
import com.functions.OptimumPoint;
import com.functions.RiskCalc;
import com.functions.SurplusCalculator;
import com.functions.TotalReturns;
import com.impl.PortfolioPointImpl;
import com.pojo.PortfolioPoint;

public class FinalTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub


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
        double risk_aversion = calc.calculateRisk(20, 1, 0, 00, 0000, 000, points, weights);
		
		
        double riskyAssetWeight = impl.calculateRiskyAssetWeight(opt, risk_aversion);
        double riskFreeAssetWeight = 1- riskyAssetWeight;

        System.out.println("invest in risk free t-bills: "+df.format(riskFreeAssetWeight*100)+"%");
        System.out.println("invest in bonds: "+df.format(riskyAssetWeight*opt.getBondWeight()*100)+"%");
        System.out.println("invest in equity: "+df.format(riskyAssetWeight*opt.getEquityWeight()*100)+"%");
        System.out.println("invest in commodity: "+df.format(riskyAssetWeight*opt.getGoldWeight()*100)+"%");
        
		CashFlow [] flow = new CashFlow[15];
		boolean [] check = {false,false,false,false};
        GoalsMap g = new GoalsMap();
        int []years = {3,5,8,10};
        double[] goals = {50000,20000,10000,5000};
        check = g.GoalCheck(years, goals, riskyAssetWeight, opt, 00000,10000,3000,flow);
        
     
        double [][] assetClassReturn =g.getAssetReturn();
        
        for(int iter = 0;iter <10;iter++) {
        System.out.println("Asset Returns For Year "+ (iter+1));
        double [] a = assetClassReturn[iter];
        for(double b:a) {
        	System.out.println(b);
        }
        }
        
//		int loop = 0;
//		while(loop<years[3]) {
//		flow[loop].PrintCashFlow();
//		loop++;
//		}
//        
//        for(int i = 0;i<4;i++) {
//        System.out.println(check[i]);
//        
//        }
//       
        //Put Income, Expense
        for(int i =1;i<10;i++) {
        System.out.println("Total Returns for year "+i+" "+TotalReturns.getTotalReturns(flow, i,goals,years,check,10000,3000));
        }
        
	}

}
