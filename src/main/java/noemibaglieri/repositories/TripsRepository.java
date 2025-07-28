package noemibaglieri.repositories;

import noemibaglieri.entities.Trip;
import noemibaglieri.enums.TripStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TripsRepository extends JpaRepository<Trip, Long> {

    List<Trip> findByStatus(TripStatus status);

    boolean existsByDestinationAndDateOfTravelAndStatus(String destination, LocalDate dateOfTravel, TripStatus status);

    List<Trip> findByDateOfTravel(LocalDate dateOfTravel);

    List<Trip> findByDestination(String destination);
}
