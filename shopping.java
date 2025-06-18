import java.util.*;
import java.sql.*;
class Product {
    int id;
    String name;
    double price;

    Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}

class CartItem {
    Product product;
    int quantity;

    CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}

class DBConnection {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/zom";
        String user = "root";
        String password = "9027707502";
        return DriverManager.getConnection(url, user, password);
    }
}

class ProductDAO {
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM products")) {

            while (rs.next()) {
                products.add(new Product(rs.getInt("id"), rs.getString("name"), rs.getDouble("price")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public Product getProductById(int id) {
        Product product = null;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM products WHERE id = ?")) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                product = new Product(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }
}

class ShoppingCart {
    private final Map<Integer, CartItem> cart = new HashMap<>();

    public void addToCart(Product product, int quantity) {
        if (cart.containsKey(product.id)) {
            CartItem item = cart.get(product.id);
            item.quantity += quantity;
        } else {
            cart.put(product.id, new CartItem(product, quantity));
        }
        System.out.println("Added to cart: " + product.name);
    }

    public void viewCart() {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }
        double total = 0;
        System.out.println("\n--- Cart Items ---");
        for (CartItem item : cart.values()) {
            System.out.printf("%s x%d = %.2f\n", item.product.name, item.quantity, item.quantity * item.product.price);
            total += item.quantity * item.product.price;
        }
        System.out.printf("Total: %.2f\n", total);
    }

    public void updateQuantity(int productId, int newQty) {
        if (cart.containsKey(productId)) {
            cart.get(productId).quantity = newQty;
            System.out.println("Updated quantity.");
        } else {
            System.out.println("Item not in cart.");
        }
    }

    public void removeItem(int productId) {
        if (cart.remove(productId) != null) {
            System.out.println("Item removed from cart.");
        } else {
            System.out.println("Item not found in cart.");
        }
    }

    public void clearCart() {
        cart.clear();
        System.out.println("Cart cleared.");
    }

    public void checkout() {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }
        double total = 0;
        for (CartItem item : cart.values()) {
            total += item.quantity * item.product.price;
        }
        System.out.printf("Total Amount: %.2f\n", total);
        System.out.println("Checkout successful. Thank you!");
        clearCart();
    }
}
public class shopping {

     public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProductDAO dao = new ProductDAO();
        ShoppingCart cart = new ShoppingCart();

        while (true) {
            System.out.println("\n1. View Products");
            System.out.println("2. Add to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Update Cart Item Quantity");
            System.out.println("5. Remove Cart Item");
            System.out.println("6. Clear Cart");
            System.out.println("7. Checkout");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    List<Product> products = dao.getAllProducts();
                    for (Product p : products) {
                        System.out.printf("%d - %s: %.2f\n", p.id, p.name, p.price);
                    }
                    break;
                case 2:
                    System.out.print("Enter Product ID: ");
                    int id = sc.nextInt();
                    Product p = dao.getProductById(id);
                    if (p != null) {
                        System.out.printf("Selected: %s - %.2f\n", p.name, p.price);
                        System.out.print("Enter Quantity: ");
                        int qty = sc.nextInt();
                        cart.addToCart(p, qty);
                    } else {
                        System.out.println("Invalid Product ID");
                    }
                    break;
                case 3:
                    cart.viewCart();
                    break;
                case 4:
                    System.out.print("Enter Product ID to update: ");
                    int upId = sc.nextInt();
                    System.out.print("Enter new quantity: ");
                    int newQty = sc.nextInt();
                    cart.updateQuantity(upId, newQty);
                    break;
                case 5:
                    System.out.print("Enter Product ID to remove: ");
                    int remId = sc.nextInt();
                    cart.removeItem(remId);
                    break;
                case 6:
                    cart.clearCart();
                    break;
                case 7:
                    cart.checkout();
                    break;
                case 8:
                    System.out.println("Goodbye!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
    
}

