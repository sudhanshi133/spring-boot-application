# 🍽️ Restaurant Menu Manager - Spring Boot + AOP Demo

A full-stack web application demonstrating **Spring Boot** backend with **Aspect-Oriented Programming (AOP)** and a vanilla JavaScript frontend for managing restaurant menu items.

## 📋 Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Installation & Setup](#installation--setup)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [AOP Implementation](#aop-implementation)
- [Frontend Features](#frontend-features)
- [Testing](#testing)

## ✨ Features

### Backend Features
- ✅ RESTful API with full CRUD operations
- ✅ **Aspect-Oriented Programming (AOP)** for cross-cutting concerns
- ✅ Automatic method execution time logging
- ✅ In-memory data storage with repository pattern
- ✅ Input validation and error handling
- ✅ Spring Boot 3.2.1 with Java 17

### Frontend Features
- ✅ Modern, responsive UI with gradient design
- ✅ Create, Read, Update, Delete menu items
- ✅ Real-time updates
- ✅ Toast notifications for user feedback
- ✅ Modal-based editing
- ✅ Category-based organization
- ✅ No framework dependencies (Vanilla JS)

## 🛠️ Technologies Used

### Backend
- **Spring Boot** 3.2.1
- **Spring AOP** - Aspect-Oriented Programming
- **Java** 17
- **Maven** - Build tool
- **Tomcat** - Embedded server

### Frontend
- **HTML5**
- **CSS3** (with modern gradients and animations)
- **Vanilla JavaScript** (ES6+)
- **Fetch API** for HTTP requests

## 📁 Project Structure

```
backend-day-3/
├── spring-boot/                    # Backend application
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/tekion/spring_boot/
│   │   │   │   ├── Application.java           # Main Spring Boot application
│   │   │   │   ├── aspect/
│   │   │   │   │   └── LoggingAspect.java     # AOP aspect for logging
│   │   │   │   ├── controller/
│   │   │   │   │   ├── HealthController.java  # Health check endpoint
│   │   │   │   │   └── MenuController.java    # REST API controller
│   │   │   │   ├── model/
│   │   │   │   │   └── MenuItem.java          # Menu item entity
│   │   │   │   ├── repository/
│   │   │   │   │   └── MenuItemRepository.java # Data access layer
│   │   │   │   └── service/
│   │   │   │       └── MenuItemService.java   # Business logic layer
│   │   │   └── resources/
│   │   │       └── application.properties     # Application configuration
│   │   └── test/                              # Test files
│   ├── pom.xml                                # Maven dependencies
│   └── mvnw                                   # Maven wrapper
├── frontend/                       # Frontend application
│   ├── index.html                 # Main HTML file
│   ├── styles.css                 # Styling
│   └── app.js                     # JavaScript logic
└── README.md                      # This file
```

## 📦 Prerequisites

- **Java 17** or higher
- **Maven** 3.6+ (or use included Maven wrapper)
- **Modern web browser** (Chrome, Firefox, Safari, Edge)
- **Terminal/Command Prompt**

## 🚀 Installation & Setup

### 1. Clone or Download the Project

```bash
cd backend-day-3
```

### 2. Verify Java Installation

```bash
java -version
# Should show Java 17 or higher
```

## ▶️ Running the Application

### Step 1: Start the Backend Server

```bash
cd spring-boot
./mvnw clean spring-boot:run
```

**On Windows:**
```bash
mvnw.cmd clean spring-boot:run
```

The backend server will start on **http://localhost:8080**

### Step 2: Open the Frontend

Open `frontend/index.html` in your web browser:

```bash
cd ../frontend
open index.html          # macOS
start index.html         # Windows
xdg-open index.html      # Linux
```

Or simply double-click the `index.html` file.

### Step 3: Watch the AOP Logs

Keep an eye on the terminal where the backend is running. You'll see AOP logs like:

```
🔵 [AOP PROXY] Entering method: com.tekion.spring_boot.service.MenuItemService.getAllMenuItems
🔵 [AOP PROXY] Method is being wrapped by a proxy - AOP is working!
✅ [AOP PROXY] Method ...getAllMenuItems executed successfully in 2 ms
```

## 🔌 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/entities` | Get all menu items |
| GET | `/api/entities/{id}` | Get menu item by ID |
| POST | `/api/entities` | Create new menu item |
| PUT | `/api/entities/{id}` | Update menu item |
| DELETE | `/api/entities/{id}` | Delete menu item |
| GET | `/health` | Health check endpoint |

### Example API Requests

**Create Menu Item:**
```bash
curl -X POST http://localhost:8080/api/entities \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Margherita Pizza",
    "description": "Classic pizza with tomato and mozzarella",
    "price": 12.99,
    "category": "Main Course"
  }'
```

**Get All Menu Items:**
```bash
curl http://localhost:8080/api/entities
```

**Update Menu Item:**
```bash
curl -X PUT http://localhost:8080/api/entities/1 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Updated Pizza",
    "description": "Updated description",
    "price": 14.99,
    "category": "Main Course"
  }'
```

**Delete Menu Item:**
```bash
curl -X DELETE http://localhost:8080/api/entities/1
```

