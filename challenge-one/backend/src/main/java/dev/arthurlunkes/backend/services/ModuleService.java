package dev.arthurlunkes.backend.services;

import dev.arthurlunkes.backend.models.ModuleModel;
import dev.arthurlunkes.backend.repositories.ModuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleService {

    final private ModuleRepository moduleRepository;

    public ModuleService(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }

    public List<ModuleModel> findAll() {
        return moduleRepository.findAll();
    }

    public ModuleModel findById(int i) {
        return moduleRepository.findById(i).get();
    }
}
