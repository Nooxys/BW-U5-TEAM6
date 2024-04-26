package Team6.BWU5TEAM6.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record DistrictDTO(
        @NotEmpty(message = "Sigla is required")
        @NotNull(message = "Sigla not null")
        String district,
        @NotEmpty(message = "Region is required")
        @NotNull(message = "Region not null")
        String region
) {
}
