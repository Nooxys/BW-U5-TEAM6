
package Team6.BWU5TEAM6.dto;

import jakarta.validation.constraints.NotEmpty;


public record AddressDTO(
        @NotEmpty(message = "Street is required")
        String street,
        @NotEmpty(message = "Civic number is required")
        String civicNumber,
        @NotEmpty(message = "Locality is required")
        String locality,
        @NotEmpty(message = "Postal code is required")
        String postalCode,
        @NotEmpty(message = "Municipality is required")
        String municipality
) {
}
