package dev.arthurlunkes.backend.dtos;

import dev.arthurlunkes.backend.models.ClientModel;
import dev.arthurlunkes.backend.models.ModuleModel;

import java.time.LocalDate;

public record RequestSaveTicketDTO(String title, String dateOpen, String dateClose, ClientModel client, ModuleModel module) {
}
