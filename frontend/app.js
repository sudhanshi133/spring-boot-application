// API Base URL
const API_URL = 'http://localhost:8080/api/entities';

// DOM Elements
const addItemForm = document.getElementById('addItemForm');
const editItemForm = document.getElementById('editItemForm');
const menuItemsContainer = document.getElementById('menuItems');
const refreshBtn = document.getElementById('refreshBtn');
const editModal = document.getElementById('editModal');
const closeModal = document.querySelector('.close');
const cancelEditBtn = document.getElementById('cancelEdit');
const toast = document.getElementById('toast');
const themeToggle = document.getElementById('themeToggle');
const themeIcon = document.querySelector('.theme-icon');

// Theme Toggle Functionality
function initTheme() {
    const savedTheme = localStorage.getItem('theme') || 'light';
    if (savedTheme === 'dark') {
        document.body.classList.add('dark-mode');
        themeIcon.textContent = '☀️';
    }
}

function toggleTheme() {
    document.body.classList.toggle('dark-mode');
    const isDark = document.body.classList.contains('dark-mode');
    themeIcon.textContent = isDark ? '☀️' : '🌙';
    localStorage.setItem('theme', isDark ? 'dark' : 'light');
}

// Load menu items on page load
document.addEventListener('DOMContentLoaded', () => {
    initTheme();
    loadMenuItems();
});

// Theme toggle event
themeToggle.addEventListener('click', toggleTheme);

// Refresh button
refreshBtn.addEventListener('click', () => {
    loadMenuItems();
    showToast('Menu refreshed!', 'success');
});

// Add new menu item
addItemForm.addEventListener('submit', async (e) => {
    e.preventDefault();

    const menuItem = {
        name: document.getElementById('name').value,
        description: document.getElementById('description').value,
        price: parseFloat(document.getElementById('price').value),
        category: document.getElementById('category').value
    };

    try {
        const response = await fetch(API_URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(menuItem)
        });

        if (response.ok) {
            showToast('Menu item added successfully!', 'success');
            addItemForm.reset();
            loadMenuItems();
        } else {
            showToast('Failed to add menu item. Check if price is valid.', 'error');
        }
    } catch (error) {
        console.error('Error adding menu item:', error);
        showToast('Error adding menu item!', 'error');
    }
});

// Load all menu items
async function loadMenuItems() {
    try {
        const response = await fetch(API_URL);
        const items = await response.json();

        if (items.length === 0) {
            menuItemsContainer.innerHTML = `
                <div class="empty-state">
                    <div class="empty-state-icon">🍽️</div>
                    <p>No menu items yet. Add your first delicious item above!</p>
                </div>
            `;
            return;
        }

        menuItemsContainer.innerHTML = items.map(item => `
            <div class="menu-item">
                <div class="menu-item-header">
                    <div>
                        <h3>${item.name}</h3>
                        <span class="category-badge">${item.category}</span>
                    </div>
                </div>
                <p>${item.description}</p>
                <div class="price">$${item.price.toFixed(2)}</div>
                <div class="menu-item-actions">
                    <button class="btn-edit" onclick="openEditModal(${item.id})">Edit</button>
                    <button class="btn-delete" onclick="deleteMenuItem(${item.id})">Delete</button>
                </div>
            </div>
        `).join('');
    } catch (error) {
        console.error('Error loading menu items:', error);
        showToast('Error loading menu items!', 'error');
    }
}

// Open edit modal
async function openEditModal(id) {
    try {
        const response = await fetch(`${API_URL}/${id}`);
        const item = await response.json();

        document.getElementById('editId').value = item.id;
        document.getElementById('editName').value = item.name;
        document.getElementById('editDescription').value = item.description;
        document.getElementById('editPrice').value = item.price;
        document.getElementById('editCategory').value = item.category;

        editModal.style.display = 'block';
    } catch (error) {
        console.error('Error loading menu item:', error);
        showToast('Error loading menu item!', 'error');
    }
}

// Close modal
closeModal.addEventListener('click', () => {
    editModal.style.display = 'none';
});

cancelEditBtn.addEventListener('click', () => {
    editModal.style.display = 'none';
});

window.addEventListener('click', (e) => {
    if (e.target === editModal) {
        editModal.style.display = 'none';
    }
});

// Update menu item
editItemForm.addEventListener('submit', async (e) => {
    e.preventDefault();

    const id = document.getElementById('editId').value;
    const menuItem = {
        name: document.getElementById('editName').value,
        description: document.getElementById('editDescription').value,
        price: parseFloat(document.getElementById('editPrice').value),
        category: document.getElementById('editCategory').value
    };

    try {
        const response = await fetch(`${API_URL}/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(menuItem)
        });

        if (response.ok) {
            showToast('Menu item updated successfully!', 'success');
            editModal.style.display = 'none';
            loadMenuItems();
        } else {
            showToast('Failed to update menu item!', 'error');
        }
    } catch (error) {
        console.error('Error updating menu item:', error);
        showToast('Error updating menu item!', 'error');
    }
});

// Delete menu item
async function deleteMenuItem(id) {
    if (!confirm('Are you sure you want to delete this menu item?')) {
        return;
    }

    try {
        const response = await fetch(`${API_URL}/${id}`, {
            method: 'DELETE'
        });

        if (response.ok) {
            showToast('Menu item deleted successfully!', 'success');
            loadMenuItems();
        } else {
            showToast('Failed to delete menu item!', 'error');
        }
    } catch (error) {
        console.error('Error deleting menu item:', error);
        showToast('Error deleting menu item!', 'error');
    }
}

// Show toast notification
function showToast(message, type = 'success') {
    toast.textContent = message;
    toast.className = `toast show ${type}`;

    setTimeout(() => {
        toast.className = 'toast';
    }, 3000);
}

