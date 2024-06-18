package uv.cjrh.hotel_reservation.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import uv.cjrh.hotel_reservation.model.Reservation;
import uv.cjrh.hotel_reservation.repository.ReservationRepository;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservationRepository reservationRepository;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(MockMvcRestDocumentation.documentationConfiguration(restDocumentation))
                .build();
    }

    @Test
    public void testGetAllReservations() throws Exception {
        when(reservationRepository.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/reservations"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty())
                .andDo(document("get-all-reservations"));
    }

    @Test
    public void testGetReservationById() throws Exception {
        Reservation reservation = createReservation("1", "John Doe", "john.doe@example.com", new Date(System.currentTimeMillis() + 100000), new Date(System.currentTimeMillis() + 200000), "Single");

        when(reservationRepository.findById("1")).thenReturn(Optional.of(reservation));

        mockMvc.perform(get("/api/reservations/{id}", "1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.customerName").value("John Doe"))
                .andExpect(jsonPath("$.customerEmail").value("john.doe@example.com"))
                .andExpect(jsonPath("$.roomType").value("Single"))
                .andDo(document("get-reservation-by-id"));
    }

    @Test
    public void testGetReservationByIdNotFound() throws Exception {
        when(reservationRepository.findById("999")).thenReturn(Optional.empty());
    
        mockMvc.perform(get("/api/reservations/{id}", "999"))
                .andExpect(status().isNotFound())
                .andDo(document("get-reservation-by-id-not-found"));
    }

    @Test
    public void testCreateReservation() throws Exception {
        Reservation reservation = createReservation(null, "Jane Smith", "jane.smith@example.com", new Date(), new Date(System.currentTimeMillis() + 86400000), "Double");

        when(reservationRepository.save(any())).thenReturn(reservation);

        mockMvc.perform(post("/api/reservations")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"customerName\": \"Jane Smith\", \"customerEmail\": \"jane.smith@example.com\", \"startDate\": \"2024-06-20T12:00:00Z\", \"endDate\": \"2024-06-21T12:00:00Z\", \"roomType\": \"Double\" }"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.customerName").value("Jane Smith"))
                .andExpect(jsonPath("$.customerEmail").value("jane.smith@example.com"))
                .andExpect(jsonPath("$.roomType").value("Double"))
                .andDo(document("create-reservation"));
    }

    @Test
    public void testUpdateReservation() throws Exception {
        Reservation existingReservation = createReservation("1", "John Doe", "john.doe@example.com", new Date(System.currentTimeMillis() + 100000), new Date(System.currentTimeMillis() + 200000), "Single");
        Reservation updatedReservation = createReservation("1", "Jane Smith", "jane.smith@example.com", new Date(System.currentTimeMillis() + 200000), new Date(System.currentTimeMillis() + 300000), "Double");

        when(reservationRepository.findById("1")).thenReturn(Optional.of(existingReservation));
        when(reservationRepository.save(any())).thenReturn(updatedReservation);

        mockMvc.perform(put("/api/reservations/{id}", "1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"id\": \"1\", \"customerName\": \"Jane Smith\", \"customerEmail\": \"jane.smith@example.com\", \"startDate\": \"2024-06-21T12:00:00Z\", \"endDate\": \"2024-06-22T12:00:00Z\", \"roomType\": \"Double\" }"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.customerName").value("Jane Smith"))
                .andExpect(jsonPath("$.customerEmail").value("jane.smith@example.com"))
                .andExpect(jsonPath("$.roomType").value("Double"))
                .andDo(document("update-reservation"));
    }

    @Test
    public void testDeleteReservation() throws Exception {
        doNothing().when(reservationRepository).deleteById("1");

        mockMvc.perform(delete("/api/reservations/{id}", "1"))
                .andExpect(status().isOk())
                .andDo(document("delete-reservation"));
    }

    private Reservation createReservation(String id, String customerName, String customerEmail, Date startDate, Date endDate, String roomType) {
        Reservation reservation = new Reservation();
        reservation.setId(id);
        reservation.setCustomerName(customerName);
        reservation.setCustomerEmail(customerEmail);
        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);
        reservation.setRoomType(roomType);
        return reservation;
    }
}
