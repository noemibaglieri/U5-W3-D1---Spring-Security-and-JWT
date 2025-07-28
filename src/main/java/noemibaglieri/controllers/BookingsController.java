package noemibaglieri.controllers;

import noemibaglieri.entities.Booking;
import noemibaglieri.exceptions.ValidationException;
import noemibaglieri.payloads.AssignEmployeeDTO;
import noemibaglieri.payloads.NewBookingDTO;
import noemibaglieri.services.BookingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingsController {

    @Autowired
    private BookingsService bookingsService;

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingsService.findAll();
    }

    @GetMapping("/{bookingId}")
    public Booking getBookingById(@PathVariable long bookingId) {
        return bookingsService.findById(bookingId);
    }

    @GetMapping("/employee/{employeeId}")
    public List<Booking> getBookingsByEmployee(@PathVariable long employeeId) {
        return bookingsService.findByEmployee(employeeId);
    }

    @GetMapping("/trip/{tripId}")
    public List<Booking> getBookingsByTrip(@PathVariable long tripId) {
        return bookingsService.findByTrip(tripId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Booking createBooking(@RequestBody @Validated NewBookingDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException(validationResult.getFieldErrors()
                    .stream().map(e -> e.getDefaultMessage()).toList());
        }
        return bookingsService.save(body);
    }

    @PutMapping("/{bookingId}")
    public Booking updateBooking(@PathVariable long bookingId, @RequestBody @Validated NewBookingDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException(validationResult.getFieldErrors()
                    .stream().map(e -> e.getDefaultMessage()).toList());
        }
        return bookingsService.findByIdAndUpdate(bookingId, body);
    }

    @PatchMapping("/{tripId}/assign")
    @ResponseStatus(HttpStatus.OK)
    public Booking assignEmployeeToTrip(
            @PathVariable long tripId,
            @RequestBody @Validated AssignEmployeeDTO body,
            BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException(validationResult.getFieldErrors().stream()
                    .map(e -> e.getDefaultMessage()).toList());
        }
        return bookingsService.assignEmployeeToTrip(body.employeeId(), tripId);
    }

    @DeleteMapping("/{bookingId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBooking(@PathVariable long bookingId) {
        bookingsService.findByIdAndDelete(bookingId);
    }
}
