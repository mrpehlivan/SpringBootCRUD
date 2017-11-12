package com.mrpehlivan.SpringBootCRUD.services;

import com.mrpehlivan.SpringBootCRUD.dto.DeveloperRequest;
import com.mrpehlivan.SpringBootCRUD.dto.DeveloperResponse;
import com.mrpehlivan.SpringBootCRUD.model.DeveloperModel;
import com.mrpehlivan.SpringBootCRUD.repository.DeveloperRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DeveloperServiceTest {

    @InjectMocks
    private DeveloperService developerService;

    @Mock
    private DeveloperRepository developerRepository;

    private static final String EMAIL="m.arifpehlivan@gmail.com";

    @Test
    public void shouldFindDeveloperByEmail(){
        //Given
        Optional<DeveloperModel> developerModel = Optional.of(createDeveloperModel());

        //When
        when(developerRepository.findFirstByEmail(EMAIL)).thenReturn(developerModel);
        DeveloperResponse developerResponse = developerService.findDeveloperByEmail(EMAIL);

        //Then
        Assert.assertNotNull(developerResponse);
    }

    @Test
    public void shouldAddDeveloper(){
        //Given
        DeveloperModel developerModel = createDeveloperModel();

        //When
        when(developerRepository.save(any(DeveloperModel.class))).thenReturn(developerModel);
        boolean insert = developerService.insert(createDeveloperRequest());
        Assert.assertTrue(insert);


    }

    @Test
    public void shouldDeleteDeveloperByEmail() throws Exception {
        //Given
        Optional<DeveloperModel> developerModelOptional = Optional.of(createDeveloperModel());
        DeveloperModel developerModel = createDeveloperModel();

        //When
        when(developerRepository.findFirstByEmail(EMAIL)).thenReturn(developerModelOptional);
        when(developerRepository.save(any(DeveloperModel.class))).thenReturn(developerModel);
        boolean delete = developerService.delete(EMAIL);

        //Then
        Assert.assertTrue(delete);
    }

    @Test
    public void shouldUpdateDeveloper() throws Exception {
        //Given
        Optional<DeveloperModel> developerModelOptional = Optional.of(createDeveloperModel());
        DeveloperModel developerModel = createDeveloperModel();
        DeveloperRequest developerRequest = createDeveloperRequest();

        //When
        when(developerRepository.findFirstByEmail(anyString())).thenReturn(developerModelOptional);
        when(developerRepository.save(any(DeveloperModel.class))).thenReturn(developerModel);
        boolean update = developerService.update(developerRequest);

        //Then
        Assert.assertTrue(update);
    }

    private DeveloperModel createDeveloperModel(){
        DeveloperModel developerModel = new DeveloperModel();
        developerModel.setName("Chuck");
        developerModel.setLastName("Norris");
        developerModel.setPhoneNumber("5331112244");
        developerModel.setEmail("chuck.norris@mrpehlivan.com");
        developerModel.setSkill("java");
        developerModel.setActive(true);
        return developerModel;
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
