package noemibaglieri.payloads;

import jakarta.validation.constraints.NotNull;
import noemibaglieri.enums.TripStatus;

public record UpdateTripStatusDTO(
        @NotNull(message = "New status is required.")
        TripStatus newStatus
) {}
