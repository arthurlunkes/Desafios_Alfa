package dev.arthurlunkes.backend.repositories;

import dev.arthurlunkes.backend.models.TicketModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<TicketModel, Integer> {

    @Query("SELECT t FROM TicketModel t WHERE MONTH(t.dateOpen) = :month AND YEAR(t.dateOpen) = :year")
    List<TicketModel> findByMonthAndYear(Integer month, Integer year);

}
