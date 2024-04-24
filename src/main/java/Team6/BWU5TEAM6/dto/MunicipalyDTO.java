package Team6.BWU5TEAM6.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record MunicipalyDTO(
        @NotEmpty(message = "Municipality is required")
        @NotNull(message = "Municipality not null")
        String name_municipality,
        @NotEmpty(message = "District is required")
        @NotNull(message = "District not null")
        String name_district
) {
}
