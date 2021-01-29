package com.uvalet.users.api.data.repositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.uvalet.users.api.domains.entities.Valet;
import com.uvalet.users.api.domains.valueobjects.DriverLicense;
import com.uvalet.users.api.factory.TestsUtil;
import org.apache.juli.logging.LogFactory;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;

public class ValetRepositoryTest extends AbstractRepositoryTest{

    @Autowired
    private ValetRepository repository;

    @Before
    public void setup(){
        final Valet valet = TestsUtil.buildAValet("1000","dl1234");
        final Valet valet2 = TestsUtil.buildAValet("2000","dl1234");
        valet2.setEmail("janedoe@m.com");
        valet2.setDriverLicense(DriverLicense.builder().number("2222").build());
        repository.save(valet);
        repository.save(valet2);
    }

    @Test
    public void shouldSaveAValetUser() throws JsonProcessingException {
        final Valet valet = TestsUtil.buildAValet(null,"dl1235");
        valet.setEmail("anotherEmai@m.com");
        final Valet saved = repository.save(valet);

        LogFactory.getLog(getClass()).info(objectMapper.writeValueAsString(saved));

        Assertions.assertThat(saved.getId()).isNotNull();
    }

    @Test(expected = DuplicateKeyException.class)
    public void shouldThrowDuplicateKeyExceptionWhenEmailExists() throws JsonProcessingException {
        final Valet valet = TestsUtil.buildAValet("1000","dl1235");
        final Valet saved = repository.save(valet);
        valet.setId("2000");
        repository.save(valet);

        LogFactory.getLog(getClass()).info(objectMapper.writeValueAsString(saved));

        Assertions.assertThat(saved.getId()).isNotNull();
    }


    @Test
    public void shouldReturnOnlyOneResult(){
        final Valet found = repository.findByDriverLicenseNumber("2222");
        Assertions.assertThat(found.getEmail()).isEqualTo("janedoe@m.com");
    }

    @After
    public void clearUp(){
        repository.deleteAll();
    }

}