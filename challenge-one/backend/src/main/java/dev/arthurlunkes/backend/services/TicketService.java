package dev.arthurlunkes.backend.services;

import dev.arthurlunkes.backend.dtos.RequestSaveTicketDTO;
import dev.arthurlunkes.backend.models.TicketModel;
import dev.arthurlunkes.backend.repositories.TicketRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class TicketService {

    final private TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }
    public List<TicketModel> findAllTickets() {
        return ticketRepository.findAll();
    }

    public TicketModel findTicketById(Integer id) {
        return ticketRepository.findById(id).get();
    }

    public TicketModel saveTicket(RequestSaveTicketDTO requestSaveTicketDTO) {
        TicketModel ticket = new TicketModel();

        ticket.setTitle(requestSaveTicketDTO.title());
        ticket.setClient(requestSaveTicketDTO.client());
        ticket.setModule(requestSaveTicketDTO.module());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        ticket.setDateOpen(LocalDate.parse(requestSaveTicketDTO.dateOpen(), formatter));
        ticket.setDateClose(LocalDate.parse(requestSaveTicketDTO.dateClose(), formatter));

        return ticketRepository.save(ticket);
    }

    public TicketModel updateTicket(TicketModel ticket) {
        return ticketRepository.save(ticket);
    }

    public void deleteTicketById(Integer id) {
        ticketRepository.deleteById(id);
    }
}
