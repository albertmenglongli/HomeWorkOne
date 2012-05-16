/**
 * Created with IntelliJ IDEA.
 * User: lml
 * Date: 5/15/12
 * Time: 5:32 AM
 * To change this template use File | Settings | File Templates.
 */
import java.util.ArrayList;

public class CheckStand {

    

    public double getTotalPrice(Basket basket) {
        double result = 0;
        ArrayList<IProduct> products = basket.getProducts();
        for (IProduct product : products) {
            result += product.getTotalPrice();
        }
        return result;
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

    public static void main(String[] args) {
	System.out.println("Here is not test_methods, this is the main method of the CheckStand");
        System.out.println("\"\""+"costs:"+price(""));
        System.out.println("\"A\""+"costs:"+price("A"));
        System.out.println("\"AB\""+"costs:"+price("AB"));
        System.out.println("\"CDBA\""+"costs:"+price("CDBA"));
        System.out.println("\"AA\""+"costs:"+price("AA"));
        System.out.println("\"AAA\""+"costs:"+price("AAA"));
        System.out.println("\"AAAA\""+"costs:"+price("AAAA"));
        System.out.println("\"AAAAA\""+"costs:"+price("AAAAA"));
        System.out.println("\"AAAAAA\""+"costs:"+price("AAAAAA"));
        System.out.println("\"AAAB\""+"costs:"+price("AAAB"));
        System.out.println("\"AAABB\""+"costs:"+price("AAABB"));
        System.out.println("\"AAABBD\""+"costs:"+price("AAABBD"));
        System.out.println("\"DABABA\""+"costs:"+price("DABABA"));
     }

}
