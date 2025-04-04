# ecommerce-system

---

```markdown
# E-commerce System

This is a simple e-commerce system implemented in Java, designed to handle basic online shopping functionalities
such as product management, cart operations, checkout processes, and shipping. The system includes product expirability
based on expiration dates, stock management, and dynamic shipping fees calculated based on
the total weight of shippable items.

## Features
- **Product Management**: Define products with attributes like name, price, quantity, shippability, weight, and expiration date.
- **Cart Functionality**: Add products to a cart with checks for stock availability and expiration.
- **Checkout Process**: Validate cart contents, calculate subtotal and dynamic shipping fees, process payments, update stock, and handle shipping.
- **Shipping Handling**: Generate shipment notices for shippable items, calculating total weight and grouping items by name.
- **Error Handling**: Manage cases like empty carts, insufficient balance, out-of-stock products, and expired items.

## Project Structure
The project is organized with the main file (`EcommerceSystem.java`) at the root of the `src` directory, and other classes in packages for modularity:
```
```
ecommerce-system/
├── src/
│   ├── EcommerceSystem.java
│   ├── com/
│   │   ├── fawry/
│   │   │   ├── ecommerce/
│   │   │   │   ├── model/
│   │   │   │   │   ├── ShippableItem.java
│   │   │   │   │   ├── ShippedItem.java
│   │   │   │   │   ├── Product.java
│   │   │   │   │   ├── Cart.java
│   │   │   │   │   └── Customer.java
│   │   │   │   ├── service/
│   │   │   │   │   ├── ShippingService.java
│   │   │   │   │   └── CheckoutService.java
└── README.md
```

### Package Descriptions
- **`src/`**: Contains the main file `EcommerceSystem.java` (no package).
- **`com.fawry.ecommerce.model`**: Holds data models and interfaces.
- **`com.fawry.ecommerce.service`**: Contains service classes for business logic.

## Class and Method Explanations

### 1. `EcommerceSystem` (Main Class)
- **Location**: `src/EcommerceSystem.java`
- **Purpose**: Entry point of the application, demonstrating the system's functionality.
- **Methods**:
  - `main(String[] args)`: Creates products, customers, and carts, and tests various scenarios (normal checkout, expired product, out-of-stock, insufficient balance).

### 2. `ShippableItem` (Interface)
- **Location**: `src/com/fawry/ecommerce/model/ShippableItem.java`
- **Purpose**: Defines the contract for shippable items.
- **Methods**:
  - `String getName()`: Returns the item's name.
  - `double getWeight()`: Returns the item's weight in grams.

### 3. `ShippedItem` (Class)
- **Location**: `src/com/fawry/ecommerce/model/ShippedItem.java`
- **Purpose**: Implements `ShippableItem` for items prepared for shipping.
- **Methods**:
  - `ShippedItem(String name, double weight)`: Constructor to initialize name and weight.
  - `String getName()`: Returns the name.
  - `double getWeight()`: Returns the weight.

### 4. `Product` (Class)
- **Location**: `src/com/fawry/ecommerce/model/Product.java`
- **Purpose**: Represents a product with attributes like price, stock, and expiration.
- **Methods**:
  - `Product(String name, double price, int quantity, boolean isShippable, double weight, boolean isExpirable, Date expirationDate)`: Constructor to initialize product attributes.
  - `String getName()`: Returns the product name.
  - `double getPrice()`: Returns the price.
  - `int getQuantity()`: Returns available stock.
  - `boolean isShippable()`: Indicates if shipping is required.
  - `double getWeight()`: Returns weight (0 if not shippable).
  - `boolean isExpirable()`: Indicates if the product can expire.
  - `boolean isExpired()`: Checks if the product is expired based on the current date.
  - `void reduceQuantity(int qty)`: Reduces stock by the specified amount.

### 5. `Cart` (Class)
- **Location**: `src/com/fawry/ecommerce/model/Cart.java`
- **Purpose**: Manages the shopping cart.
- **Methods**:
  - `void add(Product product, int quantity)`: Adds a product to the cart if stock is available and it’s not expired.
  - `Map<Product, Integer> getItems()`: Returns an unmodifiable map of cart contents.

### 6. `Customer` (Class)
- **Location**: `src/com/fawry/ecommerce/model/Customer.java`
- **Purpose**: Represents a customer with a balance for payments.
- **Methods**:
  - `Customer(double balance)`: Constructor to set initial balance.
  - `double getBalance()`: Returns the current balance.
  - `void deduct(double amount)`: Deducts the specified amount from the balance.

### 7. `ShippingService` (Class)
- **Location**: `src/com/fawry/ecommerce/service/ShippingService.java`
- **Purpose**: Handles shipping operations for shippable items.
- **Methods**:
  - `void ship(List<ShippableItem> items)`: Processes shippable items, printing a shipment notice with grouped items and total weight in kilograms.

### 8. `CheckoutService` (Class)
- **Location**: `src/com/fawry/ecommerce/service/CheckoutService.java`
- **Purpose**: Manages the checkout process with dynamic shipping fees.
- **Methods**:
  - `CheckoutService(ShippingService shippingService)`: Constructor with dependency injection for `ShippingService`.
  - `void checkout(Customer customer, Cart cart)`: Validates cart, calculates subtotal and dynamic shipping fees based on weight, processes payment, updates stock, handles shipping, and prints a receipt.
  - `private double calculateShippingFee(List<ShippableItem> shippableItems)`: Calculates the shipping fee as $10 per kilogram of total weight, with a minimum fee of $5.

## How to Run
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/MohamedIbrahim-20/ecommerce-system.git
   cd ecommerce-system 
   ```
2. **Compile the Code**:
   ```bash
   javac -d . src/com/fawry/ecommerce/model/*.java src/com/fawry/ecommerce/service/*.java src/EcommerceSystem.java 
   ```
3. **Run the Program**:
   ```bash
   java EcommerceSystem 
   ```

## Assumptions
- **Shipping Fee**: Dynamic fee calculated at $10 per kilogram of total weight of shippable items, with a minimum fee of $5. Non-shippable items incur no fee.
- **Weight Units**: Individual item weights are in grams; total weight is converted to kilograms for shipping calculations and display.
- **Stock Management**: Stock is checked when adding to the cart and at checkout, but not reserved until checkout succeeds.
- **Expiration**: Products are checked for expiration based on the current date at the time of cart addition and checkout.

## Potential Improvements
- Add a database for persistent storage of products and customer data.
- Implement a user interface (e.g., CLI or GUI) instead of console output.
- Refine the shipping fee structure (e.g., tiered rates: $5 for 0-1kg, $10 for 1-3kg, etc.).
- Add weight display in the receipt for transparency.
- Support product categories and search functionality.

## License
This project is open-source and available under the MIT License.

