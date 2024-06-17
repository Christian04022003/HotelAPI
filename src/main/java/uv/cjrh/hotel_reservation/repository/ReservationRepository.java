package uv.cjrh.hotel_reservation.repository;



import org.springframework.data.mongodb.repository.MongoRepository;
import uv.cjrh.hotel_reservation.model.Reservation;

public interface ReservationRepository extends MongoRepository<Reservation, String> {
}

