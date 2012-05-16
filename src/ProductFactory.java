/**
 * Created with IntelliJ IDEA.
 * User: lml
 * Date: 5/15/12
 * Time: 5:33 AM
 * To change this template use File | Settings | File Templates.
 */
import java.util.Arrays;
import java.util.HashMap;

public class ProductFactory {
    private Product p;
    String[] productsList = { "A", "B", "C", "D" };

    public Product getProduct(String name) {
        p = null;
        Boolean has_Product = false;
        for (int i = 0; i < productsList.length; i++) {
            if (name.equals(productsList[i]))
                has_Product = true;
        }
        if (!has_Product) {
            return p;
        }
        if (name.equals("A")) {
            p = new Product();
            p.setName(name);
            p.setUnitPrice(50.0);
            p.addDiscount(3, 130.0);
            return p;
        }
        if (name.equals("B")) {
            p = new Product();
            p.setName(name);
            p.setUnitPrice(30);
            p.addDiscount(2, 45);
            return p;
        }
        if (name.equals("C")) {
            p = new Product();
            p.setName(name);
            p.setUnitPrice(20);
            return p;
        }
        if (name.equals("D")) {
            p = new Product();
            p.setName(name);
            p.setUnitPrice(15);
            return p;
        }
        return p;
    }

    private class Product implements IProduct, Cloneable {
        class Discount {
            private HashMap<Integer, Double> map;
            private Object[] key;

            public Discount() {
                map = new HashMap<Integer, Double>();
            }

            public void addDiscount(int num, double cost) {
                map.put(num, cost);
                update();
            }

            public void update() {
                key = map.keySet().toArray();
                Arrays.sort(key);
            }

            public int getDiscuntItemsTotalNum() {
                return key.length;
            }

            public int getDiscountNums(int disCnts) {
                return (Integer) key[disCnts];
            }

            public double getDiscountPrice(int disCnts) {
                return (Double) map.get(key[disCnts]);
            }

        }

        private String name;
        private int nums;
        private double unitPrice;
        private double totalPrice;
        private Discount discount;

        public Product() {
            nums = 0;
            unitPrice = 0;
            totalPrice = 0;
            discount = new Discount();
        }

        public Product clone() {
            Product O = new Product();
            O.name = this.name;
            O.nums = this.nums;
            O.totalPrice = this.totalPrice;
            O.unitPrice = this.unitPrice;
            O.discount = new Discount();
            O.discount.map = new HashMap<Integer, Double>();
            for (int i = 0; i < this.discount.map.size(); i++) {
                O.discount.map.put((Integer) this.discount.key[i],
                        this.discount.map.get(this.discount.key[i]));
            }
            O.discount.key = O.discount.map.keySet().toArray();
            return O;
        }

        public int getNums() {
            return nums;
        }

        public void setNums(int nums) {
            this.nums = nums;
        }

        public double getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(double unitPrice) {
            this.unitPrice = unitPrice;
            this.discount.addDiscount(1, unitPrice);
        }

        public double getTotalPrice() {
            totalPrice = calPrice();
            return totalPrice;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public void addDiscount(int num, double cost) {
            this.discount.addDiscount(num, cost);
        }

        public void insert2Basket(Basket basket, int nums) {
            basket.addProducts(this, nums);
        }

        private double calPrice() {
            this.totalPrice = 0.0;
            totalPrice = cal(this.nums, discount.getDiscuntItemsTotalNum() - 1);
            return totalPrice;
        }

        private double cal(int nums, int disLevelCnts) {
            double result = 0.0;
            double currDisPrice = discount.getDiscountPrice(disLevelCnts);
            int currDisNums = discount.getDiscountNums(disLevelCnts);
            int cnts = nums / currDisNums;
            int left = nums % currDisNums;
            if (left != 0) {
                result = currDisPrice * cnts + cal(left, disLevelCnts - 1);
            } else {
                result = currDisPrice * cnts;
            }
            return result;
        }

    }

}
