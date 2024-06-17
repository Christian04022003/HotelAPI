package uv.cjrh.hotel_reservation.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

@Document(collection = "reservations")
@Data
public class Reservation {
    @Id
    private String id;

    @NotBlank(message = "El nombre del cliente es obligatorio")
    private String customerName;

    @NotBlank(message = "El email del cliente es obligatorio")
    private String customerEmail;

    @NotNull(message = "La fecha de inicio es obligatoria")
    @Future(message = "La fecha de inicio debe ser en el futuro")
    private Date startDate;

    @NotNull(message = "La fecha de fin es obligatoria")
    @Future(message = "La fecha de fin debe ser en el futuro")
    private Date endDate;

    @NotBlank(message = "El tipo de habitación es obligatorio")
    private String roomType;

    public void setId(String id2) {
    }
}

