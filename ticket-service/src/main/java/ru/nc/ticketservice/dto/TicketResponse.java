package ru.nc.ticketservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TicketResponse {
    private float latitude;
    private float longitude;
    private String description;
    private Timestamp created;
    private Timestamp updated;
}
