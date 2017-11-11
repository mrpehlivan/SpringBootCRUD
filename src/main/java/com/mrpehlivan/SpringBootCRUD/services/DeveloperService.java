package com.mrpehlivan.SpringBootCRUD.services;


import com.mrpehlivan.SpringBootCRUD.dto.DeveloperRequest;
import com.mrpehlivan.SpringBootCRUD.dto.DeveloperResponse;
import com.mrpehlivan.SpringBootCRUD.model.DeveloperModel;
import com.mrpehlivan.SpringBootCRUD.repository.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeveloperService {

    private DeveloperRepository repository;

    @Autowired
    public DeveloperService(DeveloperRepository developerRepository) {
        this.repository = developerRepository;
    }

    public DeveloperResponse findDeveloperByEmail(String email) {
        return repository.findFirstByEmail(email).map(developerModel -> new DeveloperResponse(developerModel)).orElse(new DeveloperResponse());
    }

    public void insert(DeveloperRequest request) {
        DeveloperModel model = new DeveloperModel();
        model.setName(request.getName());
        model.setLastName(request.getLastName());
        model.setEmail(request.getEmail());
        model.setPhoneNumber(request.getPhoneNumber());
        model.setSkill(request.getSkill());
        model.setActive(true);
        repository.save(model);
    }

    public void delete(String email) {
        Optional<DeveloperModel> model = repository.findFirstByEmail(email);
        model.ifPresent(developerModel -> {
            developerModel.setActive(false);
            repository.save(developerModel);
        });
    }


    public void update(DeveloperRequest request) {
        Optional<DeveloperModel> model = repository.findFirstByEmail(request.getEmail());
        model.ifPresent(developerModel -> {
            developerModel.setPhoneNumber(request.getPhoneNumber());
            developerModel.setSkill(request.getSkill());
            repository.save(developerModel);
        });
    }
}