package dev.arthurlunkes.backend.services;

import dev.arthurlunkes.backend.dtos.RequestSaveTicketDTO;
import dev.arthurlunkes.backend.dtos.ResponseGetAllGroupyByClientAndModuleDTO;
import dev.arthurlunkes.backend.models.TicketModel;
import dev.arthurlunkes.backend.repositories.TicketRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TicketService {

    final private TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }
    public ResponseGetAllGroupyByClientAndModuleDTO findAllTickets() {

        List<TicketModel> tickets = ticketRepository.findAll();
        Map<String, List<TicketModel>> ticketsByClient = tickets.stream()
                .collect(Collectors.groupingBy(ticket -> ticket.getClient().getName()));
        Map<String, List<TicketModel>> ticketsByModule = tickets.stream()
                .collect(Collectors.groupingBy(ticket -> ticket.getModule().getName()));

        ResponseGetAllGroupyByClientAndModuleDTO response = new ResponseGetAllGroupyByClientAndModuleDTO(tickets,
                ticketsByClient, ticketsByModule);

        return response;
    }

    public TicketModel findTicketById(Integer id) {
        return ticketRepository.findById(id).get();
    }

    public ResponseGetAllGroupyByClientAndModuleDTO findTicketsByMonthAndYear(int Month, int Year) {

        List<TicketModel> tickets = ticketRepository.findByMonthAndYear(Month, Year);
        Map<String, List<TicketModel>> ticketsByClient = tickets.stream()
                .collect(Collectors.groupingBy(ticket -> ticket.getClient().getName()));
        Map<String, List<TicketModel>> ticketsByModule = tickets.stream()
                .collect(Collectors.groupingBy(ticket -> ticket.getModule().getName()));

        ResponseGetAllGroupyByClientAndModuleDTO response = new ResponseGetAllGroupyByClientAndModuleDTO(tickets,
                ticketsByClient, ticketsByModule);

        return response;
    }

    public TicketModel saveTicket(RequestSaveTicketDTO requestSaveTicketDTO) {
        TicketModel ticket = new TicketModel();

        ticket.setTitle(requestSaveTicketDTO.title());
        ticket.setClient(requestSaveTicketDTO.client());
        ticket.setModule(requestSaveTicketDTO.module());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        ticket.setDateOpen(LocalDate.parse(requestSaveTicketDTO.dateOpen(), formatter));
        ticket.setDateClose(LocalDate.parse(requestSaveTicketDTO.dateClose(), formatter));

        return ticketRepository.save(ticket);
    }

    public List<TicketModel> saveAllTickets(List<RequestSaveTicketDTO> tickets) {
        List<TicketModel> savedTickets = new ArrayList<>();

        for (RequestSaveTicketDTO ticket : tickets) {
            savedTickets.add(saveTicket(ticket));
        }

        return savedTickets;
    }

    public TicketModel updateTicket(TicketModel ticket) {
        return ticketRepository.save(ticket);
    }

    public void deleteTicketById(Integer id) {
        ticketRepository.deleteById(id);
    }
}