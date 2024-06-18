package uv.cjrh.hotel_reservation.config;

import org.junit.jupiter.api.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import uv.cjrh.hotel_reservation.dto.ErrorDTO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ExceptionAdviceTest {

    @Test
    public void testValidationErrors() {
        FieldError fieldError1 = new FieldError("reservation", "customerName", "El nombre del cliente es obligatorio");
        FieldError fieldError2 = new FieldError("reservation", "customerEmail", "El email del cliente es obligatorio");
        List<FieldError> fieldErrors = List.of(fieldError1, fieldError2);

        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(new Object(), "reservation");
        
        // Agregar cada FieldError individualmente
        for (FieldError fieldError : fieldErrors) {
            bindingResult.addError(fieldError);
        }

        when(ex.getBindingResult()).thenReturn(bindingResult);

        ExceptionAdvice advice = new ExceptionAdvice();
        ErrorDTO errorDTO = advice.validationErrors(ex);

        assertEquals("LIS001", errorDTO.getCode());
        assertEquals("Error validación datos de entrada", errorDTO.getMessage());
        assertEquals(List.of("El nombre del cliente es obligatorio", "El email del cliente es obligatorio"), errorDTO.getDetails());
    }
}
