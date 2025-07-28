package noemibaglieri.entities;

import jakarta.persistence.*;
import lombok.*;
import noemibaglieri.enums.TripStatus;

import java.time.LocalDate;

@Entity
@Table(name = "trips")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;

    @Column(nullable = false)
    private String destination;

    @Column(name = "date_of_travel", nullable = false)
    private LocalDate dateOfTravel;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TripStatus status;

    public Trip(String destination, LocalDate dateOfTravel, TripStatus status) {
        this.destination = destination;
        this.dateOfTravel = dateOfTravel;
        this.status = status;
    }
}
