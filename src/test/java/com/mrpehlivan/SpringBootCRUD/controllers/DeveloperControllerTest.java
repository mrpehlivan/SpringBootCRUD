package com.mrpehlivan.SpringBootCRUD.controllers;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrpehlivan.SpringBootCRUD.dto.DeveloperRequest;
import com.mrpehlivan.SpringBootCRUD.dto.DeveloperResponse;
import com.mrpehlivan.SpringBootCRUD.services.DeveloperService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(DeveloperController.class)
public class DeveloperControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeveloperService developerService;

    private static final String EMAIL="chuck.norris@mrpehlivan.com";

    private static String jsonConverter(Object obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void shouldFindDeveloperByEmail() throws Exception {
        when(developerService.findDeveloperByEmail(EMAIL)).thenReturn(createDeveloperResponse());
        this.mockMvc.perform(get("/developer/findByEmail?email="+EMAIL))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldAddDeveloper() throws Exception {
        when(developerService.insert(createDeveloperRequest())).thenReturn(true);
        this.mockMvc.perform(
                post("/developer/add")
                        .content(jsonConverter(createDeveloperRequest()))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteDeveloperByEmail() throws Exception {
        when(developerService.delete(EMAIL)).thenReturn(true);
        this.mockMvc.perform(delete("/developer/delete?email="+EMAIL))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateDeveloper() throws Exception {
        when(developerService.update(createDeveloperRequest())).thenReturn(true);
        this.mockMvc.perform(
                put("/developer/update")
                        .content(jsonConverter(createDeveloperRequest()))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }


    private DeveloperResponse createDeveloperResponse(){
        DeveloperResponse developerResponse = new DeveloperResponse();
        developerResponse.setName("Chuck");
        developerResponse.setLastName("Norris");
        developerResponse.setPhoneNumber("5331112244");
        developerResponse.setEmail("chuck.norris@mrpehlivan.com");
        developerResponse.setSkill("java");
        developerResponse.setActive(true);
        return developerResponse;
    }

    private DeveloperRequest createDeveloperRequest(){
        DeveloperRequest developerRequest = new DeveloperRequest();
        developerRequest.setName("Chuck");
        developerRequest.setLastName("Norris");
        developerRequest.setPhoneNumber("5331112244");
        developerRequest.setEmail("chuck.norris@mrpehlivan.com");
        developerRequest.setSkill("java");
        return developerRequest;
    }
 }
