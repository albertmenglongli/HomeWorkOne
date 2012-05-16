/**
 * Created with IntelliJ IDEA.
 * User: lml
 * Date: 5/15/12
 * Time: 5:33 AM
 * To change this template use File | Settings | File Templates.
 */
import java.util.ArrayList;

public class Parser {
    private String productsString;
    private ArrayList<IProduct> products;
    private ProductFactory pfactory;
    public void feed(String str) {
        productsString=str;
    }
    public ArrayList<IProduct> getProducts() {
        pfactory=new ProductFactory();
        products=new ArrayList<IProduct>();
        analyseString();
        return products;
    }
    private void analyseString()
    {
        IProduct A=pfactory.getProduct("A");
        IProduct B=pfactory.getProduct("B");
        IProduct C=pfactory.getProduct("C");
        IProduct D=pfactory.getProduct("D");
        A.setNums(0);
        B.setNums(0);
        C.setNums(0);
        D.setNums(0);
        String tmpStr="";
        for(int i=0;i<productsString.length();i++)
        {
            tmpStr+=productsString.charAt(i);
            if("A".equals(tmpStr))
                A.setNums(A.getNums()+1);
            if("B".equals(tmpStr))
                B.setNums(B.getNums()+1);
            if("C".equals(tmpStr))
                C.setNums(C.getNums()+1);
            if("D".equals(tmpStr))
                D.setNums(D.getNums()+1);
            tmpStr="";
        }
        products.add(A);
        products.add(B);
        products.add(C);
        products.add(D);
    }

    public static int price(String str) {
        CheckStand checkStand = new CheckStand();
        Basket basket = new Basket();
        Parser ps = new Parser();
        ps.feed(str);
        ArrayList<IProduct> products_from_parser = ps.getProducts();
        for (IProduct pd : products_from_parser) {
            pd.insert2Basket(basket, pd.getNums());
        }
        double result = checkStand.getTotalPrice(basket);
        return (int) result;
    }

    public static void main(String [] args)
    {
        System.out.print(price("AAAA"));
    }


}
