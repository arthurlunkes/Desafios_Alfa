package dev.arthurlunkes.backend.repositories;

import dev.arthurlunkes.backend.models.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientModel, Integer> {
}
