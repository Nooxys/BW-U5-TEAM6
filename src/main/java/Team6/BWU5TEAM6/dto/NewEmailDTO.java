package Team6.BWU5TEAM6.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record NewEmailDTO(@NotEmpty(message = "email is required!")
                          @Email(message = "insert a valid email!")
                          String email,
                          @NotEmpty(message = "subject is required!")
                          String subject,
                          @NotEmpty(message = "text is required!")
                          String text) {
}
