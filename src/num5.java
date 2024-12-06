import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

class Purchase {
    String category;
    double amount;
    LocalDate purchaseDate;

    public Purchase(String category, double amount, LocalDate purchaseDate) {
        this.category = category;
        this.amount = amount;
        this.purchaseDate = purchaseDate;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }
}

class Customer {
    String email;
    List<Purchase> purchases;

    public Customer(String email, List<Purchase> purchases) {
        this.email = email;
        this.purchases = purchases;
    }

    public String getEmail() {
        return email;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public double getTotalPurchases() {
        return purchases.stream()
                .mapToDouble(Purchase::getAmount)
                .sum();
    }
}

class CustomerFilter {

    public static List<String> filterCustomers(List<Customer> customers) {
        LocalDate now = LocalDate.now();
        LocalDate oneMonthAgo = now.minusMonths(1);
        LocalDate twoWeeksAgo = now.minusWeeks(2);

        return customers.stream()
                .filter(customer -> customer.getTotalPurchases() > 1000) // Фильтрация по общей сумме покупок
                .peek(customer -> customer.getPurchases().forEach(purchase -> {
                    if ("electronics".equalsIgnoreCase(purchase.getCategory()) &&
                            purchase.getPurchaseDate().isAfter(twoWeeksAgo)) {

                        double updatedAmount = purchase.getAmount() * 1.1;
                        purchase.amount = updatedAmount;
                    }
                }))
                .sorted(Comparator.comparingInt(customer -> ((Customer) customer).getPurchases().size()).reversed()) // Сортировка по количеству покупок
                .map(Customer::getEmail)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Customer> customers = Arrays.asList(
                new Customer("alice@example.com", Arrays.asList(
                        new Purchase("electronics", 500.0, LocalDate.now().minusDays(5)),
                        new Purchase("clothing", 600.0, LocalDate.now().minusDays(10))
                )),
                new Customer("bob@example.com", Arrays.asList(
                        new Purchase("electronics", 300.0, LocalDate.now().minusDays(2)),
                        new Purchase("electronics", 800.0, LocalDate.now().minusDays(3))
                )),
                new Customer("charlie@example.com", Arrays.asList(
                        new Purchase("groceries", 150.0, LocalDate.now().minusDays(15)),
                        new Purchase("clothing", 200.0, LocalDate.now().minusDays(5))
                )),
                new Customer("david@example.com", Arrays.asList(
                        new Purchase("electronics", 1500.0, LocalDate.now().minusDays(20)),
                        new Purchase("electronics", 600.0, LocalDate.now().minusDays(1))
                ))
        );

        List<String> filteredEmails = filterCustomers(customers);

        System.out.println(filteredEmails);
    }
}