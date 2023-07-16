package ru.nc.ticketservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.nc.ticketservice.dto.CreateTicketRequest;
import ru.nc.ticketservice.dto.TicketResponse;
import ru.nc.ticketservice.dto.UpdateTicketRequest;
import ru.nc.ticketservice.exception.TicketNotFoundException;
import ru.nc.ticketservice.model.Ticket;
import ru.nc.ticketservice.repository.TicketRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;

    public UUID createTicket(UUID userId, CreateTicketRequest request) {
        Ticket newTicket = Ticket
                .builder()
                .description(request.getDescription())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .userId(userId)
                .build();

        Ticket savedTicket = ticketRepository.save(newTicket);

        return savedTicket.getTicketId();
    }

    public TicketResponse getTicket(UUID ticketId) throws TicketNotFoundException {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(TicketNotFoundException::new);

        return TicketResponse.builder()
                .description(ticket.getDescription())
                .latitude(ticket.getLatitude())
                .longitude(ticket.getLongitude())
                .build();
    }

    public void updateTicket(UUID ticketId, UpdateTicketRequest request) throws TicketNotFoundException {
        Ticket ticket = ticketRepository.findById(ticketId).orElseThrow(TicketNotFoundException::new);
        ticket.setDescription(request.getDescription());
        ticket.setLatitude(request.getLatitude());
        ticket.setLongitude(request.getLongitude());
        //TODO use criteria API hibernate for skip all null fields

        ticketRepository.save(ticket);
    }

    public void deleteTicket(UUID ticketId) throws TicketNotFoundException {
        if (!ticketRepository.existsById(ticketId)) {
            throw new TicketNotFoundException();
        }

        ticketRepository.deleteById(ticketId);
    }
}
