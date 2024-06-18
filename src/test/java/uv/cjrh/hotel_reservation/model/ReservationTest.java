package uv.cjrh.hotel_reservation.model;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ReservationTest {

    @Test
    public void testReservationGettersAndSetters() {
        Reservation reservation = new Reservation();
        reservation.setId("1");
        reservation.setCustomerName("John Doe");
        reservation.setCustomerEmail("john.doe@example.com");
        reservation.setStartDate(new Date(System.currentTimeMillis() + 100000));
        reservation.setEndDate(new Date(System.currentTimeMillis() + 200000));
        reservation.setRoomType("Single");

        assertEquals("1", reservation.getId());
        assertEquals("John Doe", reservation.getCustomerName());
        assertEquals("john.doe@example.com", reservation.getCustomerEmail());
        assertNotNull(reservation.getStartDate());
        assertNotNull(reservation.getEndDate());
        assertEquals("Single", reservation.getRoomType());
    }

    @Test
    public void testReservationEqualsAndHashCode() {
        Reservation reservation1 = new Reservation();
        reservation1.setId("1");

        Reservation reservation2 = new Reservation();
        reservation2.setId("1");

        assertEquals(reservation1, reservation2);
        assertEquals(reservation1.hashCode(), reservation2.hashCode());
    }
}
