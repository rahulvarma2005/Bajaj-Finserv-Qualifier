package com.bajaj.Bajaj.Finserv.Qualifier;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WebhookResponse {

    @JsonProperty("webhook") // Corrected from "webhook_url"
    private String webhookUrl;

    @JsonProperty("accessToken") // Corrected from "access_token"
    private String accessToken;
}