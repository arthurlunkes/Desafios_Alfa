package dev.arthurlunkes.backend.dtos;

import dev.arthurlunkes.backend.models.TicketModel;

import java.util.List;
import java.util.Map;

public record ResponseGetAllGroupyByClientAndModuleDTO(List<TicketModel> tickets, Map<String, List<TicketModel>> ticketsByClient,
                                                       Map<String, List<TicketModel>> ticketsByModule) {
}
