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

## 🎯 AOP Implementation

### What is AOP?

**Aspect-Oriented Programming (AOP)** is a programming paradigm that allows you to separate cross-cutting concerns (like logging, security, transactions) from business logic.

### How It Works in This Project

The `LoggingAspect` class demonstrates AOP by:

1. **Intercepting** all method calls in the service layer
2. **Logging** method entry with timestamp
3. **Measuring** execution time
4. **Logging** success or failure with execution time

### Key Components

**@Aspect** - Marks the class as an aspect
```java
@Aspect
@Component
public class LoggingAspect {
    // ...
}
```

**@Around** - Advice that wraps around method execution
```java
@Around("execution(* com.tekion.spring_boot.service.*.*(..))")
public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
    // Before method execution
    long startTime = System.currentTimeMillis();

    // Execute the actual method
    Object result = joinPoint.proceed();

    // After method execution
    long executionTime = System.currentTimeMillis() - startTime;
    logger.info("Method executed in {} ms", executionTime);

    return result;
}
```

**Pointcut Expression** - Defines which methods to intercept
- `execution(* com.tekion.spring_boot.service.*.*(..))` means:
  - `*` - any return type
  - `com.tekion.spring_boot.service.*` - any class in service package
  - `*(..)` - any method with any parameters

### Observing the Proxy

When you make API calls, check the backend console to see:
- 🔵 Blue logs showing method entry (proxy intercepting)
- ✅ Green logs showing successful execution with timing
- ❌ Red logs showing exceptions with timing

This proves that Spring creates a **proxy wrapper** around your service beans!

## 🎨 Frontend Features

### User Interface
- **Gradient Background** - Modern purple gradient design
- **Responsive Grid** - Adapts to different screen sizes
- **Card-based Layout** - Each menu item in a beautiful card
- **Modal Editing** - Edit items in a popup modal
- **Toast Notifications** - Real-time feedback for actions

### Functionality
- **Add Items** - Fill the form and submit
- **View Items** - Automatically loaded on page load
- **Edit Items** - Click edit button, modify in modal
- **Delete Items** - Click delete with confirmation
- **Refresh** - Manual refresh button available
- **Validation** - Price must be positive, all fields required

## 🧪 Testing

### Manual Testing

1. **Start the backend** and verify it's running:
   ```bash
   curl http://localhost:8080/health
   # Should return: {"status":"UP"}
   ```

2. **Open the frontend** in your browser

3. **Test CRUD operations:**
   - ✅ Create a new menu item
   - ✅ View all menu items
   - ✅ Edit an existing item
   - ✅ Delete an item
   - ✅ Try creating an item with negative price (should fail)

4. **Check AOP logs** in the backend terminal after each operation

### Expected AOP Log Output

```
2026-01-14T11:55:12.557  INFO ... 🔵 [AOP PROXY] Entering method: MenuItemService.getAllMenuItems
2026-01-14T11:55:12.557  INFO ... 🔵 [AOP PROXY] Method is being wrapped by a proxy - AOP is working!
2026-01-14T11:55:12.557  INFO ... ✅ [AOP PROXY] Method ...getAllMenuItems executed successfully in 1 ms
```

## 🐛 Troubleshooting

### Backend Issues

**Port 8080 already in use:**
```bash
# Find and kill the process using port 8080
lsof -ti:8080 | xargs kill -9  # macOS/Linux
netstat -ano | findstr :8080   # Windows
```

**Java version mismatch:**
```bash
# Check Java version
java -version
# Make sure it's Java 17 or higher
```

### Frontend Issues

**CORS errors:**
- Make sure the backend is running on `http://localhost:8080`
- Check browser console for specific errors

**API not responding:**
- Verify backend is running: `curl http://localhost:8080/health`
- Check the API URL in `app.js` (should be `http://localhost:8080/api/entities`)

## 📚 Learning Objectives

This project demonstrates:

1. ✅ **Spring Boot** - Modern Java web framework
2. ✅ **RESTful API Design** - Standard HTTP methods and endpoints
3. ✅ **Aspect-Oriented Programming** - Cross-cutting concerns
4. ✅ **Dependency Injection** - Spring's IoC container
5. ✅ **Repository Pattern** - Data access abstraction
6. ✅ **Service Layer** - Business logic separation
7. ✅ **Frontend-Backend Integration** - Full-stack development
8. ✅ **Vanilla JavaScript** - No framework dependencies

## 🎓 Key Concepts

### Spring Boot Layers
```
Controller Layer (REST API)
    ↓
Service Layer (Business Logic) ← AOP Proxy wraps this!
    ↓
Repository Layer (Data Access)
    ↓
Data Storage (In-Memory)
```

### AOP Benefits
- ✅ **Separation of Concerns** - Logging separate from business logic
- ✅ **Code Reusability** - One aspect applies to all service methods
- ✅ **Maintainability** - Easy to modify logging without touching services
- ✅ **Non-invasive** - No changes to existing service code

## 📝 License

This is a demo project for educational purposes.

## 👨‍💻 Author

Created as a Spring Boot + AOP demonstration project.

---

**Happy Coding! 🚀**

For questions or issues, check the backend console logs and browser console for detailed error messages.

