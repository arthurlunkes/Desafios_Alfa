package dev.arthurlunkes.backend.controllers;

import dev.arthurlunkes.backend.dtos.RequestSaveTicketDTO;
import dev.arthurlunkes.backend.models.TicketModel;
import dev.arthurlunkes.backend.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping
    public ResponseEntity findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(ticketService.findAllTickets());
    }

    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(ticketService.findTicketById(id));
    }

    @PostMapping
    public ResponseEntity<Object> saveTicket(@RequestBody RequestSaveTicketDTO requestSaveTicketDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketService.saveTicket(requestSaveTicketDTO));
    }

    @PutMapping
    public ResponseEntity<Object> update(@RequestBody TicketModel ticket) {
        return ResponseEntity.status(HttpStatus.OK).body(ticketService.updateTicket(ticket));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        try {
            ticketService.deleteTicketById(id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body("Ticket deletado com sucesso!");
    }

}
