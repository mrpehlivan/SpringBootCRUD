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
        return repository.findFirstByEmail(email).map(DeveloperResponse::new).orElse(new DeveloperResponse());
    }

    public boolean insert(DeveloperRequest request) {
        DeveloperModel model = new DeveloperModel();
        model.setName(request.getName());
        model.setLastName(request.getLastName());
        model.setEmail(request.getEmail());
        model.setPhoneNumber(request.getPhoneNumber());
        model.setSkill(request.getSkill());
        model.setActive(true);
        DeveloperModel save = repository.save(model);
        return save != null;
    }

    public boolean delete(String email) {
        Optional<DeveloperModel> model = repository.findFirstByEmail(email);
         return model.map(developerModel -> {
            developerModel.setActive(false);
            return repository.save(developerModel) != null;
        }).orElse(false);
    }


    public boolean update(DeveloperRequest request) {
        Optional<DeveloperModel> model = repository.findFirstByEmail(request.getEmail());
         return model.map(developerModel -> {
            developerModel.setPhoneNumber(request.getPhoneNumber());
            developerModel.setSkill(request.getSkill());
            return repository.save(developerModel) != null;
        }).orElse(false);
    }
}