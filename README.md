# EasyShop_eccomerce_api

## Overview

This is the backend API for **EasyShop**, a demo e-commerce platform developed using **Spring Boot** and **MySQL**. The API supports user authentication, category and product management, and a complete shopping cart system. This version is building on a previously existing site.

---

## Features

###  Authentication
- Register and Login with roles (`USER`, `ADMIN`)
- JWT Token-based session handling

### Product Catalog
- List and search products
- Filter by category, price range, and color
- Admin-only CRUD operations for products

### Categories
- View all categories
- Admin-only CRUD operations for categories

###  Shopping Cart (Implemented)
- Logged-in users can:
  - Add products to cart
  - Add muliple quantity of a single product by clicking add to cart more than once
  - Clear the cart
  - View the current cart contents

### User Profiles (Implemented)
- View and update the logged in user profile information

## Future Steps 

1. Create order
2. Checkout functionality 
3. Get cart after checkout
4. Update product quanity straight from the cart page
5. Possibly allowing users to create an account

---

## Technologies Used

- Java 17
- Spring Boot 3
- Spring Security (JWT)
- MySQL
- JPA / JDBC
- Postman (API testing)
- Frontend: HTML/CSS/JS (for demo)

---

## How to Run

1. **Clone this repository**  

2. **Set up the database**  
- Open `create_database.sql` in MySQL Workbench.
- Run the script to set up schema and sample data.

3. **Run the application**  
Use IntelliJ or terminal:

4. **Open Postman**
Create a workspace and uplad the two files easyshop_solo and easyshop_solo_optional files and run.
Must run easyshop_solo before running the optional postman tests

5. **Run the frontend site by cloning the other repositary called EasyShop_eccomerce_frontend-website and navigate to index.html and open in your chosen browser**

## User for Testing 
Use the following usernames admin, user, george with password: password

## Screenshot of Postman tests
[EasyShop_solo_postman_results](Screenshots/easyShop_api_postman%20_results.png)

[EasyShop_solo_optional_postman_results](Screenshots/easyshop_optional_api_postman_results.png)


