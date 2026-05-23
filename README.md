# FitLab: AI-Powered Smart Fitness Tracker



FitLab is a production-ready, full-stack Java web application designed for comprehensive personal fitness tracking. Moving beyond standard CRUD functionality, this platform integrates a custom machine learning pipeline to provide real-time, data-driven activity predictions.

Built with scalability, portability, and clean architecture in mind, this project demonstrates advanced backend engineering, secure data management, and seamless third-party API integration.

---

## ✨ Standout Engineering Achievements

This project was built to exceed standard web application requirements, showcasing several advanced implementation details:

* **Real-Time Machine Learning API:** Engineered a real-time JAX-RS REST API that interfaces with a custom machine learning model.


* **Event-Driven Predictions:** Utilizes the JavaScript Fetch API to process user input dynamically, delivering instant AI activity suggestions without page reloads.


* **Dynamic Path Resolution:** Developed a custom `AppLifecycleListener` to resolve database paths dynamically at startup.


* **Environment Portability:** This architectural decision guarantees application portability across varying server environments without relying on hardcoded paths.


* **Enterprise-Grade Security:** Implemented robust session-based authentication.


* **Cryptographic Hashing:** Secures user credentials using jBCrypt with a 12-round salt.


* **SQL Injection Prevention:** Universally applies PreparedStatements across all data access operations.


* **JSP Execution Optimization:** Successfully navigated and resolved complex JSP template literal (`${}`) conflicts with frontend JavaScript.


* **Modern UI/UX:** Designed a responsive frontend utilizing CSS3 glassmorphism effects alongside dynamic Chart.js data visualizations.



---

## 🛠️ Technology Stack

| Category | Technology | Purpose |
| --- | --- | --- |
| **Backend Core** | Java 17, Servlet API 4.0.1 | Application logic and HTTP request handling |
| **View Layer** | JSP 2.2, JSTL 1.2 | Dynamic view rendering and templating |
| **Machine Learning** | WEKA 3.7.13 | Activity classification and predictive modeling |
| **Database** | SQLite 3.45.1.0, JDBC | File-based relational data storage |
| **Security** | jBCrypt 0.4 | Cryptographic password hashing |
| **Frontend** | HTML5, CSS3, JavaScript, Chart.js | UI structure, styling, and analytical dashboards |
| **DevOps & Build** | Apache Maven 3.x, Apache Tomcat 9+ | Dependency management, packaging (.war), and deployment |

---

## 🧠 Machine Learning Pipeline

A core feature of FitLab is its automated activity classification, powered by the WEKA machine learning library.

* **Custom Dataset:** Trained on a balanced, custom dataset comprising 72 distinct workout sessions.


* **Training & Testing Split:** Utilized an 80/20 split, designating 58 instances for training and 14 instances for testing.


* **Feature Engineering:** The model analyzes `duration_mins`, `distance_km`, and `calories_burned` to output accurate classifications.


* **Model Evaluation:** Developed an offline Java application (`ModelTrainer.java`) to evaluate multiple algorithms.


* **Algorithm Selection:** Benchmarked J48 Decision Tree, Random Forest, and NaiveBayes classifiers.


* **Final Accuracy:** Deployed the NaiveBayes model after it achieved a 100% classification accuracy during testing.


* **Memory Optimization:** The application loads the serialized `.model` file only once during servlet initialization to maximize performance.



---

## 🏗️ System Architecture

The application enforces a strict separation of concerns using industry-standard design patterns:

* **Model-View-Controller (MVC):** The foundation of the application, cleanly separating data entities, JSP views, and Servlet controllers.


* **Data Access Object (DAO):** Encapsulates all database interactions (`UserDAO`, `WorkoutDAO`), shielding business logic from SQL operations.


* **Singleton Pattern:** Manages database connections centrally via the `DatabaseManager` class to optimize resource utilization.


* **Observer Pattern:** Frontend event listeners continuously monitor user input to trigger asynchronous AI predictions.



---

## 🚀 Getting Started

### Prerequisites

* Java JDK 17+


* Apache Maven 3.6+


* Apache Tomcat 9.0+



### Local Deployment Instructions

1. **Clone the repository:**
`git clone https://github.com/ilpeiris/FitLab-AI-Powered-Smart-Fitness-Tracker.git`

2. **Generate the Machine Learning Model:**
Run the `src/main/java/com/FitLab/ml/ModelTrainer.java` file locally to process the training data.


3. **Position the Model:**
Move the newly generated `activity_model.model` file from the root directory into `src/main/webapp/WEB-INF/`.


4. **Build the Archive:**
Execute `mvn clean package` in your terminal to generate the `FitLab.war` artifact.


5. **Deploy to Server:**
Copy the `.war` file into your Tomcat `webapps` directory (`C:\servers\apache-tomcat-9.0.83\webapps\`).


6. **Launch:**
Execute `startup.bat` (or `startup.sh`) in the Tomcat `bin` folder.


7. **Access:**
Navigate to `http://localhost:8080/FitLab/` in your web browser.
