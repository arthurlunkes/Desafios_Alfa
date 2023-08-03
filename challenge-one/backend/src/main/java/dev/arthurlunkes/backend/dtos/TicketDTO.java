package dev.arthurlunkes.backend.dtos;

import java.time.LocalDate;

public record TicketDTO(String title, LocalDate dateOpen, LocalDate dateClose, ClientDTO client, ModuleDTO module) {
}
