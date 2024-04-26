package Team6.BWU5TEAM6.dto;

public record NewAdressDTO(String street,
                           int civicNumber,
                           String locality,
                           int postalCode,
                           long municipality_id,
                           long district_id) {
}
