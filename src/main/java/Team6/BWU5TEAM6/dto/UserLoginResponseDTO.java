package Team6.BWU5TEAM6.dto;

import jakarta.validation.constraints.NotEmpty;

public record UserLoginResponseDTO(@NotEmpty(message = "Token is required!")
                                   String accessToken) {
}
