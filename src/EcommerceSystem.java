import com.fawry.ecommerce.model.*;
import com.fawry.ecommerce.service.*;
import java.util.Date;

public class EcommerceSystem {
    public static void main(String[] args) {
        ShippingService shippingService = new ShippingService();
        CheckoutService checkoutService = new CheckoutService(shippingService);

        Date futureDate = new Date(System.currentTimeMillis() + 100000000); // Future date
        Date pastDate = new Date(System.currentTimeMillis() - 100000000); // Past date

        Product cheese = new Product("Cheese", 100, 10, true, 200, true, futureDate);
        Product expiredCheese = new Product("Expired Cheese", 100, 10, true, 200, true, pastDate);
        Product biscuits = new Product("Biscuits", 150, 5, true, 700, true, futureDate);
        Product scratchCard = new Product("Scratch Card", 10, 100, false, 0, false, null);

        Customer customer = new Customer(1000);
        Cart cart = new Cart();
        cart.add(cheese, 2);
        cart.add(biscuits, 1);
        cart.add(scratchCard, 1);
        System.out.println("--- Normal Checkout ---");
        checkoutService.checkout(customer, cart);

        Cart cart2 = new Cart();
        System.out.println("\n--- Testing Expired Product ---");
        cart2.add(expiredCheese, 1);
        checkoutService.checkout(customer, cart2);

        Cart cart3 = new Cart();
        System.out.println("\n--- Testing Out of Stock ---");
        cart3.add(cheese, 9);
        System.out.println("\n--- Testing Empty Cart ---");
        checkoutService.checkout(customer, cart3);

        Customer poorCustomer = new Customer(50);
        Cart cart4 = new Cart();
        cart4.add(cheese, 1);
        System.out.println("\n--- Testing Insufficient Balance ---");
        checkoutService.checkout(poorCustomer, cart4);
    }
}
