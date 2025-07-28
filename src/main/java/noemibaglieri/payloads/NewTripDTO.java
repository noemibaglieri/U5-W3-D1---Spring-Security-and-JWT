package noemibaglieri.payloads;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import noemibaglieri.enums.TripStatus;

import java.time.LocalDate;

public record NewTripDTO(
        @NotBlank(message = "Destination is required.")
        String destination,

        @NotNull(message = "Trip date is required.")
        @FutureOrPresent(message = "The requested date must be today or in the future")
        LocalDate dateOfTravel,

        @NotNull(message = "Trip status is required.")
        TripStatus status) {
}
