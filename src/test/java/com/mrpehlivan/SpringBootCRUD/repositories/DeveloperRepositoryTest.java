package com.mrpehlivan.SpringBootCRUD.repositories;

import com.mrpehlivan.SpringBootCRUD.model.DeveloperModel;
import com.mrpehlivan.SpringBootCRUD.repository.DeveloperRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DeveloperRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DeveloperRepository developerRepository;

    @Test
    public void shouldFindFirstByEmail(){
        //Given
        DeveloperModel model = createDeveloperModel();
        entityManager.persist(model);
        entityManager.flush();

        //When
        Optional<DeveloperModel> developerModel = developerRepository.findFirstByEmail(model.getEmail());

        //Then
        Assert.assertNotNull(developerModel);
        Assert.assertNotNull(developerModel.get());
        Assert.assertEquals(developerModel.get(),model);
        Assert.assertEquals(developerModel.get().getEmail(),model.getEmail());
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
}
