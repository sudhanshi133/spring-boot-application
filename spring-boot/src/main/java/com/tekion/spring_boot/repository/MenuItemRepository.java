package com.tekion.spring_boot.repository;

import com.tekion.spring_boot.model.MenuItem;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class MenuItemRepository {
    
    private final Map<Long, MenuItem> storage = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public MenuItemRepository() {
        // Initialize with some sample data
        save(new MenuItem(null, "Margherita Pizza", "Classic pizza with tomato sauce, mozzarella, and fresh basil", 12.99, "Main Course"));
        save(new MenuItem(null, "Caesar Salad", "Crisp romaine lettuce with parmesan cheese and croutons", 8.99, "Appetizer"));
        save(new MenuItem(null, "Grilled Salmon", "Fresh Atlantic salmon with lemon butter sauce and vegetables", 18.99, "Main Course"));
    }

    public MenuItem save(MenuItem menuItem) {
        if (menuItem.getId() == null) {
            menuItem.setId(idGenerator.getAndIncrement());
        }
        storage.put(menuItem.getId(), menuItem);
        return menuItem;
    }

    public Optional<MenuItem> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    public List<MenuItem> findAll() {
        return new ArrayList<>(storage.values());
    }

    public boolean existsById(Long id) {
        return storage.containsKey(id);
    }

    public void deleteById(Long id) {
        storage.remove(id);
    }

    public void deleteAll() {
        storage.clear();
    }
}

