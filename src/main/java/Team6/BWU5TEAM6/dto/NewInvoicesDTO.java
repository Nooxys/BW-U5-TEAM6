package Team6.BWU5TEAM6.dto;

import Team6.BWU5TEAM6.entities.Client;
import jakarta.validation.constraints.NotEmpty;

import java.util.Date;

public record NewInvoicesDTO(@NotEmpty(message = "date required") Date date,
                             @NotEmpty(message = "amount required") double amount,
                             @NotEmpty(message = "number required") int number,
                             @NotEmpty(message = "state required") String state,
                             @NotEmpty(message = "costumer required") Client client) {
}
