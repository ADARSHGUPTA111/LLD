/**
 * This pattern is used to abstract traversal logic of a collection.
 * By doing that, we take care of separation of concerns. The collection itself stores information
 * about the data and their connectivity only.
 * And the iterator class takes care of traversal.
 * In this example, a TreeNode is an element of a collection. It is only concerned with value
 * and left and right children
 * Traversal is taken care of by BfsTree class which implements TreeIterable interface and implements
 * the iteration logic.
 */


 import java.util.*;

// Product class representing individual products
class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

// Iterator interface
interface Iterator {
    Product first();
    Product next();
    boolean hasNext();
}

// Concrete iterator for the product collection
class ProductIterator implements Iterator {
    private List<Product> products;
    private int current;

    public ProductIterator(List<Product> products) {
        this.products = products;
        this.current = 0;
    }

    public Product first() {
        if (products.isEmpty()) {
            return null;
        }
        current = 0;
        return products.get(current);
    }

    public Product next() {
        if (hasNext()) {
            return products.get(++current);
        }
        return null;
    }

    public boolean hasNext() {
        return current < products.size() - 1;
    }
}

// Aggregate class that stores products and provides an iterator
class Inventory {
    private List<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public Iterator createIterator() {
        return new ProductIterator(products);
    }
}

public class AmazonInventory {
    public static void main(String[] args) {
        // Create some products
        Product product1 = new Product("Laptop", 99999.99);
        Product product2 = new Product("Smartphone", 49999.99);
        Product product3 = new Product("Headphones", 7999.99);

        // Create an inventory and add products
        Inventory inventory = new Inventory();
        inventory.addProduct(product1);
        inventory.addProduct(product2);
        inventory.addProduct(product3);

        // Create an iterator and iterate over the products
        Iterator iterator = inventory.createIterator();
        Product currentProduct = iterator.first();

        while (currentProduct != null) {
            System.out.println("Product: " + currentProduct.getName() + ", Price: $" + currentProduct.getPrice());
            currentProduct = iterator.next();
        }
    }
}