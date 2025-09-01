package com.mvpproject.mvp_project.services;

import com.mvpproject.mvp_project.dto.AddressDTO;
import com.mvpproject.mvp_project.dto.ClientDTO;
import com.mvpproject.mvp_project.dto.CreateClientDTO;
import com.mvpproject.mvp_project.dto.UpdateClientDTO;
import com.mvpproject.mvp_project.entities.Address;
import com.mvpproject.mvp_project.entities.Client;
import com.mvpproject.mvp_project.exceptions.ResourceNotFoundException;
import com.mvpproject.mvp_project.repositories.AddressRepository;
import com.mvpproject.mvp_project.repositories.ClientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final AddressRepository addressRepository;

    public ClientService(ClientRepository clientRepository,  AddressRepository addressRepository) {
        this.clientRepository = clientRepository;
        this.addressRepository = addressRepository;
    }

    public Page<ClientDTO> findAll(Pageable pageable) {
        return clientRepository.findAll(pageable).map(ClientDTO::new);
    }

    public Page<ClientDTO> findBy(String name, String email, String cpf, Pageable pageable) {
        if (name != null && !name.isEmpty()) {
            Page<Client> obj = clientRepository.findByNameContainingIgnoreCase(name, pageable);
            return obj.map(ClientDTO::new);
        } else if (cpf != null && !cpf.isEmpty()) {
            Page<Client> obj = clientRepository.findByCpf(cpf, pageable);
            return obj.map(ClientDTO::new);
        } else if (email != null && !email.isEmpty()) {
            Page<Client> obj = clientRepository.findByEmail(email, pageable);
            return obj.map(ClientDTO::new);
        } else {
            return findAll(pageable);
        }
    }


    public ClientDTO updateClient(Long id, UpdateClientDTO dto) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found!"));

        if (dto.getName() != null && !dto.getName().isBlank()) {
            client.setName(dto.getName());
        }
        if (dto.getBirthDate() != null) {
            client.setBirthDate(dto.getBirthDate());
        }
        if (dto.getCpf() != null && !dto.getCpf().isBlank()) {
            client.setCpf(dto.getCpf());
        }
        if (dto.getEmail() != null && !dto.getEmail().isBlank()) {
            client.setEmail(dto.getEmail());
        }
        if (dto.getPhone() != null && !dto.getPhone().isBlank()) {
            client.setPhone(dto.getPhone());
    }
        Client save = clientRepository.save(client);
        return new ClientDTO(save);
    }

    public AddressDTO updateAddress(AddressDTO dto, Long id) {
        Address address = addressRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found!"));

        if (dto.getCity() != null && !dto.getCity().isBlank()){
            address.setCity(dto.getCity());
        }
        if  (dto.getStreet() != null && !dto.getStreet().isBlank()){
            address.setStreet(dto.getStreet());
        }
        if (dto.getState() != null && !dto.getState().isBlank()){
            address.setState(dto.getState());
        }

        Address save = addressRepository.save(address);
        return new AddressDTO(save);
    }


    public ClientDTO create(CreateClientDTO dto) {
        Client client = new Client();
        Address address = new Address();

        client.setName(dto.getName());
        client.setBirthDate(dto.getBirthDate());
        client.setCpf(dto.getCpf());
        client.setEmail(dto.getEmail());
        client.setPhone(dto.getPhone());

        address.setCity(dto.getAddress().getCity());
        address.setState(dto.getAddress().getState());
        address.setStreet(dto.getAddress().getStreet());

        client.setAddress(address);
        address.setClient(client);

        Client save = clientRepository.save(client);
        return new ClientDTO(save);
    }

    public void delete(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found!"));
        clientRepository.delete(client);
    }
}
