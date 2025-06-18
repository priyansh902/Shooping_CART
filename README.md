# Shooping_CART
Create an application to manage tasks with the ability to add, update, delete, and view tasks. with the help of JDBC &amp; mysql.
A Java-based console application for managing a shopping cart with full CRUD operations using JDBC and MySQL. This project demonstrates the integration of Java, JDBC, SQL, and object-oriented programming concepts in a real-world application.

📌 Features
🔍 View all available products
➕ Add products to the cart
✏️ Update quantity of products in cart
❌ Delete products from the cart
💳 Checkout and display total bill
🧹 Clear the cart on checkout
🗃️ Add, update, or delete products (admin/user functionality)
🛠️ Exception handling and input validation

⚙️ Tech Stack
Java (Core Java, OOP)
JDBC (Java Database Connectivity)
MySQL (Database)
SQL (Schema + Queries)
CLI (Console interface)


🧪 How to Run
1. Clone the Repository
bash
Copy
Edit
git clone -url
cd shopping-cart
2. Setup MySQL Database
Open sql/schema.sql and execute the script in your MySQL client.
Update DBConnection.java with your own DB credentials.

java
String url = "jdbc:mysql://localhost:3306/shopping_cart";
String user = "root";
String password = "your_password";
3. Compile and Run
bash
Copy
Edit
javac -cp ".:mysql-connector-java.jar" src/*.java
java -cp ".:mysql-connector-java.jar:src" Main
⚠️ Don't forget to add the MySQL JDBC driver to your classpath.

🧩 Future Improvements
Convert to GUI or Web-based version using JavaFX or Spring Boot

Add user login/authentication
Track orders history
Add product categories and filters

👨‍💻 Author
Priyanshu Kumar
Backend Java Developer 
📧 [Priyanshukumar902776@gmail.com]



