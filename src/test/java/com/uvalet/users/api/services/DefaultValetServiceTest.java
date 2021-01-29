package com.uvalet.users.api.services;

import com.uvalet.users.api.data.repositories.ValetRepository;
import com.uvalet.users.api.domains.entities.Valet;
import com.uvalet.users.api.factory.TestsUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DefaultValetServiceTest {

    @Mock
    private ValetRepository valetRepository;

    @InjectMocks
    private DefaultValetService service;

    @Test
    public void shouldSaveValet() {
        String expectedId = "1";
        Valet valet = TestsUtil.buildAValet(null,"d1");
        when(valetRepository.save(valet)).thenAnswer(a -> {
            valet.setId(expectedId) ;
            return valet;
        });
        assertThat(service.register(valet.mapToDtoOrDomain()).getId())
                .isEqualTo(expectedId);
    }

    @Test
    public void getCustomerById() {
        String expectedEmail = "iam@m.com";
        Valet valet = TestsUtil.buildAValet("100","d1");
        when(valetRepository.findById(anyString())).thenReturn(Optional.of(valet));
        assertThat(service.getUserById("100").getEmail()).isEqualTo(expectedEmail);
    }

    @Test
    public void shouldGetAllCustomers(){
        service.getValets();
        verify(valetRepository, only()).findAll();
    }

}