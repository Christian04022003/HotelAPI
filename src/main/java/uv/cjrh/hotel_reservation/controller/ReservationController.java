package uv.cjrh.hotel_reservation.controller;


import uv.cjrh.hotel_reservation.model.Reservation;
import uv.cjrh.hotel_reservation.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;

    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @PostMapping
    public Reservation createReservation(@Valid @RequestBody Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @GetMapping("/{id}")
    public Reservation getReservationById(@PathVariable String id) {
        return reservationRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Reservation updateReservation(@PathVariable String id, @Valid @RequestBody Reservation reservation) {
        reservation.setId(id);
        return reservationRepository.save(reservation);
    }

    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable String id) {
        reservationRepository.deleteById(id);
    }
}

