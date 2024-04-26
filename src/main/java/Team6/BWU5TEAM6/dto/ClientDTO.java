package Team6.BWU5TEAM6.dto;

import Team6.BWU5TEAM6.enums.ClientType;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;

public record ClientDTO(
        @NotEmpty(message = "Business name is required")
        String businessName,
        @NotEmpty(message = "VAT number is required")
        int vatNumber,
        @NotEmpty(message = "Email is required")
        String email,
        @NotEmpty(message = "Insertion date is required")
        LocalDate insertionDate,
        @NotEmpty(message = "Last contact date is required")
        LocalDate lastContactDate,
        @NotEmpty(message = "Annual turnover is required")
        double annualTurnover,
        @NotEmpty(message = "PEC is required")
        String pec,
        @NotEmpty(message = "Telephone is required")
        int telephone,
        @NotEmpty(message = "Email contact is required")
        String emailContact,
        @NotEmpty(message = "Name contact is required")
        String nameContact,
        @NotEmpty(message = "Telephone contact is required")
        int telephoneContact,
        @NotEmpty(message = "Company logo is required")
        String companyLogo,
//        @NotEmpty(message = "Type is required")
        ClientType type,
        @NotEmpty(message = "Address is required")
        String address

) {
}
