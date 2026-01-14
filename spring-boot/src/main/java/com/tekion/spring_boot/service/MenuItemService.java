package com.tekion.spring_boot.service;

import com.tekion.spring_boot.model.MenuItem;
import com.tekion.spring_boot.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuItemService {

    private final MenuItemRepository repository;

    @Autowired
    public MenuItemService(MenuItemRepository repository) {
        this.repository = repository;
    }

    public MenuItem createMenuItem(MenuItem menuItem) {
        // Business logic: validate price is positive
        if (menuItem.getPrice() < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        return repository.save(menuItem);
    }

    public List<MenuItem> getAllMenuItems() {
        return repository.findAll();
    }

    public Optional<MenuItem> getMenuItemById(Long id) {
        return repository.findById(id);
    }

    public MenuItem updateMenuItem(Long id, MenuItem menuItem) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("MenuItem with id " + id + " not found");
        }
        // Business logic: validate price is positive
        if (menuItem.getPrice() < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        menuItem.setId(id);
        return repository.save(menuItem);
    }

    public void deleteMenuItem(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("MenuItem with id " + id + " not found");
        }
        repository.deleteById(id);
    }
}

