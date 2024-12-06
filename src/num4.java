import java.util.*;
import java.util.stream.Collectors;

class Product {
    String name;
    double price;
    int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
}

class ProductFilter {

    public static List<String> filterProducts(List<Product> products) {
        return products.stream()
                .filter(product -> product.getPrice() > 50)
                .filter(product -> product.getQuantity() > 0)
                .sorted(Comparator.comparingInt(Product::getQuantity))
                .map(Product::getName)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
                new Product("Laptop", 800.0, 5),
                new Product("Mouse", 25.0, 10),
                new Product("Keyboard", 75.0, 0),
                new Product("Monitor", 150.0, 3),
                new Product("Headphones", 60.0, 7),
                new Product("Webcam", 45.0, 2)
        );

        List<String> filteredProductNames = filterProducts(products);

        System.out.println(filteredProductNames);
    }
}