package com.mvpproject.mvp_project.services;

import com.mvpproject.mvp_project.dto.AddressDTO;
import com.mvpproject.mvp_project.dto.ClientDTO;
import com.mvpproject.mvp_project.dto.CreateClientDTO;
import com.mvpproject.mvp_project.dto.UpdateClientDTO;
import com.mvpproject.mvp_project.entities.Address;
import com.mvpproject.mvp_project.entities.Client;
import com.mvpproject.mvp_project.exceptions.ResourceNotFoundException;
import com.mvpproject.mvp_project.repositories.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository clientRepository;

    private CreateClientDTO createClientDTO;
    private UpdateClientDTO updateClientDTO;
    private Client client;
    private Long existingId;
    private Long nonExistingId;

    @BeforeEach
    public void setUp() {
        existingId = 1L;
        nonExistingId = 2L;

        createClientDTO = new CreateClientDTO();
        createClientDTO.setName("Clay");
        createClientDTO.setEmail("clay@email.com");
        createClientDTO.setCpf("1234567890");
        createClientDTO.setPhone("1234567890");
        createClientDTO.setBirthDate(LocalDate.parse("2005-03-31"));
        Address tempAddress = new Address(null, "Avenida Beira Mar, 1234", "Fortaleza", "CE");
        createClientDTO.setAddress(new AddressDTO(tempAddress));

        client = new Client();
        client.setId(existingId);
        client.setName(createClientDTO.getName());
        client.setEmail(createClientDTO.getEmail());
        client.setCpf(createClientDTO.getCpf());
        client.setPhone(createClientDTO.getPhone());
        client.setBirthDate(createClientDTO.getBirthDate());
        client.setAddress(tempAddress);

        updateClientDTO = new UpdateClientDTO();
        updateClientDTO.setName("Nome Atualizado");
        updateClientDTO.setEmail("atualizado@email.com");
    }

    @Test
    @DisplayName("create - Deve criar um cliente com sucesso e retornar um ClientDTO")
    public void createShouldReturnClientDTOWhenSuccessful() {
        Mockito.when(clientRepository.save(any(Client.class))).thenReturn(client);

        ClientDTO result = clientService.create(createClientDTO);
        int expectedAge = Period.between(client.getBirthDate(), LocalDate.now()).getYears();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(client.getId(), result.getId());
        Assertions.assertEquals(client.getName(), result.getName());
        Assertions.assertEquals(client.getEmail(), result.getEmail());
        Assertions.assertEquals(expectedAge, result.getAge());

        Mockito.verify(clientRepository, Mockito.times(1)).save(any(Client.class));
    }

    @Test
    @DisplayName("findBy - Deve retornar uma página vazia quando nenhum cliente for encontrado")
    public void findByShouldReturnEmptyPageWhenNoClientIsFound() { // NOME CORRIGIDO
        Page<Client> emptyPage = new PageImpl<>(Collections.emptyList());
        Mockito.when(clientRepository.findByNameContainingIgnoreCase(anyString(), any(Pageable.class)))
                .thenReturn(emptyPage);

        Page<ClientDTO> result = clientService.findBy("Nome inexistente", null, null, Pageable.unpaged());

        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.isEmpty());
        Assertions.assertEquals(0, result.getTotalElements());

        Mockito.verify(clientRepository, Mockito.times(1))
                .findByNameContainingIgnoreCase(anyString(), any(Pageable.class));
    }

    @Test
    @DisplayName("updateClient - Deve atualizar um cliente com sucesso quando o ID existir")
    public void updateClientShouldReturnClientDTOWhenIdExists() {
        Mockito.when(clientRepository.findById(existingId)).thenReturn(Optional.of(client));
        Mockito.when(clientRepository.save(any(Client.class))).thenReturn(client);

        ClientDTO result = clientService.updateClient(existingId, updateClientDTO);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(existingId, result.getId());
        Assertions.assertEquals(updateClientDTO.getName(), result.getName());
        Assertions.assertEquals(updateClientDTO.getEmail(), result.getEmail());

        Mockito.verify(clientRepository, Mockito.times(1)).findById(existingId);
        Mockito.verify(clientRepository, Mockito.times(1)).save(client);
    }

    @Test
    @DisplayName("updateClient - Deve lançar ResourceNotFoundException quando o ID não existir")
    public void updateClientShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
        Mockito.when(clientRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            clientService.updateClient(nonExistingId, updateClientDTO);
        });

        Mockito.verify(clientRepository, Mockito.never()).save(any(Client.class));
    }

    @Test
    @DisplayName("delete - Deve deletar o cliente com sucesso quando o ID existir")
    public void deleteShouldDoNothingWhenIdExists() { // NOME CORRIGIDO
        Mockito.when(clientRepository.findById(existingId)).thenReturn(Optional.of(client));
        Mockito.doNothing().when(clientRepository).delete(client);

        Assertions.assertDoesNotThrow(() -> {
            clientService.delete(existingId);
        });

        Mockito.verify(clientRepository, Mockito.times(1)).delete(client);
    }

    @Test
    @DisplayName("delete - Deve lançar ResourceNotFoundException quando o ID não existir")
    public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {
        Mockito.when(clientRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            clientService.delete(nonExistingId);
        });

        Mockito.verify(clientRepository, Mockito.never()).delete(any(Client.class));
    }
}

