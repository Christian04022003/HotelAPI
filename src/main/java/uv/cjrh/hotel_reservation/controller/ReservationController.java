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
    public Reservation updateReservation(@PathVariable String id, @Valid @RequestBody Reservation updatedReservation) {
        // Buscar la reserva existente por su ID
        Reservation existingReservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found with id " + id));

        // Actualizar los campos de la reserva existente con los nuevos valores
        existingReservation.setCustomerName(updatedReservation.getCustomerName());
        existingReservation.setCustomerEmail(updatedReservation.getCustomerEmail());
        existingReservation.setStartDate(updatedReservation.getStartDate());
        existingReservation.setEndDate(updatedReservation.getEndDate());
        existingReservation.setRoomType(updatedReservation.getRoomType());

        // Guardar la reserva actualizada
        return reservationRepository.save(existingReservation);
    }

    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable String id) {
        reservationRepository.deleteById(id);
    }
}

