/**
 * Created with IntelliJ IDEA.
 * User: lml
 * Date: 5/15/12
 * Time: 5:32 AM
 * To change this template use File | Settings | File Templates.
 */
public interface IProduct {

    public int getNums();

    void setNums(int nums);

    public double getUnitPrice();

    public void setUnitPrice(double unitPrice);

    public double getTotalPrice();

    public void setName(String name);

    public String getName();

    public void addDiscount(int num, double cost);

    public void insert2Basket(Basket basket, int nums);

    public Object clone();

}
