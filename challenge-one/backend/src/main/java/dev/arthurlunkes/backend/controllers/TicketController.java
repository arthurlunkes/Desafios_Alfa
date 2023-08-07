package dev.arthurlunkes.backend.controllers;

import dev.arthurlunkes.backend.dtos.RequestSaveTicketDTO;
import dev.arthurlunkes.backend.models.TicketModel;
import dev.arthurlunkes.backend.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping
    public ResponseEntity findAll(@RequestHeader HttpHeaders headers) {

        // Verificando se os headers são nulos, se sim, retornando todos os chamados filtrados
        if (headers.get("Month") != null && headers.get("Year") != null) {
            return ResponseEntity.status(HttpStatus.OK).body(ticketService.findTicketsByMonthAndYear(
                    Integer.parseInt(headers.get("Month").get(0)), Integer.parseInt(headers.get("Year").get(0))
            ));

        // Se não, retornando todos os chamados
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(ticketService.findAllTickets());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity findTicketById(@PathVariable Integer id) {
        TicketModel ticket;

        // Verificando se o chamado com o id existe, se não, retorna um erro
        try {
            ticket = ticketService.findTicketById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Chamado não encontrado!");
        }

        // se existir, retorna o chamado
        return ResponseEntity.status(HttpStatus.OK).body(ticket);
    }

    @PostMapping
    public ResponseEntity<Object> saveTicket(@RequestBody RequestSaveTicketDTO requestSaveTicketDTO) {
        TicketModel ticket;

        // Verifica se consegue criar o chamado, se não, retorna um erro
        try {
            ticket = ticketService.saveTicket(requestSaveTicketDTO);
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

        // se conseguir, retorna o chamado
        return ResponseEntity.status(HttpStatus.CREATED).body(ticket);
    }

    @PostMapping("/saveAll")
    public ResponseEntity<Object> saveAllTickets(@RequestBody List<RequestSaveTicketDTO> tickets) {
        List<TicketModel> savedTickets;

        // Verifica se consegue salvar todos os chamados, se não, retorna um erro
        try {
            savedTickets = ticketService.saveAllTickets(tickets);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

        // se conseguir, retorna todos os chamados
        return ResponseEntity.status(HttpStatus.CREATED).body(savedTickets);
    }

    @PutMapping
    public ResponseEntity<Object> updateTicket(@RequestBody TicketModel ticket) {
        TicketModel updatedTicket;

        // Verifica se o chamado existe, se não, retorna um erro
        try {
            ticketService.findTicketById(ticket.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Chamado não encontrado!");
        }

        // Verifica se consegue atualizar o chamado, se não, retorna um erro
        try {
            updatedTicket = ticketService.updateTicket(ticket);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

        // se conseguir, retorna o chamado
        return ResponseEntity.status(HttpStatus.OK).body(updatedTicket);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTicketById(@PathVariable Integer id) {

        // Verifica se o chamado existe, se não, retorna um erro
        try {
            ticketService.findTicketById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Chamado não encontrado!");
        }

        // se existe, deleta e retorna o sucesso
        ticketService.deleteTicketById(id);

        return ResponseEntity.status(HttpStatus.OK).body("Chamado deletado com sucesso!");
    }

}
