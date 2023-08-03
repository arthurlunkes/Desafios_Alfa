package dev.arthurlunkes.backend.repositories;

import dev.arthurlunkes.backend.models.ModuleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends JpaRepository<ModuleModel, Integer> {
}
