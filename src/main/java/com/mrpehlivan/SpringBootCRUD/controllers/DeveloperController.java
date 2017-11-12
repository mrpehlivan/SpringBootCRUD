package com.mrpehlivan.SpringBootCRUD.controllers;

import com.mrpehlivan.SpringBootCRUD.dto.DeveloperRequest;
import com.mrpehlivan.SpringBootCRUD.dto.DeveloperResponse;
import com.mrpehlivan.SpringBootCRUD.exception.MissingArgumentException;
import com.mrpehlivan.SpringBootCRUD.services.DeveloperService;
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
    public boolean insertDeveloper(@RequestBody DeveloperRequest request) throws MissingArgumentException {
        if (StringUtils.isEmpty(request.getEmail())) {
            throw new MissingArgumentException("failure","400", "Email can not be null");
        }
        return service.insert(request);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete", params = "email")
    public boolean deleteDeveloper(@RequestParam String email) throws MissingArgumentException {
        if (StringUtils.isEmpty(email)) {
            throw new MissingArgumentException("failure","400", "Email can not be null");
        }
        return service.delete(email);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/update")
    public boolean updateDeveloper(@RequestBody DeveloperRequest request) throws MissingArgumentException {
        if (StringUtils.isEmpty(request.getEmail())) {
            throw new MissingArgumentException("failure","400", "Email can not be null");
        }
        return service.update(request);
    }


}
