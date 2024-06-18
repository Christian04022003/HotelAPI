package uv.cjrh.hotel_reservation.dto;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ErrorDTOTest {

    @Test
    public void testErrorDTO() {
        List<String> details = List.of("Error 1", "Error 2");
        ErrorDTO errorDTO = new ErrorDTO("LIS001", "Error validación datos de entrada", details);

        assertEquals("LIS001", errorDTO.getCode());
        assertEquals("Error validación datos de entrada", errorDTO.getMessage());
        assertEquals(details, errorDTO.getDetails());
    }
}
