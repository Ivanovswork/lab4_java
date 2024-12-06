import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

class Order {
    int orderId;
    List<Item> items;
    LocalDate orderDate;

    public Order(int orderId, List<Item> items, LocalDate orderDate) {
        this.orderId = orderId;
        this.items = items;
        this.orderDate = orderDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public List<Item> getItems() {
        return items;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }
}

class Item {
    String category;
    double price;

    public Item(String category, double price) {
        this.category = category;
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }
}

class OrderFilter {

    public static Map<Integer, Double> filterAndGroupOrders(List<Order> orders) {
        LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);

        return orders.stream()
                .filter(order -> order.getOrderDate().isAfter(oneMonthAgo))
                .filter(order -> order.getItems().stream()
                        .anyMatch(item -> "clothing".equalsIgnoreCase(item.getCategory())))
                .collect(Collectors.groupingBy(Order::getOrderId,
                        Collectors.summingDouble(order -> order.getItems().stream()
                                .filter(item -> "clothing".equalsIgnoreCase(item.getCategory()))
                                .mapToDouble(Item::getPrice)
                                .sum())));
    }

    public static void main(String[] args) {
        // Пример данных
        List<Order> orders = Arrays.asList(
                new Order(1, Arrays.asList(
                        new Item("clothing", 20.0),
                        new Item("electronics", 100.0)), LocalDate.now().minusWeeks(2)),
                new Order(2, Arrays.asList(
                        new Item("accessories", 15.0)), LocalDate.now().minusMonths(2)),
                new Order(3, Arrays.asList(
                        new Item("clothing", 60.0),
                        new Item("clothing", 40.0)), LocalDate.now().minusWeeks(1)),
                new Order(4, Arrays.asList(
                        new Item("footwear", 50.0)), LocalDate.now().minusDays(5)),
                new Order(5, Arrays.asList(
                        new Item("clothing", 30.0),
                        new Item("accessories", 25.0)), LocalDate.now().minusDays(10))
        );

        Map<Integer, Double> result = filterAndGroupOrders(orders);

        // Вывод результатов
        result.forEach((orderId, total) -> System.out.println("Order ID: " + orderId + ", Total Clothing Cost: " + total));
    }
}