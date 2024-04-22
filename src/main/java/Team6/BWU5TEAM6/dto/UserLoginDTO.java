package Team6.BWU5TEAM6.dto;

import jakarta.validation.constraints.NotEmpty;

public record UserLoginDTO(@NotEmpty(message = "Email is required! ")
                           String email,
                           @NotEmpty(message = "Password is required!")
                           String password) {
}
