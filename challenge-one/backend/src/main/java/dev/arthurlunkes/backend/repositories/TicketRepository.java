package dev.arthurlunkes.backend.repositories;

import dev.arthurlunkes.backend.models.TicketModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<TicketModel, Integer> {
}
