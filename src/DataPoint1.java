

/**
 * Created by jenilshah on 13/09/2018.
 */
public class DataPoint1 {
    private double equityWeight;
    private double bondWeight;
    private double goldWeight;
    private double portfolioExpectedReturn;
    private double portfolioVolatility;
    private double sharpeRatio;

    public DataPoint1(double equityWeight, double bondWeight, double goldWeight, double portfolioExpectedReturn, double portfolioVolatility, double sharpeRatio) {
        this.equityWeight = equityWeight;
        this.bondWeight = bondWeight;
        this.goldWeight = goldWeight;
        this.portfolioExpectedReturn = portfolioExpectedReturn;
        this.portfolioVolatility = portfolioVolatility;
        this.sharpeRatio = sharpeRatio;
    }

    public DataPoint1()
    {
        equityWeight = 0.0;
        bondWeight = 0.0;
        goldWeight = 0.0;
        portfolioExpectedReturn = 0.0;
        portfolioVolatility = 0.0;
        sharpeRatio = 0.0;
    }

    public double getEquityWeight() {
        return equityWeight;
    }

    public double getBondWeight() {
        return bondWeight;
    }

    public double getGoldWeight() {
        return goldWeight;
    }

    public double getPortfolioExpectedReturn() {
        return portfolioExpectedReturn;
    }

    public double getPortfolioVolatility() {
        return portfolioVolatility;
    }

    public double getSharpeRatio() {
        return sharpeRatio;
    }
}
