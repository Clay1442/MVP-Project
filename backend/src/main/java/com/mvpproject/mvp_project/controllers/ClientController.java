package com.mvpproject.mvp_project.controllers;

import com.mvpproject.mvp_project.dto.AddressDTO;
import com.mvpproject.mvp_project.dto.ClientDTO;
import com.mvpproject.mvp_project.dto.UpdateClientDTO;
import com.mvpproject.mvp_project.entities.Client;
import com.mvpproject.mvp_project.services.ClientService;
import org.hibernate.sql.Update;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<Page<ClientDTO>> find(@RequestParam(required = false) String name,
                                  @RequestParam(required = false) String email,
                                  @RequestParam(required = false) String cpf,
                                  Pageable pageable ) {

        Page<ClientDTO> response = clientService.findBy(name, email, cpf, pageable);
        return ResponseEntity.ok().body(response);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> update(@PathVariable Long id, @RequestBody UpdateClientDTO dto) {
        ClientDTO response = clientService.updateClient(id, dto);
        return ResponseEntity.ok().body(response);
    }

    @PatchMapping(value = "address/{id}")
    public ResponseEntity<AddressDTO> updateAddress(@PathVariable Long id, @RequestBody AddressDTO dto) {
        AddressDTO response = clientService.updateAddress(dto, id);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@RequestParam Long id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
