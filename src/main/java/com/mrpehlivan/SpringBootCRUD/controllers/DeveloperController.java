package com.mrpehlivan.SpringBootCRUD.controllers;

import com.mrpehlivan.SpringBootCRUD.dto.DeveloperRequest;
import com.mrpehlivan.SpringBootCRUD.dto.DeveloperResponse;
import com.mrpehlivan.SpringBootCRUD.services.DeveloperService;
import com.sun.javaws.exceptions.MissingFieldException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/developer")
public class DeveloperController {

    private DeveloperService service;

    @Autowired
    public DeveloperController(DeveloperService developerService) {
        this.service = developerService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findByEmail", params = "email")
    public DeveloperResponse findDeveloperByEmail(@RequestParam String email) {
        return service.findDeveloperByEmail(email);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/add")
    public void insertDeveloper(@RequestBody DeveloperRequest request) throws MissingFieldException {
        if (StringUtils.isEmpty(request.getEmail())) {
            throw new MissingFieldException(request.getEmail(), "email can not be null");
        }
        service.insert(request);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete", params = "email")
    public void deleteDeveloper(@RequestParam String email) throws MissingFieldException {
        if (StringUtils.isEmpty(email)) {
            throw new MissingFieldException(email, "email can not be null");
        }
        service.delete(email);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update")
    public void updateDeveloper(@RequestBody DeveloperRequest request) throws MissingFieldException {
        if (StringUtils.isEmpty(request.getEmail())) {
            throw new MissingFieldException(request.getEmail(), "email can not be null");
        }
        service.update(request);
    }


}
