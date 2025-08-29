# Bajaj Finserv Health - Java Qualifier Challenge

This project is a Spring Boot application built to solve the Bajaj Finserv Health Qualifier 1 challenge. The application automates a workflow that involves retrieving a dynamic SQL challenge, solving it, and submitting the solution to a webhook, all triggered on application startup.

---

## 📥 Download

**Direct JAR File Link:** [Bajaj-Finserv-Qualifier-0.0.1-SNAPSHOT.jar](https://github.com/rahulvarma2005/Bajaj-Finserv-Qualifier/raw/refs/heads/master/Bajaj-Finserv-Qualifier-0.0.1-SNAPSHOT.jar)

---

## 📋 Task Overview

The core task is to build a Spring Boot application that:
1.  **Initiates on Startup**: The entire process runs automatically when the application starts, with no need for manual triggers or API endpoints.
2.  **Fetches the Challenge**: It sends a `POST` request with registration details to a specified endpoint to receive a unique webhook URL, a JWT access token, and a SQL problem.
3.  **Solves the SQL Problem**: It prepares the correct SQL query based on the assigned problem.
4.  **Submits the Solution**: It sends the final SQL query as a `POST` request to the received webhook URL, using the JWT token for authorization.

---

## ⚙️ How It Works

The application's logic is contained within the `ChallengeRunner.java` class, which implements `CommandLineRunner`. This ensures the process is executed as soon as the Spring application context is loaded.

1.  **Registration**: A `POST` request containing the candidate's name, registration number, and email is sent to `https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA`.
2.  **Response Handling**: The application parses the JSON response to extract the `webhookUrl` and `accessToken`.
3.  **Query Preparation**: The final SQL query for the assigned problem is stored as a string.
4.  **Submission**: A final `POST` request is made to the dynamic `webhookUrl`. The request includes the `accessToken` in the `Authorization` header and the SQL query in the JSON body.

---

## 🚀 How to Run

1.  **Prerequisites**:
    * Java 17 or higher
    * Apache Maven
2.  **Clone the repository**:
    ```bash
    git clone [https://github.com/rahulvarma2005/Bajaj-Finserv-Qualifier.git](https://github.com/rahulvarma2005/Bajaj-Finserv-Qualifier.git)
    cd Bajaj-Finserv-Qualifier
    ```
3.  **Update Personal Details** (Optional):
    * Open `src/main/java/com/bajaj/Bajaj/Finserv/Qualifier/ChallengeRunner.java`.
    * Modify the `RegistrationRequest` object with your own details if needed.
4.  **Build the project**:
    ```bash
    mvn clean package
    ```
5.  **Run the application**:
    ```bash
    java -jar target/Bajaj-Finserv-Qualifier-0.0.1-SNAPSHOT.jar
    ```
    The application will start, run the entire process, and print the logs to the console before exiting.

---

## 🛠️ Technologies Used

* **Java 21**
* **Spring Boot 3**
* **Spring Web** (for `RestTemplate`)
* **Apache Maven** (for dependency management and build)
* **Lombok** (to reduce boilerplate code)
