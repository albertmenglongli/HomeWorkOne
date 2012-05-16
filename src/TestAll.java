/**
* Created with IntelliJ IDEA.
* User: lml
* Date: 5/15/12
* Time: 6:49 AM
* To change this template use File | Settings | File Templates.
*/


import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class TestAll extends TestCase{
    private CheckStand checkStand;
    private Basket basket;
    private ProductFactory productFactory;
    private IProduct A;
    private IProduct B;

    @Before
    public void setUp() throws Exception {
        checkStand = new CheckStand();
        basket = new Basket();
        productFactory = new ProductFactory();
        A = productFactory.getProduct("A");
        B = productFactory.getProduct("B");
        A.insert2Basket(basket, 1);
        A.insert2Basket(basket, 1);
        B.insert2Basket(basket, 1);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testProductFacory() {
        A = productFactory.getProduct("A");
        assertEquals("A", A.getName());
    }

    @Test
    public void testProductDiscounts() {
        IProduct AA = productFactory.getProduct("A");
        AA.setNums(5);
        // new dropping price, one only costs 45, let's see what will happen
        AA.addDiscount(1, 45);
        assertEquals(220.0, AA.getTotalPrice());
    }

    @Test
    public void testAddProduct2Basket() {

        ArrayList<IProduct> products = basket.getProducts();
        for (IProduct product : products) {
            if (product.getName().equals("A")) {
                assertEquals(2, product.getNums());
            }
            if (product.getName().equals("B")) {
                assertEquals(1, product.getNums());
            }
        }
    }

    @Test
    public void testCheckStand() {
        double result = checkStand.getTotalPrice(basket);
        assertEquals(130.0, result);
    }

    @Test
    public void testBuyManyThings() {
        Basket largeBasket = new Basket();
        IProduct itemA = productFactory.getProduct("A");
        IProduct itemB = productFactory.getProduct("B");
        IProduct itemC = productFactory.getProduct("C");
        IProduct itemD = productFactory.getProduct("D");
        itemA.insert2Basket(largeBasket, 2);
        itemB.insert2Basket(largeBasket, 3);
        itemA.insert2Basket(largeBasket, 2);
        itemB.insert2Basket(largeBasket, 5);
        itemC.insert2Basket(largeBasket, 1);
        itemD.insert2Basket(largeBasket, 1);
        // 4A(180)+8B(180)+1C(20)+1D(15)=395
        double totalPrice = 0;
        totalPrice = checkStand.getTotalPrice(largeBasket);
        // here we can see what is in our basket
        // largeBasket.showBasket();
        assertEquals(395.0, totalPrice);
    }

    @Test
    public void testClone() throws CloneNotSupportedException {
        IProduct origin = productFactory.getProduct("A");
        IProduct copy = (IProduct) origin.clone();
        assertEquals(copy.getName(), origin.getName());
    }

    @Test
    public void testParser() {
        String str = "AAAABBBBBBBBCD";
        basket = new Basket();
        Parser ps = new Parser();
        ps.feed(str);
        ArrayList<IProduct> products_from_parser = ps.getProducts();
        for (IProduct pd : products_from_parser) {
            pd.insert2Basket(basket, pd.getNums());
        }
        // 4A(180)+8B(180)+1C(20)+1D(15)=395
        double result = checkStand.getTotalPrice(basket);
        assertEquals(395.0, result);
    }

    public int price(String str) {
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

    @Test
    public void test_as_a_whole() {
        assertEquals(0, price(""));
        assertEquals(50, price("A"));
        assertEquals(80, price("AB"));
        assertEquals(115, price("CDBA"));

        assertEquals(100, price("AA"));
        assertEquals(130, price("AAA"));
        assertEquals(180, price("AAAA"));
        assertEquals(230, price("AAAAA"));
        assertEquals(260, price("AAAAAA"));

        assertEquals(160, price("AAAB"));
        assertEquals(175, price("AAABB"));
        assertEquals(190, price("AAABBD"));
        assertEquals(190, price("DABABA"));
    }

}
