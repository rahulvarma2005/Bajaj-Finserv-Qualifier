package com.bajaj.Bajaj.Finserv.Qualifier;

import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class ChallengeRunner implements CommandLineRunner {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void run(String... args) throws Exception {
        System.out.println(" Starting the Bajaj Finserv Health Challenge process...");

        try {

            System.out.println("Step 1: Generating webhook...");
            RegistrationRequest registrationRequest = new RegistrationRequest(
                    "Mudunuri Rahul Varma",
                    "22BCI0091",
                    "mudunurirahul.varma2022@vitstudent.ac.in"
            );
            String generateWebhookUrl = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";


            WebhookResponse webhookResponse = restTemplate.postForObject(generateWebhookUrl, registrationRequest, WebhookResponse.class);


            if (webhookResponse == null || webhookResponse.getAccessToken() == null) {
                System.err.println("Failed to get a valid webhook response. The response body was empty or incomplete.");
                return;
            }

            String webhookUrl = webhookResponse.getWebhookUrl();
            String accessToken = webhookResponse.getAccessToken();

            System.out.println(" Webhook and Token received successfully!");
            System.out.println("   - Webhook URL: " + webhookUrl);


            System.out.println("\nStep 2: Preparing SQL query...");
            String sqlQuery = "SELECT p.AMOUNT AS SALARY, CONCAT(e.FIRST_NAME, ' ', e.LAST_NAME) AS NAME, TIMESTAMPDIFF(YEAR, e.DOB, CURDATE()) AS AGE, d.DEPARTMENT_NAME FROM PAYMENTS p JOIN EMPLOYEE e ON p.EMP_ID = e.EMP_ID JOIN DEPARTMENT d ON e.DEPARTMENT = d.DEPARTMENT_ID WHERE EXTRACT(DAY FROM p.PAYMENT_TIME) <> 1 ORDER BY p.AMOUNT DESC LIMIT 1;";
            System.out.println(" SQL Query prepared.");



            System.out.println("\nStep 3: Submitting the solution...");
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", accessToken);
            headers.set("Content-Type", "application/json");

            SolutionRequest solutionRequest = new SolutionRequest(sqlQuery);
            HttpEntity<SolutionRequest> entity = new HttpEntity<>(solutionRequest, headers);

            ResponseEntity<String> submissionResponse = restTemplate.exchange(
                    webhookUrl,
                    HttpMethod.POST,
                    entity,
                    String.class
            );

            System.out.println(" Solution submitted!");
            System.out.println("   - Status Code: " + submissionResponse.getStatusCode());
            System.out.println("   - Response Body: " + submissionResponse.getBody());
            System.out.println("\n Challenge process completed!");

        } catch (HttpClientErrorException e) {

            System.err.println(" An error occurred during the API call!");
            System.err.println("   - HTTP Status Code: " + e.getStatusCode());
            System.err.println("   - Server Error Message: " + e.getResponseBodyAsString());
        }
    }
}
