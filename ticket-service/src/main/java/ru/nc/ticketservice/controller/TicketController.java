package ru.nc.ticketservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.nc.ticketservice.dto.CreateTicketRequest;
import ru.nc.ticketservice.dto.TicketResponse;
import ru.nc.ticketservice.dto.UpdateTicketRequest;
import ru.nc.ticketservice.exception.TicketNotFoundException;
import ru.nc.ticketservice.service.TicketService;

import java.util.UUID;

@RestController
@RequestMapping("/api/ticket")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public UUID createTicket(@RequestBody CreateTicketRequest request) {
        UUID userId = UUID.randomUUID();

        return ticketService.createTicket(userId, request);
    }

    @GetMapping("/{ticketId}")
    @ResponseStatus(HttpStatus.OK)
    public TicketResponse getTicket(@PathVariable("ticketId") UUID ticketId) throws TicketNotFoundException {
        return ticketService.getTicket(ticketId);
    }

    @PutMapping("/{ticketId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateTicket(@PathVariable("ticketId") UUID ticketId, @RequestBody UpdateTicketRequest request) throws TicketNotFoundException {
        ticketService.updateTicket(ticketId, request);
    }

    @DeleteMapping("/{ticketId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTicket(@PathVariable("ticketId") UUID ticketId) throws TicketNotFoundException {
        ticketService.deleteTicket(ticketId);
    }

}
