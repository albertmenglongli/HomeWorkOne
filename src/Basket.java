/**
 * Created with IntelliJ IDEA.
 * User: lml
 * Date: 5/15/12
 * Time: 5:32 AM
 * To change this template use File | Settings | File Templates.
 */
import java.util.ArrayList;

public class Basket {
    private ArrayList<IProduct> products;

    public Basket() {
        products = new ArrayList<IProduct>();
    }

    public void addProducts(IProduct currProduct, int nums) {
        IProduct copy = null;
        if (products.isEmpty()) {
            copy = (IProduct) currProduct.clone();
            copy.setNums(0);
            products.add(copy);
            addNums(currProduct.getName(), nums);
            return;
        }
        for (IProduct product : products) {
            if (currProduct.getName().equals(product.getName())) {
                addNums(product.getName(), nums);
                return;
            }
        }
        copy = (IProduct) currProduct.clone();
        copy.setNums(0);
        products.add(copy);
        addNums(currProduct.getName(), nums);
        return;

    }

    public void addNums(String string, int nums) {
        for (IProduct product : this.products) {
            if (product.getName().equals(string))
                product.setNums(product.getNums() + nums);
        }
    }

    public ArrayList<IProduct> getProducts() {
        return this.products;
    }

    public void showBasket() {
        for (IProduct p : products) {
            System.out.println(p.getName() + "*" + p.getNums());
        }
    }

}