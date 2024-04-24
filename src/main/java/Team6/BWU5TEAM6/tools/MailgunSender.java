package Team6.BWU5TEAM6.tools;

import Team6.BWU5TEAM6.entities.Client;
import Team6.BWU5TEAM6.entities.User;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MailgunSender {
    private String apiKey;
    private String domainName;

    public MailgunSender(@Value("${mailgun.apikey}") String apiKey, @Value("${mailgun.domainname}") String domainName) {
        this.apiKey = apiKey;
        this.domainName = domainName;
    }

    public void sendRegistrationEmail(User recipient) {
        HttpResponse<JsonNode> response = Unirest.post("https://api.mailgun.net/v3/" + this.domainName + "/messages")
                .basicAuth("api", this.apiKey)
                .queryString("from", "ciro_end94@hotmail.it")
                .queryString("to", recipient.getEmail())
                .queryString("subject", "Registration completed!")
                .queryString("text", "Great! " + recipient.getName() + " you are now registered!")
                .asJson();

        System.out.println(response.getBody());

    }

    public void sendEmail(Client recipient, String subject, String text) {
        HttpResponse<JsonNode> response = Unirest.post("https://api.mailgun.net/v3/" + this.domainName + "/messages")
                .basicAuth("api", this.apiKey)
                .queryString("from", "ciro_end94@hotmail.it")
                .queryString("to", recipient.getEmail())
                .queryString("subject", subject)
                .queryString("text", text)
                .asJson();
        System.out.println(response.getBody());
    }
}