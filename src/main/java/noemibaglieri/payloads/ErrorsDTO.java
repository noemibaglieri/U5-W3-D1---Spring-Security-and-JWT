package noemibaglieri.payloads;
import java.time.LocalDateTime;

public record ErrorsDTO(String message, LocalDateTime timestamp) {
}