import java.io.IOException;
import java.text.DecimalFormat;

import com.functions.AllocateAssets;
import com.functions.CashFlow;
import com.functions.GoalsMap;
import com.functions.OptimumPoint;
import com.functions.RiskCalc;
import com.functions.SurplusCalculator;
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
        double risk_aversion = calc.calculateRisk(20, 1, 8000, 7200, 50000, 3000, points, weights);
		
		
        double riskyAssetWeight = impl.calculateRiskyAssetWeight(opt, risk_aversion);
        double riskFreeAssetWeight = 1- riskyAssetWeight;

        System.out.println("invest in risk free t-bills: "+df.format(riskFreeAssetWeight*100)+"%");
        System.out.println("invest in bonds: "+df.format(riskyAssetWeight*opt.getBondWeight()*100)+"%");
        System.out.println("invest in equity: "+df.format(riskyAssetWeight*opt.getEquityWeight()*100)+"%");
        System.out.println("invest in commodity: "+df.format(riskyAssetWeight*opt.getGoldWeight()*100)+"%");
        
		CashFlow [] flow = new CashFlow[15];
		
        GoalsMap g = new GoalsMap();
        int []years = {3,6,8,10};
        int[] goals = {50000,7000,100000,5000};
        boolean [] check = g.GoalCheck(years, goals, riskyAssetWeight, opt, 68000,50000,45000,flow);
        
        
		int loop = 0;
		while(loop<years[3]) {
		flow[loop].PrintCashFlow();
		loop++;
		}
        
        for(int i = 0;i<4;i++) {
        System.out.println(check[i]);
        }
        
/*        
        int years = 7;
        
        
        double [] assets = new double [4];
    
        double total = 0;
        
        
        
        for(int i=0;i<years;i++) {
        	 
        	assets = AllocateAssets.cashFlow(SurplusCalculator.surplusWithTime(i, 50000, 45000), opt, 68000, riskyAssetWeight );
        	System.out.println(SurplusCalculator.surplusWithTime(i, 50000, 45000)+"\n");
        	
        	
        	for(int j=0;j<4;j++) {
        		
        		if(j==0)System.out.println("Bonds Now is: "+assets[0]);
        		else if(j==1)System.out.println("Equities now is "+assets[1]);
        		else if(j==2)System.out.println("Comodities now is "+assets[2]);
        		else if(j==3)System.out.println("TBills now is "+assets[3]);      		
        	}
        	
        	
        	
        }
    
        for(double js:assets) {
    		total = total+js;
    	}
    	
    	System.out.println(" \nTotal Now is "+total);
        
*/        

	}

}
