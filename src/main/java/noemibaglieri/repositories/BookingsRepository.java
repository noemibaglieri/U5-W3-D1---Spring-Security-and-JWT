package noemibaglieri.repositories;

import noemibaglieri.entities.Booking;
import noemibaglieri.entities.Employee;
import noemibaglieri.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookingsRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT COUNT(b) > 0 FROM Booking b WHERE b.employee = :employee AND b.trip.dateOfTravel = :date")
    boolean existsByEmployeeAndTripDate(@Param("employee") Employee employee, @Param("date") LocalDate date);

    List<Booking> findByEmployee(Employee employee);

    List<Booking> findByTrip(Trip trip);

    boolean existsByEmployeeAndTrip(Employee employee, Trip trip);

}
