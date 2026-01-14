package com.tekion.spring_boot.controller;

import com.tekion.spring_boot.model.MenuItem;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MenuController {

    @GetMapping("/menu")
    public List<MenuItem> getMenu() {
        return Arrays.asList(
            new MenuItem("Margherita Pizza", "Classic pizza with tomato sauce, mozzarella, and fresh basil", 12.99, "Main Course"),
            new MenuItem("Caesar Salad", "Crisp romaine lettuce with parmesan cheese and croutons", 8.99, "Appetizer"),
            new MenuItem("Grilled Salmon", "Fresh Atlantic salmon with lemon butter sauce and vegetables", 18.99, "Main Course"),
            new MenuItem("Chicken Tikka Masala", "Tender chicken in creamy tomato curry sauce with basmati rice", 15.99, "Main Course"),
            new MenuItem("Spaghetti Carbonara", "Classic Italian pasta with bacon, eggs, and parmesan", 14.99, "Main Course"),
            new MenuItem("French Onion Soup", "Rich beef broth with caramelized onions and melted cheese", 7.99, "Appetizer"),
            new MenuItem("Chocolate Lava Cake", "Warm chocolate cake with molten center, served with vanilla ice cream", 6.99, "Dessert"),
            new MenuItem("Tiramisu", "Classic Italian dessert with coffee-soaked ladyfingers and mascarpone", 7.99, "Dessert"),
            new MenuItem("Mango Lassi", "Refreshing yogurt-based drink with fresh mango", 4.99, "Beverage"),
            new MenuItem("Iced Coffee", "Cold brew coffee served over ice", 3.99, "Beverage")
        );
    }
}

